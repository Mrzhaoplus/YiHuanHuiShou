package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class wangji_forgetActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.huo_yan)
    TextView huoYan;
    @BindView(R.id.zhao)
    Button zhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangji_forget);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        beak.setOnClickListener(this);
        huoYan.setOnClickListener(this);
        zhao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.zhao:
                finish();
                break;
            case R.id.huo_yan:
                ToastUtils.getToast(wangji_forgetActivity.this,"发送验证码");
                break;


        }
    }
}
