package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Recycler_prive_Adapter;
import com.example.mr.yihuanhuishou.base.Recycle_price_bean;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Recycle_priceActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    List<Recycle_price_bean> list = new ArrayList<>();
    @BindView(R.id.spring_view)
    SpringView sp_view;
    @BindView(R.id.tijiao)
    Button tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_price);
        ButterKnife.bind(this);
        initdate();
    }

    private void initdate() {
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                ToastUtils.getToast(Recycle_priceActivity.this,"以保存");
            }
        });


        list.add(new Recycle_price_bean(true, "衣服"));
        list.add(new Recycle_price_bean(true, "塑料瓶"));
        list.add(new Recycle_price_bean(true, "手机数码"));
        list.add(new Recycle_price_bean(false, "鞋类"));
        list.add(new Recycle_price_bean(false, "纸箱"));
        list.add(new Recycle_price_bean(false, "金属"));
        list.add(new Recycle_price_bean(false, "大家电"));
        list.add(new Recycle_price_bean(false, "衣服"));
        list.add(new Recycle_price_bean(false, "衣服"));

        //刷新
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


        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //适配器
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(this));
        recyView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        Recycler_prive_Adapter recycler_prive_adapter = new Recycler_prive_Adapter(Recycle_priceActivity.this, list);
        recyView.setAdapter(recycler_prive_adapter);


    }
}
