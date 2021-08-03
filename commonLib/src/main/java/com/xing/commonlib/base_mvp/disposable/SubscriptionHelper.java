package com.xing.commonlib.base_mvp.disposable;
import io.reactivex.disposables.Disposable;


/**
 * Author:x
 * Date:2020-05-29
 * Des:订阅关系处理
 */
public interface SubscriptionHelper<T> {
    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();
}
