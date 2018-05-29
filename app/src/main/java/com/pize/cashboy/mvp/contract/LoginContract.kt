package com.pize.cashboy.mvp.contract


import com.pize.cashboy.base.IBaseView
import com.pize.cashboy.base.IPresenter
import com.pize.cashboy.mvp.model.entity.UserEntity

/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类 契约类
 */
interface LoginContract {

    interface View : IBaseView {

    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getWxData(code: String)
    }
}