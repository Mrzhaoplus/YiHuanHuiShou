package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.LogonActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XiuGai_Bean;
import com.example.mr.yihuanhuishou.utils.AppManager;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPassWordActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.oldpass_et)
    EditText oldpassEt;
    @BindView(R.id.newpass_et)
    EditText newpassEt;
    @BindView(R.id.querypass_et)
    EditText querypassEt;
    @BindView(R.id.edit_tv)
    TextView editTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private SharedPreferences sp;
    private int state;
    private SharedPreferences spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        state = getIntent().getIntExtra("type", 0);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("修改密码");
    }

    private void showPayFinishDialog(final String type) {
        mBuilder = new BaseDialog.Builder(this);
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

        final ImageView imageView = mDialog.getView(R.id.item_img_iv);
        final TextView contentTv = mDialog.getView(R.id.item_content_tv);
        TextView queryTv = mDialog.getView(R.id.item_query_tv);
        if (TextUtils.equals("success",type)){
            imageView.setImageResource(R.drawable.driver_qhcg);
            contentTv.setText("密码修改成功");
        }else if (TextUtils.equals("filed",type)){
            imageView.setImageResource(R.drawable.xgsb_iv);
            contentTv.setText("请输入新密码");
        }else if (TextUtils.equals("quren",type)){
            imageView.setImageResource(R.drawable.xgsb_iv);
            contentTv.setText("请输入确认密码");
        }else if (TextUtils.equals("nold",type)) {
            imageView.setImageResource(R.drawable.mmcw_iv);
            contentTv.setText("旧密码不正确，请重新输入");
        }else if (TextUtils.equals("ispass",type)) {
            imageView.setImageResource(R.drawable.mmcw_iv);
            contentTv.setText("密码格式错误，必须为英文加数字");
        }else if (TextUtils.equals("budeng",type)) {
            imageView.setImageResource(R.drawable.mmcw_iv);
            contentTv.setText("确认密码与新密码不符");
        }else if (TextUtils.equals("xiaoyu",type)) {
            imageView.setImageResource(R.drawable.mmcw_iv);
            contentTv.setText("请输入6-18位密码");
        }
        mDialog.show();
        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals("success",type)){
                    mDialog.dismiss();
                    if(state==1){
                        SharedPreferences.Editor edit = sp.edit();
                        edit.clear();
                        edit.commit();
                        AppManager.getAppManager().finishAllActivity();
                        Intent intent = new Intent(EditPassWordActivity.this, LogonActivity.class);
                        intent.putExtra("type",state);
                        startActivity(intent);
                    }else if(state==2){
                        SharedPreferences.Editor edit = spp.edit();
                        edit.clear();
                        edit.commit();
                        AppManager.getAppManager().finishAllActivity();
                        Intent intent = new Intent(EditPassWordActivity.this, LogonActivity.class);
                        intent.putExtra("type",state);
                        startActivity(intent);
                    }
                }else if (TextUtils.equals("filed",type)){
                    mDialog.dismiss();
                }else if (TextUtils.equals("nold",type)) {
                    mDialog.dismiss();
                }else if (TextUtils.equals("quren",type)) {
                    mDialog.dismiss();
                }else if (TextUtils.equals("ispass",type)) {
                    mDialog.dismiss();
                }else if (TextUtils.equals("budeng",type)) {
                    mDialog.dismiss();
                }else if (TextUtils.equals("xiaoyu",type)) {
                    mDialog.dismiss();
                }
            }
        });

    }

    @OnClick({R.id.title_back_iv, R.id.edit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.edit_tv:
                String oldpass = oldpassEt.getText().toString().trim();
                String newpass = newpassEt.getText().toString().trim();
                String queren = querypassEt.getText().toString().trim();
                boolean ispsd = Validator.ispsd(newpass);
                if(state==1){
                    if(oldpass.equals(sp.getString(GGUtils.PASSWORD,""))){
                        if(newpass.equals(queren)&&!TextUtils.isEmpty(newpass)&&!TextUtils.isEmpty(queren)&&ispsd&&newpass.equals(queren)&&newpass.length()>5&&newpass.length()<19){
                            infoview(queren,oldpass);
                        }else if(TextUtils.isEmpty(newpass)){
                            showPayFinishDialog("filed");
                        }else if(TextUtils.isEmpty(queren)){
                            showPayFinishDialog("quren");
                        }else if(ispsd==false){
                            showPayFinishDialog("ispass");
                        }else if(!newpass.equals(queren)){
                            showPayFinishDialog("budeng");
                        }else if(newpass.length()<6||newpass.length()>18){
                            showPayFinishDialog("xiaoyu");
                        }else if(queren.length()<6||queren.length()>18){
                            showPayFinishDialog("xiaoyu");
                        }
                    }else{
                        showPayFinishDialog("nold");
                    }
                }else if(state==2){
                    if(oldpass.equals(spp.getString(GGUtils.DrPASSWORD,""))){
                        if(newpass.equals(queren)&&!TextUtils.isEmpty(newpass)&&!TextUtils.isEmpty(queren)&&ispsd&&newpass.equals(queren)&&newpass.length()>5&&newpass.length()<19){
                            infoview(queren,oldpass);
                        }else if(TextUtils.isEmpty(newpass)){
                            showPayFinishDialog("filed");
                        }else if(TextUtils.isEmpty(queren)){
                            showPayFinishDialog("quren");
                        }else if(ispsd==false){
                            showPayFinishDialog("ispass");
                        }else if(!newpass.equals(queren)){
                            showPayFinishDialog("budeng");
                        }else if(newpass.length()<6||newpass.length()>18){
                            showPayFinishDialog("xiaoyu");
                        }else if(queren.length()<6||queren.length()>18){
                            showPayFinishDialog("xiaoyu");
                        }
                    }else{
                        showPayFinishDialog("nold");
                    }
                }
                break;
        }
    }
    private void infoview(final String pass,String oldpass) {
        if(state==1){
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("updCode","passWord");
            params.put("content",pass);
            OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<XiuGai_Bean>(EditPassWordActivity.this, XiuGai_Bean.class) {
                        @Override
                        public void onSuccess(Response<XiuGai_Bean> response) {
                            XiuGai_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.PASSWORD,pass);
                                edit.commit();
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                                showPayFinishDialog("success");
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            }
                        }
                    });
        }else if(state==2){
            HttpParams params = new HttpParams();
            params.put("token",spp.getString(GGUtils.DrTOKEN,""));
            params.put("ordPassword",oldpass);
            params.put("newPassword",pass);
            OkGo.<com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean>post(MyUrls.BASEURL + "/driverMine/updatePassword")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean>(EditPassWordActivity.this, com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean.class) {
                        @Override
                        public void onSuccess(Response<com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean> response) {
                            com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.DrPASSWORD,pass);
                                edit.commit();
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                                showPayFinishDialog("success");
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(EditPassWordActivity.this, body.getMsg());
                            }
                        }
                    });
        }


    }
}
