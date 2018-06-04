package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TixianManagerActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.ali_isbind_tv)
    TextView aliIsbindTv;
    @BindView(R.id.ali_rl)
    RelativeLayout aliRl;
    @BindView(R.id.wechat_isbind_tv)
    TextView wechatIsbindTv;
    @BindView(R.id.wechat_rl)
    RelativeLayout wechatRl;
    @BindView(R.id.bank_iv)
    ImageView bankIv;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_num_tv)
    TextView bankNumTv;
    @BindView(R.id.bind_tv)
    TextView bindTv;
    @BindView(R.id.bank_rl)
    RelativeLayout bankRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_manager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("提现账户管理");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("解绑银行卡");
    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.ali_rl, R.id.wechat_rl,R.id.bank_rl,R.id.bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                startActivity(new Intent(this,UnBindCardActivity.class));
                break;
            case R.id.ali_rl:
                break;
            case R.id.wechat_rl:
                break;
            case R.id.bank_rl:
                break;
            case R.id.bind_tv:
                startActivity(new Intent(this,BindCardActivity.class));
                break;
        }
    }
}
