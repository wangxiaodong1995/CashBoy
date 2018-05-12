package com.pize.cashboy.mvp.presenter

import com.pize.cashboy.base.BasePresenter
import com.pize.cashboy.mvp.contract.MainContract
import com.pize.cashboy.mvp.model.MainModel
import com.pize.cashboy.mvp.model.entity.UserEntity
import com.pize.cashboy.rx.errorhandle.ErrorSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var lastIdQueried = 1

    private val mainModel by lazy { MainModel() }

    override fun getCategoryData(pullToRefresh: Boolean) {
        if (pullToRefresh) {
            lastIdQueried = 1
        }
        // 检测是否绑定 View
        checkViewAttached()
        mainModel.requestUserData(lastIdQueried)
                .doOnSubscribe {
                    if (pullToRefresh) {
                        mRootView?.showLoading()
                    } else {
                        mRootView?.startLoadMore()
                    }
                }
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (pullToRefresh) {
                        mRootView?.hideLoading()
                    } else {
                        mRootView?.endLoadMore()
                    }
                }
                .subscribe(object : ErrorSubscriber<ArrayList<UserEntity>>(rxErrorHandler) {
                    override fun toNext(t: ArrayList<UserEntity>) {
                        mRootView?.setUserDate(t)
                        lastIdQueried++
                    }
                })

        //addSubscription(disposable)
    }


}