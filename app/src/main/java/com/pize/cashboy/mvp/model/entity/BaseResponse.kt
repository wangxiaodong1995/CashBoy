package com.pize.cashboy.mvp.model.entity


import java.io.Serializable


/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 *
 * @author wangxiaodong
 */

class BaseResponse<T> {

    var method: String? = null
    var time: Int = 0
    var data: T? = null
    var error: ErrorBean? = null

    val isSuccess: Boolean
        get() = null == error


    val isLoginOut: Boolean
        get() {
            if (null == error) {
                return false
            }
            return "未登录" == error!!.message
        }

    class ErrorBean {
        var code: Int = 0
        var message: String? = null
    }
}
