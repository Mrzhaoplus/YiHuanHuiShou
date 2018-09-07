package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Yajin_Tui_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Yajin_yue_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YaJinActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.tui_price)
    TextView tuiPrice;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.ya_tui)
    RelativeLayout yaTui;
    @BindView(R.id.ya_jilu)
    RelativeLayout yaJilu;
    private SharedPreferences sp;
    private String depost;
    private double deposit;
    private String orderNo;
    private String payType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ya_jin);
        initSystemBarTint();
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initdata();
    }

    private void initdata() {
        titleBackIv.setOnClickListener(this);
        yaTui.setOnClickListener(this);
        yaJilu.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        depost = sp.getString(GGUtils.DEPOST, "");
        if(depost.equals("0")){
            price.setText("未缴纳");
            tuiPrice.setText("交纳押金");
        }else if(depost.equals("1")){
            tuiPrice.setText("押金退款");
            infoview();
        }
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        OkGo.<Yajin_yue_Bean>post(MyUrls.BASEURL + "/recyclers/info/depositBalance")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Yajin_yue_Bean>(YaJinActivity.this, Yajin_yue_Bean.class) {

                    @Override
                    public void onSuccess(Response<Yajin_yue_Bean> response) {
                        Yajin_yue_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            deposit = body.getDeposit();
                            if(deposit==0){
                                price.setText("未缴纳");
                            }else{
                                price.setText(deposit+"");
                            }
                            orderNo = body.getOrderNo();
                            payType = body.getPayType();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(YaJinActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(YaJinActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(YaJinActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(YaJinActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(YaJinActivity.this, body.getMsg());
                        }
                    }
                });
    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.transparent_db);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.ya_tui:
                String string = tuiPrice.getText().toString();
                if(string.equals("押金退款")){
                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                }else if(string.equals("交纳押金")){
                    Intent intent = new Intent(YaJinActivity.this, ChongzhiActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ya_jilu:
                startActivity(new Intent(YaJinActivity.this,Yajin_recordActivity.class));
                break;
        }
    }
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(YaJinActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.tuiyajin_pop)
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

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                tuiya();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
////////////////////////////////////////////////////////////////////////////
    private void tuiya() {
        if(payType.equals("1")){
            String string = price.getText().toString();
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("totalFee",deposit);
            params.put("merchantNumber",orderNo);
            OkGo.<Yajin_Tui_Bean>post(MyUrls.BASEURL + "/weChatPay/depositWeChatRefund")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Yajin_Tui_Bean>(YaJinActivity.this, Yajin_Tui_Bean.class) {
                        @Override
                        public void onSuccess(Response<Yajin_Tui_Bean> response) {
                            Yajin_Tui_Bean body = response.body();
                            String status = body.getStatus();
                            if(status.equals("success")){
                                infoview();
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.DEPOST,"0");
                                edit.commit();
                                price.setText("未缴纳");
                                tuiPrice.setText("交纳押金");
                                ToastUtils.getToast(YaJinActivity.this,"退款成功");
                            }else{
                                ToastUtils.getToast(YaJinActivity.this,"押金退款失败");
                            }
                        }
                    });
        }else if(payType.equals("0")){
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("amount",deposit);
            params.put("orderNo",orderNo);
            OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/alipay/refundAlipay")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Jiaoyan_Bean>(YaJinActivity.this, Jiaoyan_Bean.class) {
                        @Override
                        public void onSuccess(Response<Jiaoyan_Bean> response) {
                            Jiaoyan_Bean body = response.body();
                            String code = body.getCode();
                            if(code.equals("200")){
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.DEPOST,"0");
                                edit.commit();
                                infoview();
                                price.setText("未缴纳");
                                tuiPrice.setText("交纳押金");
                                ToastUtils.getToast(YaJinActivity.this,"退款成功");
                            }else{
                                ToastUtils.getToast(YaJinActivity.this,"押金退款失败");
                            }
                        }
                    });
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////

}
