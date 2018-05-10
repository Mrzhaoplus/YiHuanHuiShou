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

public class Clame_Goods_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.take_bh)
    TextView bianhao;
    @BindView(R.id.take_state)
    TextView state;
    @BindView(R.id.take_lx)
    TextView sort;
    @BindView(R.id.take_zl)
    TextView weight;
    @BindView(R.id.take_zj)
    TextView price;
    @BindView(R.id.take_name)
    TextView name;
    @BindView(R.id.take_tel)
    TextView tel;
    @BindView(R.id.take_dizhi)
    TextView address;
    @BindView(R.id.take_qu_time)
    TextView qu_time;
    @BindView(R.id.take_time)
    TextView fa_time;
    @BindView(R.id.take_jie_time)
    TextView jie_time;
    @BindView(R.id.take_Lpnum)
    TextView Lpnum;
    @BindView(R.id.cancle_bill)
    Button cancle_bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clame__goods__details);
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
