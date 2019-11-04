package com.bawei.unit4_test1_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bawei.unit4_test1_demo.adapter.MyAdapter;
import com.bawei.unit4_test1_demo.beans.MyData;
import com.bawei.unit4_test1_demo.persenter.PersenterImpl;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contacts.IView {
    private String url = "http://blog.zhaoliang5156.cn/api/news/lawyer.json\n";

    private Contacts.Persenter mpersenter;
    private XBanner xBanner;
    private GridView grid;
    private List<MyData.ListdataBean> mlist = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myAdapter = new MyAdapter(MainActivity.this, mlist);
        grid.setAdapter(myAdapter);
        mpersenter = new PersenterImpl();
        mpersenter.onAttach(this);
        mpersenter.startRequest(url);

    }

    private void initView() {

        xBanner = (XBanner) findViewById(R.id.xb);

        grid = (GridView) findViewById(R.id.grid);

    }

    @Override
    public void onSuccess(String json) {
        //解析
        final List<MyData.BannerdataBean> bannerdata = new Gson().fromJson(json, MyData.class).getBannerdata();

        xBanner.setBannerData(bannerdata);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(MainActivity.this).load(bannerdata.get(position).getImageUrl()).into((ImageView) view);
            }
        });
        List<MyData.ListdataBean> listdata = new Gson().fromJson(json, MyData.class).getListdata();
        mlist.addAll(listdata);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpersenter !=null){
            mpersenter.onDeattch();
            mpersenter=null;
        }
    }
}
