package com.hy.onlinemarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.hy.onlinemarket.JsonUtils;
import com.hy.onlinemarket.R;
import com.hy.onlinemarket.activity.DetailsActivity;
import com.hy.onlinemarket.activity.MainActivity;
import com.hy.onlinemarket.adapter.GridKindContentAdapter;
import com.hy.onlinemarket.adapter.ListKindDrawerAdapter;
import com.hy.onlinemarket.bean.ContentKindBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.id.text1;



public class KindContentFragment extends BaseFragment {
    private GridView gridView;
    private int[] imgs = {R.mipmap.v_bai_luo_bo,R.mipmap.v_da_bai_cai,R.mipmap.v_bo_cai,
            R.mipmap.v_dong_gua,R.mipmap.v_dou_jiao,R.mipmap.v_gan_lan,
            R.mipmap.v_hu_luo_bo,R.mipmap.v_hua_cai,R.mipmap.v_huang_gua,
            R.mipmap.v_jiu_cai,R.mipmap.v_qin_cai,R.mipmap.v_qing_jiao,
            R.mipmap.v_tu_dou,R.mipmap.v_xi_hong_shi,R.mipmap.v_xiang_cai,
            R.mipmap.v_xiao_cong,R.mipmap.v_xiao_hong_shi,R.mipmap.v_yu_mi,
            R.mipmap.v_yuan_bai_cai,R.mipmap.v_zi_qie_zi};
    private String[] name1 = {"白萝卜","大白菜","菠菜","冬瓜","豆角","橄榄","胡萝卜","花菜","黄瓜",
            "韭菜","芹菜","青椒","土豆","西红柿","香菜","小葱","小红是","玉米",
            "圆白菜","紫茄子"};
    private Float[] price1 = {1.56f,1.56f,1.56f,1.56f,1.56f,1.56f,1.56f,
            1.56f,1.56f,1.56f,1.56f,1.56f,1.56f,1.56f,
            1.56f,1.56f,1.56f,1.56f,1.56f,1.56f};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        gridView = new GridView(inflater.getContext());
        gridView.setNumColumns(2);

        final List<ContentKindBean> data=getData();
        GridKindContentAdapter adapter = new GridKindContentAdapter(data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentKindBean v = data.get(position);
                String json = JsonUtils.toJson(v);
                Intent intent = new Intent(getContext(),DetailsActivity.class);
                intent.putExtra("vegetable",json);
                startActivity(intent);
            }
        });
        return gridView;
    }

    public List<ContentKindBean> getData() {
        List<ContentKindBean> data = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ContentKindBean item = new ContentKindBean();
            item.id=i;
            item.imgId = imgs[i % imgs.length];
            item.name = name1[i % name1.length];
            item.price = price1[i % price1.length];
            data.add(item);
        }
        return data;


    }

}
