package com.hy.onlinemarket.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.hy.onlinemarket.R;
import com.hy.onlinemarket.adapter.GridKindContentAdapter;
import com.hy.onlinemarket.adapter.ListGoodsAdapter;
import com.hy.onlinemarket.bean.GoodsBean;
import com.hy.onlinemarket.db.GoodsDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;



public class ShoppingActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_edit,priceAll,tv_buy,tv_del;
    private ListView lv;
    private CheckBox cb_all;

    private boolean isEdit;

    private List<GoodsBean> listData;
    private ListGoodsAdapter adapter;
    private DecimalFormat dcPrice;
    private boolean isEditing;
    private DecimalFormat dfPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_shopping);

        tv_edit=f(R.id.tv_edit);
        priceAll=f(R.id.priceAll);
        tv_buy=f(R.id.tv_buy);
        lv=f(R.id.lv);
        cb_all=f(R.id.cb_all);
        tv_del=f(R.id.tv_del);

        dfPrice = new DecimalFormat(".00");

        listData = GoodsDao.getGoodsList();
        adapter = new ListGoodsAdapter(listData);
        adapter.setListener(new ListGoodsAdapter.Listener() {
            @Override
            public void updateAllCheck() {
                updateAllcheckBox();
            }

            @Override
            public void updateAllPrices(GoodsBean bean, int count) {
                updateAllPrice(bean,count);
            }
        });

        lv.setAdapter(adapter);

        tv_edit.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        cb_all.setOnClickListener(this);
        tv_del.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_edit:
                //点击编辑
                if (isEdit){
                    tv_buy.setVisibility(View.VISIBLE);
                    tv_del.setVisibility(View.GONE);
                    isEdit=false;

                }else {
                    tv_buy.setVisibility(View.GONE);
                    tv_del.setVisibility(View.VISIBLE);
                    tv_edit.setText("完成");
                    isEdit=true;
                }
                break;
            case R.id.cb_all:
                //点击全选
                boolean checked=cb_all.isChecked();
                for (GoodsBean g:listData){
                    g.setSelected(checked);
                }
                adapter.notifyDataSetChanged();
                updateAllPrice(null,0);
                break;
            case R.id.tv_del:
                //点击删除
                //结算按钮出现
                tv_buy.setVisibility(View.VISIBLE);
                //删除按钮消失
                tv_del.setVisibility(View.GONE);
                List<GoodsBean> bean=new ArrayList<>();
                for (GoodsBean g:listData){
                    if (g.isSelected()){
                        bean.add(g);
                    }
                }
                tv_edit.setText("编辑");
                GoodsDao.delete(bean);
               listData.removeAll(bean);
                updateAllPrice(null,0);

                adapter.notifyDataSetChanged();
                break;

        }

    }

    public void updateAllPrice(GoodsBean bean, int count) {
        float sum = 0;
        for (GoodsBean g : listData) {// 遍历
            if (g.isSelected()) {//如果是被选中的商品，则进行价格计算
                if (bean!=null&&g.getId() == bean.getId()) {// 当前正在修改的bean
                    sum += count * g.getPrice();
                } else {// 不是当前修改的bean，则利用bean中存储的count数
                    sum += g.getCount() * g.getPrice();
                }
            }
        }
        // 获取activity对象，调用getTV方法，更新数字
        priceAll.setText("￥" + dfPrice.format(sum));
    }
    public void updateAllcheckBox(){
        boolean checked=true;
        for (GoodsBean g:listData){
            if (!g.isSelected()){
                checked=false;
                break;
            }

        }
        cb_all.setChecked(checked);
    }

}
