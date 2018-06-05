package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.ui.CompanyDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeCompanyDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_more_iv)
    ImageView titleMoreIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.zhipai_tv)
    TextView zhipaiTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_company_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("公司详情");
        titleMoreIv.setVisibility(View.VISIBLE);

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

    private class ContentAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

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

    @OnClick({R.id.title_back_iv, R.id.title_more_iv, R.id.zhipai_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_more_iv:

                break;
            case R.id.zhipai_tv:

                break;
        }
    }
}
