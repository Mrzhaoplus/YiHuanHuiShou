package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindCardActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.card_name_et)
    EditText cardNameEt;
    @BindView(R.id.card_num_et)
    EditText cardNumEt;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_rl)
    RelativeLayout bankRl;
    @BindView(R.id.bind_tv)
    TextView bindTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("添加银行卡");
    }

    @OnClick({R.id.title_back_iv, R.id.bank_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.bank_rl:
                break;
        }
    }
}
