package com.xing.commonlib.base_mvp.ui

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.alibaba.fastjson.JSONObject
import com.xing.commonlib.R
import com.xing.commonlib.base_mvp.bean.TestBean
import com.xing.commonlib.base_mvp.bean.TestBean2
import com.xing.commonlib.base_mvp.error.ExceptionHandle
import com.xing.commonlib.base_mvp.present.Gspresent
import com.xing.commonlib.utils.Logger
import okhttp3.MediaType
import okhttp3.RequestBody
import kotlin.collections.HashMap

/**
 *Author:x
 *Date:2020-06-22
 *Des:
 */
class TestActivity:MvpActivity<Gspresent>(),SimpleView{

    var body:RequestBody?=null
    var tvNontWork:TextView?=null
    private val TAG = "TestActivity"

    override fun initPresener(): Gspresent {
        return Gspresent()
    }

    override fun initData() {
       var map=HashMap<String,String>()
        map["configName"] = "MPNS_PUSH_URL"
        presener?.getGSxinxi2(map)
        Logger.v("testActivity","测试")
    }

    override fun initView() {
        val btn=findViewById<Button>(R.id.btn_update)
        tvNontWork=findViewById<TextView>(R.id.tv_none_network)
        var map=HashMap<String,String>()
        var json=JSONObject.toJSONString(map)
//        body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json)
//        btn.setOnClickListener {
//            presener?.getGSxinxi(body)
//        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun showNoneNetWorkView() {
        tvNontWork?.visibility= View.VISIBLE
    }

    override fun hideNoneNetWorkView() {
        tvNontWork?.visibility=View.GONE
    }

    override fun onSuccess(bean: Any) {
                println("onSuccess:-----"+bean)
        if (bean is TestBean){
            println("testBean------")
        }
        if (bean is TestBean2){
            println("testBean2----")
        }
    }

    override fun onFail(t: ExceptionHandle.ResponeThrowable) {

    }

    override fun onCompleted() {
    }
}