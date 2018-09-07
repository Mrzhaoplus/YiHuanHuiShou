package com.example.mr.yihuanhuishou.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Gonggao_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XitongMassage_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Noticol_DrtailActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.content)
    TextView content;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticol__drtail);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        initdata();
        infoview();
    }

    private void infoview() {
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN,""));
        params.put("noticeID",id);
        OkGo.<Gonggao_Details_Bean>post(MyUrls.BASEURL + "/recyclers/info/systemNoticeContent")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Gonggao_Details_Bean>(Noticol_DrtailActivity.this, Gonggao_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Gonggao_Details_Bean> response) {
                        Gonggao_Details_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            content.setText(body.getData().getContent());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Noticol_DrtailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Noticol_DrtailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Noticol_DrtailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Noticol_DrtailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Noticol_DrtailActivity.this, body.getMsg());
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
    }
}
