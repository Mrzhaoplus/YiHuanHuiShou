package com.example.mr.yihuanhuishou.driver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mr.yihuanhuishou.R;

/**
 * @author power
 * @date 2018/6/1 下午4:54
 * @description:
 */
public class PagerNotFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packge_not_fragment,null);
        return view;
    }

    @Override
    protected void initLazyData() {

    }
}
