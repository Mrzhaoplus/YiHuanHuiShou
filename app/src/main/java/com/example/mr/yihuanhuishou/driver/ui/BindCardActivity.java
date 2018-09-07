package com.example.mr.yihuanhuishou.driver.ui;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
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

public class BindCardActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.card_name_et)
    EditText cardNameEt;
    @BindView(R.id.card_num_et)
    EditText cardNumEt;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_rl)
    RelativeLayout bankRl;
    @BindView(R.id.bind_tv)
    TextView bindTv;
    private TextView quxiao;
    private TextView queding;
    private WheelView wheelView;
    private int index1;
    private String list_itme;
    private SharedPreferences sp;
    private SharedPreferences spp;
    private String bank;
    private long mill;
    private Handler handler = new Handler();
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
    }
    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            indataview(cardNumEt.getText().toString().trim());
        }
    };

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("添加银行卡");

        cardNumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mill = System.currentTimeMillis();
                if (delayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }
                if (!TextUtils.isEmpty(cardNumEt.getText())&&editable.toString().length()>15&&editable.toString().length()<20) {
                        handler.postDelayed(delayRun, 1500);
                }

            }
        });
    }

    @OnClick({R.id.title_back_iv, R.id.bank_rl, R.id.bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.bank_rl:
               // showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.bind_tv:
              infoview();
                break;

        }
    }

    private void indataview(String edit) {
        HttpParams params = new HttpParams();
        params.put("card",edit);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/resident/verifyBank")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(BindCardActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            if(TextUtils.isEmpty(body.getTitle())){
                                bankTv.setText("银行卡格式错误");
                            }else{
                                bankTv.setText(body.getTitle());
                            }
                            bank = body.getData().getBank();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                        }
                    }
                });

    }

    private void infoview() {
        String nameed= cardNameEt.getText().toString().trim();
        String numed = cardNumEt.getText().toString().trim();
        if(TextUtils.isEmpty(nameed)){
            ToastUtils.getToast(this,"请输入持卡人姓名");
        }else if(TextUtils.isEmpty(numed)){
            ToastUtils.getToast(this,"请输入银行卡号");
        }else{
            if(state==1){
                HttpParams params = new HttpParams();
                params.put("userId",sp.getInt(GGUtils.USER_id,0));
                params.put("userType","1");
                params.put("cardNumber",numed);
                params.put("name",nameed);
                params.put("code",bank);
                OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/resident/addBankCard")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Zhece_Bean>(BindCardActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                    finish();
                                } else if (code.equals("201")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("500")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("404")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("203")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("204")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                }
                            }
                        });
            }else if(state==2){
                HttpParams params = new HttpParams();
                params.put("userId",spp.getInt(GGUtils.DrUSER_id,0));
                params.put("userType","2");
                params.put("cardNumber",numed);
                params.put("name",nameed);
                params.put("code",bank);
                OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/resident/addBankCard")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Zhece_Bean>(BindCardActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                    finish();
                                } else if (code.equals("201")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("500")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("404")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("203")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                } else if (code.equals("204")) {
                                    ToastUtils.getToast(BindCardActivity.this, body.getMsg());
                                }
                            }
                        });
            }

        }
    }
}
