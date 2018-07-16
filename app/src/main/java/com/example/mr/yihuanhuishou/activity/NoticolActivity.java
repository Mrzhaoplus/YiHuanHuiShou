package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Infor_Msg_Adapter;
import com.example.mr.yihuanhuishou.adapter.NoticolAdapter;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticolActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recy_view;
    @BindView(R.id.sp_view)
    SpringView sp_view;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticol);
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

        for (int i=0;i<8;i++){
            list.add("");
        }

        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(this));
        recy_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        NoticolAdapter noticolAdapter = new NoticolAdapter(this,list);
        recy_view.setAdapter(noticolAdapter);


    }
}
