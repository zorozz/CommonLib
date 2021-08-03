package com.xing.commonlib.base_mvp.present;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */
public class BasePresenter<V> {

    public V view;

    //加载View,建立连接
    public void addView(V v) {
        this.view = v;
    }

    //断开连接
    public void detattch() {
        if (view != null) {
            view = null;
        }
    }

}

