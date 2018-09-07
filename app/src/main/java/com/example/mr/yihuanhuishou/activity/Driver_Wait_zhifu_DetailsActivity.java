package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Driver_Wait_zhifu_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.pay_bh)
    TextView bianhao;
    @BindView(R.id.pay_state)
    TextView state;
    @BindView(R.id.pay_lx)
    TextView sort;
    @BindView(R.id.pay_zl)
    TextView weight;
    @BindView(R.id.pay_zj)
    TextView price;
    @BindView(R.id.pay_name)
    TextView name;
    @BindView(R.id.pay_tel)
    TextView tel;
    @BindView(R.id.pay_dizhi)
    TextView address;
    @BindView(R.id.pay_qu_time)
    TextView qu_time;
    @BindView(R.id.pay_time)
    TextView fa_time;
    @BindView(R.id.pay_jie_time)
    TextView jie_time;
    @BindView(R.id.pay_qu_time2)
    TextView qu_time2;
    @BindView(R.id.pay_success_time)
    TextView success_time;
    @BindView(R.id.pay_Lpnum)
    TextView Lpnum;
    private SharedPreferences sp;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__wait_zhifu__details);
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
                .execute(new DialogCallback<Order_Details_Bean>(Driver_Wait_zhifu_DetailsActivity.this, Order_Details_Bean.class) {
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
                            price.setText(demandinfo.getTotalPrice()+"元");
                            address.setText(demandinfo.getDetailAddress());
                            long createDate = recoveryOrder.getCreateDate();
                            String dateToString = getDateToString(String.valueOf(createDate/1000));
                            fa_time.setText(dateToString);
                            long comeDate = demandinfo.getComeDate();
                            qu_time2.setText(getDateToString(String.valueOf(comeDate/1000)));
                            long receiptDate = recoveryOrder.getReceiptDate();
                            jie_time.setText(getDateToString(String.valueOf(receiptDate/1000)));
                            Lpnum.setText(recoveryOrder.getCarPlate());
                            long endtime = recoveryOrder.getEndtime();
                            success_time.setText(getDateToString(String.valueOf(endtime/1000)));
                            String comeType = demandinfo.getComeType();
                            if(comeType.equals("1")){
                                qu_time.setText("预约时间");
                            }else{
                                qu_time.setText("立即上门");
                            }
                        } else {
                            ToastUtils.getToast(Driver_Wait_zhifu_DetailsActivity.this, body.getMsg());
                        }
                    }
                    @Override
                    public void onError(Response<Order_Details_Bean> response) {
                        super.onError(response);
                        Order_Details_Bean body = response.body();
                        Log.e("=================","失败"+body);
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
