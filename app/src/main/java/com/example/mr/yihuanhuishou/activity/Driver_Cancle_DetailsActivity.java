package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driver_Cancle_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.cancle_bh)
    TextView bianhao;
    @BindView(R.id.cancle_state)
    TextView state;
    @BindView(R.id.cancle_lx)
    TextView sort;
    @BindView(R.id.cancle_zl)
    TextView weight;
    @BindView(R.id.cancle_zj)
    TextView zong_price;
    @BindView(R.id.cancle_name)
    TextView name;
    @BindView(R.id.cancle_tel)
    TextView tel;
    @BindView(R.id.cancle_dizhi)
    TextView address;
    @BindView(R.id.cancle_qu_time)
    TextView qu_time;
    @BindView(R.id.cancle_time)
    TextView fa_time;
    @BindView(R.id.cancle_cancle_time)
    TextView quxiao_time;
    @BindView(R.id.cancle_reason)
    TextView reason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__cancle__details);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        beak.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
        }
    }
}
