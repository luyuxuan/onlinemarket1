package com.hy.onlinemarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.hy.onlinemarket.AppContent;
import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.KindBean;

import java.util.ArrayList;

import io.realm.Realm;



public class SplasthActivity extends BaseActivity {
    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splasth);

        handler=new android.os.Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //闪屏，如果有用户登录，进入main
                Intent intent=new Intent(SplasthActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                long userid=getSP(AppContent.SP_KEY_USERID,-1);
                if(userid==-1){
                    //闪屏，如果没有用户登录，进入login登录界面
                    Intent login=new Intent(SplasthActivity.this,LoginActivity.class);
                    startActivity(login);
                    finish();
                }

            }
        },3*1000);
        initDbKindData();

    }

    private void initDbKindData() {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(KindBean.class).count();
        if (count == 0) {
            realm.beginTransaction();
            ArrayList<KindBean> ks = new ArrayList<>();
            // 加载kindTitles
            for (int i = 0; i < kindTitles.length; i++) {
                ks.add(new KindBean(i, kindTitles[i], false, -1));
            }
            // 加载kinds
            for (int i = 0; i < kinds.length; i++) {
                for (int j = 0; j < kinds[i].length; j++) {
                    ks.add(new KindBean(ks.size(), kinds[i][j], false, i));

                }
            }
            realm.insert(ks);
            realm.commitTransaction();
        }
        realm.close();
    }

    private String[] kindTitles = {
            "了解会员特权", "微淘钱包", "个性设置", "我的收藏", "我的足迹", "安全设置", "更多"
    };
    private String[][] kinds = {
            {"白金会员", "黄金会员", "青铜会员", "大众会员"},
            {"我的零钱", "账户余额"},
            {"更换背景", "设置主题", "设置新字体"},
            {"我最喜欢的宝贝"},
            {"飘柔洗发水", "新疆和田大枣", "正宗哈密瓜"},
            {"设置安全密码", "设置指纹付款", "更改密码"},
            {"优惠券福利", "商城抽奖活动", "每日优惠", "每日抢购"},

    };
}