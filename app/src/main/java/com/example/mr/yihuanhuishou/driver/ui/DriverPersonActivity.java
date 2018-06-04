package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverPersonActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    CircleImageView faceIv;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.dingdan_tv)
    TextView dingdanTv;
    @BindView(R.id.qianbao_tv)
    TextView qianbaoTv;
    @BindView(R.id.chepai_tv)
    TextView chepaiTv;
    @BindView(R.id.baozhuangdai_tv)
    TextView baozhuangdaiTv;
    @BindView(R.id.yijian_tv)
    TextView yijianTv;
    @BindView(R.id.setting_tv)
    TextView settingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_person);
        ButterKnife.bind(this);
        initSystemBarTint();
        initView();
    }

    private void initView() {
        int statusBarHeight = getStatusBarHeight(this);
        MyUtils.setMargins(titleRl, 0, statusBarHeight, 0, 0);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的");
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @OnClick({R.id.title_back_iv, R.id.face_iv,R.id.dingdan_tv, R.id.qianbao_tv,
            R.id.chepai_tv, R.id.baozhuangdai_tv, R.id.yijian_tv, R.id.setting_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.face_iv:
                startActivity(new Intent(this,PersonInfoActivity.class));
                break;
            case R.id.dingdan_tv:
                startActivity(new Intent(this,DriverOrderActivity.class));
                break;
            case R.id.qianbao_tv:
                startActivity(new Intent(this,DriverWalletActivity.class));
                break;
            case R.id.chepai_tv:
                startActivity(new Intent(this,DriverChepaiActivity.class));
                break;
            case R.id.baozhuangdai_tv:
                startActivity(new Intent(this,PackageBagManagerActivity.class));
                break;
            case R.id.yijian_tv:
                startActivity(new Intent(this,DriverYijianActivity.class));
                break;
            case R.id.setting_tv:
                startActivity(new Intent(this,DriverSettingActivity.class));
                break;
        }
    }
}
