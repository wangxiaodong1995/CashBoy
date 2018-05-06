package com.pize.cashboy.rx.errorhandle

import android.content.Context

import com.pize.cashboy.rx.errorhandle.listener.ResponseErrorListener


class ErrorHandlerFactory(private val mContext: Context, private val mResponseErrorListener: ResponseErrorListener) {

    /**
     * 处理错误
     * @param throwable
     */
    fun handleError(throwable: Throwable) {
        mResponseErrorListener.handleResponseError(mContext, throwable)
    }
}
