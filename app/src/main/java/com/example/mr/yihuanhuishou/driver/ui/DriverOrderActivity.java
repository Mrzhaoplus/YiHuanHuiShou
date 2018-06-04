package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.fragment.DriverAllOrderFragment;
import com.example.mr.yihuanhuishou.driver.weight.NoCacheViewPager;
import com.example.mr.yihuanhuishou.fragment.driver.Driver_all_fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverOrderActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<String> tabList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_order);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("订单列表");

        tabList.add("全部");
        tabList.add("待取货");
        tabList.add("配送中");
        tabList.add("已完成");
        tabList.add("已取消");

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabList.get(position);
            }
            @Override
            public int getCount() {
                return tabList.size();
            }
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment= new DriverAllOrderFragment(tabList.get(position));
                        break;
                    case 1:
                        fragment= new DriverAllOrderFragment(tabList.get(position));
                        break;
                    case 2:
                        fragment= new DriverAllOrderFragment(tabList.get(position));
                        break;
                    case 3:
                        fragment= new DriverAllOrderFragment(tabList.get(position));
                        break;
                    case 4:
                        fragment= new DriverAllOrderFragment(tabList.get(position));
                        break;
                }
                return fragment;
            }
        });
        tablayout.setupWithViewPager(viewpager);
    }

    @OnClick({R.id.title_back_iv, R.id.title_content_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_tv:
                break;
        }
    }
}
