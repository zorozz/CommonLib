package com.xing.commonlib.base_mvp.retrofit;

/**
 * @author:x
 * Date:2020-05-30
 * Des:
 */

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.net.Proxy;
import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.N)
public class RetrofitManager<T> {
    private static  int DEFAULT_TIMEOUT=1000*10;

    private volatile static RetrofitManager retrofitManager;
    private Retrofit retrofit;
//    private OkHttpClient.Builder client = new OkHttpClient.Builder()
//            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .sslSocketFactory(createSSLSocketFactory(),new TrustAllManager())
//            .hostnameVerifier(new AllowAllHostnameVerifier());

    private Map<String,Retrofit> retrofitMap=new HashMap<>();

    private OkHttpClient okHttpClient = null;

    public static RetrofitManager getSingleton() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }


    private RetrofitManager() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

                .hostnameVerifier(new AllowAllHostnameVerifier());
        if (Build.VERSION.SDK_INT> Build.VERSION_CODES.P){
            client.sslSocketFactory(createSSLSocketFactory(),new TrustAllManager());
        }else {
            client.sslSocketFactory(createSSLSocketFactory());
        }
        okHttpClient= client.build();
    }

    private void initRetrofitManager(String baseUrl) {
        if (!retrofitMap.containsKey(baseUrl)) {
            retrofitMap.put(baseUrl,createRetrofit(baseUrl));
        }else {
        }
    }

    public Retrofit getRetrofit(String baseUrl) {
        initRetrofitManager(baseUrl);
        return retrofitMap.get(baseUrl);
    }

    private Retrofit createRetrofit(String baseUrl){
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
//                .client(getOkHttpClient())
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
            if (Build.VERSION.SDK_INT> Build.VERSION_CODES.P){
                sc.init(null, new TrustManager[]{new TrustAllManager()},
                        new SecureRandom());
            }else {
                sc.init(null, new TrustManager[]{new TrustAllManager2()},
                        new SecureRandom());
            }
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return sSLSocketFactory;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static class TrustAllManager extends X509ExtendedTrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAllManager2 implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

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

    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}