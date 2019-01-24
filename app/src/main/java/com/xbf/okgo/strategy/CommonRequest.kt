package com.xbf.okgo.strategy

import com.xbf.okgo.base.UpFileParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import io.reactivex.Observable

abstract class CommonRequest {

    abstract fun createHttpRequest(requestMode: Boolean, url: String, usableTag: Any?): Request<*, *>

    abstract fun adapt(request: Request<*, *>): Observable<Response<String>>

    open fun upJson(request: Request<*, *>, upJson: String?) {}

    open fun addFileParams(request: Request<*, *>, upFileParams: UpFileParams?) {}


    class Factory {

        fun create(getRequestMode: Boolean): CommonRequest {
            return if (getRequestMode) {
                GetRequest()
            } else {
                PostRequest()
            }
        }

    }
}