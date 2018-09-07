package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/23.
 */

public class ViewPager_Adapter extends PagerAdapter {
    private List<View> list;
    private Context context;
    private int i;

    public ViewPager_Adapter(List<View> list, Context context, int i) {
        this.list = list;
        this.context = context;
        this.i = i;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
