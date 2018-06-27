package com.hy.onlinemarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemarket.R;
import com.hy.onlinemarket.bean.ContentKindBean;

import java.util.ArrayList;
import java.util.List;

import static com.hy.onlinemarket.R.id.name;



public class GridKindContentAdapter extends BaseAdapter {

    List<ContentKindBean> data;

    public GridKindContentAdapter(List<ContentKindBean> data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
     ViewHolder vh;
        if (convertView == null) {
            view=View.inflate(parent.getContext(), R.layout.item_gridview_kind,null);
            vh=new ViewHolder();
            vh.iv= (ImageView) view.findViewById(R.id.imgId);
            vh.name= (TextView) view.findViewById(name);
            vh.price= (TextView) view.findViewById(R.id.price);
            view.setTag(vh);
        }else{
            view=convertView;
            vh= (ViewHolder) convertView.getTag();
        }

        ContentKindBean bean=data.get(position);

        vh.iv.setImageResource(bean.imgId);
        vh.name.setText(bean.name);
        vh.price.setText("商城价￥:"+String.valueOf(bean.price));

        return view;
    }

    class ViewHolder{
        ImageView iv;
        TextView name;
        TextView price;
    }
}