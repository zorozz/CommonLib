package com.xing.commonlib.base_mvp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.xing.commonlib.base_mvp.base.BaseActivity
import com.xing.commonlib.base_mvp.bean.EventBusMessages
import com.xing.commonlib.utils.NetUtil
import org.greenrobot.eventbus.EventBus

/**
 *@author:x
 *@date:2020-06-23
 * @des:监听网络变化广播
 */
class NetBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (ConnectivityManager.CONNECTIVITY_ACTION == action) {
            val netWorkState: Int = NetUtil.getNetWorkState(context)
            var eventBusMessages = EventBusMessages()
            eventBusMessages.messageType = netWorkState
            EventBus.getDefault().post(eventBusMessages)
        }
    }

}