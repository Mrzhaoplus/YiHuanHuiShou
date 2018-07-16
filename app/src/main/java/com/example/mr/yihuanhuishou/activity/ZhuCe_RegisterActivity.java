package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.ZhiPai_wupin_Adapter;
import com.example.mr.yihuanhuishou.jsonbean.Fengzhuang;
import com.example.mr.yihuanhuishou.jsonbean.Xitong_Recycler_Bean;
import com.example.mr.yihuanhuishou.jsonbean.Yanzheng_Bean;
import com.example.mr.yihuanhuishou.jsonbean.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuCe_RegisterActivity extends AppCompatActivity implements View.OnClickListener, ZhiPai_wupin_Adapter.Onclickflag {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.img_tu)
    ImageView imgTu;
    @BindView(R.id.huo_yan)
    TextView huoYan;
    @BindView(R.id.zhuce)
    Button zhuce;
    @BindView(R.id.xieyi)
    TextView xieyi;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    List<Xitong_Recycler_Bean.DataBean> mlist = new ArrayList<>();
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.vertir_code)
    EditText vertirCode;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.IDcase)
    EditText IDcase;
    List<Integer>list=new ArrayList<>();
    private List<Integer> pos;
    private ZhiPai_wupin_Adapter zhiPai_wupin_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce__register);
        ButterKnife.bind(this);
        initdate();
        infoview();
    }

    private void infoview() {
        mlist.clear();
        OkGo.<Xitong_Recycler_Bean>post(MyUrls.BASEURL + "/recyclers/info/allVarieties")
                .tag(this)
                .execute(new DialogCallback<Xitong_Recycler_Bean>(ZhuCe_RegisterActivity.this, Xitong_Recycler_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xitong_Recycler_Bean> response) {
                        Xitong_Recycler_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Xitong_Recycler_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                            zhiPai_wupin_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        }

                    }
                });
    }

    private void initdate() {


        beak.setOnClickListener(this);
        huoYan.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        xieyi.setOnClickListener(this);

        recyView.setLayoutManager(new GridLayoutManager(ZhuCe_RegisterActivity.this, 3));
        recyView.setNestedScrollingEnabled(false);
        zhiPai_wupin_adapter = new ZhiPai_wupin_Adapter(ZhuCe_RegisterActivity.this, mlist);
        recyView.setAdapter(zhiPai_wupin_adapter);
        zhiPai_wupin_adapter.getclick(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.zhuce:
                for (int i = 0; i < pos.size(); i++) {
                    list.add(mlist.get(pos.get(i)).getId());
                }

                //获取手机号
                String tell = tel.getText().toString().trim();
                boolean mobileNO = Validator.isMobileNO(tell);
                //获取验证码
                String yanzheng = vertirCode.getText().toString().trim();
                //获取密码
                String password = pass.getText().toString().trim();
                //获取身份证号
                String idcard = IDcase.getText().toString().trim();
                boolean idCard = Validator.isIDCard(idcard);

                if(mobileNO&&idCard&&list!=null&&!TextUtils.isEmpty(yanzheng)&&!TextUtils.isEmpty(password)){
                    infodata(tell,yanzheng,password,idcard,list);
                }else if(!mobileNO){
                    ToastUtils.getToast(this,"手机号有误！");
                }else if(!idCard){
                    ToastUtils.getToast(this,"身份证有误！");
                }else if(list==null){
                    ToastUtils.getToast(this,"请选择回收类型");
                }else if(TextUtils.isEmpty(yanzheng)){
                    ToastUtils.getToast(this,"验证码为空");
                }else if(TextUtils.isEmpty(password)){
                    ToastUtils.getToast(this,"密码为空");
                }
                break;
            case R.id.huo_yan:
                //获取手机号
                String phone = tel.getText().toString().trim();
                boolean mobile = Validator.isMobileNO(phone);
                if(mobile){
                    yancode(phone);
                }else{
                    ToastUtils.getToast(ZhuCe_RegisterActivity.this,"号码有误，请重新输入！");
                    tel.setText("");
                }
                break;
            case R.id.xieyi:
                startActivity(new Intent(this, XieyiActivity.class));
                break;

        }
    }
     //验证码
    private void yancode(String phone) {
        HttpParams params = new HttpParams();
        params.put("phoneNumber",phone);
        OkGo.<Yanzheng_Bean>post(MyUrls.BASEURL + "/recyclers/getVCodeTheRecyclersRegister")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Yanzheng_Bean>(ZhuCe_RegisterActivity.this, Yanzheng_Bean.class) {
                    @Override
                    public void onSuccess(Response<Yanzheng_Bean> response) {
                        Yanzheng_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            vertirCode.setText(body.getVCode()+"");
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        }

                    }
                });

    }

    //网络请求框架
    private void infodata(String tel,String yanzheng,String password,String idcaed,List<Integer>list) {
        Fengzhuang fengzhuang = new Fengzhuang();
        fengzhuang.setVarieties(list);
        Gson gson = new Gson();
        String s = gson.toJson(fengzhuang);
        Log.e("================",s.substring(14,s.length()-2));
        HttpParams params = new HttpParams();
        params.put("phoneNumber",tel);
        params.put("passWord",password);
        params.put("idCard",idcaed);
        params.put("VCode",yanzheng);
       params.put("varieties",s.substring(14,s.length()-2));
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/register")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(ZhuCe_RegisterActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                         Log.e("===============",code+"");
                        if (code.equals("200")) {
                            ZhuCe_RegisterActivity.this.finish();
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        }else if (code.equals("202")) {
                            ToastUtils.getToast(ZhuCe_RegisterActivity.this, body.getMsg());
                        }
                    }
                });
    }

    @Override
    public void flag(List<Integer> postion) {
        pos = postion;
    }
}
