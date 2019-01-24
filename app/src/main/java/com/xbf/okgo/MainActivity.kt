package com.xbf.okgo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            test()
        }.start()
    }

    private fun test() {
        val client = OkHttpClient.Builder().build()  // 1.1 构建HttpClient对象，okhttp的门面或者外观对象
        val request = Request.Builder().url("http://www.baidu.com")
            .build()  //1.2 使用构建者模式创建一个包含请求参数的Request对象
        try {
            val call = client.newCall(request)//1.3 call对象表示一个执行请求的实体对象，一个call代表一个请求
            val response = call.execute()  //1.4 执行同步请求

            if (response.isSuccessful) { // 根据服务器返回数据封装的Response对象，包含响应码、响应体等
                System.out.println("成功")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
