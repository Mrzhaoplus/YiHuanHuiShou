package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Driver_PSZ_DetailsActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
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
import butterknife.OnClick;

public class QiangdanDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.fplx_tv)
    TextView fplxTv;
    @BindView(R.id.fpsl_tv)
    TextView fpslTv;
    @BindView(R.id.qhsj_tv)
    TextView qhsjTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.fpzj_tv)
    TextView fpzjTv;
    @BindView(R.id.lijiqiangdan_tv)
    TextView lijiqiangdanTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private int id;
    private SharedPreferences sp;
    private String state;
    private String phoneNumber;
    private BaseDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiangdan_detail);
        id = getIntent().getIntExtra("id", 0);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        ButterKnife.bind(this);
        initView();
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
                .execute(new DialogCallback<Order_Details_Bean>(QiangdanDetailActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.DataBean data = body.getData();
                            fplxTv.setText(data.getVarieties());
                            fpslTv.setText(data.getCount()+""+data.getUnit());
                            fpzjTv.setText(data.getTotalPrice()+"元");
                            nameTv.setText(data.getEecRecyclersaddr().getName());
                            addressTv.setText(data.getEecRecyclersaddr().getDetailAddr());
                            Double juli = (double)data.getDistance() / 1000;
                            String string = doubleToString(juli);
                            juliTv.setText(string+"Km");
                               long receiptDate = data.getCreateDate();
                                String dateToString = getDateToString(String.valueOf(receiptDate/1000));
                                qhsjTv.setText(dateToString);
                                state = data.getState();
                            if(state.equals("0")){
                                lijiqiangdanTv.setText("立即抢单");
                            }else if(state.equals("1")){
                                lijiqiangdanTv.setText("联系回收人员");
                            }
                            phoneNumber = data.getEecRecyclersaddr().getPhoneNumber();
                        } else {
                            ToastUtils.getToast(QiangdanDetailActivity.this, body.getMsg());
                        }
                    }
                });


    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("配送需求详情");
    }

    @OnClick({R.id.title_back_iv, R.id.lijiqiangdan_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.lijiqiangdan_tv:
                String string = lijiqiangdanTv.getText().toString();
                if(string.equals("立即抢单")){
                    qianmgdan();
                }else{
                    hujiao(Gravity.CENTER,R.style.Alpah_aniamtion);
                }
                break;
        }
    }
    private void hujiao(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.hujiao_pop)
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
        TextView quxiao = dialog.getView(R.id.text_pause);
        final TextView phone = dialog.getView(R.id.tell);
        phone.setText(phoneNumber);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:"+phone.getText().toString());
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(HuiShouCompanyDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        });
        dialog.show();

    }

    private void qianmgdan() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("id",id);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/placeOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(QiangdanDetailActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                             showPayFinishDialog("抢单成功",true);

                        } else {
                            ToastUtils.getToast(QiangdanDetailActivity.this, body.getMsg());
                            showPayFinishDialog("抢单失败",false);
                        }
                    }
                });

    }

    private void showPayFinishDialog(String type,boolean isSuccess) {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay_finish)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.nomal_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        mDialog.setCancelable(false);

        ImageView imageView = mDialog.getView(R.id.item_img_iv);
        TextView contentTv = mDialog.getView(R.id.item_content_tv);
        TextView queryTv = mDialog.getView(R.id.item_query_tv);
        if (isSuccess){
            imageView.setImageResource(R.drawable.driver_qhcg);
            contentTv.setText(type);
        }else {
            imageView.setImageResource(R.drawable.driver_qhsb_iv);
            contentTv.setText(type);
        }
        mDialog.show();
        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                lijiqiangdanTv.setText("联系回收人员");
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
    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
