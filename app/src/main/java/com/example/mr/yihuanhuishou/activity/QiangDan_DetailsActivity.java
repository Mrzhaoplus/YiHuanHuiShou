package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiangDan_DetailsActivity extends BaseActivity {
    @BindView(R.id.chakan_tv)
    TextView chakanTv;
    @BindView(R.id.back_iv)
    TextView backIv;
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.chenggong)
    LinearLayout chenggong;
    @BindView(R.id.chong)
    TextView chong;
    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.shibai)
    LinearLayout shibai;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiang_dan__details);
        ButterKnife.bind(this);
        msg = getIntent().getStringExtra("msg");

        initdata();
    }

    private void initdata() {
        if(msg.equals("成功！")){
            chenggong.setVisibility(View.VISIBLE);
            shibai.setVisibility(View.GONE);
        }else{
            shibai.setVisibility(View.VISIBLE);
            chenggong.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.chakan_tv, R.id.back_iv, R.id.beak,R.id.chong,R.id.fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.chakan_tv:
                Intent intent2 = new Intent(QiangDan_DetailsActivity.this, OrderActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.back_iv:
                //AppManager.getAppManager().finishAllActivity();
                Intent intent = new Intent(this, Home_detailActivity.class);
                startActivity(intent);
                break;
            case R.id.chong:
                finish();
                break;
            case R.id.fanhui:
               // AppManager.getAppManager().finishAllActivity();
                Intent intent1 = new Intent(this, Home_detailActivity.class);
                startActivity(intent1);
                break;
        }
    }


}
