package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.GGUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YihuanBiActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.chong)
    RelativeLayout chong;
    @BindView(R.id.xiaofei_rl)
    RelativeLayout xiaofeiRl;
    @BindView(R.id.zhichu_rl)
    RelativeLayout zhichuRl;
    @BindView(R.id.price)
    TextView price;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yihuan_bi);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initdate();
    }

    private void initdate() {
        beak.setOnClickListener(this);
        chong.setOnClickListener(this);
        xiaofeiRl.setOnClickListener(this);
        zhichuRl.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        price.setText(sp.getFloat(GGUtils.YIHUAN, 0) + "");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.chong:
                Intent intent2 = new Intent(YihuanBiActivity.this, Chongzhi_yihuanActivity.class);
                startActivity(intent2);
                break;
            case R.id.xiaofei_rl:
                Intent intent = new Intent(YihuanBiActivity.this, Record_jiluActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.zhichu_rl:
                Intent intent1 = new Intent(YihuanBiActivity.this, Record_jiluActivity.class);
                intent1.putExtra("type", "1");
                startActivity(intent1);
                break;
        }
    }
}
