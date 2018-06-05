package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Real_nameActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ButterKnife.bind(this);
        initdate();
    }

    private void initdate() {
        beak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
        }

    }
}
