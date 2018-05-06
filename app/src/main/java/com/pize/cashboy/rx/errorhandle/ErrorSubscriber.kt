package com.pize.cashboy.rx.errorhandle


import com.pize.cashboy.mvp.model.entity.BaseResponse
import com.pize.cashboy.rx.errorhandle.core.RxErrorHandler

import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable


/**
 * @author wangxiaodong
 */
abstract class ErrorSubscriber<T>(rxErrorHandler: RxErrorHandler) : Observer<T> {
    var mHandlerFactory: ErrorHandlerFactory

    init {
        this.mHandlerFactory = rxErrorHandler.handlerFactory

    }

    override fun onNext(@NonNull t: T) {
        if (t is BaseResponse<*>) {
            val baseResponse = t as BaseResponse<*>
            if (baseResponse.isLoginOut) {
                onError(ReloginException())
            } else if (!baseResponse.isSuccess) {
                onError(ServerException(baseResponse.error!!.message))
            }
        } else {
            toNext(t)
        }

    }

    abstract fun toNext(@NonNull t: T)

    override fun onSubscribe(@NonNull d: Disposable) {

    }

    override fun onComplete() {

    }

    override fun onError(@NonNull e: Throwable) {
        e.printStackTrace()
        mHandlerFactory.handleError(e)
    }
}

