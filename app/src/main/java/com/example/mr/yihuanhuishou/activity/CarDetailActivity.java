package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.chepai_tv)
    TextView chepaiTv;
    @BindView(R.id.pinzhong_tv)
    TextView pinzhongTv;
    @BindView(R.id.gongsi_tv)
    TextView gongsiTv;
    @BindView(R.id.call_tv)
    TextView callTv;
    @BindView(R.id.dianhua_ll)
    LinearLayout dianhuaLl;
    @BindView(R.id.zaixian_ll)
    LinearLayout zaixianLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("车辆详情");
    }

    @OnClick({R.id.title_back_iv, R.id.dianhua_ll, R.id.zaixian_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.dianhua_ll:
                break;
            case R.id.zaixian_ll:
                break;
        }
    }
}
