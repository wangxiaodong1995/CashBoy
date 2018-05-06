package com.pize.cashboy.rx.errorhandle


import com.pize.cashboy.mvp.model.entity.BaseResponse

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * @author wangxiaodong
 */

abstract class ErrorFunctionHandle<T> : Function<BaseResponse<T>, ObservableSource<T>> {

    @Throws(Exception::class)
    override fun apply(@NonNull baseResponse: BaseResponse<T>): ObservableSource<T> {
        return if (baseResponse.isSuccess) {
            onNext(Observable.just(baseResponse.data!!))
        } else {
            onNext(Observable.just(baseResponse as T))
        }
    }

    abstract fun onNext(@NonNull data: Observable<T>): ObservableSource<T>
}

