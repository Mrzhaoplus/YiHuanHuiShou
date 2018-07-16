package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Yajin_record_Adapter;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Yajin_recordActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
    List<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yajin_record);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i=0;i<10;i++){
            list.add("");
        }

        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(Yajin_recordActivity.this));
        mrecycler.addItemDecoration(new DividerItemDecoration(Yajin_recordActivity.this, DividerItemDecoration.VERTICAL_LIST));
        Yajin_record_Adapter yajin_record_adapter = new Yajin_record_Adapter(Yajin_recordActivity.this,list);
        mrecycler.setAdapter(yajin_record_adapter);
    }
}
