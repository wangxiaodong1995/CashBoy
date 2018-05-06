package com.pize.cashboy.mvp.ui.adapter

import android.content.Context
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pize.cashboy.R
import com.pize.cashboy.glide.GlideApp
import com.pize.cashboy.mvp.model.entity.UserEntity
import com.pize.cashboy.view.recyclerview.ViewHolder
import com.pize.cashboy.view.recyclerview.adapter.CommonAdapter


/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类的 Adapter
 */

class UserAdapter(mContext: Context, categoryList: ArrayList<UserEntity>, layoutId: Int) :
        CommonAdapter<UserEntity>(mContext, categoryList, layoutId) {


    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<UserEntity>) {
        mData = categoryList
        notifyDataSetChanged()
    }

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: UserEntity, position: Int) {
        holder.setText(R.id.tv_name, data.login)
        // 加载封页图
        GlideApp.with(mContext)
                .load(data.avatarUrl)
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_avatar))

    }
}
