package com.hy.onlinemarket.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by HUANYU on 2017/10/13.
 */

public class FullListView extends ListView{
    public FullListView(Context context) {
        super(context);
        init();
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSelector(new ColorDrawable());
        setCacheColorHint(0);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
