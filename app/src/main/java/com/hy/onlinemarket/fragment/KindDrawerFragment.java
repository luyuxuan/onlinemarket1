package com.hy.onlinemarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hy.onlinemarket.adapter.ListKindDrawerAdapter;



public class KindDrawerFragment extends BaseFragment {


    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lv = new ListView(inflater.getContext());
        lv.setDividerHeight(0);
        ListKindDrawerAdapter adapter = new ListKindDrawerAdapter();
        lv.setAdapter(adapter);
        return lv;
    }
}

