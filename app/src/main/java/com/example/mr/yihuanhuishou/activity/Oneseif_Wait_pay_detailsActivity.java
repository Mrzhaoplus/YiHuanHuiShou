package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Oneseif_Wait_pay_detailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
     @BindView(R.id.pay_bh)
    TextView number;
     @BindView(R.id.pay_state)
     TextView state;
     @BindView(R.id.pay_lx)
     TextView sort;
     @BindView(R.id.pay_zl)
     TextView weight;
     @BindView(R.id.pay_zj)
     TextView zprice;
     @BindView(R.id.pay_time)
     TextView fa_time;
     @BindView(R.id.pay_zhi_time)
     TextView zhi_time;
     @BindView(R.id.pay_firm)
     TextView firm;
     @BindView(R.id.pay_price)
     TextView wait_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneseif__wait_pay_details);
        ButterKnife.bind(this);
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
