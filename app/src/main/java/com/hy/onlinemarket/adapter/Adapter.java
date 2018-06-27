package com.hy.onlinemarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hy.onlinemarket.R;



public class Adapter extends BaseAdapter {

    private  int[] imgs;

    public Adapter(int[] imgs) {
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
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
            view=View.inflate(parent.getContext(), R.layout.item_gridview_main,null);
            vh=new ViewHolder();
            vh.iv= (ImageView) view.findViewById(R.id.iv);
            view.setTag(vh);
        }else{
            view=convertView;
            vh= (ViewHolder) convertView.getTag();
        }

        vh.iv.setImageResource(imgs[position]);

        return view;
    }

    class ViewHolder{
        ImageView iv;
    }
}
