package com.example.mr.yihuanhuishou.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.PayResult;
import com.example.mr.yihuanhuishou.driver.ui.ChatActivity;
import com.example.mr.yihuanhuishou.driver.ui.HuiShouCompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.weight.CommonPopupWindow;
import com.example.mr.yihuanhuishou.jsonbean.WX_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.ZFB_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Danwei_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Leibie_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.API;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.SpUtils;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Driver_DZF_DetailsActivity extends BaseActivity {
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.title_more_iv)
    ImageView titleMoreIv;
    @BindView(R.id.title_more_bj)
    ImageView titleMoreBj;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.ddbh_tv)
    TextView ddbhTv;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.bzdtm_tv)
    TextView bzdtmTv;
    @BindView(R.id.fplx_tv)
    TextView fplxTv;
    @BindView(R.id.fpsl_tv)
    TextView fpslTv;
    @BindView(R.id.fpzj_tv)
    TextView fpzjTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.jdsj_tv)
    TextView jdsjTv;
    @BindView(R.id.jdsj_ll)
    LinearLayout jdsjLl;
    @BindView(R.id.shsj_tv)
    TextView shsjTv;
    @BindView(R.id.qhsj_ll)
    LinearLayout qhsjLl;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.left_tv)
    TextView leftTv;
    private String state;
    private int id;
    private BaseDialog.Builder mBuilder;
    private BaseDialog mDialog;

    private View shumuRl;
    private TextView leibieTv;
    private EditText shumuEt;
    private EditText zongjiaEt;
    private LinearLayout leibieLl;
    private TextView shumuTv;
    private List<Driver_Leibie_Bean.DataBean> list = new ArrayList<>();
    private List<Danwei_Bean.DataBean> zlist = new ArrayList<>();
    private CommonPopupWindow popupWindow;
    private int init_id;
    private SharedPreferences sp;
    private String orderNumber;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wxf32e64cfe4bd433c";// 微信appid
    private boolean flag=true;
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
                        Toast.makeText(Driver_DZF_DetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(Driver_DZF_DetailsActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Driver_DZF_DetailsActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private String phoneNumber;
    private String imUser;
    private BaseDialog dialog;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__dzf__details);
        ButterKnife.bind(this);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, true);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        state = getIntent().getStringExtra("state");
        id = getIntent().getIntExtra("id", 0);
        infoview();
        initView();

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
            finish();
        }else if(msg==2){
            AlertDialog.Builder builder = new AlertDialog.Builder(Driver_DZF_DetailsActivity.this);
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg,"支付失败"));
            builder.show();
        }
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("id",id);
        params.put("latitude",sp.getString(GGUtils.Drlat,""));
        params.put("longitude",sp.getString(GGUtils.Drlon,""));
        if(TextUtils.isEmpty(sp.getString(GGUtils.Drlat,""))){
            params.put("state",0);
        }else{
            params.put("state",1);
        }
        OkGo.<Order_Details_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/orderInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Details_Bean>(Driver_DZF_DetailsActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.DataBean data = body.getData();

                            orderNumber = data.getOrderNumber();
                            imUser = data.getImUser();
                            phoneNumber = data.getEecRecyclersaddr().getPhoneNumber();
                            ddbhTv.setText(orderNumber);
                            bzdtmTv.setText(data.getBarCode());
                            fplxTv.setText(data.getVarieties());
                            fpslTv.setText(data.getCount()+""+data.getUnit());
                            totalPrice = data.getTotalPrice();
                            fpzjTv.setText(totalPrice +"元");
                            nameTv.setText(data.getEecRecyclersaddr().getName());
                            addressTv.setText(data.getEecRecyclersaddr().getDetailAddr());
                            Double i = (double)data.getDistance() / 1000;
                            String s = StringToDouble(i);
                            juliTv.setText(s+"公里");
                            long receiptDate = data.getReceiptDate();
                            String dateToString = getDateToString(String.valueOf(receiptDate / 1000));
                            jdsjTv.setText(dateToString);
                            shsjTv.setText(dateToString);
                        } else {
                            ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }


    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("订单详情");
    }

    @OnClick({R.id.title_back_iv, R.id.right_tv, R.id.left_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.right_tv:
                showPayDialog();
                break;
            case R.id.left_tv:
                showTelDialog();
                break;
        }
    }

    private void showPayDialog() {
        mBuilder = new BaseDialog.Builder(Driver_DZF_DetailsActivity.this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();


        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mDialog.getView(R.id.self_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                showPaySelctorDialog();
            }
        });


        mDialog.getView(R.id.company_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                HttpParams params = new HttpParams();
                params.put("token",sp.getString(GGUtils.DrTOKEN,""));
                params.put("ip", API.getLocalInetAddress().toString().substring(1));
                params.put("orderId",id);
                params.put("type",0);
                OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Jiaoyan_Bean>(Driver_DZF_DetailsActivity.this, Jiaoyan_Bean.class) {
                            @Override
                            public void onSuccess(Response<Jiaoyan_Bean> response) {
                                Jiaoyan_Bean body = response.body();
                                String code = body.getCode() + "";
                                if (code.equals("200")) {
                                    ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                                    showPayFinishDialog("company",true);
                                } else {
                                    ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                                }
                            }
                            @Override
                            public void onError(Response<Jiaoyan_Bean> response) {
                                super.onError(response);
                            }
                        });
            }
        });
    }

    private void showPaySelctorDialog() {
        mBuilder = new BaseDialog.Builder(Driver_DZF_DetailsActivity.this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay_selector)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.balance_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                HttpParams params = new HttpParams();
                params.put("token",sp.getString(GGUtils.DrTOKEN,""));
                params.put("ip", API.getLocalInetAddress().toString().substring(1));
                params.put("orderId",id);
                params.put("type",2);
                OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Jiaoyan_Bean>(Driver_DZF_DetailsActivity.this, Jiaoyan_Bean.class) {
                            @Override
                            public void onSuccess(Response<Jiaoyan_Bean> response) {
                                Jiaoyan_Bean body = response.body();
                                String code = body.getCode() + "";
                                if (code.equals("200")) {
                                    ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                                    showPayFinishDialog("balanbce",true);
                                    float v = Float.parseFloat(totalPrice + "");
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putFloat(GGUtils.DrXIANJIN,(sp.getFloat(GGUtils.DrXIANJIN,0)-v));
                                    edit.commit();
                                } else {
                                    showPayFinishDialog("balanbce",false);
                                    ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                                }
                            }
                            @Override
                            public void onError(Response<Jiaoyan_Bean> response) {
                                super.onError(response);
                            }
                        });
            }
        });
        mDialog.getView(R.id.ali_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zhifubao();
            }
        });
        mDialog.getView(R.id.wechat_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weixinthpas();
            }
        });
    }
    //微信
    private void weixinthpas() {
        Log.e("==================",id+"");
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("ip", API.getLocalInetAddress().toString().substring(1));
        params.put("orderId",id);
        params.put("type",1);
        OkGo.<WX_zhifu_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<WX_zhifu_Bean>(Driver_DZF_DetailsActivity.this, WX_zhifu_Bean.class) {
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
                            ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                        }

                    }
                    @Override
                    public void onError(Response<WX_zhifu_Bean> response) {
                        super.onError(response);
                    }
                });
    }
    //支付宝
    private void zhifubao() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("outTradeNo",orderNumber);
        // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
        OkGo.<ZFB_zhifu_Bean>post(MyUrls.BASEURL + "/alipay/driverOrderPay")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZFB_zhifu_Bean>(Driver_DZF_DetailsActivity.this, ZFB_zhifu_Bean.class) {
                    @Override
                    public void onSuccess(Response<ZFB_zhifu_Bean> response) {
                        final ZFB_zhifu_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(Driver_DZF_DetailsActivity.this);
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
                            ToastUtils.getToast(Driver_DZF_DetailsActivity.this, body.getMsg());
                        }
                    }
                    @Override
                    public void onError(Response<ZFB_zhifu_Bean> response) {
                        super.onError(response);
                    }
                });
    }

    private void showPayFinishDialog(String type,boolean isPay) {
        mBuilder = new BaseDialog.Builder(Driver_DZF_DetailsActivity.this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay_finish)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.nomal_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        mDialog.setCancelable(false);

        ImageView imageView = mDialog.getView(R.id.item_img_iv);
        TextView contentTv = mDialog.getView(R.id.item_content_tv);
        TextView queryTv = mDialog.getView(R.id.item_query_tv);
        if (TextUtils.equals("company",type)){
            if (isPay){
                imageView.setImageResource(R.drawable.driver_qhcg);
                contentTv.setText("取货完成");
                queryTv.setText("确认");
            }else {
                imageView.setImageResource(R.drawable.driver_qhsb_iv);
                contentTv.setText("取货失败");
                queryTv.setText("请重新操作");
            }
        }else {
            if (isPay){
                imageView.setImageResource(R.drawable.driver_pay_true_iv);
                contentTv.setText("余额支付成功");
                queryTv.setText("确认");
            }else {
                imageView.setImageResource(R.drawable.driver_pay_false_iv);
                contentTv.setText("余额支付失败");
                queryTv.setText("重新选择支付方式");
            }
        }
        mDialog.show();
        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                finish();
            }
        });
    }
    private void showTelDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_tel)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.dianlian_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                hujiao(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });
        mDialog.getView(R.id.zaixian_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                if(!TextUtils.isEmpty(imUser)){
                    //设置要发送出去的昵称
                    SpUtils.putString(Driver_DZF_DetailsActivity.this,"userName",sp.getString(GGUtils.DrUSER_NAME,""));
                    //设置要发送出去的头像
                    SpUtils.putString(Driver_DZF_DetailsActivity.this,"face", sp.getString(GGUtils.DrIMAGE_path,""));

                    Intent intent = new Intent(Driver_DZF_DetailsActivity.this,ChatActivity.class);
                    intent.putExtra(EaseConstant.EXTRA_USER_ID,imUser);
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                    startActivity(intent);
                }else{
                        ToastUtils.getToast(Driver_DZF_DetailsActivity.this,"无法在线联系回收人员，回收人员未注册聊天！");
                }


            }
        });
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
    private void hujiao(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        dialog = builder.setViewId(R.layout.hujiao_pop)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        TextView quxiao = dialog.getView(R.id.text_pause);
        final TextView phone = dialog.getView(R.id.tell);
        phone.setText(phoneNumber);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        });
        dialog.show();
    }

  public static String StringToDouble(double num){
          return new DecimalFormat("0.00").format(num);
  }



}
