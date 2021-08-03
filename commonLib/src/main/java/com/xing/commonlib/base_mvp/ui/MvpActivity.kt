package com.xing.commonlib.base_mvp.ui

import android.os.Bundle
import com.xing.commonlib.base_mvp.base.BaseActivity
import com.xing.commonlib.base_mvp.disposable.SubscriptionManager
import com.xing.commonlib.base_mvp.present.BasePresenter

/**
 *Author:x
 *Date:2020-06-22
 *Des:mvp 基础activity
 */
 abstract class MvpActivity< P :BasePresenter<SimpleView>> :BaseActivity(){

    var presener:P?=null
    override fun initBefore(savedInstanceState: Bundle?) {
        super.initBefore(savedInstanceState)
        presener=initPresener()
        presener?.addView(this)
    }

    abstract fun initPresener():P

    override fun onDestroy() {
        super.onDestroy()
        presener?.detattch()
        //View消除时取消订阅关系
        SubscriptionManager.getInstance().cancelall()
    }

}