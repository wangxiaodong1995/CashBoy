package com.pize.cashboy.base

import com.pize.cashboy.rx.errorhandle.core.RxErrorHandler
import com.pize.cashboy.rx.errorhandle.listener.ResponseErrorListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by wangxiaodong on 2017/11/16.
 *
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var mRootView: T? = null
        private set

    private var compositeDisposable = CompositeDisposable()

    var rxErrorHandler: RxErrorHandler = RxErrorHandler
            .builder()
            .with(mRootView?.geActivity())
            .responseErrorListener(ResponseErrorListener.EMPTY)
            .build()

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }

    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) befor requesting data to the IPresenter")


}