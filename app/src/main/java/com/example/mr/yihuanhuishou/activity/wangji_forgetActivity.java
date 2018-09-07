package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.mr.yihuanhuishou.jsonbean.huishou.Yanzheng_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
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

public class wangji_forgetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.huo_yan)
    TextView huoYan;
    @BindView(R.id.zhao)
    Button zhao;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.yancord)
    EditText yancord;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.back_cord)
    EditText backCord;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangji_forget);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        time = new TimeCount(60000, 1000);
        beak.setOnClickListener(this);
        huoYan.setOnClickListener(this);
        zhao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.zhao:
                //获取手机号
                String tell = tel.getText().toString().trim();
                String yannum = yancord.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String backnum = backCord.getText().toString().trim();
                boolean mobileNO = Validator.isMobileNO(tell);
                boolean ispsd = Validator.ispsd(password);
                boolean idCard = false;
                try {
                    idCard = IDCard.IDCardValidate(backnum);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(ispsd&&mobileNO&&idCard&&!TextUtils.isEmpty(yannum)&&!TextUtils.isEmpty(password)&&password.length()>5&&password.length()<19){
                    if(!getSharedPreferences(GGUtils.SP_NAME,MODE_PRIVATE).getString(GGUtils.PASSWORD,"").equals(password)){
                        infoview(tell,yannum,password,backnum);
                    }
                }else if(!mobileNO){
                    ToastUtils.getToast(this,"手机号有误！");
                }else if(!idCard){
                    ToastUtils.getToast(this,"身份证有误！");
                }else if(TextUtils.isEmpty(yannum)){
                    ToastUtils.getToast(this,"验证码为空");
                }else if(TextUtils.isEmpty(password)){
                    ToastUtils.getToast(this,"密码为空");
                }else if(password.length()<6&&password.length()>18) {
                    ToastUtils.getToast(this, "密码格式错误，请输入6-18位密码");
                }else if (ispsd == false) {
                    ToastUtils.getToast(this, "密码格式错误，必须为英文加数字");
                }


                break;
            case R.id.huo_yan:
                //获取手机号
                String phone = tel.getText().toString().trim();
                boolean mobile = Validator.isMobileNO(phone);
                if (mobile) {
                    yancode(phone);
                    time.start();
                } else {
                    ToastUtils.getToast(wangji_forgetActivity.this, "号码有误，请重新输入！");
                    tel.setText("");
                }
                break;
        }
    }
     //找回
    private void infoview(String tell, String yannum, String password, String backnum) {
        Log.e("===================",password);
        HttpParams params = new HttpParams();
        params.put("phoneNumber",tell);
        params.put("vCode",yannum);
        params.put("pwd",password);
        params.put("idCard",backnum);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/forgetPwdUpdate")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(wangji_forgetActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                            finish();
                        } else {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        }
                    }
                });
    }
    private void yancode(String phone) {
        HttpParams params = new HttpParams();
        params.put("phoneNumber",phone);
        OkGo.<Yanzheng_Bean>post(MyUrls.BASEURL + "/recyclers/getVCodeTheRecyclersRegister")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Yanzheng_Bean>(wangji_forgetActivity.this, Yanzheng_Bean.class) {
                    @Override
                    public void onSuccess(Response<Yanzheng_Bean> response) {
                        Yanzheng_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                          //  yancord.setText(body.getVCode()+"");
                            ToastUtils.getToast(wangji_forgetActivity.this,"验证码已发送，请注意查收");
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(wangji_forgetActivity.this, body.getMsg());
                        }
                    }
                });

    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            huoYan.setClickable(false);
            huoYan.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }
        @Override
        public void onFinish() {
            huoYan.setText("重新获取验证码");
            huoYan.setClickable(true);
        }

    }
}
