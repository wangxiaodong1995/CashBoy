package com.pize.cashboy.mvp.contract


import com.pize.cashboy.base.IBaseView
import com.pize.cashboy.base.IPresenter
import com.pize.cashboy.mvp.model.entity.UserEntity

/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类 契约类
 */
interface MainContract {

    interface View : IBaseView {

        /**
         * 初始化数据
         */
        fun setUserDate(userList: ArrayList<UserEntity>?)
        /**
         * 开始下啦
         */
        fun startLoadMore()

        /**
         * 结束下啦
         */
        fun endLoadMore()
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getCategoryData(pullToRefresh: Boolean)
    }
}