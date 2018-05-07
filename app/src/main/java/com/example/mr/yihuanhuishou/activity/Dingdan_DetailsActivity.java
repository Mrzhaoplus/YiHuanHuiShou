package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
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

public class Dingdan_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.shang_qx)
    TextView quxiao;
    @BindView(R.id.shang_bh)
    TextView bianhao;
    @BindView(R.id.shang_name)
    TextView name;
    @BindView(R.id.shang_ly)
    TextView laiyuan;
    @BindView(R.id.shang_lx)
    TextView leixing;
    @BindView(R.id.shang_zl)
    TextView zhongliang;
    @BindView(R.id.shang_ms)
    TextView miaoshu;
    @BindView(R.id.shang_dz)
    TextView address;
    @BindView(R.id.shang_time)
    TextView time;
    @BindView(R.id.shang_qr)
    Button queren;
    @BindView(R.id.shang_state)
    TextView state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan__details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        String state1= intent.getStringExtra("state");
        String bianhao1 = intent.getStringExtra("bianhao");
        String sort = intent.getStringExtra("sort");
        String weight = intent.getStringExtra("weight");
        beak.setOnClickListener(this);
        queren.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        //赋值
        name.setText(name1);
        bianhao.setText(bianhao1);
        leixing.setText(sort);
        state.setText(state1);
        zhongliang.setText(weight);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.shang_qx:
                ToastUtils.getToast(this,"取消订单");
                finish();
                break;
            case R.id.shang_qr:
                ToastUtils.getToast(this,"确认订单");
                break;
        }

    }
}
