package cn.staynoob.bankcard.model

class BankCardInfo(
        val cardNumber: String,
        /**
         * DC 储蓄卡
         * CC 信用卡
         * SCC 准贷记卡
         * PC 预付费卡
         */
        val cardType: String,
        val bankCode: String
)