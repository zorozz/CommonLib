package com.xing.myapplication

import android.view.View
import com.xing.commonlib.base_mvp.error.ExceptionHandle
import com.xing.commonlib.base_mvp.present.Gspresent
import com.xing.commonlib.base_mvp.ui.MvpActivity
import com.xing.commonlib.base_mvp.ui.SimpleView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<Gspresent>(),SimpleView {

    override fun initPresener(): Gspresent {
        return Gspresent()
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun showNoneNetWorkView() {
        tv_none_network.visibility= View.VISIBLE
    }

    override fun hideNoneNetWorkView() {
        tv_none_network.visibility=View.GONE
    }

    override fun onSuccess(bean: Any) {
    }

    override fun onFail(t: ExceptionHandle.ResponeThrowable) {
    }

    override fun onCompleted() {
    }


}
