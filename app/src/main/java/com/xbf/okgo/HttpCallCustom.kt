package com.krt.network

import com.xbf.okgo.base.LCEParams
import com.xbf.okgo.base.UpFileParams
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.xbf.okgo.HttpCallTransform
import com.xbf.okgo.httpCall

fun <T> httpPost(
    url: String,
    tag: Any?,

    upJson: String? = null,
    upFile: UpFileParams? = null,

    defaultTokenHead: Boolean = false,
    headers: HttpHeaders? = null,

    //MORE : LCE MODE
    lce: LCEParams = LCEParams(),

    //MORE : DEFAULT_WARN
    errorWarn: String? = null,

    //MORE : CALLBACK
    actionCallBackError: ((String) -> Unit)? = null,
    callBackSuccess: ((String) -> Unit)? = null
): HttpCallTransform<T> {
    return httpCall(
        url,
        false,
        tag,
        false,
        upJson = upJson,
        upFile = upFile,
        defaultTokenHead = defaultTokenHead,
        headers = headers,
        lce = lce,
        errorWarn = errorWarn,
        actionCallBackError = actionCallBackError,
        callBackSuccess = callBackSuccess
    )
}

fun <T> httpGet(
    url: String,
    tag: Any?,

    //MORE : PARAMS
    httpParams: HttpParams? = null,

    //MORE : HEAD
    defaultTokenHead: Boolean = false,
    headers: HttpHeaders? = null,

    //MORE : LCE MODE
    lce: LCEParams = LCEParams(),

    //MORE : DEFAULT_WARN
    errorWarn: String? = null,

    //MORE : CALLBACK
    actionCallBackError: ((String) -> Unit)? = null,
    callBackSuccess: ((String) -> Unit)? = null
): HttpCallTransform<T> {
    return httpCall(
        url,
        true,
        tag,
        false,
        httpParams = httpParams,
        defaultTokenHead = defaultTokenHead,
        headers = headers,
        lce = lce,
        errorWarn = errorWarn,
        actionCallBackError = actionCallBackError,
        callBackSuccess = callBackSuccess
    )
}

fun httpGetString(
    url: String,
    tag: Any?,
    justReturnString: Boolean = false,
    //MORE : PARAMS
    httpParams: HttpParams? = null,

    //MORE : HEAD
    defaultTokenHead: Boolean = false,
    headers: HttpHeaders? = null,

    //MORE : LCE MODE
    lce: LCEParams = LCEParams(),

    //MORE : DEFAULT_WARN
    errorWarn: String? = null,

    //MORE : CALLBACK
    actionCallBackError: ((String) -> Unit)? = null,
    callBackSuccess: ((String) -> Unit)? = null
): HttpCallTransform<String> {
    return httpCall(
        url,
        true,
        tag,
        justReturnString = justReturnString,
        httpParams = httpParams,
        defaultTokenHead = defaultTokenHead,
        headers = headers,
        lce = lce,
        errorWarn = errorWarn,
        actionCallBackError = actionCallBackError,
        callBackSuccess = callBackSuccess
    )
}

fun httpPostString(
    url: String,
    tag: Any?,

    //MORE : PARAMS
    upJson: String? = null,
    upFile: UpFileParams? = null,

    //MORE : HEAD
    defaultTokenHead: Boolean = false,
    headers: HttpHeaders? = null,

    //MORE : LCE MODE
    lce: LCEParams = LCEParams(),

    //MORE : DEFAULT_WARN
    errorWarn: String? = null,

    //MORE : CALLBACK
    actionCallBackError: ((String) -> Unit)? = null,
    callBackSuccess: ((String) -> Unit)? = null
) = httpPost<String>(
    url,
    tag,
    upJson,
    upFile,
    defaultTokenHead,
    headers,
    lce,
    errorWarn,
    actionCallBackError,
    callBackSuccess
)