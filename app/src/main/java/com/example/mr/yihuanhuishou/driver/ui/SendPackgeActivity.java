package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendPackgeActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.bianma_tv)
    TextView bianmaTv;
    @BindView(R.id.saoma_iv)
    ImageView saomaIv;
    @BindView(R.id.tiaoma_iv)
    ImageView tiaomaIv;
    @BindView(R.id.first_recyclerView)
    RecyclerView firstRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_packge);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("发放包装袋");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("确认发放");

        List<String> firstList = new ArrayList<>();
        firstList.add("");
        firstList.add("");
        firstList.add("");
        firstList.add("");
        firstRecyclerView.setNestedScrollingEnabled(false);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirstAdapter firstAdapter = new FirstAdapter(R.layout.item_first_send_layout,firstList);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private class FirstAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public FirstAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView secondRecycler = helper.getView(R.id.second_recyclerView);
            List<String> secondList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                secondList.add("");
            }
            secondRecycler.setNestedScrollingEnabled(false);
            secondRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
            SecondAdapter secondAdapter = new SecondAdapter(R.layout.item_second_send_layout,secondList);
            secondRecycler.setAdapter(secondAdapter);
        }
    }

    private class SecondAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public SecondAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.saoma_iv, R.id.tiaoma_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                finish();
                break;
            case R.id.saoma_iv:
                break;
            case R.id.tiaoma_iv:
                break;
        }
    }
}
