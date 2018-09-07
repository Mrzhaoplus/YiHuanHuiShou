package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Address_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Address_List_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Select_AddressActivity extends BaseActivity implements View.OnClickListener, Address_Adapter.getclick {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.tianjia)
    TextView tianjia;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
    List<Address_List_Bean.DataBean> list = new ArrayList<>();
    @BindView(R.id.spring_view)
    SpringView sp_view;
    private Address_Adapter address_adapter;
    private int state;
    private boolean flag = true;
    private boolean fffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__address);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        if (flag) {
            //注册
            EventBus.getDefault().register(this);
            flag = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
        infoview();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_fragment eveen) {
        int msgg = eveen.getMsg();
        if (msgg == 7) {
            //infodata();
        }
    }

    private void infoview() {
        list.clear();
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN, ""));
        OkGo.<Address_List_Bean>post(MyUrls.BASEURL + "/recyclers/address/list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Address_List_Bean>(Select_AddressActivity.this, Address_List_Bean.class) {
                    @Override
                    public void onSuccess(Response<Address_List_Bean> response) {
                        Address_List_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Address_List_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                              list.addAll(data);
                            }
                            address_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initdata() {
        beak.setOnClickListener(this);
        tianjia.setOnClickListener(this);
        //无下划线
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        address_adapter = new Address_Adapter(Select_AddressActivity.this, list, state);
        mrecycler.setAdapter(address_adapter);
        address_adapter.getjiekou(this);


        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        infoview();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.tianjia:
                startActivity(new Intent(Select_AddressActivity.this, New_AddressActivity.class));
                break;
        }
    }
    @Override
    public void click(int postion) {
        Address_List_Bean.DataBean dataBean = list.get(postion);
        Intent intent = new Intent();
        intent.putExtra("add_details", dataBean.getProvince() + dataBean.getCity() + dataBean.getCounry() + dataBean.getDetailAddr());
        intent.putExtra("id", list.get(postion).getId());
        intent.putExtra("long", list.get(postion).getLongitude() + "");
        intent.putExtra("la", list.get(postion).getLatitude() + "");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
