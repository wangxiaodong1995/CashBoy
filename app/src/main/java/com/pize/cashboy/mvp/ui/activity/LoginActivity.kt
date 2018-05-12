package com.pize.cashboy.mvp.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.pize.cashboy.BaseApplication
import com.pize.cashboy.R
import com.pize.cashboy.base.BaseActivity
import com.pize.cashboy.utils.AppUtils
import com.orhanobut.logger.Logger
import com.pize.cashboy.BaseApplication.Companion.context
import com.pize.cashboy.base.BasePresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem


/**
 * Created by wangxiaodong on 2017/12/5.
 * desc: 启动页
 */

class LoginActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        rl_wx.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            launchActivity(intent)
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

}