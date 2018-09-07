package com.example.mr.yihuanhuishou.driver.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.ChongzhiActivity;
import com.example.mr.yihuanhuishou.activity.HomePersonInfoActivity;
import com.example.mr.yihuanhuishou.adapter.Back_card_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.AuthResult;
import com.example.mr.yihuanhuishou.bean.OrderInfoUtil2_0;
import com.example.mr.yihuanhuishou.bean.PayResult;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Back_card_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XiuGai_Bean;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TixianManagerActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.ali_isbind_tv)
    TextView aliIsbindTv;
    @BindView(R.id.ali_rl)
    RelativeLayout aliRl;
    @BindView(R.id.wechat_isbind_tv)
    TextView wechatIsbindTv;
    @BindView(R.id.bind_tv)
    TextView bindTv;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    List<Back_card_Bean.DataBean>list=new ArrayList<>();
    private Back_card_Adapter back_card_adapter;
    private SharedPreferences sp;
    private SharedPreferences spp;
    private BaseDialog dialog;
    //支付宝支付业务：入参app_id
    private static final int SDK_AUTH_FLAG = 2;
    public static final String APPID = "2018080160825868";
    //支付宝账户登录授权业务：入参pid值
    public static final String PID = "2088131457944730";
    //私钥
    public static final String RSA2_PRIVATE = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQC+ugS39ZmlgDWqYqY3ddu16JxHSwoeYSdrDmYlFMgEfL43iougC9GisCAJvSw+yHTDTXLlBZmCMYVdTeo6kUZZ2lYzKrJS4y36fwlf9eCjG8vLTycl/YiDn/QtlObAv5PTv6MK7XaD0V5MXjQ9ip5mLNrtayAQDkoHPpPUd1AQ4RHf/1r8lit44wBrG1tcXgSLfTwc0bQDBYgAKiWzfqUA61MTiz0sUewEKqPLw19nFzBcPUTySIJpcr1M9Uz2KoESmcTKLefFAcPW4lfSfQyWhPXFaDRcxRAeknbtklSFQ4KdPot9Pfw9M7e2dxbcG3XYhhjvcIji/aDPHlTWRiBrAgMBAAECggEBAIXyCiJPv+o1Unso8Ob+RKFKjYacMq0HOjG7WQ0tvRM5sDU9fbWW11FUNAvi6VHmYepPWCNzfU2sDadrctZS9H9iESu6DqabmczUrvbPrUWf3c4Y9rt9hQQ7r7bbXft8EAukCJNCFfuZbIphlnqqAXhshAw9MbVU87SGxDdw27Adx8EI2Jh9HhUUM5HGA1ahXWS8/qqoQDQK5j9fI2JBqvVRalVTB/2FQ0eaiysdCRwHaZ6xtP8EAcfujPYm4ZoMtohdaTfajYL/rTq2s76kKPceptu8plyP51b5iyLh5X6dzNrDE8Q7ChPJ4ZtciYGTZxP6ApG7CCAupPh2g9g5/PECgYEA/Qao5lSPVKdSmUsGWY5xbmo7sT1doKMpSpzxweTVTnPglb1Tv47yi46IiERMb049PZB3yhsgFZUM586+9tdYeZF3D6/pCNjCnfAeRa7fIh0o3ukOhoV3bDMW2YLiA3ulYnb29ZDOtHh+IalmLXXBJ6HcxC8xVZ2PJPpFzUHZB7UCgYEAwPfnTiX0IuJqY1UGMdLhsh4gw06+0z37LKfFS5UOBvDpl3Gee1p8up2rhoA4vdUO0s1mNuEH07HsFhkgxJ9bstJJWuPOdTC4ZmWl92OjCQOLx1UBa2agFPg8w0rHaH7b5qJynz0DdqE8Eb6g/3sJ4qsxJIpUkhB/pFExxAvpW58CgYEA1GIcr4lW7fWszWM+Jzn7WHTvOeyKhGWwKSLngprzuPBnGQlcHLR+PkobrFW32s3Pdben9QsjuTntJT5S6JsFdrV1PMOpVXWslimRH2iTfuz++2ygdqXdAgM4MMc9szK8NZQp4yHzf2SMavDoBUyMHxosVrhOLtEb8CvrH0kgROkCgYEAuY6pUjCU7uaiPxqHdmm+tjTadHTIcwFrAqnhwqh2cJ5BQWv0ZdHBlwH+6rtp1MGIG5V/8M27ZQn2Z9mJ9Qj4eKBWAZyF75MF/xFH4vhg0k4in29SiiCJVyDN7U1/KzCx7LnRVuao6e5pgPa6gnGiM+FCxExwvnQ3CZ86J7b8kykCgYEAgRGeu/q2vbeQ65Gmsp3UN9XM5hX61U2bL3jq+X80W7BF2VKBTeP8+/TYybpaCvmT29mQIJ91E0y+GQx1ScgNnaPQMZhlrUhJ++GNQBhwlCD0hoHZ3kumOi91WE9tVw0WOBZRDoiIPWajmuOBZ6VS1DJy2fYRDeqvT6jLUa600eY=";
    public static final String RSA_PRIVATE ="";
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        String result = authResult.getResult();
                        String[] split = result.split("=");

                        zhifubao(split[7].toString());
                    } else {

                        ToastUtils.getToast(TixianManagerActivity.this,"授权失败请重试");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private int state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_manager);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
    }
    private void infoview() {
        if(state==1){
            HttpParams params = new HttpParams();
            params.put("userId",sp.getInt(GGUtils.USER_id,0));
            params.put("userType","1");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(TixianManagerActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                back_card_adapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            }
                        }
                    });
        }else if(state==2){
            HttpParams params = new HttpParams();
            params.put("userId",spp.getInt(GGUtils.DrUSER_id,0));
            params.put("userType","2");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(TixianManagerActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                back_card_adapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            }
                        }
                    });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        infoview();
        if(state==1){
            sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
            String zhifu = sp.getString(GGUtils.ZHIFUBAO, "");
            String weixin = sp.getString(GGUtils.WEIXIN, "");
            if(TextUtils.isEmpty(zhifu)){
                aliIsbindTv.setText("未绑定");
                aliIsbindTv.setTextColor(getResources().getColor(R.color.red1));
            }else{
                aliIsbindTv.setText("已绑定");
                aliIsbindTv.setTextColor(getResources().getColor(R.color.black));
            }
            if(TextUtils.isEmpty(weixin)){
                wechatIsbindTv.setText("已绑定");
                wechatIsbindTv.setTextColor(getResources().getColor(R.color.red1));
            }else{
                wechatIsbindTv.setText(weixin);
                wechatIsbindTv.setTextColor(getResources().getColor(R.color.black));
            }
            //司机端进入判断绑定支付宝
        }else if(state==2){
            spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
            String zhifu = spp.getString(GGUtils.DrZHIFUBAO, "");
            if(TextUtils.isEmpty(zhifu)){
                aliIsbindTv.setText("未绑定");
                aliIsbindTv.setTextColor(getResources().getColor(R.color.red1));
            }else{
                aliIsbindTv.setText("已绑定");
                aliIsbindTv.setTextColor(getResources().getColor(R.color.black));
            }
        }

    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("提现账户管理");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("解绑银行卡");
        //无下划线
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        back_card_adapter = new Back_card_Adapter(TixianManagerActivity.this,list);
        recyView.setAdapter(back_card_adapter);
    }
    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.ali_rl, R.id.wechat_rl, R.id.bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                Intent intent1 = new Intent(this, UnBindCardActivity.class);
                intent1.putExtra("state",state);
                startActivity(intent1);
                break;
            case R.id.ali_rl:
                if(state==1){
                    String zhifu = sp.getString(GGUtils.ZHIFUBAO, "");
                    if(TextUtils.isEmpty(zhifu)){
                        ZFshouquan();
                    }else{
                        ToastUtils.getToast(TixianManagerActivity.this,"已绑定");
                    }

                }else if(state==2){
                    String zhifu = spp.getString(GGUtils.DrZHIFUBAO, "");
                    if(TextUtils.isEmpty(zhifu)){
                        ZFshouquan();
                    }else{
                       jiebang();
                    }
                }
                break;
            case R.id.wechat_rl:
                ToastUtils.getToast(TixianManagerActivity.this,"暂无微信绑定");
                break;
            case R.id.bind_tv:
                Intent intent = new Intent(this, BindCardActivity.class);
                intent.putExtra("state",state);
                startActivity(intent);
                break;
        }
    }

    private void jiebang() {
        HttpParams params2 = new HttpParams();
        params2.put("token",spp.getString(GGUtils.DrTOKEN,""));
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/driver/residentOrder/driverboundOrunbind")
                .tag(this)
                .params(params2)
                .execute(new DialogCallback<Zhece_Bean>(TixianManagerActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = spp.edit();
                            edit.putString(GGUtils.DrZHIFUBAO,"");
                            edit.commit();
                            ToastUtils.getToast(TixianManagerActivity.this,"解绑成功");
                            aliIsbindTv.setText("未绑定");
                            aliIsbindTv.setTextColor(getResources().getColor(R.color.red1));
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        }
                    }
                });

    }

    //点击授权
    private void ZFshouquan() {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) )) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID,"", rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(TixianManagerActivity.this);
                // 调用授权接口，获取授权结果

                Map<String, String> result = authTask.authV2(authInfo, true);
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }
    private void weixin(String trim) {
        HttpParams params2 = new HttpParams();
        params2.put("token",sp.getString(GGUtils.TOKEN,""));
        params2.put("updCode","weChat");
        params2.put("content",trim);
        OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                .tag(this)
                .params(params2)
                .execute(new DialogCallback<XiuGai_Bean>(TixianManagerActivity.this, XiuGai_Bean.class) {
                    @Override
                    public void onSuccess(Response<XiuGai_Bean> response) {
                        XiuGai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.WEIXIN, body.getData().getWechat());
                            edit.commit();
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            dialog.dismiss();
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void zhifubao(final String trim) {
        if(state==1){
            HttpParams params2 = new HttpParams();
            params2.put("token",sp.getString(GGUtils.TOKEN,""));
            params2.put("updCode","aliPay");
            params2.put("content",trim);
            OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                    .tag(this)
                    .params(params2)
                    .execute(new DialogCallback<XiuGai_Bean>(TixianManagerActivity.this, XiuGai_Bean.class) {
                        @Override
                        public void onSuccess(Response<XiuGai_Bean> response) {
                            XiuGai_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.ZHIFUBAO, body.getData().getAliPay());
                                edit.commit();
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                                finish();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            }
                        }
                    });
        }else if(state==2){
            HttpParams params2 = new HttpParams();
            params2.put("token",spp.getString(GGUtils.DrTOKEN,""));
            params2.put("payid",trim);
            OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/driver/residentOrder/driverboundOrunbind")
                    .tag(this)
                    .params(params2)
                    .execute(new DialogCallback<Zhece_Bean>(TixianManagerActivity.this, Zhece_Bean.class) {
                        @Override
                        public void onSuccess(Response<Zhece_Bean> response) {
                            Zhece_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = spp.edit();
                                edit.putString(GGUtils.DrZHIFUBAO,trim);
                                edit.commit();
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                                finish();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianManagerActivity.this, body.getMsg());
                            }
                        }
                    });

        }


    }


}
