package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Clame_Goods_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.take_bh)
    TextView bianhao;
    @BindView(R.id.take_state)
    TextView state;
    @BindView(R.id.take_lx)
    TextView sort;
    @BindView(R.id.take_zl)
    TextView weight;
    @BindView(R.id.take_zj)
    TextView price;
    @BindView(R.id.take_name)
    TextView name;
    @BindView(R.id.take_tel)
    TextView tel;
    @BindView(R.id.take_dizhi)
    TextView address;
    @BindView(R.id.take_qu_time)
    TextView qu_time;
    @BindView(R.id.take_time)
    TextView fa_time;
    @BindView(R.id.take_jie_time)
    TextView jie_time;
    @BindView(R.id.take_Lpnum)
    TextView Lpnum;
    @BindView(R.id.cancle_bill)
    Button cancle_bill;
    private SharedPreferences sp;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clame__goods__details);
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
                .execute(new DialogCallback<Order_Details_Bean>(Clame_Goods_DetailsActivity.this, Order_Details_Bean.class) {
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
                            qu_time.setText(getDateToString(String.valueOf(comeDate/1000)));
                            long receiptDate = recoveryOrder.getReceiptDate();
                            jie_time.setText(getDateToString(String.valueOf(receiptDate/1000)));
                            Lpnum.setText(recoveryOrder.getCarPlate());

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
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
        cancle_bill.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.cancle_bill:
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
        }
    }
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.quxiao_pop)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        TextView text_pause = dialog.getView(R.id.text_pause);
        final EditText reson = dialog.getView(R.id.reason);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = reson.getText().toString().trim();
                if(TextUtils.isEmpty(trim)){
                    ToastUtils.getToast(Clame_Goods_DetailsActivity.this,"请输入取消原因！");
                }else{
                    initdata(trim,id);
                    dialog.dismiss();
                }

            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initdata(String reson, int id) {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        params.put("reason",reson);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/cancel")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Clame_Goods_DetailsActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            EventBus.getDefault().postSticky(new Event_fragment(1));
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
                            finish();
                        } else  {
                            ToastUtils.getToast(Clame_Goods_DetailsActivity.this, body.getMsg());
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
