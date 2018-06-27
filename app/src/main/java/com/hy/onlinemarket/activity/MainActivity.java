package com.hy.onlinemarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hy.onlinemarket.AppContent;
import com.hy.onlinemarket.adapter.GridKindContentAdapter;
import com.hy.onlinemarket.bean.ContentKindBean;
import com.hy.onlinemarket.view.FullGridView;
import com.hy.onlinemarket.R;
import com.hy.onlinemarket.adapter.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private FullGridView gv1,gv2;
    private ImageView iv,caidan,shopping;
    private int [] img1={R.mipmap.hot1,R.mipmap.hot2,R.mipmap.hot3,R.mipmap.hot4,
            R.mipmap.hot5,R.mipmap.hot6,
            R.mipmap.hot7,R.mipmap.hot8,
    };
    private int [] img2={R.mipmap.new1,R.mipmap.new2,R.mipmap.new3,R.mipmap.new4,
            R.mipmap.new5,R.mipmap.new6,
            R.mipmap.new7,R.mipmap.new8,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        gv1=f(R.id.gv1);
        gv2=f(R.id.gv2);
        iv=f(R.id.iv);
        caidan=f(R.id.caidan);
        shopping=f(R.id.shopping);

        shopping.setOnClickListener(this);

        caidan.setOnClickListener(this);

        Adapter adapter=new Adapter(img1);
        gv1.setAdapter(adapter);

        Adapter adapter1=new Adapter(img2);
        gv2.setAdapter(adapter1);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //检查用户是否登录，修改图标

        //未登录状态
        if (getSP(AppContent.SP_KEY_USERID,-1)==-1){
            //修改图标为未登录图标
            iv.setImageResource(R.mipmap.icon_me);
            //点击图标登录
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            //登录状态
        }else {
            //修改图标为登录图标
            iv.setImageResource(R.mipmap.icon_me_menu);
            //点击图标注销登录，跳转到登录界面
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeSP(AppContent.SP_KEY_USERID);
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shopping:
                Intent intent=new Intent(this,ShoppingActivity.class);
                startActivity(intent);
                break;

            case R.id.caidan:
                Intent caidan=new Intent(MainActivity.this,KindActivity.class);
                startActivity(caidan);
                break;
        }
    }
}
