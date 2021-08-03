package com.xing.commonlib.base_mvp.base

import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xing.commonlib.base_mvp.bean.EventBusMessages
import com.xing.commonlib.base_mvp.receiver.NetBroadcastReceiver
import com.xing.commonlib.base_mvp.ui.SimpleView
import com.xing.commonlib.utils.NetUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 *@author:x
 *@date:2020-06-22
 *Des:基础activity
 */
abstract class BaseActivity :AppCompatActivity(),SimpleView{

//    companion object event: Event {
//        override fun onChangeNet(netState: Int) {
//            when(netState){
//                NetUtil.NETWORK_NONE->showNoneNetWorkView()
//            }
//        }
//    }
    val netBroadcastReceiver=NetBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        registerReceiver(netBroadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        EventBus.getDefault().register(this)
        initBefore(savedInstanceState)
        initView()
        initData()

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        unregisterReceiver(netBroadcastReceiver)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(eventBusMessages: EventBusMessages){
        when(eventBusMessages.messageType){
            NetUtil.NETWORK_NONE->showNoneNetWorkView()
            else->hideNoneNetWorkView()
        }
    }


    /**
     * 初始化数据o
     */
    abstract fun initData()

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     *初始化p
     */
     open fun initBefore(savedInstanceState: Bundle?){}

    /**
     * 获取布局文件
     */
    abstract fun getLayoutId(): Int

    fun toast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    abstract fun showNoneNetWorkView()

    abstract fun hideNoneNetWorkView()
}