package com.example.mr.yihuanhuishou.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.PayResult;
import com.example.mr.yihuanhuishou.jsonbean.WX_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.ZFB_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.API;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pay_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.duihuan)
    TextView duihuan;
    @BindView(R.id.zhifubao)
    CheckBox zhifubao;
    @BindView(R.id.weixin)
    CheckBox weixin;
    @BindView(R.id.xianxia)
    CheckBox xianxia;
    @BindView(R.id.zhifu)
    Button zhifu;
    @BindView(R.id.beak)
    ImageView beak;
    private int ids;
    private float yihuan;
    private  String type="1";
    private double money;
    private String dingdan;
    private boolean flag=true;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wxf32e64cfe4bd433c";// 微信appid
    private SharedPreferences sp;
    private static final int SDK_PAY_FLAG = 1;//支付宝

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.e("======================",resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(Pay_DetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putFloat(GGUtils.YIHUAN,sp.getFloat(GGUtils.YIHUAN,0)-yihuan);
                        edit.commit();
                        finish();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(Pay_DetailsActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Pay_DetailsActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__details);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initview();
    }
    private void initview() {
        money = getIntent().getDoubleExtra("money", 0);
        ids = getIntent().getIntExtra("ids", 0);
        yihuan = getIntent().getFloatExtra("yihuan", 0);
        dingdan = getIntent().getStringExtra("dingdan");
        if(money==0){
              xianxia.setChecked(true);
              zhifubao.setChecked(false);
              weixin.setChecked(false);
              type="3";
        }
        price.setText(money +"元");
        duihuan.setText(yihuan+"");
        zhifu.setOnClickListener(this);
        beak.setOnClickListener(this);
        weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean wei) {
                if(wei){
                    if(money==0){
                        weixin.setChecked(false);
                    }else{
                        zhifubao.setChecked(false);
                        xianxia.setChecked(false);
                        type="2";
                    }
                }else{
                    type="";
                }
            }
        });
        zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean zhi) {
                if(zhi){
                    if(money==0){
                        zhifubao.setChecked(false);
                    }else{
                        weixin.setChecked(false);
                        xianxia.setChecked(false);
                        type="1";
                    }
                }else{
                    type="";
                }
            }
        });
        xianxia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean xian) {
                if(xian){
                    zhifubao.setChecked(false);
                    weixin.setChecked(false);
                    type="3";
                }else{
                    type="";
                }
            }
        });
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag =false;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){
        int msg = eveen.getMsg();
        if(msg==1){
            SharedPreferences.Editor edit = sp.edit();
            edit.putFloat(GGUtils.YIHUAN,sp.getFloat(GGUtils.YIHUAN,0)-yihuan);
            edit.commit();
            finish();
        }else if(msg==2){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg,"支付失败"));
            builder.show();
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhifu:
              infoview();
                Log.e("================", API.getLocalInetAddress().toString().substring(1)+",,,,,"+dingdan);
                break;
            case R.id.beak:
                   finish();
                break;
        }
    }

    private void infoview() {
       //微信支付
       if(type.equals("2")){
           HttpParams params = new HttpParams();
           params.put("token",sp.getString(GGUtils.TOKEN,""));
           params.put("body","订单支付");
           params.put("outTradeNo",dingdan);
          // params.put("totalFee",(money*100));
           params.put("totalFee",1);
           params.put("ip",API.getLocalInetAddress().toString().substring(1));
           // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
           OkGo.<WX_zhifu_Bean>post(MyUrls.BASEURL + "/weChatPay/recyclersOrderGetPayParams")
                   .tag(this)
                   .params(params)
                   .execute(new DialogCallback<WX_zhifu_Bean>(Pay_DetailsActivity.this, WX_zhifu_Bean.class) {
                       @Override
                       public void onSuccess(Response<WX_zhifu_Bean> response) {
                           WX_zhifu_Bean body = response.body();
                           String code = body.getCode() + "";
                           if (code.equals("200")) {
                               Log.e("=====================","唤醒");
                               WX_zhifu_Bean.DatasBean datas = body.getDatas();
                               PayReq req = new PayReq();
                               req.appId=datas.getAppid();// 微信开放平台审核通过的应用APPID
                               req.partnerId = datas.getPartnerid();// 微信支付分配的商户号
                               req.prepayId = datas.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                               req.nonceStr = datas.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
                               req.timeStamp = datas.getTimestamp() + "";// 时间戳，app服务器小哥给出
                               req.packageValue = datas.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                               req.sign = datas.getSign();// 签名，服务器小哥给出
                                 req.extData = "app data"; // optional
                               // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                               api.sendReq(req);//调起支付
                           } else {
                               ToastUtils.getToast(Pay_DetailsActivity.this, body.getMsg());
                           }

                       }
                       @Override
                       public void onError(Response<WX_zhifu_Bean> response) {
                           super.onError(response);
                       }
                   });



       }else if(type.equals("1")){
           HttpParams params = new HttpParams();
           params.put("body","订单的支付");
           params.put("outTradeNo",dingdan);
         //  params.put("totalAmount",money);
           params.put("totalAmount",0.01);
           params.put("subject", "订单支付");
           params.put("token",sp.getString(GGUtils.TOKEN,""));
           // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
           OkGo.<ZFB_zhifu_Bean>post(MyUrls.BASEURL + "/alipay/wasteOrderPay")
                   .tag(this)
                   .params(params)
                   .execute(new DialogCallback<ZFB_zhifu_Bean>(Pay_DetailsActivity.this, ZFB_zhifu_Bean.class) {
                       @Override
                       public void onSuccess(Response<ZFB_zhifu_Bean> response) {
                           final ZFB_zhifu_Bean body = response.body();
                           String code = body.getCode() + "";
                           if (code.equals("200")) {
                               Runnable payRunnable = new Runnable() {
                                   @Override
                                   public void run() {
                                       PayTask alipay = new PayTask(Pay_DetailsActivity.this);
                                       Map<String, String> result = alipay.payV2(body.getDatas(), true);//调用支付接口，获取支付结果

                                       Message msg = new Message();
                                       msg.what = SDK_PAY_FLAG;
                                       msg.obj = result;
                                       mHandler.sendMessage(msg);
                                   }
                               };
                               // 必须异步调用，支付或者授权的行为需要在独立的非ui线程中执行
                               Thread payThread = new Thread(payRunnable);
                               payThread.start();
                           } else {
                               ToastUtils.getToast(Pay_DetailsActivity.this, body.getMsg());
                           }
                       }
                       @Override
                       public void onError(Response<ZFB_zhifu_Bean> response) {
                           super.onError(response);
                       }
                   });
       }else if(type.equals("3")){
           HttpParams params = new HttpParams();
           params.put("token",sp.getString(GGUtils.TOKEN,""));
           params.put("id",ids);
           // http://172.168.20.73:8080/EEC/weChatPay/getPayParams

           OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/recyclers/order/payment")
                   .tag(this)
                   .params(params)
                   .execute(new DialogCallback<Jiaoyan_Bean>(Pay_DetailsActivity.this, Jiaoyan_Bean.class) {
                       @Override
                       public void onSuccess(Response<Jiaoyan_Bean> response) {
                           Jiaoyan_Bean body = response.body();
                           String code = body.getCode() + "";
                           if (code.equals("200")) {
                               ToastUtils.getToast(Pay_DetailsActivity.this, body.getMsg());
                               SharedPreferences.Editor edit = sp.edit();
                               edit.putFloat(GGUtils.YIHUAN,sp.getFloat(GGUtils.YIHUAN,0)-yihuan);
                               edit.commit();
                               finish();
                           } else {
                               ToastUtils.getToast(Pay_DetailsActivity.this, body.getMsg());
                           }
                       }
                       @Override
                       public void onError(Response<Jiaoyan_Bean> response) {
                           super.onError(response);
                       }
                   });
       }else if(TextUtils.isEmpty(type)){
           ToastUtils.getToast(Pay_DetailsActivity.this, "请选择支付方式");
       }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
