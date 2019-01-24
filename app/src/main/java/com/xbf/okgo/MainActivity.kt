package com.xbf.okgo

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.krt.network.httpGetString
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.DBCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initOkGo(this.applicationContext)

        tv.setOnClickListener {
            httpGetString("http://www.baidu2.com", this, true) {
                println("11111111111111")
            }
        }

        tv.performClick()
    }


    private fun initOkGo(context: Context) {
        val builder = OkHttpClient.Builder()
        //log相关
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO)                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor)                                 //添加OkGo默认debug日志

        builder.readTimeout(NET_TIME_OUT, TimeUnit.MILLISECONDS)      //全局的读取超时时间
        builder.writeTimeout(NET_TIME_OUT, TimeUnit.MILLISECONDS)     //全局的写入超时时间
        builder.connectTimeout(NET_TIME_OUT, TimeUnit.MILLISECONDS)   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(CookieJarImpl(DBCookieStore(context)))              //使用数据库保持cookie，如果cookie不过期，则一直有效
        OkGo.getInstance().init(context as Application)                           //必须调用初始化
            .setCacheMode(CacheMode.NO_CACHE)
            .setOkHttpClient(builder.build()).retryCount =
                0                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

        OkGo.getInstance().okHttpClient.connectTimeoutMillis()
    }

    private val NET_TIME_OUT = 3000.toLong()
}
