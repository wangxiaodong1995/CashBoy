package com.pize.cashboy.rx.errorhandle.listener

import android.content.Context
import android.net.ParseException
import android.widget.Toast
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import com.pize.cashboy.rx.errorhandle.ReloginException
import com.pize.cashboy.rx.errorhandle.ServerException
import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ResponseErrorListener {
    fun handleResponseError(context: Context, t: Throwable)

    companion object {

        val EMPTY: ResponseErrorListener = object : ResponseErrorListener {
            override fun handleResponseError(context: Context, t: Throwable) {
                Logger.e("Catch-Error", t.message)
                //这里不光是只能打印错误,还可以根据不同的错误作出不同的逻辑处理
                var msg = "未知错误"
                if (t is ServerException) {
                    msg = t.msg!!
                } else if (t is UnknownHostException) {
                    msg = "网络不可用"
                } else if (t is SocketTimeoutException) {
                    msg = "请求网络超时"
                } else if (t is HttpException) {
                    msg = convertStatusCode(t)
                } else if (t is JsonParseException || t is ParseException || t is JSONException || t is JsonIOException) {
                    msg = "数据解析错误"
                } else if (t is ReloginException) {
                    msg = "会话失效"
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG)
            }


            private fun convertStatusCode(httpException: HttpException): String {
                val msg: String
                if (httpException.code() == 500) {
                    msg = "服务器发生错误"
                } else if (httpException.code() == 404) {
                    msg = "请求地址不存在"
                } else if (httpException.code() == 403) {
                    msg = "请求被服务器拒绝"
                } else if (httpException.code() == 307) {
                    msg = "请求被重定向到其他页面"
                } else {
                    msg = httpException.code().toString() + httpException.message()
                }
                return msg
            }
        }
    }
}
