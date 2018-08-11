package cn.staynoob.bankcard

class HttpException(
        val status: Int,
        val statusText: String,
        cause: Throwable? = null
) : RuntimeException(statusText, cause)