package com.example.mr.yihuanhuishou.driver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author power
 * @date 2018/6/1 下午4:53
 * @description:
 */
public class PagerReadyFragment extends BaseFragment {
    @BindView(R.id.first_recyclerView)
    RecyclerView firstRecyclerView;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packge_ready_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        List<String> firstList = new ArrayList<>();
        firstList.add("");
        firstList.add("");
        firstList.add("");

        firstRecyclerView.setNestedScrollingEnabled(false);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FirstAdapter firstAdapter = new FirstAdapter(R.layout.item_first_layout,firstList);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private class FirstAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public FirstAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView secondRecyclerView = helper.getView(R.id.second_recyclerView);
            List<String> secondList = new ArrayList<>();
            secondList.add("");
            secondList.add("");
            secondList.add("");
            secondRecyclerView.setNestedScrollingEnabled(false);
            secondRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            SecondAdapter secondAdapter = new SecondAdapter(R.layout.item_second_layout,secondList);

        }
    }

    private class SecondAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public SecondAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
