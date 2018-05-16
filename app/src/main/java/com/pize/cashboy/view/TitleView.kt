package com.pize.cashboy.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pize.cashboy.R
import com.zhy.autolayout.AutoRelativeLayout
import kotlinx.android.synthetic.main.layout_title.view.*

/**
 *@fileName TitileView
 *@author wangxiaodong
 *@data 2018/5/13 下午2:06
 *@describe TODO
 */
class TitleView(context: Context, attrs: AttributeSet) : AutoRelativeLayout(context, attrs) {

    private var delegate: ViewGroup? = null
    private var toolbar: AutoRelativeLayout? = null
    private var title: TextView? = null
    private var mune: AutoRelativeLayout? = null

    init {
        setupViews(context, attrs)
    }

    private fun setupViews(context: Context, attrs: AttributeSet) {
        delegate = LayoutInflater.from(context).inflate(R.layout.layout_title, null) as ViewGroup
        toolbar = delegate?.toolbar_back!!
        title = delegate?.toolbar_title!!
        mune = delegate?.toolbar_muen!!
        val type = context.obtainStyledAttributes(attrs, R.styleable.TitileView)
        addView(delegate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setTitleName(s: String) {
        title?.setText(s)
    }

    fun getMune(): AutoRelativeLayout? {
        return mune
    }


}
