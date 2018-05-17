package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Infor_Msg_DetailsActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor__msg__details);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        //点击返回
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String title1 = getIntent().getStringExtra("title");
        title.setText(title1);


    }
}
