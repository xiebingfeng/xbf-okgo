package com.xbf.okgo.error

import com.lzy.okgo.exception.HttpException

object NetWorkError {

    val NET_WORK_ERROR_NO_NET_WORK = "test"


    fun convertStatusCode(exception: Throwable): String =
        if (exception is HttpException) {
            when {
//                exception.code() == 500 -> "服务器发生错误"
//                exception.code() == 404 -> "请求地址不存在"
//                exception.code() == 403 -> "请求被服务器拒绝"
//                exception.code() == 307 -> "请求被重定向其他页面"
                else -> "error222"
            }
        } else {
            "error222dkfaj;"
        }

}