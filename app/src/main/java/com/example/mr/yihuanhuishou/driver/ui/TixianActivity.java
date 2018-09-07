package com.example.mr.yihuanhuishou.driver.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.HomePersonInfoActivity;
import com.example.mr.yihuanhuishou.adapter.Pop_card_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Back_card_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XiuGai_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
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
import butterknife.OnClick;

public class TixianActivity extends BaseActivity implements Pop_card_Adapter.Backclick {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.checkbox_ali)
    CheckBox checkboxAli;
    @BindView(R.id.ali_rl)
    RelativeLayout aliRl;
    @BindView(R.id.checkbox_weichat)
    CheckBox checkboxWeichat;
    @BindView(R.id.weichat_rl)
    RelativeLayout weichatRl;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_rl)
    RelativeLayout bankRl;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    List<Back_card_Bean.DataBean> list = new ArrayList<>();
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.gao)
    TextView gao;
    @BindView(R.id.view_01)
    ImageView view01;
    private SharedPreferences sp;
    private SharedPreferences spp;
    private Dialog dialog;
    private int flag = 1;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        if(state==1){
            gao.setText(sp.getFloat(GGUtils.XIANJIN,0)+"");

        }else{
            float aFloat = spp.getFloat(GGUtils.DrXIANJIN, 0);
                gao.setText(aFloat+"");
        }
        infoview();
    }
    private void infoview() {
        if(state==1){
            HttpParams params = new HttpParams();
            params.put("userId", sp.getInt(GGUtils.USER_id, 0));
            params.put("userType", "1");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(TixianActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if (data.size() > 0) {
                                    list.addAll(data);
                                    boolean weixin = checkboxWeichat.isChecked();
                                    boolean zhifu = checkboxAli.isChecked();
                                    String bank = bankTv.getText().toString();
                                    if (weixin == true || zhifu == true) {
                                        bankTv.setText("");
                                    } else {
                                        bankTv.setText(data.get(0).getEecBankcardtype().getBankName());
                                    }
                                }
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            }

                        }
                    });
        }else if(state==2){
            HttpParams params = new HttpParams();
            params.put("userId", spp.getInt(GGUtils.DrUSER_id, 0));
            params.put("userType", "2");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(TixianActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if (data.size() > 0) {
                                    list.addAll(data);
                                    boolean weixin = checkboxWeichat.isChecked();
                                    boolean zhifu = checkboxAli.isChecked();
                                    String bank = bankTv.getText().toString();
                                    if (weixin == true || zhifu == true) {
                                        bankTv.setText("");
                                    } else {
                                        bankTv.setText(data.get(0).getEecBankcardtype().getBankName());
                                    }
                                }
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            }

                        }
                    });
        }


    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("提现");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("提现账户管理");

        if (!TextUtils.isEmpty(bankTv.getText().toString())) {
            checkboxWeichat.setChecked(false);
            checkboxAli.setChecked(false);
        }
        checkboxAli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkboxWeichat.setChecked(false);
                    bankTv.setText("");
                    flag = 1;
                }
            }
        });
        checkboxWeichat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkboxAli.setChecked(false);
                    bankTv.setText("");
                    flag = 2;
                }
            }
        });

    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv, R.id.ali_rl, R.id.weichat_rl, R.id.bank_rl, R.id.tixian_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                Intent intent1 = new Intent(this, TixianManagerActivity.class);
                intent1.putExtra("state",state);
                startActivity(intent1);
                break;
            case R.id.ali_rl:
                break;
            case R.id.weichat_rl:
                break;
            case R.id.bank_rl:
                if(list.size()>0){
                    showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                }else{
                    Intent intent = new Intent(this, BindCardActivity.class);
                    intent.putExtra("state",state);
                    startActivity(intent);

                }
                break;
            case R.id.tixian_tv:
                String trim = price.getText().toString().trim();
                if(state==1){
                    if (TextUtils.isEmpty(trim)) {
                        ToastUtils.getToast(this, "请输入提现金额");
                    } else {
                        boolean weixin = checkboxWeichat.isChecked();
                        boolean zhifu = checkboxAli.isChecked();
                        String bank = bankTv.getText().toString();
                        if (weixin == false && zhifu == false && TextUtils.isEmpty(bank)) {
                            ToastUtils.getToast(this, "请选择提现渠道");
                        } else if (weixin == true && zhifu == false && TextUtils.isEmpty(bank)) {
                            // int i = Integer.parseInt(trim);
                            ToastUtils.getToast(this, "暂无微信提现接口");
                            price.setText("");
                        } else if (weixin == false && zhifu == true && TextUtils.isEmpty(bank)) {
                            float i = Float.parseFloat(trim);
                            if(i>sp.getFloat(GGUtils.XIANJIN,0)){
                                ToastUtils.getToast(this, "提现失败，最高可提现"+sp.getInt(GGUtils.XIANJIN,0)+"元");
                            }else if(i==0||i<0){
                                ToastUtils.getToast(this, "提现失败，提现金额不可低于0元");
                            }else if(TextUtils.isEmpty(sp.getString(GGUtils.ZHIFUBAO,""))){
                                ToastUtils.getToast(this, "提现失败，未绑定支付宝");
                            }else{
                                tixian(i);
                            }
                        } else if (weixin == false && zhifu == false && !TextUtils.isEmpty(bank)) {
                            // int i = Integer.parseInt(trim);
                            ToastUtils.getToast(this, bank + "暂无银行卡提现接口");
                            price.setText("");
                        }
                    }
                }else if(state==2){
                    if (TextUtils.isEmpty(trim)) {
                        ToastUtils.getToast(this, "请输入提现金额");
                    } else {
                        boolean weixin = checkboxWeichat.isChecked();
                        boolean zhifu = checkboxAli.isChecked();
                        String bank = bankTv.getText().toString();
                        if (weixin == false && zhifu == false && TextUtils.isEmpty(bank)) {
                            ToastUtils.getToast(this, "请选择提现渠道");
                        } else if (weixin == true && zhifu == false && TextUtils.isEmpty(bank)) {
                            // int i = Integer.parseInt(trim);
                            ToastUtils.getToast(this, "暂无微信提现接口");
                            price.setText("");
                        } else if (weixin == false && zhifu == true && TextUtils.isEmpty(bank)) {
                            String string = spp.getString(GGUtils.DrZHIFUBAO, "");
                            float i = Float.parseFloat(trim);
                            if(i>spp.getFloat(GGUtils.DrXIANJIN,0)){
                                ToastUtils.getToast(this, "提现失败，最高可提现"+spp.getFloat(GGUtils.DrXIANJIN,0)+"元");
                            }else if(i==0||i<0){
                                ToastUtils.getToast(this, "提现失败，提现金额不可低于0元");
                            }else if(TextUtils.isEmpty(string)){
                                ToastUtils.getToast(this, "提现失败，未绑定支付宝");
                            }else{
                                tixian(i);
                            }
                        } else if (weixin == false && zhifu == false && !TextUtils.isEmpty(bank)) {
                            // int i = Integer.parseInt(trim);
                            ToastUtils.getToast(this, bank + "暂无银行卡提现接口");
                            price.setText("");
                        }
                    }
                }
                break;
        }
    }

    private void tixian(final float i) {
        if(state==1){
            HttpParams params2 = new HttpParams();
            params2.put("payeeAccount",sp.getString(GGUtils.ZHIFUBAO,""));
            params2.put("amount",i);
            params2.put("token",sp.getString(GGUtils.TOKEN,""));
            OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/alipay/withdrawsAlipay")
                    .tag(this)
                    .params(params2)
                    .execute(new DialogCallback<Jiaoyan_Bean>(TixianActivity.this, Jiaoyan_Bean.class) {
                        @Override
                        public void onSuccess(Response<Jiaoyan_Bean> response) {
                            Jiaoyan_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putFloat(GGUtils.XIANJIN,(sp.getFloat(GGUtils.XIANJIN,0)-i));
                                edit.commit();
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                                finish();
                            } else{
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            }
                        }
                    });
        }else if(state==2){
            HttpParams params2 = new HttpParams();
            params2.put("payeeAccount",spp.getString(GGUtils.DrZHIFUBAO,""));
            params2.put("amount",i);
            params2.put("token",spp.getString(GGUtils.DrTOKEN,""));
            OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/alipay/withdrawsAlipay")
                    .tag(this)
                    .params(params2)
                    .execute(new DialogCallback<Jiaoyan_Bean>(TixianActivity.this, Jiaoyan_Bean.class) {
                        @Override
                        public void onSuccess(Response<Jiaoyan_Bean> response) {
                            Jiaoyan_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = spp.edit();
                                edit.putFloat(GGUtils.DrXIANJIN,(spp.getInt(GGUtils.DrXIANJIN,0)-i));
                                edit.commit();
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                                finish();
                            } else{
                                ToastUtils.getToast(TixianActivity.this, body.getMsg());
                            }
                        }
                    });
        }
    }
    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.pop_bankcrad_adapter)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        RecyclerView recyview = dialog.findViewById(R.id.recyview);
        recyview.setNestedScrollingEnabled(false);
        recyview.setLayoutManager(new LinearLayoutManager(this));
        recyview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        Pop_card_Adapter pop_card_adapter = new Pop_card_Adapter(TixianActivity.this, list);
        recyview.setAdapter(pop_card_adapter);
        pop_card_adapter.clickname(this);
    }

    @Override
    public void getclick(String backname) {
        bankTv.setText(backname);
        checkboxWeichat.setChecked(false);
        checkboxAli.setChecked(false);
        dialog.dismiss();
    }
}
