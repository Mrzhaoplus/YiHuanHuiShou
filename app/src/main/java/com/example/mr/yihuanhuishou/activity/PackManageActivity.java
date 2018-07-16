package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.PackManage_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackManageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    List<String> mList = new ArrayList<>();
    @BindView(R.id.tiaoma)
    ImageView tiaoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_manage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        beak.setOnClickListener(this);
        tiaoma.setOnClickListener(this);
        for (int i = 0; i < 10; i++) {
            mList.add("");
        }
        //无下划线
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PackManage_Adapter packManage_adapter = new PackManage_Adapter(this, mList);
        recyView.setAdapter(packManage_adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.tiaoma:
                startActivity(new Intent(PackManageActivity.this,Bar_CodeActivity.class));
                break;
        }

    }
}
