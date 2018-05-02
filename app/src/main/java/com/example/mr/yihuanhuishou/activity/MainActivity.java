package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.MyContants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
      @BindView(R.id.huishou_img)
      ImageView huishou_img;
      @BindView(R.id.siji_img)
      ImageView siji_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyContants.windows(this);
        ButterKnife.bind(this);
    }
}
