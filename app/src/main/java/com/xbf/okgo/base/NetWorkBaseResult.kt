package com.xbf.okgo.base

import com.alibaba.fastjson.annotation.JSONField

data class NetWorkBaseResult(
    @JSONField(name = "code")
    val code: Int?,
    @JSONField(name = "data")
    val `data`: String?,
    @JSONField(name = "message")
    val message: String = ""
)