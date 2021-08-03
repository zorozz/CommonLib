package com.xing.commonlib.base_mvp.base

import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xing.commonlib.base_mvp.bean.EventBusMessages
import com.xing.commonlib.base_mvp.receiver.NetBroadcastReceiver
import com.xing.commonlib.utils.NetUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *@author:x
 *@date:2020-06-23
 * @des:
 */

abstract class BaseFragment :Fragment(){

    val netBroadcastReceiver= NetBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.registerReceiver(netBroadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        activity?.unregisterReceiver(netBroadcastReceiver)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(eventBusMessages: EventBusMessages){
        when(eventBusMessages.messageType){
            NetUtil.NETWORK_NONE->showNoneNetWorkView()
            else->hideNoneNetWorkView()
        }
    }

    fun toast(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    abstract fun showNoneNetWorkView()

    abstract fun hideNoneNetWorkView()

}