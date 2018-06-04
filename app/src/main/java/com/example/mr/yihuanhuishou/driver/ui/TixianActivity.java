package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TixianActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.checkbox_ali)
    CheckBox checkboxAli;
    @BindView(R.id.ali_rl)
    RelativeLayout aliRl;
    @BindView(R.id.checkbox_weichat)
    CheckBox checkboxWeichat;
    @BindView(R.id.weichat_rl)
    RelativeLayout weichatRl;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_rl)
    RelativeLayout bankRl;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("提现");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("提现账户管理");
    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.ali_rl, R.id.weichat_rl, R.id.bank_rl,R.id.tixian_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                startActivity(new Intent(this,TixianManagerActivity.class));
                break;
            case R.id.ali_rl:
                break;
            case R.id.weichat_rl:
                break;
            case R.id.bank_rl:
                break;
            case R.id.tixian_tv:
                break;
        }
    }

}
