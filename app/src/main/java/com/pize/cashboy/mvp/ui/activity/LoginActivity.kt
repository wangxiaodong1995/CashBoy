package com.pize.cashboy.mvp.ui.activity


import android.content.Intent
import android.os.Bundle

import com.pize.cashboy.R
import com.pize.cashboy.base.BaseActivity

import com.pize.cashboy.api.AppConstant

import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_login.*

import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber
import org.simple.eventbus.ThreadMode


/**
 * Created by wangxiaodong on 2017/12/5.
 * desc: 启动页
 */

class LoginActivity : BaseActivity() {
    /**
     * 微信登录相关
     */
    private var api: IWXAPI? = null

    override fun layoutId(): Int = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {

        api = WXAPIFactory.createWXAPI(this, AppConstant.APP_ID, true)
        EventBus.getDefault().register(this)
        api!!.registerApp(AppConstant.APP_ID)

        rl_wx.setOnClickListener {
            toWxLogin()
        }

        rl_qq.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            launchActivity(intent)
        }


        rl_xl.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            launchActivity(intent)
        }
    }

    fun toWxLogin() {
        val req: SendAuth.Req = SendAuth.Req()
        req.scope = "snsapi_userinfo"
        req.state = "test"
        var isSendReq: Boolean = api!!.sendReq(req)
        if (!isSendReq) {
            showMessage("您未安装微信")
        }
    }

    @Subscriber(tag = AppConstant.LOGIN, mode = ThreadMode.MAIN)
    fun onEventMainThread(code: String) {
        //mPresenter.toWxLogin(code, state)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}