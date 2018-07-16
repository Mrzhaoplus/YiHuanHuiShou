package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Shenhe_Frim_DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.dianhua_ll)
    LinearLayout dianhuaLl;
    @BindView(R.id.zaixian_ll)
    LinearLayout zaixianLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenhe__frim__details);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        beak.setOnClickListener(this);
        dianhuaLl.setOnClickListener(this);
        zaixianLl.setOnClickListener(this);
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ContentAdapter contentAdapter = new ContentAdapter(R.layout.item_gongsi_detail, list);
        recyclerView.setAdapter(contentAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
                case R.id.dianhua_ll:

                break;
                case R.id.zaixian_ll:
                break;
        }

    }

    private class ContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ContentAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView textView = helper.getView(R.id.content_tv);
            helper.setText(R.id.content_tv, item);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
