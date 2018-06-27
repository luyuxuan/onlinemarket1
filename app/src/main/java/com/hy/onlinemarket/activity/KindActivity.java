package com.hy.onlinemarket.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.hy.onlinemarket.R;
import com.hy.onlinemarket.adapter.Adapter;
import com.hy.onlinemarket.adapter.GridKindContentAdapter;
import com.hy.onlinemarket.bean.ContentKindBean;

import java.util.ArrayList;
import java.util.List;



public class KindActivity extends BaseActivity {

    private Toolbar tb;
    private DrawerLayout dl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_kind);

        tb = f(R.id.toolbar);
        dl = f(R.id.drawerLayout);


//        Toolbar代替系统自带的标题栏ActionBar
        tb.setTitle("");
        setSupportActionBar(tb);

//        dl与tb设置关联
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, 0, 0);
        toggle.syncState();// sync同步 state状态
        dl.addDrawerListener(toggle);
//
//        dl.openDrawer(Gravity.START);
//        dl.openDrawer(Gravity.END);
//        dl.closeDrawers();
    }


}
