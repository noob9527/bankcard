package cn.staynoob.bankcard

import cn.staynoob.bankcard.Bankcards.BANK_LOGO_BASE_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BankcardsTest {
    @Nested
    @DisplayName("findBankLogoUrl")
    inner class FindBankLogoUrlTest {
        @Test
        @DisplayName("should return logo url")
        fun test100() {
            val res = Bankcards.findBankLogoUrl("ABC", "cn")
            assertThat(res)
                    .isEqualTo("$BANK_LOGO_BASE_URL/cn/ABC.png")
        }

        @Test
        @DisplayName("should return null")
        fun test200() {
            val res = Bankcards.findBankLogoUrl("whatever", "cn")

            assertThat(res).isNull()
        }
    }

    @DisplayName("findBankCardInfo")
    @Nested
    inner class FindBankCardInfo {
        @Test
        @DisplayName("should return bank card info")
        fun test100() {
            val res = Bankcards.findBankCardInfo("6228430120000000000")
            println(res)
        }

        @Test
        @DisplayName("should return null")
        fun test200() {
            val res = Bankcards.findBankCardInfo("whatever")
            println(res)
        }
    }
}