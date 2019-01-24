package com.xbf.okgo.strategy

import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.base.Request
import com.lzy.okrx2.adapter.ObservableResponse
import com.xbf.okgo.strategy.CommonRequest
import io.reactivex.Observable

class GetRequest : CommonRequest() {

    override fun createHttpRequest(requestMode: Boolean, url: String, usableTag: Any?): Request<*, *> {
        return OkGo.get<String>(url)
            .converter(StringConvert())
            .tag(usableTag)
    }

    override fun adapt(request: Request<*, *>): Observable<Response<String>> {
        return (request as GetRequest<String>).adapt<Observable<Response<String>>>(ObservableResponse())
    }
}