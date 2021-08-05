package com.xing.commonlib.base_mvp.base

import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xing.commonlib.base_mvp.bean.EventBusMessages
import com.xing.commonlib.base_mvp.disposable.SubscriptionManager
import com.xing.commonlib.base_mvp.present.BasePresenter
import com.xing.commonlib.base_mvp.receiver.NetBroadcastReceiver
import com.xing.commonlib.base_mvp.ui.SimpleView
import com.xing.commonlib.utils.NetUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *@author:x
 *@date:2020-06-23
 * @des:
 */

abstract class BaseFragment< P : BasePresenter<SimpleView>> :Fragment(),SimpleView{

    val netBroadcastReceiver= NetBroadcastReceiver()
    var presener:P?=null
    var delayTime:Long=600
    var lastTime:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presener=initPresener()
        presener?.addView(this)
        activity?.registerReceiver(netBroadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    override fun onResume() {
        super.onResume()
        var curTime=System.currentTimeMillis()
        if (curTime-lastTime>delayTime){
//            initData()
            lastTime=curTime
        }
    }

    abstract fun initData()

    abstract fun initView(view: View)

    abstract fun initPresener():P

    override fun onDestroy() {
        super.onDestroy()
        presener?.detattch()
        //View消除时取消订阅关系
        SubscriptionManager.getInstance().cancelall()
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
        try {
            activity?.let {
                Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){

        }
    }

    abstract fun showNoneNetWorkView()

    abstract fun hideNoneNetWorkView()

}