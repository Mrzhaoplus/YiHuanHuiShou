package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driv_wangji_forgetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.yancord)
    EditText yancord;
    @BindView(R.id.huo_yan)
    TextView huoYan;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.back_cord)
    EditText backCord;
    @BindView(R.id.zhao)
    Button zhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driv_wangji_forget);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
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
                if(password.equals(backnum)&&ispsd&&mobileNO&&!TextUtils.isEmpty(yannum)&&!TextUtils.isEmpty(password)&&password.length()>5&&password.length()<19){
                    infoview(tell,yannum,backnum);
                }else if(!mobileNO){
                    ToastUtils.getToast(this,"手机号有误！");
                }else if(TextUtils.isEmpty(yannum)){
                    ToastUtils.getToast(this,"验证码为空");
                }else if(TextUtils.isEmpty(password)){
                    ToastUtils.getToast(this,"密码为空");
                }else if(password.length()<6&&password.length()>18) {
                    ToastUtils.getToast(this, "密码格式错误，请输入6-18位密码");

                }else if (ispsd == false) {
                    ToastUtils.getToast(this, "密码格式错误，必须为英文加数字");
                }else if (!password.equals(backnum)) {
                    ToastUtils.getToast(this, "请确认密码后再次输入");
                }
                break;
            case R.id.huo_yan:
                //获取手机号
                String phone = tel.getText().toString().trim();
                boolean mobile = Validator.isMobileNO(phone);
                if (mobile) {
                    yancode(phone);
                } else {
                    ToastUtils.getToast(Driv_wangji_forgetActivity.this, "号码有误，请重新输入！");
                    tel.setText("");
                }
                break;
        }
    }

    private void infoview(String tell, String yannum, String backnum) {
        HttpParams params = new HttpParams();
        params.put("phoneNumber",tell);
        params.put("passWord",backnum);
        params.put("code",yannum);
        OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/driver/resetPassword")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(Driv_wangji_forgetActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                            finish();
                        } else{
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        }
                    }
                });

    }

    //验证码
    private void yancode(String phone) {
        HttpParams params = new HttpParams();
        params.put("phone",phone);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driver/authPassCode")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(Driv_wangji_forgetActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Driv_wangji_forgetActivity.this, body.getMsg());
                        }

                    }
                });


    }
}
