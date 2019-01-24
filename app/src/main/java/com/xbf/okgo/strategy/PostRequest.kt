package com.xbf.okgo.strategy

import com.xbf.okgo.base.UpFileParams
import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.PostRequest
import com.lzy.okgo.request.base.Request
import com.lzy.okrx2.adapter.ObservableResponse
import io.reactivex.Observable
import java.io.File

class PostRequest : CommonRequest() {

    override fun createHttpRequest(requestMode: Boolean, url: String, usableTag: Any?): Request<*, *> {
        return OkGo.post<String>(url)
            .converter(StringConvert())
            .tag(usableTag)
    }

    override fun adapt(request: Request<*, *>): Observable<Response<String>> {
        return (request as PostRequest<String>).adapt<Observable<Response<String>>>(ObservableResponse())
    }

    override fun upJson(request: Request<*, *>, upJson: String?) {
        request as PostRequest<*>
        upJson?.let {
            request.upJson(it)
        }
    }

    override fun addFileParams(request: Request<*, *>, upFileParams: UpFileParams?) {
        request as PostRequest<*>
        upFileParams?.upFile?.let {
            val list = ArrayList<File>()
            list.add(it)
            request.addFileParams(upFileParams.upFileKey, list)
        }
    }

}