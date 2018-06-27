package com.hy.onlinemarket.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hy.onlinemarket.AppContent;



public class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences(AppContent.SP_NAME,MODE_PRIVATE);
    }

    protected  void saveSP(String key,long value){
        sp.edit().putLong(key,value).commit();
    }
    protected long getSP(String key, long def){
        return sp.getLong(key,def);
    }
    protected  void removeSP(String key){

        sp.edit().remove(key).commit();

    }



    public <V extends View> V f(@IdRes int id){
        return (V) findViewById(id);
    }
}
