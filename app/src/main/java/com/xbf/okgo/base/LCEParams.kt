package com.xbf.okgo.base

class LCEParams(
    val isEasyMode: Boolean = false,    //不启动lce，纯粹的请求网络，用户看不见
    val showLoadingDialog: Boolean = false,  //以Dialog的形式显示Loading,isEasyMode必须为false时才有作用
    val isErrorContainerShow: Boolean = false,   //当加载失败或网络错误时是否显示   错误界面 (比如主界面，当失败时显示错误界面，再点击错误界面重新加载。   普通网络请求，失败后提示一下就可以了)
    val notShowContent: Boolean = false  //是否  显示内容，（当显示内容时会取消等待状态，主要适用于，网络请求后，立马再请求其它数据）
)