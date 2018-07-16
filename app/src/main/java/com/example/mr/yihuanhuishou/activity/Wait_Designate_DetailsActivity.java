package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wait_Designate_DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.desi_bh)
    TextView number;
    @BindView(R.id.desi_state)
    TextView state;
    @BindView(R.id.desi_sort)
    TextView sort;
    @BindView(R.id.desi_weight)
    TextView weight;
    @BindView(R.id.desi_zj)
    TextView price;
    @BindView(R.id.desi_fa_time)
    TextView fa_time;
    @BindView(R.id.desi_zhipai)
    Button designate;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait__designate__details);
        ButterKnife.bind(this);
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
                .execute(new DialogCallback<Order_Details_Bean>(Wait_Designate_DetailsActivity.this, Order_Details_Bean.class) {
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
                            price.setText(demandinfo.getTotalPrice()+"元");
                            long createDate = recoveryOrder.getCreateDate();
                            String dateToString = getDateToString(String.valueOf(createDate/1000));
                            fa_time.setText(dateToString);
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Wait_Designate_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Wait_Designate_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Wait_Designate_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Wait_Designate_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Wait_Designate_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initview() {
        beak.setOnClickListener(this);
        designate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.desi_zhipai:
                ToastUtils.getToast(this,"已经指派");
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
