package com.xing.commonlib.base_mvp.bean;

/**
 * Author:x
 * Date:2020-05-30
 * Des:
 */
public class TestBean {

    /**
     * code : 200
     * data : []
     * success : true
     */

    private int code;
    private boolean success;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
