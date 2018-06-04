package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
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

public class CompanyDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.call_tv)
    TextView callTv;
    @BindView(R.id.chat_tv)
    TextView chatTv;
    @BindView(R.id.shenqing_tv)
    TextView shenqingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("公司详情");

        List<String> list = new ArrayList<>();
        list.add("衣   物：2元/斤");
        list.add("鞋   类：1元/斤");
        list.add("纸   箱：3元/斤");
        list.add("金   属：5元/斤");
        list.add("玻   瓶：1元/斤");
        list.add("大   电：10元/斤");
        list.add("塑   瓶：1元/斤");
        list.add("手机数码：20/斤");

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ContentAdapter contentAdapter = new ContentAdapter(R.layout.item_gongsi_detail,list);
        recyclerView.setAdapter(contentAdapter);
    }

    private class ContentAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public ContentAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView textView = helper.getView(R.id.content_tv);
            helper.setText(R.id.content_tv,item);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @OnClick({R.id.title_back_iv, R.id.call_tv, R.id.chat_tv, R.id.shenqing_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.call_tv:
                break;
            case R.id.chat_tv:
                break;
            case R.id.shenqing_tv:
                break;
        }
    }
}
