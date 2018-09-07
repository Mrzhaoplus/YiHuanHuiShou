package com.example.mr.yihuanhuishou.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Touxiang_DetailsActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.imag)
    ImageView imag;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    private String imagview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touxiang__details);
        initSystemBarTint();
        ButterKnife.bind(this);
        imagview = getIntent().getStringExtra("imag");
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Glide.with(this).load(imagview).into(imag);
        final int statusBarHeight = getStatusBarHeight(this);
        MyUtils.setMargins(titleRl, 0, statusBarHeight, 0, 0);
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
}
