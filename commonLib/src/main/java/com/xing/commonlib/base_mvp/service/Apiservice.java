package com.xing.commonlib.base_mvp.service;

import com.xing.commonlib.base_mvp.bean.TestBean;
import com.xing.commonlib.base_mvp.bean.TestBean2;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author:x
 * @date:2020-05-30
 * Des:retrofit 请求体
 */


public interface Apiservice {
    @POST("admin/getOfflineMsg.json")
    @Headers({"url_name:weather"})
    Observable<TestBean> getTestData(@Body RequestBody body);

//    @FormUrlEncoded
    @POST("system/config?configName=MPNS_PUSH_URL")
    @Headers({"url_name:book"})
    Observable<TestBean2> getTaseData2();

}
