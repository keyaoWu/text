package com.bawei.unit4_test1_demo.persenter;

import android.graphics.Bitmap;

import com.bawei.unit4_test1_demo.Contacts;
import com.bawei.unit4_test1_demo.model.ModelImpl;

/**
 * 功能：PersenterImpl类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：15:10
 */
public class PersenterImpl implements Contacts.Persenter {
    private Contacts.IView miView;
    private Contacts.IModel miModel;
    @Override
    public void onAttach(Contacts.IView iView) {
        this.miView = iView;
         miModel = new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
    miModel.getInfo(url, new Contacts.mCallBack() {
        @Override
        public void onSuccess(String json) {
            miView.onSuccess(json);
        }

        @Override
        public void onFaild(String error) {
          miView.onFaild(error);
        }
    });
    }

    @Override
    public void onDeattch() {
      if (miModel!=null){
          miModel =null;
      }
      if (miView != null){
          miView = null;
      }
    }
}
