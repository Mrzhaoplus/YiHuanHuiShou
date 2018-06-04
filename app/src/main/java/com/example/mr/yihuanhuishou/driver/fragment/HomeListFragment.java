package com.example.mr.yihuanhuishou.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.driver.ui.QiangdanDetailActivity;
import com.example.mr.yihuanhuishou.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/21.
 */

public class HomeListFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    Unbinder unbinder;
    @BindView(R.id.renyuan_tv)
    TextView renyuanTv;
    @BindView(R.id.indicator_renyuan)
    View indicatorRenyuan;
    @BindView(R.id.renyuan_ll)
    LinearLayout renyuanLl;
    @BindView(R.id.gongsi_tv)
    TextView gongsiTv;
    @BindView(R.id.indicator_gongsi)
    View indicatorGongsi;
    @BindView(R.id.gongsi_ll)
    LinearLayout gongsiLl;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    private void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        HomeListAdapter listAdapter = new HomeListAdapter(R.layout.item_home_list,list);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext,QiangdanDetailActivity.class));
    }

    private class HomeListAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public HomeListAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.renyuan_ll)
    public void onRenyuanLlClicked() {
        renyuanTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        gongsiTv.setTextColor(getResources().getColor(R.color.textColor));
        indicatorRenyuan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        indicatorGongsi.setBackgroundColor(getResources().getColor(R.color.white));
        searchEt.setHint("请输入回收人员名称或类型");
    }

    @OnClick(R.id.gongsi_ll)
    public void onGongsiLlClicked() {
        renyuanTv.setTextColor(getResources().getColor(R.color.textColor));
        gongsiTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        indicatorRenyuan.setBackgroundColor(getResources().getColor(R.color.white));
        indicatorGongsi.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        searchEt.setHint("请输入回收公司名称或类型");
    }

    @OnClick(R.id.search_tv)
    public void onSearchTvClicked() {
        TUtils.showShort(mContext,"点击了搜索");
    }
}
