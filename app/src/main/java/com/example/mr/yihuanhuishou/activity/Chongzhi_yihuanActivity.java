package com.example.mr.yihuanhuishou.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Chongzhi_yihuanActivity extends BaseActivity {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.yihuanbi)
    TextView yihuanbi;
    @BindView(R.id.yihuanbi_ll)
    TextView yihuanbiLl;
    @BindView(R.id.zhifubao)
    CheckBox zhifubao;
    @BindView(R.id.weixin)
    CheckBox weixin;
    @BindView(R.id.chongzhi)
    Button chongzhi;
    private String type="1";
    private SharedPreferences sp;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wxf32e64cfe4bd433c";// 微信appid
    private boolean flag=true;
    private float yihuan_price;
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
                        Toast.makeText(Chongzhi_yihuanActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putFloat(GGUtils.YIHUAN,yihuan_price+Float.parseFloat(yihuanbiLl.getText().toString()));
                        edit.commit();
                        finish();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(Chongzhi_yihuanActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Chongzhi_yihuanActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_chongzhi_yihuan);
        ButterKnife.bind(this);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID,true);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        yihuan_price = sp.getFloat(GGUtils.YIHUAN, 0);
        initdata();
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag =false;
        }

    }

    private void initdata() {
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String string = editable.toString();

                if(!TextUtils.isEmpty(string)){
                    if(string.length()<9){
                        int i = Integer.parseInt(string);
                        yihuanbiLl.setText((i*10)+"");
                    }else{
                        ToastUtils.getToast(Chongzhi_yihuanActivity.this,"输入金额过大");
                    }
                }else{
                    price.setText("0");
                    yihuanbiLl.setText("0");
                }


            }
        });
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
      chongzhi.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(type.equals("0")){
                  String qian = price.getText().toString().trim();
                  int money = Integer.parseInt(qian);
                  String string = yihuanbiLl.getText().toString();
                  int yihuan = Integer.parseInt(string);
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
                  //获取当前时间
                  Date date = new Date(System.currentTimeMillis());
                  String format = simpleDateFormat.format(date);
                  HttpParams params = new HttpParams();
                  params.put("token",sp.getString(GGUtils.TOKEN,""));
                  params.put("amount",yihuan);
                  params.put("body","易换币充值");
                  params.put("outTradeNo",format);
                  params.put("totalFee",(money*100));
                  params.put("ip", API.getLocalInetAddress().toString().substring(1));
                  // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
                  OkGo.<WX_zhifu_Bean>post(MyUrls.BASEURL + "/weChatPay/currencyGetPayParams")
                          .tag(this)
                          .params(params)
                          .execute(new DialogCallback<WX_zhifu_Bean>(Chongzhi_yihuanActivity.this, WX_zhifu_Bean.class) {
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
                                  } else if (code.equals("201")) {
                                      ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                  } else if (code.equals("500")) {
                                      ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                  } else if (code.equals("404")) {
                                      ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                  } else if (code.equals("203")) {
                                      ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                  } else if (code.equals("204")) {
                                      ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                  }

                              }
                              @Override
                              public void onError(Response<WX_zhifu_Bean> response) {
                                  super.onError(response);
                              }
                          });
              }else if(type.equals("1")){
                  String qian = price.getText().toString().trim();
                  int money = Integer.parseInt(qian);
                  if(money>0){
                      String string = yihuanbiLl.getText().toString();
                      int yihuan = Integer.parseInt(string);
                      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
                      //获取当前时间
                      Date date = new Date(System.currentTimeMillis());
                      String format = simpleDateFormat.format(date);
                      HttpParams params = new HttpParams();
                      params.put("body","易换币的购买");
                      params.put("outTradeNo",format);
                      params.put("totalAmount",money);
                      params.put("token",sp.getString(GGUtils.TOKEN,""));
                      params.put("amount",yihuan);
                      params.put("subject", "易换币购买");
                      // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
                      OkGo.<ZFB_zhifu_Bean>post(MyUrls.BASEURL + "/alipay/currencyPay")
                              .tag(this)
                              .params(params)
                              .execute(new DialogCallback<ZFB_zhifu_Bean>(Chongzhi_yihuanActivity.this, ZFB_zhifu_Bean.class) {
                                  @Override
                                  public void onSuccess(Response<ZFB_zhifu_Bean> response) {
                                      final ZFB_zhifu_Bean body = response.body();
                                      String code = body.getCode() + "";
                                      if (code.equals("200")) {
                                          Runnable payRunnable = new Runnable() {
                                              @Override
                                              public void run() {
                                                  PayTask alipay = new PayTask(Chongzhi_yihuanActivity.this);
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
                                          ToastUtils.getToast(Chongzhi_yihuanActivity.this, body.getMsg());
                                      }
                                  }
                                  @Override
                                  public void onError(Response<ZFB_zhifu_Bean> response) {
                                      super.onError(response);
                                  }
                              });
                  }else{
                      ToastUtils.getToast(Chongzhi_yihuanActivity.this,"请输入大于0的数字金额");
                  }
              }else if(TextUtils.isEmpty(type)){
                  ToastUtils.getToast(Chongzhi_yihuanActivity.this,"请选择支付方式");
              }
          }
      });
        weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean wei) {
                if (wei) {
                    zhifubao.setChecked(false);
                    type="0";
                }else{
                    type="";
                }
            }
        });
        zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean zhi) {
                if (zhi) {
                    weixin.setChecked(false);
                    type="1";
                }else{
                    type="";
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){
        int msg = eveen.getMsg();
        if(msg==1){
            SharedPreferences.Editor edit = sp.edit();
            edit.putFloat(GGUtils.YIHUAN,yihuan_price+Float.parseFloat(yihuanbiLl.getText().toString()));
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
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }


}
