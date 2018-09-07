package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XiuGai_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Help_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int type;
    List<Help_Bean.DataBean> list = new ArrayList<>();
    private HelpAdapter helpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        initView();
        infoview();
    }

    private void infoview() {
            OkGo.<Help_Bean>get(MyUrls.BASEURL + "/help/helpList")
                    .tag(this)
                    .execute(new DialogCallback<Help_Bean>(HelpActivity.this, Help_Bean.class) {
                        @Override
                        public void onSuccess(Response<Help_Bean> response) {
                            Help_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Help_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                helpAdapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(HelpActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(HelpActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(HelpActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(HelpActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(HelpActivity.this, body.getMsg());
                            }
                        }
                    });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("帮助");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        helpAdapter = new HelpAdapter(R.layout.item_help_layout,list);
        recyclerView.setAdapter(helpAdapter);
        helpAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, HelpDetailActivity.class);
        intent.putExtra("id",list.get(position).getContent());
        startActivity(intent);
    }

    private class HelpAdapter extends BaseQuickAdapter<Help_Bean.DataBean,BaseViewHolder>{
        public HelpAdapter(int layoutResId, @Nullable List<Help_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Help_Bean.DataBean item) {
            TextView title = helper.getView(R.id.item_content_tv);
            title.setText(item.getTitle());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
