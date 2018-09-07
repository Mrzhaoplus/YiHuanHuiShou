package com.example.mr.yihuanhuishou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Dingdan_Details_Bean;
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

public class Wait_wancheng_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.zhi_bh)
    TextView biaohao;
    @BindView(R.id.zhi_name)
    TextView name;
    @BindView(R.id.zhi_ly)
    TextView laiyuan;
    @BindView(R.id.zhi_lx)
    TextView leixing;
    @BindView(R.id.zhi_zl)
    TextView zhongliang;
    @BindView(R.id.zhi_ms)
    TextView miaoshu;
    @BindView(R.id.zhi_dz)
    TextView address;
    @BindView(R.id.zhi_time)
    TextView time;
    @BindView(R.id.zhi_state)
    TextView state;
    @BindView(R.id.zhi_dj)
    TextView danjia;
    @BindView(R.id.zhi_price)
    TextView price;
    @BindView(R.id.zhi_yihuan)
    TextView yihuan;
    private int id;
    private String name1;
    private String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_wancheng__details);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        name1 = getIntent().getStringExtra("name");
        add = getIntent().getStringExtra("address");
        beak.setOnClickListener(this);
        infoview();
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<Recy_Dingdan_Details_Bean>get(MyUrls.BASEURL + "/resident/residentOrder/orderInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Recy_Dingdan_Details_Bean>(Wait_wancheng_DetailsActivity.this, Recy_Dingdan_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Recy_Dingdan_Details_Bean> response) {
                        Recy_Dingdan_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            biaohao.setText(body.getData().getOrderNumber());
                            name.setText(name1);
                            leixing.setText(body.getData().getEecWasteinfo().getVarieties());
                            zhongliang.setText(body.getData().getEecWasteinfo().getCount()+""+body.getData().getEecWasteinfo().getUnit());
                            miaoshu.setText(body.getData().getEecWasteinfo().getDetailInfo());
                            address.setText(add);
                            int comeType = body.getData().getComeType();
                            if(comeType==0){
                                long comeDate = body.getData().getComeData();
                                String dateToString = getDateToString(String.valueOf(comeDate / 1000));
                                time.setText(dateToString);
                            }else if(comeType==1){
                                time.setText("立即上门");
                            }
                            danjia.setText(body.getData().getUnitPrice()+"元/"+body.getData().getEecWasteinfo().getUnit());
                            zhongliang.setText(body.getData().getCount()+""+body.getData().getEecWasteinfo().getUnit());
                            price.setText(body.getData().getUnitPrice()+"元");
                            yihuan.setText(body.getData().getGiveCurrency()+"元");
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Wait_wancheng_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Wait_wancheng_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Wait_wancheng_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Wait_wancheng_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Wait_wancheng_DetailsActivity.this, body.getMsg());
                        }
                    }
                });
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
