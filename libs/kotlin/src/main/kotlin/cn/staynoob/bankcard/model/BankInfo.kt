package cn.staynoob.bankcard.model

data class BankInfo(
        val name: String,
        val country: String,
        val color: String?,
        val bankCode: String?,
        val localTitle: String?,
        val engTitle: String?,
        val url: String?,
        val prefixes: List<String>
)