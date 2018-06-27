package com.hy.onlinemarket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemarket.R;

import com.hy.onlinemarket.bean.KindBean;
import com.hy.onlinemarket.db.KindDao;

import java.util.ArrayList;
import java.util.List;



public class ListKindDrawerAdapter extends BaseAdapter {

    ArrayList<KindBean> kb;

    public ListKindDrawerAdapter() {
        kb=new ArrayList<>();
        List<KindBean> kindTitle= KindDao.getKindTitles();
        kb.addAll(kindTitle);
    }

    @Override
    public int getCount() {
        return kb.size();
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_kind, parent, false);
            vh = new ViewHolder(view);
        } else {
            view = convertView;
            vh = (ViewHolder) convertView.getTag();
        }

        vh.setData(position);

        return view;
    }
    private class ViewHolder implements View.OnClickListener {
        private TextView tv_title, tv_content;
        private int position;

        public ViewHolder(View itemView) {
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            itemView.setTag(this);
            // 子条目item的点击事件监听
            itemView.setOnClickListener(this);

        }

        public void setData(int position) {
            this.position = position;
            KindBean k = kb.get(position);
            if (k.getTitleId() == -1) {
                // 大标题
                tv_title.setText(k.getText());
                tv_content.setVisibility(View.GONE);
                tv_title.setVisibility(View.VISIBLE);
            } else {
                // 小标题
                tv_content.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.GONE);
                tv_content.setText(k.getText());
            }
        }
        @Override
        public void onClick(View v) {

            KindBean k=kb.get(position);
            if (k.getTitleId()==-1){
                //K大标题-显示 隐藏小标题
                if (k.isOpen()){
                    for (int i = 0; i <kb.size() ; i++) {
                        KindBean kindbean=kb.get(i);
                        if (kindbean.getTitleId()!=-1){
                            kb.remove(i);
                            i--;
                        }
                    }
                    k.setOpen(false);
                }else {
                    //添加小标题
                    kb.addAll(position+1,KindDao.getKinds(k.getId()));
                    k.setOpen(true);
                }
                ListKindDrawerAdapter.this.notifyDataSetChanged();
            }else{
                //小标题
                Toast.makeText(v.getContext(), "点击了小标题"+k.getId(), Toast.LENGTH_SHORT).show();

            }
        }
    }
}
