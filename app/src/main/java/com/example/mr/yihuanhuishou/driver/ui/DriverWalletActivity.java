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

public class DriverWalletActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.xiaofei_rl)
    RelativeLayout xiaofeiRl;
    @BindView(R.id.zhichu_rl)
    RelativeLayout zhichuRl;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wallet);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的钱包");
    }

    @OnClick({R.id.title_back_iv, R.id.tixian_tv, R.id.xiaofei_rl, R.id.zhichu_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tixian_tv:
                startActivity(new Intent(this,TixianActivity.class));
                break;
            case R.id.xiaofei_rl:
                intent =  new Intent(this,JiluDetailActivity.class);
                intent.putExtra("type","shouru");
                startActivity(intent);
                break;
            case R.id.zhichu_rl:
                intent = new Intent(this,JiluDetailActivity.class);
                intent.putExtra("type","zhichu");
                startActivity(intent);
                break;
        }
    }
}
