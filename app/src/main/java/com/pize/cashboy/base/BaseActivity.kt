package com.pize.cashboy.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.pize.cashboy.MyApplication

/**
 * @author wangxiaodong
 * created: 2017/10/25
 * desc:BaseActivity基类
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    protected var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData(savedInstanceState)
    }

    /**
     *  加载布局
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String?) {

    }

    override fun launchActivity(intent: Intent?) {
        startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this)
    }


}


