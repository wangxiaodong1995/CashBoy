package com.pize.cashboy.mvp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.ImageView
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.pize.cashboy.R
import com.pize.cashboy.base.BaseActivity
import com.pize.cashboy.mvp.ui.fragment.BigMoneyFriendFragment
import com.pize.cashboy.mvp.ui.fragment.HomeFragment
import com.pize.cashboy.mvp.ui.fragment.MyFragment
import com.pize.cashboy.mvp.ui.fragment.QuickMoneyFriendFragment
import com.pize.cashboy.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_tab.view.*


@Suppress("DEPRECATION")
@SuppressLint("Registered")
class MainActivity : BaseActivity() {

    private lateinit var adapter: FragmentPagerItemAdapter

    private var mExitTime: Long = 0

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        val pages = FragmentPagerItems.with(this)
                .add("home", HomeFragment::class.java)
                .add("bigMoneyFriend", BigMoneyFriendFragment::class.java)
                .add("quickMoneyFriend", QuickMoneyFriendFragment::class.java)
                .add("my", MyFragment::class.java)
                .create()

        adapter = FragmentPagerItemAdapter(
                supportFragmentManager,
                pages)

        viewPager.offscreenPageLimit = pages.size
        viewPager.adapter = adapter
        viewPager.setScanScroll(false)
        initTabLayout(tabLayout)
        tabLayout.setViewPager(viewPager)
        viewPager.currentItem = 0
    }

    private fun initTabLayout(tabLayout: SmartTabLayout?) {
        tabLayout?.setCustomTabView({ container, position, _->
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.layout_tab, container, false)
            val ivIcon = view.tab_img as ImageView
            when (position) {
                0 -> ivIcon.setImageDrawable(resources.getDrawable(R.drawable.tab_home_selector))
                1 -> ivIcon.setImageDrawable(resources.getDrawable(R.drawable.tab_big_money_friend_selector))
                2 -> ivIcon.setImageDrawable(resources.getDrawable(R.drawable.tab_quick_money_friend_selector))
                3 -> ivIcon.setImageDrawable(resources.getDrawable(R.drawable.tab_my))
                else -> throw IllegalStateException("Invalid position: $position")
            }
            view
        })

        tabLayout?.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        tv_home.setTextColor(resources.getColor(R.color.color_AACD03))
                        tv_big_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_quick_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_my.setTextColor(resources.getColor(R.color.color_999999))
                    }
                    1 -> {
                        tv_home.setTextColor(resources.getColor(R.color.color_999999))
                        tv_big_money_friend.setTextColor(resources.getColor(R.color.color_AACD03))
                        tv_quick_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_my.setTextColor(resources.getColor(R.color.color_999999))
                    }
                    2 -> {
                        tv_home.setTextColor(resources.getColor(R.color.color_999999))
                        tv_big_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_quick_money_friend.setTextColor(resources.getColor(R.color.color_AACD03))
                        tv_my.setTextColor(resources.getColor(R.color.color_999999))
                    }
                    3 -> {
                        tv_home.setTextColor(resources.getColor(R.color.color_999999))
                        tv_big_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_quick_money_friend.setTextColor(resources.getColor(R.color.color_999999))
                        tv_my.setTextColor(resources.getColor(R.color.color_AACD03))
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        tv_home.setOnClickListener { viewPager.currentItem = 0 }
        tv_big_money_friend.setOnClickListener { viewPager.currentItem = 1 }
        tv_quick_money_friend.setOnClickListener { viewPager.currentItem = 2 }
        tv_my.setOnClickListener { viewPager.currentItem = 3 }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast(getString(R.string.exit))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}