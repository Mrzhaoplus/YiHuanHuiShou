package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Mine_Adapter;
import com.example.mr.yihuanhuishou.bean.Mine_bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineActivity extends AppCompatActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.grid_view)
    GridView gridView;
    List<Mine_bean> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initdata();
    }
    private void initdata() {
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"我的订单"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"我的消息"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"回收价格"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"包装袋管理"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"常用地址"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"实名验证"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"意见建议"));
       list.add(new Mine_bean(R.drawable.zhifu_xxxhdpi,"设置"));

        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Mine_Adapter mine_adapter = new Mine_Adapter(MineActivity.this, list);
        gridView.setAdapter(mine_adapter);


    }
}
