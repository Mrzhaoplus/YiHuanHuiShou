package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mr.yihuanhuishou.R;

import butterknife.ButterKnife;

public class Select_AddressActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__address);
        ButterKnife.bind(this);
    }
}
