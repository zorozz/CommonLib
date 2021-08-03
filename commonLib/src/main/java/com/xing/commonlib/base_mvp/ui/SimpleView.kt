package com.xing.commonlib.base_mvp.ui

import com.xing.commonlib.base_mvp.error.ExceptionHandle

/**
 *Author:x
 *Date:2020-06-22
 *Des:
 */
interface SimpleView{
    fun onSuccess(bean:Any)

    fun onFail(t:ExceptionHandle.ResponeThrowable)

    fun onCompleted()
}