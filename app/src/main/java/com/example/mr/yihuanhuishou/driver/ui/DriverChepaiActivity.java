package com.example.mr.yihuanhuishou.driver.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Chepai_Cord_itme_bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Geren_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Xiugai_chepai_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverChepaiActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.chepai_cord)
    EditText chepaiCord;
    private SharedPreferences sp;
    List<Chepai_Cord_itme_bean.DataBean.NotPlatelistBean> list = new ArrayList<>();
    private ChepaiAdapter chepaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_chepai);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
        initdata();
    }

    private void initdata() {
        list.clear();
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        OkGo.<Chepai_Cord_itme_bean>get(MyUrls.BASEURL + "/driver/driverPlate")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Chepai_Cord_itme_bean>(DriverChepaiActivity.this, Chepai_Cord_itme_bean.class) {
                    @Override
                    public void onSuccess(Response<Chepai_Cord_itme_bean> response) {
                        Chepai_Cord_itme_bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Chepai_Cord_itme_bean.DataBean.NotPlatelistBean> notPlatelist = body.getData().getNotPlatelist();
                            if(notPlatelist.size()>0){
                                list.addAll(notPlatelist);
                            }
                            chepaiAdapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        }
                    }
                });


    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的车牌号");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("确认");

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chepaiAdapter = new ChepaiAdapter(R.layout.item_chepai_layout, list);
        recyclerView.setAdapter(chepaiAdapter);
    }

    private class ChepaiAdapter extends BaseQuickAdapter<Chepai_Cord_itme_bean.DataBean.NotPlatelistBean, BaseViewHolder> {

        public ChepaiAdapter(int layoutResId, @Nullable List<Chepai_Cord_itme_bean.DataBean.NotPlatelistBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Chepai_Cord_itme_bean.DataBean.NotPlatelistBean item) {
            TextView cord_name = helper.getView(R.id.item_name_tv);
            cord_name.setText(item.getDriverPlateNumber());
            ImageView cha = helper.getView(R.id.item_del_tv);
            cha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cord_clear(item.getId());
                }
            });
        }
    }

    private void cord_clear(int id) {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("plateId", id);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driver/deletePlate")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Jiaoyan_Bean>(DriverChepaiActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                            initdata();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        }
                    }
                });

    }

    @OnClick({R.id.title_back_iv, R.id.title_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                String cord = chepaiCord.getText().toString().trim();
                boolean carnumberNO = Validator.isCarnumberNO(cord);
                if(!TextUtils.isEmpty(cord)){
                    if(cord.length()>7||cord.length()<7){
                        ToastUtils.getToast(DriverChepaiActivity.this,"请输入正确格式的车牌号！");
                    }else{
                       if(carnumberNO){
                           infoview(cord);
                       }else{
                           ToastUtils.getToast(DriverChepaiActivity.this,"请输入正确格式的车牌号！");
                       }
                    }
                }else{
                    ToastUtils.getToast(DriverChepaiActivity.this,"请输入车牌号！");
                }
                break;
        }
    }
    private void infoview(String cord) {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("newPlateNumber", cord);
        OkGo.<Xiugai_chepai_Bean>post(MyUrls.BASEURL + "/driver/updatePlate")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Xiugai_chepai_Bean>(DriverChepaiActivity.this, Xiugai_chepai_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xiugai_chepai_Bean> response) {
                        Xiugai_chepai_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(DriverChepaiActivity.this, body.getMsg());
                        }
                    }
                });
    }
}
