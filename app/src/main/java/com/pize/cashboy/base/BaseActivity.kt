package com.pize.cashboy.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pize.cashboy.BaseApplication
import com.pize.cashboy.R
import com.pize.cashboy.showToast
import com.pize.cashboy.view.dialog.SweetAlertDialog
import kotlinx.android.synthetic.main.alert_dialog.view.*

/**
 * @author wangxiaodong
 * created: 2017/10/25
 * desc:BaseActivity基类
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    protected var TAG = this.javaClass.simpleName
    private var dialog: SweetAlertDialog? = null

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
        dialog = SweetAlertDialog(this)
        if (!dialog!!.isShowing) {
            dialog?.setTitle(getString(R.string.loading))
            dialog?.dismiss()
            dialog?.show()
        }
    }

    override fun hideLoading() {
        if (dialog != null) {
            dialog?.cancel()
        }
    }

    override fun getActivity(): Context = getActivity()

    override fun showMessage(message: String?) {
        showToast(message!!)
    }

    override fun launchActivity(intent: Intent?) {
        startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        BaseApplication.getRefWatcher(this)?.watch(this)
        this.dialog = null
    }


}


