package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driver_qx_DetailsActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.title_more_iv)
    ImageView titleMoreIv;
    @BindView(R.id.title_more_bj)
    ImageView titleMoreBj;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.ddbh_tv)
    TextView ddbhTv;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.bzdtm_tv)
    TextView bzdtmTv;
    @BindView(R.id.fplx_tv)
    TextView fplxTv;
    @BindView(R.id.fpsl_tv)
    TextView fpslTv;
    @BindView(R.id.fpzj_tv)
    TextView fpzjTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.fbsj_tv)
    TextView fbsjTv;
    @BindView(R.id.fbsj_ll)
    LinearLayout fbsjLl;
    @BindView(R.id.right_tv)
    TextView rightTv;
    private SharedPreferences sp;
    private String state;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_qx__details);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        state = getIntent().getStringExtra("state");
        id = getIntent().getIntExtra("id", 0);
        initview();
        infoview();
    }

    private void infoview() {

        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("id",id);
        params.put("latitude",sp.getString(GGUtils.Drlat,""));
        params.put("longitude",sp.getString(GGUtils.Drlon,""));
        if(TextUtils.isEmpty(sp.getString(GGUtils.Drlat,""))){
            params.put("state",0);
        }else{
            params.put("state",1);
        }

        OkGo.<Order_Details_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/orderInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Details_Bean>(Driver_qx_DetailsActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.DataBean data = body.getData();
                            ddbhTv.setText(data.getOrderNumber());
                            bzdtmTv.setText(data.getBarCode());
                            fplxTv.setText(data.getVarieties());
                            fpslTv.setText(data.getCount()+""+data.getUnit());
                            fpzjTv.setText(data.getTotalPrice()+"元");
                            nameTv.setText(data.getEecRecyclersaddr().getName());
                            addressTv.setText(data.getEecRecyclersaddr().getDetailAddr());
                            Double i =(double) data.getDistance() / 1000;
                            String s = StringToDouble(i);
                            juliTv.setText(s+"公里");
                            long createDate = data.getCreateDate();
                            String dateToString1 = getDateToString(String.valueOf(createDate / 1000));
                            fbsjTv.setText(dateToString1);

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }
    public static String StringToDouble(double num){
        return new DecimalFormat("0.00").format(num);
    }
    private void initview() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("订单详情");
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scdd();
            }
        });
        titleBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void scdd() {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("id", id);
        OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/driver/residentOrder/deleteOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(Driver_qx_DetailsActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Driver_qx_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
