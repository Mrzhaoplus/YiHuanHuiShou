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

public class Wait_zhifu_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.zhi_fh)
    TextView fanhui;
    @BindView(R.id.zhi_bh)
    TextView biaohao;
    @BindView(R.id.zhi_name)
    TextView name;
    @BindView(R.id.zhi_ly)
    TextView laiyuan;
    @BindView(R.id.zhi_lx)
    TextView leixing;
    @BindView(R.id.zhi_zl)
    TextView zhongliang;
    @BindView(R.id.zhi_ms)
    TextView miaoshu;
    @BindView(R.id.zhi_dz)
    TextView address;
    @BindView(R.id.zhi_time)
    TextView time;
    @BindView(R.id.zhi_fu)
    Button zhifu;
    @BindView(R.id.zhi_state)
    TextView state;
    @BindView(R.id.zhi_dj)
    TextView danjia;
    @BindView(R.id.zhi_price)
    TextView price;
    @BindView(R.id.zhi_yihuan)
    TextView yihuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_zhifu__details);
        ButterKnife.bind(this);
        beak.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        zhifu.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.zhi_fh:
                finish();
                break;
            case R.id.zhi_fu:
                ToastUtils.getToast(this,"确认支付");
                break;
        }
    }
}
