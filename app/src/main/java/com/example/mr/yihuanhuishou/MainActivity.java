package com.example.mr.yihuanhuishou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.MyContants;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyContants.windows(this);
        ButterKnife.bind(this);

    }
}
