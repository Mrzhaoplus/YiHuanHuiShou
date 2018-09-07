package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.DriverHomeActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Login_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driv_Logon_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.IDCard;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogonActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.wangji)
    TextView wangji;
    @BindView(R.id.logon)
    Button logon;
    @BindView(R.id.IDcard)
    EditText IDcard;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.beak)
    ImageView beak;
    private SharedPreferences sp;
    private int type;
    private SharedPreferences dsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        dsp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        if (type == 1) {
            boolean aBoolean = sp.getBoolean(GGUtils.BOOLEAN, false);
            if (aBoolean) {
                startActivity(new Intent(LogonActivity.this, Home_detailActivity.class));
            }
        }else if(type==2){
            boolean aBoolean = dsp.getBoolean(GGUtils.DrBOOLEAN, false);
            if (aBoolean) {
                startActivity(new Intent(LogonActivity.this, DriverHomeActivity.class));
            }
        }
        initdata();
    }
    private void initdata() {
        beak.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        wangji.setOnClickListener(this);
        logon.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.zhuce:
                if (type == 1) {
                    startActivity(new Intent(LogonActivity.this, ZhuCe_RegisterActivity.class));
                } else if (type == 2) {
                    startActivity(new Intent(LogonActivity.this, Driv_ZhuCe_ReginActivity.class));
                }
                break;
            case R.id.wangji:
                if (type == 1) {
                    startActivity(new Intent(LogonActivity.this, wangji_forgetActivity.class));
                } else if (type == 2) {
                    startActivity(new Intent(LogonActivity.this, Driv_wangji_forgetActivity.class));
                }
                break;
            case R.id.logon:
                //获取身份证
                String idcard = IDcard.getText().toString().trim();
                boolean idCard = false;
                try {
                    idCard = IDCard.IDCardValidate(idcard);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //获取密码
                String pass = password.getText().toString().trim();
                boolean ispsd = Validator.ispsd(pass);
                if (!TextUtils.isEmpty(pass) && idCard&&ispsd&&pass.length()>5&&pass.length()<19) {
                    infodate(idcard, pass);
                } else if (!idCard) {
                    ToastUtils.getToast(this, "身份证有误！");
                } else if (TextUtils.isEmpty(pass)) {
                    ToastUtils.getToast(this, "密码为空");
                }else if (pass.length()>18||pass.length()<6) {
                    ToastUtils.getToast(this, "密码错误");
                }else if (ispsd == false) {
                    ToastUtils.getToast(this, "密码错误");
                }
                break;
        }
    }
    private void infodate(String idcard, final String pass) {
        if (type == 1) {
            HttpParams params = new HttpParams();
            params.put("idCard", idcard);
            params.put("passWord", pass);
            OkGo.<Login_Bean>post(MyUrls.BASEURL + "/recyclers/login")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Login_Bean>(LogonActivity.this, Login_Bean.class) {
                        @Override
                        public void onSuccess(Response<Login_Bean> response) {
                            Login_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString(GGUtils.TOKEN, body.getData().getToken());
                                edit.putString(GGUtils.TEL_NUMBER, body.getData().getPhoneNumber());
                                edit.putInt(GGUtils.USER_id, body.getData().getId());
                                edit.putString(GGUtils.USER_NAME, body.getData().getNickname());
                                edit.putString(GGUtils.IMAGE_path, body.getData().getPhotoPath());
                                edit.putString(GGUtils.SEX, body.getData().getSex());
                                edit.putString(GGUtils.DEPOST, body.getData().getDeposit());
                                edit.putString(GGUtils.PASSWORD, pass);
                                edit.putInt(GGUtils.AGE, body.getData().getAge());
                                edit.putFloat(GGUtils.YIHUAN, body.getData().getCurrency());
                                edit.putFloat(GGUtils.XIANJIN, body.getData().getTotalCurrency());
                                edit.putString(GGUtils.ISUSER, body.getData().getImUser());
                                edit.putString(GGUtils.ISPASS, body.getData().getImPassword());
                                edit.putString(GGUtils.ZHIFUBAO, body.getData().getAliPay());
                                edit.putString(GGUtils.WEIXIN, body.getData().getWechat());
                                edit.putBoolean(GGUtils.BOOLEAN, true);
                                edit.commit();
                                startActivity(new Intent(LogonActivity.this, Home_detailActivity.class));
                                ToastUtils.getToast(LogonActivity.this, body.getMsg());
                            } else  {
                                ToastUtils.getToast(LogonActivity.this, body.getMsg());
                            }
                        }
                    });
        } else {
            HttpParams params = new HttpParams();
            params.put("idCard", idcard);
            params.put("passWord", pass);
            OkGo.<Driv_Logon_Bean>post(MyUrls.BASEURL + "/driver/login")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Driv_Logon_Bean>(LogonActivity.this, Driv_Logon_Bean.class) {
                        @Override
                        public void onSuccess(Response<Driv_Logon_Bean> response) {
                            Driv_Logon_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                SharedPreferences.Editor edit = dsp.edit();
                                edit.putString(GGUtils.DrTOKEN, body.getData().getToken());
                                edit.putString(GGUtils.DrTEL_NUMBER, body.getData().getDriverPhone());
                                edit.putInt(GGUtils.DrUSER_id, body.getData().getId());
                                edit.putFloat(GGUtils.DrXIANJIN, body.getData().getDriverBanlance());
                                edit.putString(GGUtils.DrUSER_NAME, body.getData().getDriverNicekname());
                                edit.putString(GGUtils.DrIMAGE_path, body.getData().getDriverImage());
                                edit.putString(GGUtils.DrIMAGE_xuan, body.getData().getDriverImage());
                                edit.putBoolean(GGUtils.DrSEX, body.getData().getDriverSex());
                                edit.putString(GGUtils.DrPASSWORD, pass);
                                edit.putString(GGUtils.DrZHIFUBAO, body.getData().getPayid());
                                String imPassword = body.getData().getImPassword();
                                String imUser = body.getData().getImUser();
                                if(!TextUtils.isEmpty(imPassword)&&!TextUtils.isEmpty(imUser)){
                                    edit.putString(GGUtils.DrISPASS,imPassword );
                                    edit.putString(GGUtils.DrISUSER,imUser);
                                }
                                edit.putInt(GGUtils.DrAGE, body.getData().getDriverAge());
                                edit.putBoolean(GGUtils.DrBOOLEAN, true);
                                edit.commit();
                                startActivity(new Intent(LogonActivity.this, DriverHomeActivity.class));
                                ToastUtils.getToast(LogonActivity.this, body.getMsg());
                            } else {
                                ToastUtils.getToast(LogonActivity.this, body.getMsg());
                            }
                        }
                    });
        }


    }
}
