package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.driver.ui.DriverYijianActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.tel)
    LinearLayout tel;
    @BindView(R.id.liuyan)
    LinearLayout liuyan;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ButterKnife.bind(this);
        initview();

    }

    private void initview() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("意见建议");
        titleBackIv.setOnClickListener(this);
        tel.setOnClickListener(this);
        liuyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tel:

                break;
            case R.id.liuyan:
                startActivity(new Intent(ComplaintActivity.this, DriverYijianActivity.class));
                break;
        }
    }
}
