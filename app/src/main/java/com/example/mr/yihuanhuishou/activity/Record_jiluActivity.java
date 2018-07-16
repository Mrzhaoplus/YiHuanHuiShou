package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Chongzhi_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Record_jiluActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.jilu)
    TextView jilu;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
    private int state;
   List<String>mList=new ArrayList<>();
    private Chongzhi_Adapter chongzhi_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_jilu);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        initdata();
    }

    private void initdata() {
        for(int i=0;i<10;i++){
            mList.add("");
        }
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        if(state==1){
            jilu.setText("充值记录");
            chongzhi_adapter = new Chongzhi_Adapter(Record_jiluActivity.this,mList,state);
            mrecycler.setAdapter(chongzhi_adapter);
        }else{
            jilu.setText("支出记录");
            chongzhi_adapter = new Chongzhi_Adapter(Record_jiluActivity.this,mList,state);
            mrecycler.setAdapter(chongzhi_adapter);
        }


    }
}
