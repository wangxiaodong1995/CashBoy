package com.pize.cashboy.rx.scheduler

/**
 * Created by wangxiaodong on 2017/11/17.
 * desc:
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
