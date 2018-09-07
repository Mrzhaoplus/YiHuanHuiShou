package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Driver_Cancle_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.cancle_bh)
    TextView bianhao;
    @BindView(R.id.cancle_state)
    TextView state;
    @BindView(R.id.cancle_lx)
    TextView sort;
    @BindView(R.id.cancle_zl)
    TextView weight;
    @BindView(R.id.cancle_zj)
    TextView zong_price;
    @BindView(R.id.cancle_name)
    TextView name;
    @BindView(R.id.cancle_tel)
    TextView tel;
    @BindView(R.id.cancle_dizhi)
    TextView address;
    @BindView(R.id.cancle_qu_time)
    TextView qu_time;
    @BindView(R.id.cancle_time)
    TextView fa_time;
    @BindView(R.id.cancle_cancle_time)
    TextView quxiao_time;
    @BindView(R.id.cancle_reason)
    TextView reason;
    private int id;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__cancle__details);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        id = getIntent().getIntExtra("id", 0);
        initview();
        infoview();
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<Order_Details_Bean>post(MyUrls.BASEURL + "/recyclers/order/single")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Details_Bean>(Driver_Cancle_DetailsActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.EecDemandinfoBean demandinfo = body.getEecDemandinfo();
                            Order_Details_Bean.EecRecoveryOrderBean recoveryOrder = body.getEecRecoveryOrder();
                            bianhao.setText(recoveryOrder.getOrderNumber());
                            sort.setText(recoveryOrder.getVarieties());
                            weight.setText(recoveryOrder.getCount()+""+recoveryOrder.getUnit());
                            zong_price.setText(demandinfo.getTotalPrice()+"元");
                            address.setText(demandinfo.getDetailAddress());
                            long createDate = recoveryOrder.getCreateDate();
                            String dateToString = getDateToString(String.valueOf(createDate/1000));
                            fa_time.setText(dateToString);
                            long cancelDate = recoveryOrder.getCancelDate();
                            String dateToString1 = getDateToString(String.valueOf(cancelDate / 1000));
                            quxiao_time.setText(dateToString1);
                             reason.setText(recoveryOrder.getCancelEeason());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Driver_Cancle_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Driver_Cancle_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Driver_Cancle_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Driver_Cancle_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Driver_Cancle_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }
    private void initview() {
        beak.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
        }
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
