package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.driver.ui.HuiShouCompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.ui.QiangdanDetailActivity;
import com.example.mr.yihuanhuishou.fragment.Driver_home_frim_fragment;
import com.example.mr.yihuanhuishou.fragment.Driver_home_person_fragment;
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

public class HomeListFragment extends BaseFragment {
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
    @BindView(R.id.fl)
    FrameLayout fl;
    Unbinder unbinder1;
    private Fragment currfit;
    private Driver_home_person_fragment driver_home_person_fragment;
    private Driver_home_frim_fragment driver_home_frim_fragment;
    private double lat;
    private double lon;
    private String city;

    @SuppressLint("ValidFragment")
    public HomeListFragment(double lat, double lon,String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }

    public HomeListFragment() {
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initLazyData() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        if(driver_home_person_fragment==null){
            driver_home_person_fragment = new Driver_home_person_fragment(lat,lon,city);
        }
        AddFragment(driver_home_person_fragment);
        return rootView;
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
        if(driver_home_person_fragment==null){
            driver_home_person_fragment = new Driver_home_person_fragment();
        }
        AddFragment(driver_home_person_fragment);
    }

    @OnClick(R.id.gongsi_ll)
    public void onGongsiLlClicked() {
        renyuanTv.setTextColor(getResources().getColor(R.color.textColor));
        gongsiTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        indicatorRenyuan.setBackgroundColor(getResources().getColor(R.color.white));
        indicatorGongsi.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        if(driver_home_frim_fragment==null){
            driver_home_frim_fragment = new Driver_home_frim_fragment();
        }
        AddFragment(driver_home_frim_fragment);
    }
    /*
       * 动态添加fragment方法
       * */
    public void AddFragment(Fragment f){
        FragmentManager supportFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);
        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }



}
