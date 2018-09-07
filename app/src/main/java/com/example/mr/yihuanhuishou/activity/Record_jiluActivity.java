package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Chongzhi_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.JiLu_Bean;
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

public class Record_jiluActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.jilu)
    TextView jilu;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
   List<JiLu_Bean.DataBean>mList=new ArrayList<>();
    private Chongzhi_Adapter chongzhi_adapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_jilu);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        initdata();
        infoview();
    }

    private void infoview() {
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("currencyType","1");
        params.put("type",type);
        OkGo.<JiLu_Bean>post(MyUrls.BASEURL + "/recyclers/info/transactionRecord")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<JiLu_Bean>(Record_jiluActivity.this, JiLu_Bean.class) {
                    @Override
                    public void onSuccess(Response<JiLu_Bean> response) {
                        JiLu_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<JiLu_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mList.addAll(data);
                            }
                            chongzhi_adapter.notifyDataSetChanged();

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Record_jiluActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Record_jiluActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Record_jiluActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Record_jiluActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Record_jiluActivity.this, body.getMsg());
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
        mrecycler.setLayoutManager(new LinearLayoutManager(this));
        mrecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        if(type.equals("0")){
            jilu.setText("充值记录");
        }else if(type.equals("1")){
            jilu.setText("支出记录");
        }
        chongzhi_adapter = new Chongzhi_Adapter(Record_jiluActivity.this,mList,type);
        mrecycler.setAdapter(chongzhi_adapter);


    }
}
