package com.hy.onlinemarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemarket.AppContent;
import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.UserBean;
import com.hy.onlinemarket.db.UserDao;



public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_cancel,tv_foriget,tv_register;
    private EditText et_ssid,et_pwd;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        tv_cancel=f(R.id.tv_cancel);
        tv_foriget=f(R.id.tv_foriget);
        tv_register=f(R.id.tv_register);
        et_ssid=f(R.id.et_ssid);
        et_pwd=f(R.id.et_pwd);
        bt=f(R.id.bt);

        tv_cancel.setOnClickListener(this);
        bt.setOnClickListener(this);
        tv_foriget.setOnClickListener(this);
        tv_register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.bt:
                //登录逻辑
                String ssid=et_ssid.getText().toString().trim();
                String pwd=et_pwd.getText().toString().trim();
                UserBean bean= UserDao.check(ssid,pwd);
                if (bean==null){
                    Toast.makeText(this, "账号/密码不正确", Toast.LENGTH_SHORT).show();
                }else {
                   //保存到sp中
                    saveSP(AppContent.SP_KEY_USERID,bean.getId());
                    Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                break;
            case R.id.tv_foriget:

                break;
            case R.id.tv_register:
                Intent reg=new Intent(this,Reg1.class);
                startActivity(reg);
                break;

        }

    }

}
