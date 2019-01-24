package com.xbf.okgo

import java.lang.reflect.ParameterizedType

class HttpCallTransform<T> {

    private var functionList: ((List<T>) -> Unit)? = null
    private var functionObject: ((T) -> Unit)? = null

    fun callBack(result: String) {
        functionList?.apply {
            this.javaClass.declaredMethods.forEach { method ->
                if (method.genericParameterTypes.size == 1 && method.toString().contains("invoke(") && method.returnType.name == "void") {
                    val type = (method.genericParameterTypes[0] as ParameterizedType).actualTypeArguments[0] as Class<T>
//                    KRT.getHandler()?.post {
//                        this.invoke(JSONObject.parseArray(result, type))
//                    }
                    return
                }
            }
        }

        functionObject?.apply {
            this.javaClass.declaredMethods.forEach { method ->
                if (method.genericParameterTypes.size == 1 && method.toString().contains("invoke(") && method.returnType.name == "void") {
                    val type = method.genericParameterTypes[0] as Class<T>
//                    KRT.getHandler()?.post {
//                        this.invoke(JSONObject.parseObject(result, type))
//                    }
                    return
                }
            }
        }
    }

    fun toList(function: (List<T>) -> Unit) {
        this.functionList = function
    }

    fun toObject(function: (T) -> Unit) {
        this.functionObject = function
    }
}