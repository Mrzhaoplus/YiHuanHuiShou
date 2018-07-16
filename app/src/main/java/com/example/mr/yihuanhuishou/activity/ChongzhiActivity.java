package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChongzhiActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.zhifubao)
    CheckBox zhifubao;
    @BindView(R.id.weixin)
    CheckBox weixin;
    @BindView(R.id.chongzhi)
    Button chongzhi;
    @BindView(R.id.yihuanbi)
    TextView yihuanbi;
    @BindView(R.id.yihuanbi_ll)
    TextView yihuanbiLl;
    @BindView(R.id.price)
    TextView price;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getIntent().getIntExtra("state", 0);
        setContentView(R.layout.activity_chongzhi);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        if (state == 1) {
            yihuanbi.setVisibility(View.GONE);
            yihuanbiLl.setVisibility(View.GONE);
            price.setText("199.00");
        }
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(ChongzhiActivity.this, "充值成功");
                finish();
            }
        });

        weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean wei) {
                if (wei) {
                    zhifubao.setChecked(false);
                }
            }
        });
        zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean zhi) {
                if (zhi) {
                    weixin.setChecked(false);
                }
            }
        });
    }
}
