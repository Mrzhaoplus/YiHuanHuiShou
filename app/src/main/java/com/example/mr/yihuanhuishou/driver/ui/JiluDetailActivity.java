package com.example.mr.yihuanhuishou.driver.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.JiLu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.ZVZR_jilu_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiluDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String type;
    List<JiLu_Bean.DataBean> list = new ArrayList<>();
    List<ZVZR_jilu_Bean.DataBean> mlist = new ArrayList<>();
    private JiluAdapter jiluAdapter;
    private int state;
    private int pageno=1;
    private int pagesize=10;
    private String dtype;
    private JiluAdapter1 jiluAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jilu_detail);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        dtype = getIntent().getStringExtra("dtype");
        state = getIntent().getIntExtra("state", 0);
        initView();
        list.clear();
        mlist.clear();
        infoview();
    }

    private void infoview() {
        if(state==1){
            SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("currencyType","0");
            params.put("type",type);
            OkGo.<JiLu_Bean>post(MyUrls.BASEURL + "/recyclers/info/transactionRecord")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<JiLu_Bean>(JiluDetailActivity.this, JiLu_Bean.class) {
                        @Override
                        public void onSuccess(Response<JiLu_Bean> response) {
                            JiLu_Bean body = response.body();
                            String code = body.getCode()+"";
                            if (code.equals("200")) {
                                List<JiLu_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                jiluAdapter.notifyDataSetChanged();

                            } else if (code.equals("201")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            }
                        }
                    });
        }else if(state==2){
            SharedPreferences sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.DrTOKEN,""));
            params.put("pageNum",pageno);
            params.put("pageSize",pagesize);
            params.put("type",dtype);
            OkGo.<ZVZR_jilu_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/driverRecord")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<ZVZR_jilu_Bean>(JiluDetailActivity.this, ZVZR_jilu_Bean.class) {
                        @Override
                        public void onSuccess(Response<ZVZR_jilu_Bean> response) {
                            ZVZR_jilu_Bean body = response.body();
                            String code = body.getCode()+"";
                            if (code.equals("200")) {
                                List<ZVZR_jilu_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    mlist.addAll(data);
                                }
                                jiluAdapter1.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(JiluDetailActivity.this, body.getMsg());
                            }
                        }
                    });
        }



    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        if (TextUtils.equals("0",type)) titleContentTv.setText("收入记录");
        if (TextUtils.equals("1",type))titleContentTv.setText("支出记录");
         if(state==1){
             recyclerView.setNestedScrollingEnabled(false);
             recyclerView.setLayoutManager(new LinearLayoutManager(this));
             jiluAdapter = new JiluAdapter(R.layout.item_jilu_layout,list);
             recyclerView.setAdapter(jiluAdapter);
         }else if(state==2){
             recyclerView.setNestedScrollingEnabled(false);
             recyclerView.setLayoutManager(new LinearLayoutManager(this));
             jiluAdapter1 = new JiluAdapter1(R.layout.item_jilu_layout, mlist);
             recyclerView.setAdapter(jiluAdapter1);
         }


    }

    private class JiluAdapter extends BaseQuickAdapter<JiLu_Bean.DataBean,BaseViewHolder>{

        public JiluAdapter(int layoutResId, @Nullable List<JiLu_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, JiLu_Bean.DataBean item) {
            TextView title = helper.getView(R.id.item_content_tv);
            TextView data = helper.getView(R.id.item_date_tv);
            long createDate = item.getCreateDate();
            String dateToString = getDateToString(String.valueOf(createDate / 1000));
            data.setText(dateToString);
            String content = item.getContent();
            String[] split = content.split("，");
            title.setText(split[1]+item.getAmount()+"元");

        }
    }
    private class JiluAdapter1 extends BaseQuickAdapter<ZVZR_jilu_Bean.DataBean,BaseViewHolder>{

        public JiluAdapter1(int layoutResId, @Nullable List<ZVZR_jilu_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ZVZR_jilu_Bean.DataBean item) {
            TextView title = helper.getView(R.id.item_content_tv);
            TextView data = helper.getView(R.id.item_date_tv);
            long createDate = item.getCreateDate();
            String dateToString = getDateToString(String.valueOf(createDate / 1000));
            data.setText(dateToString);
            String content = item.getContent();
                title.setText(content+item.getAmount()+"元");
        }
    }
    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
