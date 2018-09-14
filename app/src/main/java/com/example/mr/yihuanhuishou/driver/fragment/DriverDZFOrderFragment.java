package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Driver_DZF_DetailsActivity;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.PayResult;
import com.example.mr.yihuanhuishou.driver.weight.CommonPopupWindow;
import com.example.mr.yihuanhuishou.jsonbean.WX_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.ZFB_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Danwei_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Leibie_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_Bean;
import com.example.mr.yihuanhuishou.utils.API;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/22.
 */

public class DriverDZFOrderFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.sp_view)
    SpringView spView;
    Unbinder unbinder;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private TextView leibieTv;
    private EditText shumuEt;
    private EditText zongjiaEt;
    private LinearLayout leibieLl;
    private TextView shumuTv;
    private List<Order_Bean.DataBean.DataListBean> mlist = new ArrayList<>();
    private List<Driver_Leibie_Bean.DataBean> list = new ArrayList<>();
    private List<Danwei_Bean.DataBean> zlist = new ArrayList<>();
    private RelativeLayout shumuRl;
    private TextView leftTv;
    private SharedPreferences sp;
    private int pageNo = 1;
    private int pageCount = 10;
    private AllOrderAdapter allOrderAdapter;
    private SharedPreferences sp1;
    private int init_id;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wxf32e64cfe4bd433c";// 微信appid
    private boolean flag = true;
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
                    Log.e("======================", resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        mlist.clear();
                        list.clear();
                        infoview();
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getActivity(), "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "支付宝支付取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;

    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(getActivity(), WX_APPID, true);
        // 将该app注册到微信
        api.registerApp(WX_APPID);

        if (flag) {
            //注册
            EventBus.getDefault().register(this);
            flag = false;
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_fragment eveen) {
        int msg = eveen.getMsg();
        if (msg == 1) {
            Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
        } else if (msg == 2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg, "支付失败"));
            builder.show();
        }
    }

    private void initdata() {
        //刷新加载
        spView.setType(SpringView.Type.FOLLOW);
        spView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlist.clear();
                        list.clear();
                        pageNo = 1;
                        pageCount = 10;
                        infoview();
                    }
                }, 0);
                spView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        pageCount = 10 + pageCount;
                        infoview();
                    }
                }, 0);
                spView.onFinishFreshAndLoad();
            }
        });
        spView.setFooter(new DefaultFooter(getActivity()));
        spView.setHeader(new DefaultHeader(getActivity()));
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(mContext));
        allOrderAdapter = new AllOrderAdapter(R.layout.item_all_order, mlist);
        recyView.setAdapter(allOrderAdapter);
        allOrderAdapter.setOnItemClickListener(this);
    }

    private void infoview() {
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);

        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("pageNo", pageNo);
        params.put("state", "7");
        params.put("pageCount", pageCount);
        OkGo.<Order_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Bean>(getActivity(), Order_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Bean> response) {
                        Order_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Order_Bean.DataBean.DataListBean> dataList = body.getData().getDataList();
                            if (pageNo > 1) {
                                if (dataList.size() > 0) {
                                    mlist.addAll(dataList);
                                }
                            } else {
                                mlist.addAll(dataList);
                            }
                            allOrderAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initdata();
        mlist.clear();
        list.clear();
        infoview();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public class AllOrderAdapter extends BaseQuickAdapter<Order_Bean.DataBean.DataListBean, BaseViewHolder> {

        public AllOrderAdapter(int layoutResId, @Nullable List<Order_Bean.DataBean.DataListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Order_Bean.DataBean.DataListBean item) {
            leftTv = helper.getView(R.id.item_left_tv);
            TextView name = helper.getView(R.id.name);
            TextView tel = helper.getView(R.id.tel);
            TextView order_tv = helper.getView(R.id.order_tv);
            TextView leibie_tv = helper.getView(R.id.leibie_tv);
            TextView address_tv = helper.getView(R.id.address_tv);
            TextView state_tv = helper.getView(R.id.state_tv);
            final TextView item_right_tv = helper.getView(R.id.item_right_tv);
            TextView item_left_tv = helper.getView(R.id.item_left_tv);
            name.setText(item.getEecRecyclersaddr().getName() + "：");
            tel.setText(item.getEecRecyclersaddr().getPhoneNumber());
            leibie_tv.setText(item.getVarieties());
            order_tv.setText(item.getOrderNumber());
            address_tv.setText(item.getEecRecyclersaddr().getDetailAddr());

            helper.setText(R.id.state_tv, "待支付")
                    .setText(R.id.item_right_tv, "支付")
                    .setVisible(R.id.item_left_tv, false);
            //支付
            item_right_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double totalPrice = item.getTotalPrice();
                  showPayDialog(item.getOrderNumber(), item.getId(),totalPrice);


                }
            });
        }
    }

    //OrderDetailActivity.class
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent4 = new Intent(mContext, Driver_DZF_DetailsActivity.class);
        intent4.putExtra("id", mlist.get(position).getId());
        intent4.putExtra("state", mlist.get(position).getState());
        startActivity(intent4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancle_tv:
                mDialog.dismiss();
                mlist.clear();
                infoview();
                break;
        }
    }

    private void showPaySelctorDialog(final String ordernumber, final int id, final double totalPrice) {
        mBuilder = new BaseDialog.Builder(mContext);
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
        mDialog.getView(R.id.cancle_tv).setOnClickListener(this);
        //余额支付
        mDialog.getView(R.id.balance_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                HttpParams params = new HttpParams();
                params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
                params.put("ip", API.getLocalInetAddress().toString().substring(1));
                params.put("orderId", id);
                params.put("type", 2);
                OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Jiaoyan_Bean>(getActivity(), Jiaoyan_Bean.class) {
                            @Override
                            public void onSuccess(Response<Jiaoyan_Bean> response) {
                                Jiaoyan_Bean body = response.body();
                                String code = body.getCode() + "";
                                if (code.equals("200")) {
                                    ToastUtils.getToast(getActivity(), body.getMsg());
                                    showPayFinishDialog("balanbce", true);
                                    float v = Float.parseFloat(totalPrice + "");
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putFloat(GGUtils.DrXIANJIN,(sp.getFloat(GGUtils.DrXIANJIN,0)-v));
                                    edit.commit();
                                } else {
                                    showPayFinishDialog("balanbce", false);
                                    ToastUtils.getToast(getActivity(), body.getMsg());
                                }
                            }
                            @Override
                            public void onError(Response<Jiaoyan_Bean> response) {
                                super.onError(response);
                            }
                        });
            }
        });
        //支付宝支付订单
        mDialog.getView(R.id.ali_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                zhifubao(ordernumber);
            }
        });
        //微信支付订单
        mDialog.getView(R.id.wechat_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                weixinthpas(id);
            }
        });
    }

    private void weixinthpas(int id) {
        Log.e("==================", id + "");
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("ip", API.getLocalInetAddress().toString().substring(1));
        params.put("orderId", id);
        params.put("type", 1);
        OkGo.<WX_zhifu_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<WX_zhifu_Bean>(getActivity(), WX_zhifu_Bean.class) {
                    @Override
                    public void onSuccess(Response<WX_zhifu_Bean> response) {
                        WX_zhifu_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            Log.e("=====================", "唤醒");
                            WX_zhifu_Bean.DatasBean datas = body.getDatas();
                            PayReq req = new PayReq();
                            req.appId = datas.getAppid();// 微信开放平台审核通过的应用APPID
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
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }

                    }

                    @Override
                    public void onError(Response<WX_zhifu_Bean> response) {
                        super.onError(response);
                    }
                });
    }

    private void zhifubao(String ordernumber) {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("outTradeNo", ordernumber);
        // http://172.168.20.73:8080/EEC/weChatPay/getPayParams
        OkGo.<ZFB_zhifu_Bean>post(MyUrls.BASEURL + "/alipay/driverOrderPay")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZFB_zhifu_Bean>(getActivity(), ZFB_zhifu_Bean.class) {
                    @Override
                    public void onSuccess(Response<ZFB_zhifu_Bean> response) {
                        final ZFB_zhifu_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(getActivity());
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
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<ZFB_zhifu_Bean> response) {
                        super.onError(response);
                    }
                });

    }

    private void showPayFinishDialog(String type, boolean isPay) {
        mBuilder = new BaseDialog.Builder(mContext);
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
        final TextView queryTv = mDialog.getView(R.id.item_query_tv);
        if (TextUtils.equals("company", type)) {
            if (isPay) {
                imageView.setImageResource(R.drawable.driver_qhcg);
                contentTv.setText("取货完成");
                queryTv.setText("确认");
            } else {
                imageView.setImageResource(R.drawable.driver_qhsb_iv);
                contentTv.setText("取货失败");
                queryTv.setText("请重新操作");
            }
        } else {
            if (isPay) {
                imageView.setImageResource(R.drawable.driver_pay_true_iv);
                contentTv.setText("余额支付成功");
                queryTv.setText("确认");


            } else {
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
                if (queryTv.getText().toString().equals("确认")) {
                    mlist.clear();
                    list.clear();
                    infoview();

                } else {
                    mlist.clear();
                    list.clear();
                    infoview();
                }

            }
        });

    }

    //选择
    private void showPayDialog(final String ordernumber, final int id, final double totalPrice) {
        mBuilder = new BaseDialog.Builder(mContext);
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

        mDialog.getView(R.id.cancle_tv).setOnClickListener(this);
        //自己付
        mDialog.getView(R.id.self_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                showPaySelctorDialog(ordernumber, id,totalPrice);
            }
        });
        //公司付款
        mDialog.getView(R.id.company_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                Log.e("==================", id + "");
                HttpParams params = new HttpParams();
                params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
                params.put("ip", API.getLocalInetAddress().toString().substring(1));
                params.put("orderId", id);
                params.put("type", 0);
                OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/weChatPay/driverWxPay")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Jiaoyan_Bean>(getActivity(), Jiaoyan_Bean.class) {
                            @Override
                            public void onSuccess(Response<Jiaoyan_Bean> response) {
                                Jiaoyan_Bean body = response.body();
                                String code = body.getCode() + "";
                                if (code.equals("200")) {
                                    ToastUtils.getToast(getActivity(), body.getMsg());
                                    showPayFinishDialog("company", true);
                                } else {
                                    ToastUtils.getToast(getActivity(), body.getMsg());
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
