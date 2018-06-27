package com.hy.onlinemarket.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.UserBean;
import com.hy.onlinemarket.db.UserDao;



public class Reg3 extends BaseActivity implements View.OnClickListener {
    private EditText name ,birthday,address;
    private RadioGroup sex;
    private RadioButton  sex_man,sex_wonman;
    private TextView tv;
    private Button bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg3);

        tv=f(R.id.tv);
        bt=f(R.id.bt);
        name=f(R.id.name);
        birthday=f(R.id.birthday);
        address=f(R.id.address);
        tv.setOnClickListener(this);
        bt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv:
                finish();
                break;
            case R.id.bt:
                //修改
                String Sname=name.getText().toString().trim();
                String Sbirthday=birthday.getText().toString().trim();
                String Saddress=address.getText().toString().trim();

                UserBean bean=new UserBean();
                bean.setName(Sname);
                bean.setEmail(Sbirthday);
                bean.setEmail(Saddress);
                UserDao.updata(bean);
                finish();
                break;

        }

    }
}
