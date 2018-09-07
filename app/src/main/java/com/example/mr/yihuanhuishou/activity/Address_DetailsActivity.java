package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Address_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Address_DetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.bianji)
    TextView bianji;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.add_qu)
    TextView addqu;
    @BindView(R.id.add_tree)
    TextView addtree;
    @BindView(R.id.add_detail)
    TextView adddetail;
    @BindView(R.id.xuan)
    CheckBox xuan;
    @BindView(R.id.shanchu)
    Button shanchu;
    private int id;
    private SharedPreferences sp;
    private Address_Details_Bean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__details);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        id = getIntent().getIntExtra("addid", 0);
        initdata();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }

    private void initview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        OkGo.<Address_Details_Bean>post(MyUrls.BASEURL + "/recyclers/address/single")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Address_Details_Bean>(Address_DetailsActivity.this, Address_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Address_Details_Bean> response) {
                        Address_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            data = body.getData();
                            name.setText(data.getName());
                            tel.setText(data.getPhoneNumber());
                            addqu.setText(data.getProvince()+ data.getCity()+ data.getCounry());
                            addtree.setText("");
                            adddetail.setText(data.getDetailAddr());
                            String isDefault = data.getIsDefault();
                             if(isDefault.equals("1")){
                                xuan.setChecked(true);
                            }else{
                                 xuan.setChecked(false);
                             }

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initdata() {
        beak.setOnClickListener(this);
        bianji.setOnClickListener(this);
        shanchu.setOnClickListener(this);

       xuan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               xiugai();
           }
       });
    }

    private void xiugai() {
        if(data.getIsDefault().equals("1")){
            HttpParams params = new HttpParams();
            params.put("token", sp.getString(GGUtils.TOKEN, ""));
            params.put("id", id);
            params.put("name", data.getName());
            params.put("phoneNumber", data.getPhoneNumber());
            params.put("province", data.getProvince());
            params.put("city", data.getCity());
            params.put("counry", data.getCounry());
            params.put("isDefault", "0");
            params.put("detailAddr", data.getDetailAddr());
            params.put("longitude", data.getLongitude()+"");
            params.put("latitude", data.getLatitude()+"");
            OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/address/addOrUpdate")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Zhece_Bean>(Address_DetailsActivity.this, Zhece_Bean.class) {
                        @Override
                        public void onSuccess(Response<Zhece_Bean> response) {
                            Zhece_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                                finish();
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            }
                        }
                    });
        }
        if(data.getIsDefault().equals("0")){
            HttpParams params = new HttpParams();
            params.put("token", sp.getString(GGUtils.TOKEN, ""));
            params.put("id", id);
            params.put("name", data.getName());
            params.put("phoneNumber", data.getPhoneNumber());
            params.put("province", data.getProvince());
            params.put("city", data.getCity());
            params.put("counry", data.getCounry());
            params.put("isDefault", "1");
            params.put("detailAddr", data.getDetailAddr());
            params.put("longitude", data.getLongitude()+"");
            params.put("latitude", data.getLatitude()+"");
            OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/address/addOrUpdate")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Zhece_Bean>(Address_DetailsActivity.this, Zhece_Bean.class) {
                        @Override
                        public void onSuccess(Response<Zhece_Bean> response) {
                            Zhece_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                                finish();
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                            }
                        }
                    });
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.bianji:
                Intent intent = new Intent(Address_DetailsActivity.this, Address_bianjiActivity.class);
                intent.putExtra("id",data.getId());
                intent.putExtra("add_name",data.getName());
                intent.putExtra("add_tel",data.getPhoneNumber());
                intent.putExtra("add_sf",data.getProvince());
                intent.putExtra("add_cs",data.getCity());
                intent.putExtra("add_qu",data.getCounry());
                intent.putExtra("add_xq",data.getDetailAddr());
                startActivity(intent);
                break;
            case R.id.shanchu:
                infodata();
                break;
        }
    }
    private void infodata() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("addrId",id);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/address/del")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Address_DetailsActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, "删除"+body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Address_DetailsActivity.this, body.getMsg());
                        }
                    }
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
