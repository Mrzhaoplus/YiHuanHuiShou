package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.LogonActivity;
import com.example.mr.yihuanhuishou.activity.MainActivity;
import com.example.mr.yihuanhuishou.activity.YaJinActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.AppManager;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.GGUtils;

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
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
        }
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(DriverSettingActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.tuichu_pop)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();
                AppManager.getAppManager().finishAllActivity();
                startActivity(new Intent(DriverSettingActivity.this, LogonActivity.class));
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
