package com.example.mr.yihuanhuishou.driver.ui;

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
import butterknife.OnClick;

public class DriverWalletActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.xiaofei_rl)
    RelativeLayout xiaofeiRl;
    @BindView(R.id.zhichu_rl)
    RelativeLayout zhichuRl;
    private Intent intent;
    private SharedPreferences sp;
    private SharedPreferences spp;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wallet);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        state = getIntent().getIntExtra("state", 0);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的钱包");
        if(state==1){
            moneyTv.setText("￥ "+sp.getFloat(GGUtils.XIANJIN,0)+"");
        }else{
            float aFloat = spp.getFloat(GGUtils.DrXIANJIN, 0);
            if(aFloat<0){
                moneyTv.setText("￥ 0.00");
            }else{
                moneyTv.setText("￥ "+spp.getFloat(GGUtils.DrXIANJIN,0));
            }
        }
    }
    @OnClick({R.id.title_back_iv, R.id.tixian_tv, R.id.xiaofei_rl, R.id.zhichu_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tixian_tv:
                Intent intent = new Intent(this, TixianActivity.class);
                intent.putExtra("state",state);
                startActivity(intent);
                break;
            case R.id.xiaofei_rl:
                this.intent =  new Intent(this,JiluDetailActivity.class);
                this.intent.putExtra("type","0");
                this.intent.putExtra("state",state);
                this.intent.putExtra("dtype","2");
                startActivity(this.intent);
                break;
            case R.id.zhichu_rl:
                this.intent = new Intent(this,JiluDetailActivity.class);
                this.intent.putExtra("type","1");
                this.intent.putExtra("state",state);
                this.intent.putExtra("dtype","1");
                startActivity(this.intent);
                break;
        }
    }
}
