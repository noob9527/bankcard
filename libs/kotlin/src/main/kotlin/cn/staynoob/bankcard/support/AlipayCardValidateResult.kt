package cn.staynoob.bankcard.support

internal class AlipayCardValidateResult(
        val validated: Boolean,
        val bank: String?,
        val cardType: String?
)