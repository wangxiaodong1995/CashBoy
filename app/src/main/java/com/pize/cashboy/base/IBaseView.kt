package com.pize.cashboy.base

import android.content.Context
import android.content.Intent

/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */
interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String?)

    fun launchActivity(intent: Intent?)

    fun killMyself();

    fun geActivity(): Context?
}
