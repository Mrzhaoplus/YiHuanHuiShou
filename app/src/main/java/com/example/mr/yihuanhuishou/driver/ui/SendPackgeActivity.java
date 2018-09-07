package com.example.mr.yihuanhuishou.driver.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxf.zxing.view.SweepCodeActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Baozhuang_all_N_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.DataList;
import com.example.mr.yihuanhuishou.jsonbean.siji.Sendpackgr_Bean;
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
    @BindView(R.id.tiao_code)
    TextView tiaoCode;
    //Sendpackgr_Bean
    List<Sendpackgr_Bean> firstList = new ArrayList<>();
    private int type;
    private BroadcastReceiver bdr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (SweepCodeActivity.reString.equals(action)) {
                String resultString = intent.getStringExtra("resultString");
                if (type == 1) {
                    split = resultString.split(",");
                    bianmaTv.setText(split[0]);
                } else if (type == 2) {
                    String[] split = resultString.split(",");
                    String string = split[1].toString();
                    if(firstList.size()>0){
                        Boolean isAdd = false;
                        for(int i=0;i<firstList.size();i++){
                            if(split[0].toString().equals(firstList.get(i).getList().getName())){
                                firstList.get(i).getList().getNumber().add(string);
                                isAdd = true;
                            }
                        }
                        if (!isAdd){
                            List<String>list=new ArrayList<>();
                            list.add(string);
                            DataList dataList = new DataList(split[0].toString(), split[2].toString(), list);
                            firstList.add(new Sendpackgr_Bean(dataList));
                        }

                    }else{
                        List<String>list=new ArrayList<>();
                        list.add(string);
                        DataList dataList = new DataList(split[0].toString(), split[2].toString(), list);
                        firstList.add(new Sendpackgr_Bean(dataList));
                    }

                    initdata();

                }
            }
        }
    };
    private String cord_ba=null;
    private String[] split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_packge);
        ButterKnife.bind(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SweepCodeActivity.reString);
        registerReceiver(bdr, intentFilter);
        initView();
    }


    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("发放包装袋");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("确认发放");

    } private void initdata() {
        firstRecyclerView.setNestedScrollingEnabled(false);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirstAdapter firstAdapter = new FirstAdapter(R.layout.item_first_send_layout, firstList);
        firstRecyclerView.setAdapter(firstAdapter);

    }

    private class FirstAdapter extends BaseQuickAdapter<Sendpackgr_Bean, BaseViewHolder> {

        public FirstAdapter(int layoutResId, @Nullable List<Sendpackgr_Bean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Sendpackgr_Bean item) {
            TextView name = helper.getView(R.id.name);
            DataList list = item.getList();
            name.setText(list.getName());
            List<String> number = list.getNumber();
            RecyclerView secondRecycler = helper.getView(R.id.second_recyclerView);
            secondRecycler.setNestedScrollingEnabled(false);
            secondRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
            SecondAdapter secondAdapter = new SecondAdapter(R.layout.item_second_send_layout, number);
            secondRecycler.setAdapter(secondAdapter);
        }
    }

    private class SecondAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SecondAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView number = helper.getView(R.id.number);
            number.setText(item);
        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.saoma_iv, R.id.tiaoma_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                String sendStr = "";
                for (int i=0;i<firstList.size();i++){
                    DataList dataList = firstList.get(i).getList();
                    List<String> number = dataList.getNumber();
                    if (i != 0) {
                        sendStr = sendStr+";";
                    }
                    sendStr = sendStr+dataList.getSort();
                    Log.e("============",dataList.toString());
                    Log.e("============",number.toString());
                    for (int a=0;a<number.size();a++){
                        sendStr = sendStr+",";
                        sendStr = sendStr+number.get(a);
                       Log.e("============",number.get(a));
                    }
                }
                Log.e("============",sendStr);
                if(TextUtils.isEmpty(sendStr)){
                    ToastUtils.getToast(SendPackgeActivity.this,"请扫描包装后确认发放！");
                }else{
                    sendcord(sendStr);
                }
                break;
            case R.id.saoma_iv:
                type = 1;
                startActivity(new Intent(this, SweepCodeActivity.class));
                break;
            case R.id.tiaoma_iv:
                type = 2;
                startActivity(new Intent(this, SweepCodeActivity.class));
                break;
        }
    }

    private void sendcord(String sendsrc) {
        final SharedPreferences sp =getSharedPreferences(GGUtils.DrSP_NAME,MODE_PRIVATE);
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("codeStr", sendsrc);
        params3.put("recyclersId",split[1]);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/driverMine/grantBag")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Zhece_Bean>(SendPackgeActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(SendPackgeActivity.this,body.getMsg());
                            finish();
                        }else {
                            ToastUtils.getToast(SendPackgeActivity.this,body.getMsg());
                        }
                    }
                });


    }
}
