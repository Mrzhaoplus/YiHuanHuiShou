package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Chepai_Cord_itme_bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Gongsi_Itme_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
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

public class HuishouCompanyActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<Gongsi_Itme_Bean.DataBean> list = new ArrayList<>();
    private SharedPreferences sp;
    private CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huishou_company);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        infoview();
    }
    private void infoview() {
        list.clear();
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        OkGo.<Gongsi_Itme_Bean>get(MyUrls.BASEURL + "/driverMine/companyList")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Gongsi_Itme_Bean>(HuishouCompanyActivity.this, Gongsi_Itme_Bean.class) {
                    @Override
                    public void onSuccess(Response<Gongsi_Itme_Bean> response) {
                        Gongsi_Itme_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Gongsi_Itme_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                            }
                            companyAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("回收公司");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        companyAdapter = new CompanyAdapter(R.layout.item_company_layout,list);
        recyclerView.setAdapter(companyAdapter);
        companyAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, HuiShouCompanyDetailActivity.class);
        intent.putExtra("id",list.get(position).getCompanyId());
        startActivity(intent);
    }
    private class CompanyAdapter extends BaseQuickAdapter<Gongsi_Itme_Bean.DataBean,BaseViewHolder>{
        public CompanyAdapter(int layoutResId, @Nullable List<Gongsi_Itme_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Gongsi_Itme_Bean.DataBean item) {
            TextView firm_name = helper.getView(R.id.item_name_tv);
            firm_name.setText(item.getEecCompany().getCompanyTitle());
        }
    }
    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
