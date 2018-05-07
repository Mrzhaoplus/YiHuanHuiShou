package com.example.mr.yihuanhuishou.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Bill_Adapter;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Bill_DetailActivity extends AppCompatActivity {
      @BindView(R.id.beak)
      ImageView beak;
      @BindView(R.id.sp_view)
       SpringView sp_view;
      @BindView(R.id.recy_view)
      RecyclerView recy_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__detail);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(Bill_DetailActivity.this));
        recy_view.addItemDecoration(new DividerItemDecoration(Bill_DetailActivity.this, DividerItemDecoration.VERTICAL_LIST));
        Bill_Adapter bill_adapter = new Bill_Adapter(this);
        recy_view.setAdapter(bill_adapter);

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

    }
}
