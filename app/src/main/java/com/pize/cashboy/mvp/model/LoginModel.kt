package com.pize.cashboy.mvp.model

import com.pize.cashboy.mvp.model.entity.UserEntity
import com.pize.cashboy.net.RetrofitManager
import com.pize.cashboy.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class LoginModel {

     private val page = 10


    fun requestUserData(lastIdQueried: Int): Observable<ArrayList<UserEntity>> {
        return RetrofitManager.service.getUsers(lastIdQueried, page)
                .compose(SchedulerUtils.ioToMain())
    }


}
