package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Pop_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Dingdan_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dingdan_DetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.shang_qx)
    TextView quxiao;
    @BindView(R.id.shang_bh)
    TextView bianhao;
    @BindView(R.id.shang_name)
    TextView name;
    @BindView(R.id.shang_ly)
    TextView laiyuan;
    @BindView(R.id.shang_lx)
    TextView leixing;
    @BindView(R.id.shang_zl)
    TextView zhongliang;
    @BindView(R.id.shang_ms)
    TextView miaoshu;
    @BindView(R.id.shang_dz)
    TextView address;
    @BindView(R.id.shang_time)
    TextView time;
    @BindView(R.id.shang_qr)
    Button queren;
    @BindView(R.id.shang_state)
    TextView state;
    private PopupWindow popupWindow;
    private View inflate;
    private ListView list_view;
    private LinearLayout view02;
    private TextView unit;
    private SharedPreferences sp;
    private int id;
    private String name1;
    private String add;
    private int aaaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan__details);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        name1 = getIntent().getStringExtra("name");
        add = getIntent().getStringExtra("address");
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        beak.setOnClickListener(this);
        queren.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        Log.e("====================",id+"");
        initview();
    }

    private void initview() {
        HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<Recy_Dingdan_Details_Bean>get(MyUrls.BASEURL + "/resident/residentOrder/orderInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Recy_Dingdan_Details_Bean>(Dingdan_DetailsActivity.this, Recy_Dingdan_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Recy_Dingdan_Details_Bean> response) {
                        Recy_Dingdan_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            bianhao.setText(body.getData().getOrderNumber());
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
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
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
            case R.id.shang_qx:
                shumaDialog1(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.shang_qr:
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
        }

    }
    private void shumaDialog1(int grary, int animationStyle) {
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
                clent(trim,id);
                dialog.dismiss();
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

    private void clent(String trim, int id) {

        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        params.put("cancelEeason",trim);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/recyclersCancelWasteOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Dingdan_DetailsActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        }

                    }
                });
    }


    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.qrsh_pop)
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
        final TextView shuliang = dialog.getView(R.id.shuliang);
        final TextView danjia = dialog.getView(R.id.danjia);
        final TextView zongjia = dialog.getView(R.id.zongjia);
        RadioGroup radiogroup = dialog.getView(R.id.rg);
        RadioButton rbt_shi = dialog.getView(R.id.rbt_shi);
        RadioButton rbt_fou = dialog.getView(R.id.rbt_fou);

        shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(danjia.getText().toString())){
                    double v = Double.parseDouble(editable.toString());
                    double v1 = Double.parseDouble(danjia.getText().toString());
                    String string = doubleToString((v * v1));
                    zongjia.setText(string);
                }
            }
        });
        danjia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(shuliang.getText().toString())){
                    double v = Double.parseDouble(editable.toString());
                    double v1 = Double.parseDouble(shuliang.getText().toString());
                    String string = doubleToString((v * v1));
                    zongjia.setText(string);
                }
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbt_shi:
                        aaaa = 1;
                        break;
                    case R.id.rbt_fou:
                        aaaa = 0;
                        break;
                }
            }
        });
        unit = dialog.getView(R.id.unit);
        view02 = dialog.getView(R.id.view_02);
        TextView text_pause = dialog.getView(R.id.text_pause);
        //pop框
        view02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoview();
            }
        });
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String shu = shuliang.getText().toString().trim();
                String dan = danjia.getText().toString().trim();
                String zong = zongjia.getText().toString().trim();
                String danwei = unit.getText().toString();
                if(!TextUtils.isEmpty(shu)&&!TextUtils.isEmpty(zong)&&!TextUtils.isEmpty(dan)){
                    /*int i = Integer.parseInt(shu);
                    int i1 = Integer.parseInt(dan);
                    if(i>0&&i1>0){
                    }else{
                        ToastUtils.getToast(getActivity(),"数量或者单价不可为空");
                    }*/
                    queren(shu,dan,zong,danwei,aaaa);
                    dialog.dismiss();
                }else{
                    ToastUtils.getToast(Dingdan_DetailsActivity.this,"格式不符");
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

    private void queren(String shu,String dan,String zong,String danwei,int aaaa) {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        params.put("count",shu);
        params.put("unitPrice",dan);
        params.put("totalMoney",zong);
        params.put("isGiveCurrency",aaaa);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/receipt")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Dingdan_DetailsActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Dingdan_DetailsActivity.this, body.getMsg());
                        }
                    }
                });

    }

    private void infoview() {
        if (popupWindow == null) {
            inflate = LayoutInflater.from(this).inflate(R.layout.popwindow_listview, null);
            list_view = inflate.findViewById(R.id.list_view);
            popupWindow = new PopupWindow(inflate, 180, RadioGroup.LayoutParams.WRAP_CONTENT);
        }
        final List<String> list = new ArrayList<>();
        list.add("个");
        list.add("包");
        list.add("件");
        list.add("kg");
        list.add("g");
        list.add("mg");
        Pop_Adapter pop_adapter = new Pop_Adapter(this, list);
        list_view.setAdapter(pop_adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view02, 0, 0);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.dismiss();
                unit.setText(list.get(i));
            }
        });
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
