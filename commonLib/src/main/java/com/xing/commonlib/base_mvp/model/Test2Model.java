package com.xing.commonlib.base_mvp.model;

import com.xing.commonlib.base_mvp.bean.TestBean2;
import com.xing.commonlib.base_mvp.retrofit.RetrofitManager;
import com.xing.commonlib.base_mvp.service.BaseUrlManager;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */
public class Test2Model {

    public void getxinxi(Map<String, String> body, Observer<TestBean2> observer) {
        Observable<TestBean2> gSxin = RetrofitManager.getSingleton().Apiservice(BaseUrlManager.BESE_URL2).getTaseData2();
        gSxin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }
}
