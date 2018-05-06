package com.pize.cashboy.utils

import com.pize.cashboy.base.IBaseView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@fileName RxUtils
 *@author wangxiaodong
 *@data 2018/5/2 下午2:38
 *@describe TODO
 */
object RxUtils {


    fun <T> applySchedulersLoading(view: IBaseView): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe {
                        view.showLoading()
                    }.subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally {
                        view.hideLoading()
                    }
        }
    }

    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}