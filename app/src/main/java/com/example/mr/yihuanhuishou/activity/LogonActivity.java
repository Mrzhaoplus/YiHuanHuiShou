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
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.Login_Bean;
import com.example.mr.yihuanhuishou.jsonbean.Zhece_Bean;
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

public class LogonActivity extends AppCompatActivity implements View.OnClickListener {

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
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        boolean aBoolean = sp.getBoolean(GGUtils.BOOLEAN, false);
        if(aBoolean){
            startActivity(new Intent(LogonActivity.this, MainActivity.class));
        }
        initdata();
    }

    private void initdata() {
        zhuce.setOnClickListener(this);
        wangji.setOnClickListener(this);
        logon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(LogonActivity.this, ZhuCe_RegisterActivity.class));
                break;
            case R.id.wangji:
                startActivity(new Intent(LogonActivity.this, wangji_forgetActivity.class));
                break;
            case R.id.logon:
                //获取身份证
                String idcard = IDcard.getText().toString().trim();
                boolean idCard = Validator.isIDCard(idcard);
                //获取密码
                String pass = password.getText().toString().trim();
                if(!TextUtils.isEmpty(pass)&&idCard){
                    infodate(idcard,pass);
                }else if(idCard==false){
                    ToastUtils.getToast(this,"身份证有误！");
                } else if(TextUtils.isEmpty(pass)){
                    ToastUtils.getToast(this,"密码为空");
                }
                break;
        }
    }

    private void infodate(String idcard,String pass) {
        HttpParams params = new HttpParams();
        params.put("idCard",idcard);
        params.put("passWord",pass);
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
                            edit.putString(GGUtils.TOKEN,body.getData().getToken());
                            edit.putString(GGUtils.TEL_NUMBER,body.getData().getPhoneNumber());
                            edit.putInt(GGUtils.USER_id,body.getData().getId());
                            edit.putString(GGUtils.USER_NAME,body.getData().getNickname());
                            edit.putString(GGUtils.IMAGE_path,body.getData().getPhotoPath());
                            edit.putString(GGUtils.SEX,body.getData().getSex());
                            edit.putString(GGUtils.AGE,body.getData().getAge()+"");
                            edit.putBoolean(GGUtils.BOOLEAN,true);
                            edit.commit();
                            startActivity(new Intent(LogonActivity.this, MainActivity.class));
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(LogonActivity.this, body.getMsg());
                        }

                    }
                });

    }
}
