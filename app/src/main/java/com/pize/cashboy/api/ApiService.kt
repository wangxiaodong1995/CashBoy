package com.pize.cashboy.api


import com.pize.cashboy.mvp.model.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by wangxiaodong on 2017/11/16.
 * Api 接口
 */

interface ApiService {
    companion object {
        const val HEADER_API_VERSION = "Accept: application/vnd.github.v3+json"
    }


    @Headers(HEADER_API_VERSION)
    @GET("/users")
    fun getUsers(@Query("since") lastIdQueried: Int, @Query("per_page") perPage: Int): Observable<ArrayList<UserEntity>>

    /**
     * 首页精选
     */
//    @GET("v2/feed?")
//    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>


}