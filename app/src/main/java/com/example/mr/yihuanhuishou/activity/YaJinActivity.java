package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YaJinActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.tui_price)
    TextView tuiPrice;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.ya_tui)
    RelativeLayout yaTui;
    @BindView(R.id.ya_jilu)
    RelativeLayout yaJilu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ya_jin);
        initSystemBarTint();
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        titleBackIv.setOnClickListener(this);
        yaTui.setOnClickListener(this);
        yaJilu.setOnClickListener(this);


    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.transparent_db);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.ya_tui:
                if(tuiPrice.getText().toString().equals("押金退款")){
                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                }else if(tuiPrice.getText().toString().equals("交纳押金")){
                    Intent intent = new Intent(YaJinActivity.this, ChongzhiActivity.class);
                    intent.putExtra("state",1);
                    startActivity(intent);
                }
                break;
            case R.id.ya_jilu:
                startActivity(new Intent(YaJinActivity.this,Yajin_recordActivity.class));
                break;
        }
    }
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(YaJinActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.tuiyajin_pop)
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
                tuiPrice.setText("交纳押金");
                price.setText("0.00");
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
