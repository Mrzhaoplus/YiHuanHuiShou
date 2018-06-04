package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.MainActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverSettingActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.xgmm_rl)
    RelativeLayout xgmmRl;
    @BindView(R.id.gywm_rl)
    RelativeLayout gywmRl;
    @BindView(R.id.jcgx_rl)
    RelativeLayout jcgxRl;
    @BindView(R.id.bangzhu_rl)
    RelativeLayout bangzhuRl;
    @BindView(R.id.exit_tv)
    TextView exitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("设置");
    }

    @OnClick({R.id.title_back_iv, R.id.xgmm_rl, R.id.gywm_rl, R.id.jcgx_rl, R.id.bangzhu_rl, R.id.exit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.xgmm_rl:
                startActivity(new Intent(this,EditPassWordActivity.class));
                break;
            case R.id.gywm_rl:
                startActivity(new Intent(this,AboutUsActivity.class));
                break;
            case R.id.jcgx_rl:
                break;
            case R.id.bangzhu_rl:
                startActivity(new Intent(this,HelpActivity.class));
                break;
            case R.id.exit_tv:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
