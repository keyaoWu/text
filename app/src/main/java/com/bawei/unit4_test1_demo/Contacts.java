package com.bawei.unit4_test1_demo;

import android.graphics.Bitmap;

/**
 * 功能：Contacts类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：15:00
 */
public interface Contacts {
    interface  IModel{
        void getInfo(String url,mCallBack mCallBack);
        void postInfo(String url,mCallBack mCallBack);
    }

    interface IView{
        void  onSuccess(String json);
        void onFaild(String error);

    }

    interface  Persenter{
        void onAttach(IView iView);
        void startRequest(String url);
        void onDeattch();
    }
    interface mCallBack{
        void  onSuccess(String json);

        void onFaild(String error);
    }
}
