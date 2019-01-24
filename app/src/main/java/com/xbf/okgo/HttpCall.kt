package com.xbf.okgo

import com.alibaba.fastjson.JSONObject
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.xbf.okgo.base.LCEParams
import com.xbf.okgo.base.NetWorkBaseResult
import com.xbf.okgo.base.UpFileParams
import com.xbf.okgo.error.NetWorkError
import com.xbf.okgo.strategy.CommonRequest
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

internal fun <T> httpCall(
    url: String,   //url 地址
    getRequestMode: Boolean,  //true:GET   false:POST
    tag: Any?,     //版定 OKGO生命对象的控件,常为ViewModel或View
    justReturnString: Boolean = false,    //是否只返回获取结果

    //MORE : PARAMS
    upJson: String? = null,         //上传的字符串
    httpParams: HttpParams? = null,   //参数
    upFile: UpFileParams? = null,

    //MORE : HEAD
    defaultTokenHead: Boolean = false,  //是否加载默认头
    headers: HttpHeaders? = null,    //头字段

    //MORE : LCE MODE
    lce: LCEParams = LCEParams(),

    //MORE : DEFAULT_WARN
    errorWarn: String? = null,   //数据错误时的默认提示，替代服务器返回的错误信息

    //MORE : CALLBACK
    actionCallBackError: ((String) -> Unit)? = null,
    callBackSuccess: ((String) -> Unit)? = null
): HttpCallTransform<T> {
    val returnBack = HttpCallTransform<T>()

    //3.设置  Request
    val commonRequest = CommonRequest.Factory().create(getRequestMode)

    //4.设置请求并配置一些参数
    val request = commonRequest.createHttpRequest(getRequestMode, url, tag)
    setRequestProperty(
        request,
        commonRequest,
        defaultTokenHead,
        upJson,
        upFile,
        headers,
        httpParams
    )

    //5.错误处理
    fun onError(errorMsg: String) {
//        Logger.e(errorMsg)
//
//        KRT.getHandler()?.post {
//            actionCallBackError?.invoke(errorMsg)
//            if (!lce.isEasyMode) {
//                httpTagManager.showError(errorMsg, lce.isErrorContainerShow)
//            }
//        }
    }

    //6.正确处理
    fun onSuccess(result: String) {
        returnBack.callBack(result)
//        KRT.getHandler()?.post {
//            callBackSuccess?.invoke(result)
//            if (!lce.isEasyMode && !lce.notShowContent) {
//                httpTagManager.showContent()
//            }
//        }
    }

    //7.开始请求
    commonRequest.adapt(request)
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            //加载动画
//            if (!lce.isEasyMode) {
//                httpTagManager.showLoading(lce.showLoadingDialog)
//            }
        }
        .observeOn(Schedulers.io())
        .subscribe(object : Observer<Response<String>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(response: Response<String>) {
                val result = response.body()

                if (justReturnString) {
                    onSuccess(response.body())
                    return
                }
                val resultBean = JSONObject.parseObject(result, NetWorkBaseResult::class.java)

//                KRT.getConfiguration<NetworkCustomMade>(ConfigKeys.NETWORK_CUSTOM_MADE)?.let {
//                    if (it.code == resultBean.code && it.action.invoke()) {
//                        return
//                    }
//                }

                if (resultBean.code == 200) {
                    onSuccess(resultBean.data ?: "")
                    return
                } else {
                    if (null != errorWarn) {
                        onError(errorWarn)
                    } else {
                        if (resultBean.message.isNotEmpty()) {
                            onError(resultBean.message)
                        } else {
//                            onError(getString(R.string.base_network_error))
                        }
                    }
                }
            }

            override fun onError(e: Throwable) {
//                CrashReport.postCatchedException(e)
                onError(NetWorkError.convertStatusCode(e))
            }

            override fun onComplete() {
            }
        })
    return returnBack
}

private fun setRequestProperty(
    request: Request<*, *>,
    requestStrategy: CommonRequest,
    defaultTokenHead: Boolean,
    upJson: String?,
    upFileParams: UpFileParams?,
    headers: HttpHeaders?,
    httpParams: HttpParams?
) {
    //上传的JSON串，会以JSON的格式上传，主要是mediaType
    requestStrategy.upJson(request, upJson)

    //上传的文件
    requestStrategy.addFileParams(request, upFileParams)

    headers?.let {
        request.headers(it)
    }

    httpParams?.let {
        request.params(it)
    }

    if (defaultTokenHead) {
//        (KRT.getConfiguration(ConfigKeys.DEF_HTTP_HEADERS) as? HttpHeaders)?.let {
//            request.headers(it)
//        }
    }
}
