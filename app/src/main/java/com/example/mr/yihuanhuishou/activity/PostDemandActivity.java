package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDemandActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.peisong_left_rbt)
    RadioButton peisongLeftRbt;
    @BindView(R.id.peisong_right_rbt)
    RadioButton peisongRightRbt;
    @BindView(R.id.saoma_iv)
    ImageView saomaIv;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.leixing_tv)
    TextView leixingTv;
    @BindView(R.id.leixing_rl)
    RelativeLayout leixingRl;
    @BindView(R.id.view_03)
    TextView view03;
    @BindView(R.id.shuliang_tv)
    TextView shuliangTv;
    @BindView(R.id.view_02)
    LinearLayout view02;
    @BindView(R.id.shuliang_et)
    EditText shuliangEt;
    @BindView(R.id.view_04)
    TextView view04;
    @BindView(R.id.view_05)
    TextView view05;
    @BindView(R.id.zongjia_et)
    EditText zongjiaEt;
    @BindView(R.id.quhuo_left_rbt)
    RadioButton quhuoLeftRbt;
    @BindView(R.id.quhuo_right_rbt)
    RadioButton quhuoRightRbt;
    @BindView(R.id.select_address)
    TextView selectAddress;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.map)
    MapView map;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_demand);
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("发布需求");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("发布");
        //创建地图
        map.onCreate(savedInstanceState);
        //显示地图
        if(aMap == null){
            aMap = map.getMap();
        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.saoma_iv, R.id.leixing_rl, R.id.shuliang_tv, R.id.select_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                startActivity(new Intent(this,OrderActivity.class));
                break;
            case R.id.saoma_iv:
                break;
            case R.id.leixing_rl:
                break;
            case R.id.shuliang_tv:
                break;
            case R.id.select_address:
                startActivity(new Intent(this,Select_AddressActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }
}
