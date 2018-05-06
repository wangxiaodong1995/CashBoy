package com.pize.cashboy.rx.errorhandle


import com.pize.cashboy.rx.errorhandle.core.RxErrorHandler

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

import io.reactivex.annotations.NonNull

abstract class ErrorHandleSubscriberOfFlowable<T>(rxErrorHandler: RxErrorHandler) : Subscriber<T> {
    private val mHandlerFactory: ErrorHandlerFactory

    init {
        this.mHandlerFactory = rxErrorHandler.handlerFactory
    }

    override fun onSubscribe(s: Subscription) {

    }

    override fun onComplete() {

    }


    override fun onError(@NonNull t: Throwable) {
        t.printStackTrace()
        //如果你某个地方不想使用全局错误处理,则重写 onError(Throwable) 并将 super.onError(e); 删掉
        //如果你不仅想使用全局错误处理,还想加入自己的逻辑,则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
        mHandlerFactory.handleError(t)
    }
}

