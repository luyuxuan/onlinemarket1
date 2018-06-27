package com.hy.onlinemarket.fragment;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;



public class BaseFragment extends Fragment {

    public <V extends View> V f(View view,@IdRes int id){
        return (V) view.findViewById(id);
    }
}
