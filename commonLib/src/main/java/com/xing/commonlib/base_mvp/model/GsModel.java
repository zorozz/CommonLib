package com.xing.commonlib.base_mvp.model;

import com.xing.commonlib.base_mvp.bean.TestBean;
import com.xing.commonlib.base_mvp.retrofit.RetrofitManager;
import com.xing.commonlib.base_mvp.service.BaseUrlManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */


public class GsModel {

    public void getxinxi(RequestBody body, Observer<TestBean> observer) {
        Observable<TestBean> gSxin = RetrofitManager.getSingleton().Apiservice(BaseUrlManager.BESE_URL).getTestData(body);
        gSxin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

}
