package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiangDan_DetailsActivity extends BaseActivity {
    @BindView(R.id.chakan_tv)
    TextView chakanTv;
    @BindView(R.id.back_iv)
    TextView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiang_dan__details);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.chakan_tv, R.id.back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chakan_tv:
                finish();
                break;
            case R.id.back_iv:
                finish();
                break;
        }
    }
}
