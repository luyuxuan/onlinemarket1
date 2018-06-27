package com.hy.onlinemarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.UserBean;
import com.hy.onlinemarket.db.UserDao;

import java.util.ArrayList;



public class Reg2 extends BaseActivity implements View.OnClickListener {
    private EditText et_ssid,et_pwd,et_affirmPwd,et_mail;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg2);

        et_ssid=f(R.id.et_ssid);
        et_pwd=f(R.id.et_pwd);
        et_affirmPwd=f(R.id.et_affirmPwd);
        et_mail=f(R.id.et_mail);
        bt=f(R.id.bt);

        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt:
                    String ssid=et_ssid.getText().toString().trim();
                    String pwd=et_pwd.getText().toString().trim();
                    String pwd2=et_affirmPwd.getText().toString().trim();
                    String mail=et_mail.getText().toString().trim();


                    if (et_ssid.length()==0||et_pwd.length()==0){
                        Toast.makeText(this, "用户名/密码为空", Toast.LENGTH_SHORT).show();
                    }else{
                        if (pwd.equals(pwd2)){
                            UserBean bean=new UserBean();
                            bean.setSsid(ssid);
                            bean.setPwd(pwd);
                            bean.setEmail(mail);
                            UserDao.addUser(bean);

                            Intent reg3=new Intent(this,Reg3.class);
                            startActivity(reg3);
                            finish();
                        }else {
                            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;

            }

    }
}
