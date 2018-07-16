package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Bill_Adapter;
import com.example.mr.yihuanhuishou.jsonbean.Fujin_Order_Bean;
import com.example.mr.yihuanhuishou.jsonbean.Login_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Bill_DetailActivity extends AppCompatActivity {
      @BindView(R.id.beak)
      ImageView beak;
      @BindView(R.id.sp_view)
       SpringView sp_view;
      @BindView(R.id.recy_view)
      RecyclerView recy_view;
    private String lotitude;
    private String longitude;
   List<Fujin_Order_Bean.DataBean>mlist=new ArrayList<>();
   private int pageCount=10;
    private Bill_Adapter bill_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__detail);
        ButterKnife.bind(this);
        longitude = getIntent().getStringExtra("longitude");
        lotitude = getIntent().getStringExtra("lotitude");
        initview();
        infoview();
    }

    private void infoview() {
        mlist.clear();
        HttpParams params = new HttpParams();
        params.put("longitude",longitude);
        params.put("latitude",lotitude);
        params.put("pageCount",pageCount);
        OkGo.<Fujin_Order_Bean>post(MyUrls.BASEURL + "/webSocketBusiness/getNearbyWasteOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Fujin_Order_Bean>(Bill_DetailActivity.this, Fujin_Order_Bean.class) {
                    @Override
                    public void onSuccess(Response<Fujin_Order_Bean> response) {
                        Fujin_Order_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Fujin_Order_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                            bill_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Bill_DetailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Bill_DetailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Bill_DetailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Bill_DetailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Bill_DetailActivity.this, body.getMsg());
                        }

                    }
                });

    }

    private void initview() {
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(Bill_DetailActivity.this));
        recy_view.addItemDecoration(new DividerItemDecoration(Bill_DetailActivity.this, DividerItemDecoration.VERTICAL_LIST));
        bill_adapter = new Bill_Adapter(this,mlist);
        recy_view.setAdapter(bill_adapter);
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageCount=10;
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
                        pageCount=pageCount+10;
                        infoview();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
    }
}
