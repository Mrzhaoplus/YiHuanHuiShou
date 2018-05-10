package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driver_dingdan_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.jiedan_bh)
    TextView number;
    @BindView(R.id.jiedan_state)
    TextView state;
    @BindView(R.id.jiedan_lx)
    TextView sort;
    @BindView(R.id.jiedan_zl)
    TextView weight;
    @BindView(R.id.jiedan_zj)
    TextView price;
    @BindView(R.id.jiedan_dizhi)
    TextView address;
    @BindView(R.id.jiedan_time)
    TextView time;
    @BindView(R.id.jiedan_Lpnum)
    TextView Lpmun;
    @BindView(R.id.cancle_bill)
    Button cancle_bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dingdan__details);
        ButterKnife.bind(this);
        initview();
    }
    private void initview() {

        beak.setOnClickListener(this);
        cancle_bill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.cancle_bill:
                ToastUtils.getToast(this,"取消订单");
                break;
        }
    }
}
