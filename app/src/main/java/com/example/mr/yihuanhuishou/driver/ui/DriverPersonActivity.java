package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.utils.GGUtils;
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
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_person);
        ButterKnife.bind(this);
        initSystemBarTint();
        initView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        String imagpath = sp.getString(GGUtils.DrIMAGE_path, "");
        if(imagpath.equals("")){
            Glide.with(this).load(R.drawable.test_face_iv).into(faceIv);
        }else{
            Glide.with(this).load(imagpath).into(faceIv);
        }
        String user_name = sp.getString(GGUtils.DrUSER_NAME, "");
        if(user_name.equals("")){
            nameTv.setText("暂无昵称！");
        }else{
            nameTv.setText(user_name);
        }
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
                Intent intent1 = new Intent(this, DriverWalletActivity.class);
                intent1.putExtra("state",2);
                startActivity(intent1);
                break;
            case R.id.chepai_tv:
                startActivity(new Intent(this,DriverChepaiActivity.class));
                break;
            case R.id.baozhuangdai_tv:
                startActivity(new Intent(this,PackageBagManagerActivity.class));
                break;
            case R.id.yijian_tv:
                Intent intent2 = new Intent(this, DriverYijianActivity.class);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;
            case R.id.setting_tv:
                Intent intent = new Intent(this, DriverSettingActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
        }
    }
}
