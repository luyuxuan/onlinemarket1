package com.hy.onlinemarket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemarket.R;
import com.hy.onlinemarket.activity.ShoppingActivity;
import com.hy.onlinemarket.bean.GoodsBean;
import com.hy.onlinemarket.db.GoodsDao;

import java.text.DecimalFormat;
import java.util.List;




public class ListGoodsAdapter extends BaseAdapter {

    private List<GoodsBean> listData;

    public ListGoodsAdapter(List<GoodsBean> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
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
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_shopping, parent, false);
            vh = new ViewHolder(view);
        } else {
            view = convertView;
            vh = (ViewHolder) convertView.getTag();
        }
        vh.setData(position);
        return view;
    }

    private class ViewHolder implements View.OnClickListener {
        private CheckBox cb;

        private ImageView iv, iv_edit;
        private TextView tv_name, tv_price, tv_minus, tv_add, finish;
        private EditText et_number;
        private View itemView;

        private DecimalFormat dfPrice;
        private int position;
        private GoodsBean bean;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            dfPrice = new DecimalFormat(".00");
            // findViewById
            cb = (CheckBox) itemView.findViewById(R.id.cb);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            iv_edit = (ImageView) itemView.findViewById(R.id.iv_edit);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_minus = (TextView) itemView.findViewById(R.id.tv_minus);
            tv_add = (TextView) itemView.findViewById(R.id.tv_add);
            finish = (TextView) itemView.findViewById(R.id.finish);
            et_number = (EditText) itemView.findViewById(R.id.et_number);
            // setTag
            itemView.setTag(this);

            // 初始化监听
            cb.setOnClickListener(this);
            iv_edit.setOnClickListener(this);
            tv_add.setOnClickListener(this);
            tv_minus.setOnClickListener(this);
            finish.setOnClickListener(this);


        }

        public void setData(int position) {
            this.position = position;
            bean = listData.get(position);
            cb.setChecked(bean.isSelected());
            iv.setImageBitmap(GoodsBean.bysToBitmap(bean.getImg()));
            tv_name.setText(bean.getName());
            tv_price.setText("￥" + dfPrice.format(bean.getPrice()));
            et_number.setEnabled(true);
            et_number.setText(String.valueOf(bean.getCount()));
            if (bean.isModifying()) {
                // 修改模式
                iv_edit.setVisibility(View.GONE);
                finish.setVisibility(View.VISIBLE);
                tv_minus.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.VISIBLE);
                et_number.setEnabled(true);
            } else {
                // 正常模式
                iv_edit.setVisibility(View.VISIBLE);
                finish.setVisibility(View.GONE);
                tv_minus.setVisibility(View.GONE);
                tv_add.setVisibility(View.GONE);
                et_number.setEnabled(false);// 失去作用
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cb:
                    bean.setSelected(cb.isChecked());
                    //updateAllPrice(bean, bean.getCount());
                    if (listener!=null) {
                        listener.updateAllPrices(bean,bean.getCount());
                        listener.updateAllCheck();
                    }

                    break;
                case R.id.iv_edit:
                    if (bean.isModifying()) {
                        // 现在是修改模式，变成正常模式
                        // 写在tv_finish中
                    } else {
                        // 现在是正常模式，变成修改模式
                        bean.setModifying(true);
                        setData(position);//刷新itemView的界面
                    }
                    break;
                case R.id.tv_minus:


                    String numStr0 = et_number.getText().toString().trim();
                    int count0;
                    try {
                        count0 = Integer.parseInt(numStr0);
                        if (count0>1){
                            count0--;}

                    } catch (NumberFormatException e) {
                        count0 = 1;
                    }
                    et_number.setText(String.valueOf(count0));
                   // updateAllPrice(bean, count0);
                    if (listener!=null) {
                        listener.updateAllCheck();
                    }
                    break;
                case R.id.tv_add:
                    String numStr1 = et_number.getText().toString().trim();
                    int count1;
                    try {
                        count1 = Integer.parseInt(numStr1);
                        count1++;
                    } catch (NumberFormatException e) {
                        count1 = 1;
                    }
                    et_number.setText(String.valueOf(count1));
                   // updateAllPrice(bean, count1);
                    if (listener!=null) {
                        listener.updateAllCheck();
                    }
                    break;
                case R.id.finish:
                    bean.setModifying(false);
                    String numStr = et_number.getText().toString().trim();
                    int count;
                    try {
                        count = Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        count = 1;
                    }
                    bean.setCount(count);// 更新JavaBean
                    GoodsDao.updateCount(bean.getId(), count);// 更新数据库
                    setData(position);// 更新界面
                    //updateAllPrice(bean, count);
                    if (listener!=null) {
                        listener.updateAllPrices(bean,count);
                    }
                    break;
            }
        }

        private void updateAllPrice(GoodsBean bean, int count) {
            // 获取activity对象，调用getTV方法，更新数字
            ((ShoppingActivity) (itemView.getContext())).updateAllPrice(bean, count);
        }
    }
    private Listener listener;

    public void setListener(Listener listener){
        this.listener=listener;
    }

    public interface  Listener{
        void updateAllCheck();
        void updateAllPrices(GoodsBean bean,int count);


    }

}
