package com.pize.cashboy.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast

import com.orhanobut.logger.Logger
import com.pize.cashboy.R
import com.pize.cashboy.api.AppConstant
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

import org.simple.eventbus.EventBus


/**
 * @author wangxiaodong
 * @date 2017/5/16
 */

class WXEntryActivity : Activity(), IWXAPIEventHandler {

    private var api: IWXAPI? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = WXAPIFactory.createWXAPI(this, AppConstant.APP_ID, false)
        //注册appid
        api!!.registerApp(AppConstant.APP_ID)
        try {
            val result = api!!.handleIntent(intent, this)
            if (!result) {
                Logger.e("参数不合法，未被SDK处理，退出")
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        api!!.handleIntent(intent, this)
        finish()
    }

    override fun onReq(req: BaseReq) {
        Logger.d("onReq   BaseReq" + req.toString())
        when (req.type) {
            ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX -> {
            }
            ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX -> {
            }
            else -> {
            }
        }
    }

    override fun onResp(resp: BaseResp) {
        var result = 0
        Logger.d("baseResp:" + resp.errStr + "," + resp.openId + "," + resp.transaction + "," + resp.errCode)
        when (resp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                if (!TextUtils.isEmpty((resp as SendAuth.Resp).code)) {
                    EventBus.getDefault().post(resp.code, AppConstant.LOGIN)
                }
                result = R.string.errcode_success
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> result = R.string.errcode_cancel
            BaseResp.ErrCode.ERR_AUTH_DENIED -> result = R.string.errcode_deny
            BaseResp.ErrCode.ERR_UNSUPPORT -> result = R.string.errcode_unsupported
            BaseResp.ErrCode.ERR_BAN -> result = R.string.errcode_ban
            else -> result = R.string.errcode_unknown
        }
        Toast.makeText(this, getString(result), Toast.LENGTH_LONG)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        api!!.handleIntent(data, this)
    }

}
