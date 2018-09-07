package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Oneseif_Wait_pay_detailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
     @BindView(R.id.pay_bh)
    TextView number;
     @BindView(R.id.pay_state)
     TextView state;
     @BindView(R.id.pay_lx)
     TextView sort;
     @BindView(R.id.pay_zl)
     TextView weight;
     @BindView(R.id.pay_zj)
     TextView zprice;
     @BindView(R.id.pay_time)
     TextView fa_time;
     @BindView(R.id.pay_zhi_time)
     TextView zhi_time;
     @BindView(R.id.pay_firm)
     TextView firm;
     @BindView(R.id.pay_price)
     TextView wait_price;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneseif__wait_pay_details);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        initdata();
        infoview();
    }

    private void infoview() {
        /*HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<Order_Details_Bean>post(MyUrls.BASEURL + "/recyclers/order/single")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Details_Bean>(Oneseif_Wait_pay_detailsActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.EecDemandinfoBean demandinfo = body.getEecDemandinfo();
                            Order_Details_Bean.EecRecoveryOrderBean recoveryOrder = body.getEecRecoveryOrder();
                            number.setText(recoveryOrder.getOrderNumber());
                            sort.setText(recoveryOrder.getVarieties());
                            weight.setText(recoveryOrder.getCount()+""+recoveryOrder.getUnit());
                            zprice.setText(demandinfo.getTotalPrice()+"元");
                            long createDate = recoveryOrder.getCreateDate();
                            String dateToString = getDateToString(String.valueOf(createDate/1000));
                            fa_time.setText(dateToString);
                            firm.setText(recoveryOrder.getCompanyName());

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Oneseif_Wait_pay_detailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Oneseif_Wait_pay_detailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Oneseif_Wait_pay_detailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Oneseif_Wait_pay_detailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Oneseif_Wait_pay_detailsActivity.this, body.getMsg());
                        }
                    }
                });*/

    }

    private void initdata() {
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
