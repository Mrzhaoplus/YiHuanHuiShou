package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Distribution_success_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.dis_bh)
    TextView bianhao;
    @BindView(R.id.dis_state)
    TextView state;
    @BindView(R.id.dis_lx)
    TextView sort;
    @BindView(R.id.dis_zl)
    TextView weight;
    @BindView(R.id.dis_zj)
    TextView price;
    @BindView(R.id.dis_name)
    TextView name;
    @BindView(R.id.dis_tel)
    TextView tel;
    @BindView(R.id.dis_dizhi)
    TextView address;
    @BindView(R.id.dis_qu_time)
    TextView qu_time;
    @BindView(R.id.dis_time)
    TextView fa_time;
    @BindView(R.id.dis_jie_time)
    TextView jie_time;
    @BindView(R.id.dis_qu_time2)
    TextView qu_time2;
    @BindView(R.id.dis_success_time)
    TextView success_time;
    @BindView(R.id.dis_Lpnum)
    TextView Lpnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_success__details);
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
