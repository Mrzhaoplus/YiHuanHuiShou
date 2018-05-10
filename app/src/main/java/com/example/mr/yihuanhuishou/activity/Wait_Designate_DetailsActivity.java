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

public class Wait_Designate_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.desi_bh)
    TextView number;
    @BindView(R.id.desi_state)
    TextView state;
    @BindView(R.id.desi_sort)
    TextView sort;
    @BindView(R.id.desi_weight)
    TextView weight;
    @BindView(R.id.desi_zj)
    TextView price;
    @BindView(R.id.desi_fa_time)
    TextView fa_time;
    @BindView(R.id.desi_zhipai)
    Button designate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait__designate__details);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        beak.setOnClickListener(this);
        designate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.desi_zhipai:
                ToastUtils.getToast(this,"已经指派");
                break;
        }
    }
}
