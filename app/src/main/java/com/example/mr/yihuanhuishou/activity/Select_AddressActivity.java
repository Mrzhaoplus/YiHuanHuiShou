package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Address_Adapter;
import com.example.mr.yihuanhuishou.bean.Address_bean;
import com.example.mr.yihuanhuishou.jsonbean.Address_List_Bean;
import com.example.mr.yihuanhuishou.jsonbean.XiuGai_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
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

public class Select_AddressActivity extends AppCompatActivity implements View.OnClickListener, Address_Adapter.getclick {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.tianjia)
    TextView tianjia;
    @BindView(R.id.recy_view)
    RecyclerView mrecycler;
    List<Address_List_Bean.DataBean>list=new ArrayList<>();
    private SharedPreferences sp;
    private Address_Adapter address_adapter;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__address);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        state = getIntent().getIntExtra("state", 0);
        initdata();

    }

    @Override
    protected void onResume() {
        super.onResume();
        infodata();
    }

    private void infodata() {
        list.clear();
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        OkGo.<Address_List_Bean>post(MyUrls.BASEURL + "/recyclers/address/list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Address_List_Bean>(Select_AddressActivity.this, Address_List_Bean.class) {
                    @Override
                    public void onSuccess(Response<Address_List_Bean> response) {
                        Address_List_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Address_List_Bean.DataBean> data = body.getData();
                            list.addAll(data);
                            address_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Select_AddressActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initdata() {
        beak.setOnClickListener(this);
        tianjia.setOnClickListener(this);

        //无下划线
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        address_adapter = new Address_Adapter(Select_AddressActivity.this,list,state);
        mrecycler.setAdapter(address_adapter);
        address_adapter.getjiekou(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.tianjia:
                startActivity(new Intent(Select_AddressActivity.this,New_AddressActivity.class));
                break;
        }
    }


    @Override
    public void click(int postion) {
        Address_List_Bean.DataBean dataBean = list.get(postion);
        Intent intent=new Intent();
        intent.putExtra("add_details",dataBean.getProvince()+dataBean.getCity()+dataBean.getCounry()+dataBean.getDetailAddr());
        intent.putExtra("id",list.get(postion).getId());
        intent.putExtra("long",list.get(postion).getLongitude()+"");
        intent.putExtra("la",list.get(postion).getLatitude()+"");
        setResult(RESULT_OK,intent);
        finish();
    }
}
