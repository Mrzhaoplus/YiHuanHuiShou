package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driver_zhifu_success_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.pay_bh)
    TextView bianhao;
    @BindView(R.id.pay_state)
    TextView state;
    @BindView(R.id.pay_lx)
    TextView sort;
    @BindView(R.id.pay_zl)
    TextView weight;
    @BindView(R.id.pay_zj)
    TextView zong_price;
    @BindView(R.id.pay_name)
    TextView name;
    @BindView(R.id.pay_tel)
    TextView tel;
    @BindView(R.id.pay_dizhi)
    TextView address;
    @BindView(R.id.pay_qu_time)
    TextView qu_time;
    @BindView(R.id.pay_time)
    TextView fa_time;
    @BindView(R.id.pay_jie_time)
    TextView jie_time;
    @BindView(R.id.pay_qu_time2)
    TextView qu_time2;
    @BindView(R.id.pay_success_time)
    TextView success_time;
    @BindView(R.id.pay_Lpnum)
    TextView Lpnum;
    @BindView(R.id.pay_fukuan_time)
    TextView fu_time;
    @BindView(R.id.pay_price)
    TextView fu_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_zhifu_success__details);
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
