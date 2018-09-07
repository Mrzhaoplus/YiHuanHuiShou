package com.example.mr.yihuanhuishou.driver.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverYijianActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.query_tv)
    TextView queryTv;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.title_lin)
    LinearLayout titleLin;
    private SharedPreferences sp;
    private int type;
    private SharedPreferences spp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_yijian);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        type = getIntent().getIntExtra("type", 0);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("问题留言");
        if (type==1) {
            titleLin.setVisibility(View.VISIBLE);
        }else if(type==2){
            titleLin.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.title_back_iv, R.id.query_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.query_tv:
                infoview();
                break;
        }
    }

    private void infoview() {
        String til = title.getText().toString().trim();
        String content = count.getText().toString().trim();
        if(type==1){
            if (!TextUtils.isEmpty(til) && !TextUtils.isEmpty(content)) {
                Map<String, Object> par = new HashMap<>();
                par.put("userId", sp.getInt(GGUtils.USER_id, 0));
                par.put("userType", "1");
                par.put("title", til);
                par.put("content", content);
                JSONObject jsonObject = new JSONObject(par);
                OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/resident/addFeedback")
                        .tag(this)
                        .upJson(jsonObject)
                        .execute(new DialogCallback<Zhece_Bean>(DriverYijianActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, "提交成功");
                                    finish();
                                } else if (code.equals("201")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("500")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("404")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("203")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("204")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                }
                            }
                        });
            } else if (TextUtils.isEmpty(til)) {
                ToastUtils.getToast(DriverYijianActivity.this, "请输入标题");
            } else if (TextUtils.isEmpty(content)) {
                ToastUtils.getToast(DriverYijianActivity.this, "请输入内容");
            }
        }else if(type==2){
            if (!TextUtils.isEmpty(content)) {
                Map<String, Object> par = new HashMap<>();
                par.put("userId", spp.getInt(GGUtils.DrUSER_id, 0));
                par.put("userType", "2");
                par.put("content", content);
                JSONObject jsonObject = new JSONObject(par);
                OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/resident/addFeedback")
                        .tag(this)
                        .upJson(jsonObject)
                        .execute(new DialogCallback<Zhece_Bean>(DriverYijianActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, "提交成功");
                                    finish();
                                } else if (code.equals("201")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("500")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("404")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("203")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                } else if (code.equals("204")) {
                                    ToastUtils.getToast(DriverYijianActivity.this, body.getMsg());
                                }
                            }
                        });
            } else if (TextUtils.isEmpty(content)) {
                ToastUtils.getToast(DriverYijianActivity.this, "请输入内容");
            }
        }



    }
}
