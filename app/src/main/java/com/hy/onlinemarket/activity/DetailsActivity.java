package com.hy.onlinemarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemarket.JsonUtils;
import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.ContentKindBean;
import com.hy.onlinemarket.bean.GoodsBean;

import java.text.DecimalFormat;

import io.realm.Realm;



public class DetailsActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_minus,tv_add,tv_name,tv_number,price;
    private EditText et_number;
    private ImageView imageView,imageView2,iv;
    private Button add_shoppcar;
    private ContentKindBean ckb=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);

        tv_add = f(R.id.tv_add);
        tv_minus = f(R.id.tv_minus);
        et_number = f(R.id.et_number);
        tv_name = f(R.id.tv_name);
        imageView = f(R.id.imageView);
        imageView2 = f(R.id.imageView2);
        tv_number = f(R.id.tv_number);
        add_shoppcar = f(R.id.add_shoppcar);
        price = f(R.id.price);
        iv=f(R.id.iv);


        Intent intent = getIntent();
        if (intent != null) {
            String json = intent.getStringExtra("vegetable");
            ckb = JsonUtils.toBean(json, ContentKindBean.class);
        }
        if (ckb != null) {
            imageView.setImageResource(ckb.getImgId());
            tv_name.setText(ckb.getName());
            tv_number.setText("编号：" + ckb.getId());
            DecimalFormat df = new DecimalFormat(".00");
            price.setText("￥" + df.format(ckb.getPrice()));
            imageView2.setImageResource(ckb.getImgId());
        }
        tv_add.setOnClickListener(this);
        tv_minus.setOnClickListener(this);
        add_shoppcar.setOnClickListener(this);
        iv.setOnClickListener(this);





    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_minus:
                String s = et_number.getText().toString().trim();
                if (TextUtils.isEmpty(s)) {
                    et_number.setText("1");
                } else if (s.matches("[0-9]+")) {
                    int i = Integer.parseInt(s);
                    if (--i > 0)
                        et_number.setText(String.valueOf(i));
                } else {
                    et_number.setText("1");
                }
                break;


            case R.id.tv_add:
                String s1 = et_number.getText().toString().trim();
                if (TextUtils.isEmpty(s1)) {
                    et_number.setText("1");
                } else if (s1.matches("[0-9]+")) {
                    int i = Integer.parseInt(s1);
                    i++;
                    et_number.setText(String.valueOf(i));
                } else {
                    et_number.setText("1");
                }
                break;
            case R.id.iv:
                Intent intent=new Intent(this,ShoppingActivity.class);
                startActivity(intent);
                break;
            case R.id.add_shoppcar:
                // 加入购物车
                if (ckb == null) {
                    return;
                }
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                GoodsBean g = realm.where(GoodsBean.class).equalTo("id", ckb.getId()).findFirst();
                String count = et_number.getText().toString().trim();
                int i;
                try {
                    i = Integer.parseInt(count);
                } catch (NumberFormatException e) {
                    i = 1;
                }
                if (g == null) {
                    // 之前没有加入过，直接添加
                    GoodsBean bean = new GoodsBean(ckb.getId(), ckb.getName(), GoodsBean.imgIdToBys(this, ckb.getImgId()),
                            i, ckb.getPrice(), false);
                    realm.insert(bean);
                } else {
                    // 之前已经添加过，修改数量
                    g.setCount(g.getCount() + i);
                }
                realm.commitTransaction();
                realm.close();
                break;
        }

    }
}
