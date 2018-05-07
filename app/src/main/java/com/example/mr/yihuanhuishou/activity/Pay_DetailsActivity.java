package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pay_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.duihuan)
    TextView duihuan;
    @BindView(R.id.zhifubao)
    CheckBox zhifubao;
    @BindView(R.id.weixin)
    CheckBox weixin;
    @BindView(R.id.xianxia)
    CheckBox xianxia;
    @BindView(R.id.zhifu)
    Button zhifu;
    @BindView(R.id.beak)
    ImageView beak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__details);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        float money = getIntent().getIntExtra("money", 0) * 1.0f;
        price.setText(money+"元");
        zhifu.setOnClickListener(this);
        beak.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhifu:
                EventBus.getDefault().postSticky(new Event_fragment(1));
                finish();
                break;
            case R.id.beak:
                finish();
                break;
        }
    }
}
