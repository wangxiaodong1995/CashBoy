package com.pize.cashboy.mvp.model.entity

/**
 * @author wangxiaodong
 * @fileName UserEntity
 * @data 2018/4/28 下午4:50
 * @describe TODO
 */
class UserEntity(val id: Int, val login: String, private val avatar_url: String) {

    val avatarUrl: String
        get() = if (avatar_url.isEmpty()) avatar_url else avatar_url.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]

    override fun toString(): String {
        return "id -> $id login -> $login"
    }
}
