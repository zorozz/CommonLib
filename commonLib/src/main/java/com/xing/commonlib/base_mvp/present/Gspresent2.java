package com.xing.commonlib.base_mvp.present;

import com.xing.commonlib.base_mvp.model.Test2Model;
import com.xing.commonlib.base_mvp.ui.SimpleView;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */



//(Presenter与View交互是通过接口),里面放一个接口
public class Gspresent2 extends BasePresenter<SimpleView> {
    private Test2Model gsModel;

    public Gspresent2() {
        gsModel = new Test2Model();
    }



}