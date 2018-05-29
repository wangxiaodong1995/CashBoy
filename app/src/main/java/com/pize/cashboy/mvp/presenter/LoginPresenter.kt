package com.pize.cashboy.mvp.presenter

import com.pize.cashboy.base.BasePresenter
import com.pize.cashboy.mvp.contract.LoginContract
import com.pize.cashboy.mvp.contract.MainContract
import com.pize.cashboy.mvp.model.LoginModel
import com.pize.cashboy.mvp.model.MainModel
import com.pize.cashboy.mvp.model.entity.UserEntity
import com.pize.cashboy.rx.errorhandle.ErrorSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {


    private val mModel by lazy { LoginModel() }

    override fun getWxData(code: String) {

    }


}