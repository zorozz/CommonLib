package com.xing.commonlib.base_mvp.present;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */
import com.xing.commonlib.base_mvp.bean.TestBean;
import com.xing.commonlib.base_mvp.bean.TestBean2;
import com.xing.commonlib.base_mvp.disposable.SubscriptionManager;
import com.xing.commonlib.base_mvp.error.ExceptionHandle;
import com.xing.commonlib.base_mvp.model.GsModel;
import com.xing.commonlib.base_mvp.model.Observer;
import com.xing.commonlib.base_mvp.model.Test2Model;
import com.xing.commonlib.base_mvp.ui.SimpleView;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;


//(Presenter与View交互是通过接口),里面放一个接口
public class Gspresent extends BasePresenter<SimpleView> {
    private GsModel gsModel;
    private Test2Model test2Model;

    public Gspresent() {
        gsModel = new GsModel();
        test2Model=new Test2Model();
    }

    //Presenter与View交互
    public void getGSxinxi(RequestBody body) {
        gsModel.getxinxi(body, new Observer<TestBean>() {
            @Override
            public void OnSuccess(TestBean beanGSchaxuns) {
                //继承关系，可以使用泛型里面的属性。
                view.onSuccess(beanGSchaxuns);

            }

            @Override
            public void OnFail(ExceptionHandle.ResponeThrowable e) {
                view.onFail(e);
            }

            @Override
            public void OnCompleted() {
                view.onCompleted();
            }

            @Override
            public void OnDisposable(Disposable d) {
                SubscriptionManager.getInstance().add(d);
            }

        });
    }

    //Presenter与View交互
    public void getGSxinxi2(Map<String, String> body) {
        test2Model.getxinxi(body, new Observer<TestBean2>() {
            @Override
            public void OnSuccess(TestBean2 beanGSchaxuns) {
                //继承关系，可以使用泛型里面的属性。
                view.onSuccess(beanGSchaxuns);

            }

            @Override
            public void OnFail(ExceptionHandle.ResponeThrowable e) {
                view.onFail(e);
            }

            @Override
            public void OnCompleted() {
                view.onCompleted();
            }

            @Override
            public void OnDisposable(Disposable d) {
                SubscriptionManager.getInstance().add(d);
            }

        });
    }

}