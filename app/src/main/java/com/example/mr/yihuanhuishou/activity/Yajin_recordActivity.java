package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Yajin_record_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Yajin_Record_Bean;
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

public class Yajin_recordActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
    List<Yajin_Record_Bean.DataBean>list=new ArrayList<>();
    private SharedPreferences sp;
    private Yajin_record_Adapter yajin_record_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yajin_record);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initdata();
        infoview();
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        OkGo.<Yajin_Record_Bean>post(MyUrls.BASEURL + "/recyclers/info/depositRecord")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Yajin_Record_Bean>(Yajin_recordActivity.this, Yajin_Record_Bean.class) {
                    @Override
                    public void onSuccess(Response<Yajin_Record_Bean> response) {
                        Yajin_Record_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Yajin_Record_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                            }
                            yajin_record_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Yajin_recordActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Yajin_recordActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Yajin_recordActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Yajin_recordActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Yajin_recordActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initdata() {
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(Yajin_recordActivity.this));
        mrecycler.addItemDecoration(new DividerItemDecoration(Yajin_recordActivity.this, DividerItemDecoration.VERTICAL_LIST));
        yajin_record_adapter = new Yajin_record_Adapter(Yajin_recordActivity.this,list);
        mrecycler.setAdapter(yajin_record_adapter);
    }
}
