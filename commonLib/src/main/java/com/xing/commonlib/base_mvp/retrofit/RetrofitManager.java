package com.xing.commonlib.base_mvp.retrofit;

/**
 * @author:x
 * Date:2020-05-30
 * Des:
 */

import android.annotation.SuppressLint;
import com.xing.commonlib.base_mvp.service.Apiservice;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static  int DEFAULT_TIMEOUT=1000*10;

    private volatile static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private OkHttpClient.Builder client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .sslSocketFactory(createSSLSocketFactory())
            .hostnameVerifier(new AllowAllHostnameVerifier());

    private Map<String,Retrofit> retrofitMap=new HashMap<>();

    private OkHttpClient okHttpClient = client.build();

    public static RetrofitManager getSingleton() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }


    private RetrofitManager() {

    }

    private void initRetrofitManager(String baseUrl) {
        if (!retrofitMap.containsKey(baseUrl)) {
            retrofitMap.put(baseUrl,createRetrofit(baseUrl));
        }else {
        }
    }

    public Apiservice Apiservice(String baseUrl) {
            initRetrofitManager(baseUrl);
            return retrofitMap.get(baseUrl).create(Apiservice.class);
    }

    public Retrofit getRetrofit(String baseUrl) {
            initRetrofitManager(baseUrl);
            return retrofitMap.get(baseUrl);
    }

    private Retrofit createRetrofit(String baseUrl){
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }



    /**
     * 默认信任所有证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}