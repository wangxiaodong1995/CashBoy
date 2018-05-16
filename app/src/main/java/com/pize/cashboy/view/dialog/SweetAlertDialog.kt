package com.pize.cashboy.view.dialog


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.Transformation
import android.widget.TextView

import com.pize.cashboy.R

import java.util.Objects


/**
 * @author wangxiaodong
 */
class SweetAlertDialog(context: Context) : Dialog(context, R.style.SweetAlertDialog) {
    private var mDialogView: View? = null
    private val mModalInAnim: AnimationSet
    private val mModalOutAnim: AnimationSet
    private val mOverlayOutAnim: Animation
    private var mTitleTextView: TextView? = null
    var titleText: String? = null
        set(text) {
            field = text
            if (mTitleTextView != null && titleText != null) {
                mTitleTextView!!.text = titleText
            }
        }
    private var mCloseFromCancel: Boolean = false

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        mModalInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in) as AnimationSet
        mModalOutAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out) as AnimationSet
        mModalOutAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                mDialogView!!.visibility = View.INVISIBLE
                mDialogView!!.post {
                    if (mCloseFromCancel) {
                        super@SweetAlertDialog.cancel()
                    } else {
                        super@SweetAlertDialog.dismiss()
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        // dialog overlay fade out
        mOverlayOutAnim = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val wlp = window!!.attributes
                wlp.alpha = 1 - interpolatedTime
                window!!.attributes = wlp
            }
        }
        mOverlayOutAnim.setDuration(120)
    }


    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_dialog)
        mDialogView = window!!.decorView.findViewById(android.R.id.content)
        mTitleTextView = findViewById(R.id.title_text)
        titleText = titleText
    }


    override fun onStart() {
        mDialogView!!.startAnimation(mModalInAnim)
    }

    /**
     * The real Dialog.cancel() will be invoked async-ly after the animation finishes.
     */
    override fun cancel() {
        dismissWithAnimation(true)
    }

    /**
     * The real Dialog.dismiss() will be invoked async-ly after the animation finishes.
     */
    fun dismissWithAnimation() {
        dismissWithAnimation(false)
    }

    private fun dismissWithAnimation(fromCancel: Boolean) {
        mCloseFromCancel = fromCancel
        mDialogView!!.startAnimation(mModalOutAnim)
    }
}