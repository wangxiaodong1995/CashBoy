package com.pize.cashboy.mvp.ui.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.paginate.Paginate
import com.pize.cashboy.R
import com.pize.cashboy.base.BaseActivity
import com.pize.cashboy.mvp.contract.MainContract
import com.pize.cashboy.mvp.model.MainModel
import com.pize.cashboy.mvp.model.entity.UserEntity
import com.pize.cashboy.mvp.presenter.MainPresenter
import com.pize.cashboy.mvp.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 *@fileName MainActivity
 *@author wangxiaodong
 *@data 2018/4/28 下午4:05
 *@describe TODO
 */
class MainActivity : BaseActivity(), MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    private val mPresenter: MainPresenter  by lazy {
        MainPresenter()
    }

    private val mAdapter by lazy { UserAdapter(this, itemList, R.layout.recycle_list) }

    private var itemList = ArrayList<UserEntity>()

    private var mPaginate: Paginate? = null

    private var isLoadingMore: Boolean = false

    init {
        mPresenter.attachView(this)
        mPresenter.getCategoryData(true)
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener(this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        initPaginate()
    }


    override fun setUserDate(userList: ArrayList<UserEntity>?) {
        mAdapter.setData(userList!!)
    }

    override fun endLoadMore() {
        isLoadingMore = false
    }

    override fun startLoadMore() {
        isLoadingMore = true
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onRefresh() {
        mPresenter.getCategoryData(true)
    }

    private fun initPaginate() {
        if (mPaginate == null) {
            val callbacks = object : Paginate.Callbacks {

                override fun isLoading(): Boolean {
                    return false
                }

                override fun onLoadMore() {
                    mPresenter.getCategoryData(false)
                }

                override fun hasLoadedAllItems(): Boolean {
                    return false
                }
            }

            mPaginate = Paginate.with(recyclerView, callbacks)
                    .setLoadingTriggerThreshold(2)
                    .build()

            mPaginate?.setHasMoreDataToLoad(false)
        }
    }

}