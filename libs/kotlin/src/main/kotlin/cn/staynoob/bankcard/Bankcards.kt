package cn.staynoob.bankcard

import cn.staynoob.bankcard.model.BankCardInfo
import cn.staynoob.bankcard.model.BankInfo
import cn.staynoob.bankcard.support.AlipayCardValidateResult
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mashape.unirest.http.Unirest
import java.util.concurrent.ConcurrentHashMap

object Bankcards {

    internal const val BANK_LOGO_BASE_URL = "https://raw.githubusercontent.com/noob9527/bankcard/master/source/banklogo"

    private const val ALIPAY_CARD_VALIDATE_URL = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardBinCheck=true"

    private val objectMapper = ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerKotlinModule()

    private val countryBanksMap: ConcurrentHashMap<String, List<BankInfo>> = ConcurrentHashMap()

    fun findBankInfo(bankCode: String, countryCode: String): BankInfo? {
        val banks = countryBanksMap
                .getOrPut(countryCode) {
                    readCountryBanks(countryCode)
                }

        return banks.firstOrNull {
            it.bankCode == bankCode
        }
    }

    private fun readCountryBanks(countryCode: String): List<BankInfo> {
        val url = ClassLoader.getSystemResource("banks-$countryCode.json")
                ?: return listOf()
        @Suppress("RemoveExplicitTypeArguments")
        return objectMapper.readValue<List<BankInfo>>(url)
    }

    fun findBankLogoUrl(bankCode: String, countryCode: String): String? {
        val url = "$BANK_LOGO_BASE_URL/$countryCode/$bankCode.png"
        val res = Unirest.get(url)
                .asBinary()
        return when (res.status) {
            200 -> url
            404 -> null
            else -> throw HttpException(res.status, res.statusText)
        }
    }

    /**
     * @param cardNumber
     * @return BankCardInfo if it is a valid card
     * @return null if it isn't a valid card
     * @throws HttpException
     */
    fun findBankCardInfo(cardNumber: String): BankCardInfo? {
        val response = Unirest.get(ALIPAY_CARD_VALIDATE_URL)
                .queryString("cardNo", cardNumber)
                .asString()

        if (response.status >= 300 || response.status < 200) {
            throw HttpException(response.status, response.statusText)
        }

        val validateResult = objectMapper.readValue<AlipayCardValidateResult>(response.body)

        return if (validateResult.validated) {
            BankCardInfo(
                    cardNumber,
                    validateResult.cardType!!,
                    validateResult.bank!!
            )
        } else null
    }

}
