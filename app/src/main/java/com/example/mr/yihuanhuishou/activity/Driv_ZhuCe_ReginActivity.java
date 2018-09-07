package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
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

public class Driv_ZhuCe_ReginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.img_tu)
    LinearLayout imgTu;
    @BindView(R.id.vertir_code)
    EditText vertirCode;
    @BindView(R.id.huo_yan)
    TextView huoYan;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.IDcase)
    EditText IDcase;
    @BindView(R.id.zhuce)
    Button zhuce;
    @BindView(R.id.xieyi)
    TextView xieyi;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driv__zhu_ce__regin);
        ButterKnife.bind(this);
          indata();

    }

    private void indata() {
        beak.setOnClickListener(this);
        huoYan.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        xieyi.setOnClickListener(this);
        time = new TimeCount(60000, 1000);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.zhuce:
                //获取手机号
                String tell = tel.getText().toString().trim();
                boolean mobileNO = Validator.isMobileNO(tell);
                //获取验证码
                String yanzheng = vertirCode.getText().toString().trim();
                //获取密码
                String password = pass.getText().toString().trim();
                boolean ispsd = Validator.ispsd(password);
                //获取身份证号
                String idcard = IDcase.getText().toString().trim();
                boolean idCard = false;
                try {
                    idCard = IDCard.IDCardValidate(idcard);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(ispsd&&mobileNO&&idCard&&!TextUtils.isEmpty(yanzheng)&&!TextUtils.isEmpty(password)&&password.length()>5&&password.length()<19){
                    infodata(tell,yanzheng,password,idcard);
                }else if(!mobileNO){
                    ToastUtils.getToast(this,"手机号有误！");
                }else if(!idCard){
                    ToastUtils.getToast(this,"身份证有误！");
                }else if(TextUtils.isEmpty(yanzheng)){
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
                if(mobile){
                    yancode(phone);
                    time.start();
                }else{
                    ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this,"号码有误，请重新输入！");
                    tel.setText("");
                }
                break;
            case R.id.xieyi:
                startActivity(new Intent(this, XieyiActivity.class));
                break;

        }
    }
//注册
    private void infodata(String tell, String yanzheng, String password, String idcard) {
        HttpParams params = new HttpParams();
        params.put("phoneNumber",tell);
        params.put("code",yanzheng);
        params.put("IdentityCard",idcard);
        params.put("passWord",password);

        OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/driver/register")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(Driv_ZhuCe_ReginActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                            finish();
                        } else {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        }
                    }
                });

    }
    //获取验证码
    private void yancode(String phone) {
        HttpParams params = new HttpParams();
        params.put("phone",phone);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driver/authEnrollCode")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(Driv_ZhuCe_ReginActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Driv_ZhuCe_ReginActivity.this, body.getMsg());
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
