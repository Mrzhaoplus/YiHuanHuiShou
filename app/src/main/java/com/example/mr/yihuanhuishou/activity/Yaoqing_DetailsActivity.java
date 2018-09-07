package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Firm_Message_Itme_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.My_Massage_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Yaoqing_DetailsActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    List<Firm_Message_Itme_Bean.DataBean> list = new ArrayList<>();
    @BindView(R.id.spring_view)
    SpringView spView;
    private SharedPreferences sp;
    private int pageNo = 1;
    private int pagesize = 10;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaoqing__details);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initdata();
        list.clear();
        infoview();
    }

    private void infoview() {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("pageNum", pageNo);
        params3.put("pageSize", pagesize);
        OkGo.<Firm_Message_Itme_Bean>get(MyUrls.BASEURL + "/driverMine/CompanyManageList")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Firm_Message_Itme_Bean>(this, Firm_Message_Itme_Bean.class) {
                    @Override
                    public void onSuccess(Response<Firm_Message_Itme_Bean> response) {
                        Firm_Message_Itme_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Firm_Message_Itme_Bean.DataBean> dataList = body.getData();
                            if (pageNo > 1) {
                                if (dataList.size() > 0) {
                                    list.addAll(dataList);
                                }
                            } else {
                                list.addAll(dataList);
                            }

                            messageAdapter.notifyDataSetChanged();
                        }
                    }
                });


    }

    private void initdata() {
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //无下划线
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        messageAdapter = new MessageAdapter(R.layout.yaoqing_details_adapter, list);
        recyView.setAdapter(messageAdapter);

        //刷新加载
        spView.setType(SpringView.Type.FOLLOW);
        spView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        pageNo=1;
                        pagesize=10;
                        infoview();
                    }
                },0);
                spView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        pagesize=pagesize+10;
                        infoview();
                    }
                },0);
                spView.onFinishFreshAndLoad();
            }
        });
        spView.setFooter(new DefaultFooter(this));
        spView.setHeader(new DefaultHeader(this));
    }
    private class MessageAdapter extends BaseQuickAdapter<Firm_Message_Itme_Bean.DataBean, BaseViewHolder> {

        public MessageAdapter(int layoutResId, @Nullable List<Firm_Message_Itme_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Firm_Message_Itme_Bean.DataBean item) {
            TextView time = helper.getView(R.id.time);
            TextView content = helper.getView(R.id.content);
            TextView jiaru = helper.getView(R.id.jiaru);
            LinearLayout tongyi= helper.getView(R.id.tongyi);
            LinearLayout quxiao= helper.getView(R.id.quxiao);
            long createDate = item.getCreateDate();
            String dateToString = getDateToString(String.valueOf(createDate / 1000));
            time.setText(dateToString);
               content.setText(item.getContent());
            int status = item.getStatus();
            if(status==4){
                tongyi.setVisibility(View.GONE);
                quxiao.setVisibility(View.GONE);
                jiaru.setVisibility(View.VISIBLE);
                jiaru.setText("已拒绝");
                jiaru.setTextColor(getResources().getColor(R.color.red));
            }else if(status==5){
                tongyi.setVisibility(View.GONE);
                quxiao.setVisibility(View.GONE);
                jiaru.setVisibility(View.VISIBLE);
                jiaru.setText("已同意");
                jiaru.setTextColor(getResources().getColor(R.color.colorAccent));
            }else{
                tongyi.setVisibility(View.VISIBLE);
                quxiao.setVisibility(View.VISIBLE);
                jiaru.setVisibility(View.GONE);
            }
            tongyi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tongyijiaru(item.getId());
                }
            });
            quxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shumaDialog1(Gravity.CENTER,R.style.Alpah_aniamtion,item.getId());
                }
            });
        }
    }
    private void shumaDialog1(int grary, int animationStyle, final int id) {
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
                if(!TextUtils.isEmpty(trim)){
                    quxiao(trim,id);
                    dialog.dismiss();
                }else{
                    ToastUtils.getToast(Yaoqing_DetailsActivity.this,"取消原因不能为空！");
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

    private void quxiao(String trim, int id) {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("id", id);
        params3.put("content", trim);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driverMine/notJoin")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Jiaoyan_Bean>(this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(Yaoqing_DetailsActivity.this,body.getMsg());
                            infoview();
                        }else{
                            ToastUtils.getToast(Yaoqing_DetailsActivity.this,body.getMsg());
                        }
                    }
                });
    }

    private void tongyijiaru(int id) {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("id", id);
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driverMine/addJoin")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Jiaoyan_Bean>(this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(Yaoqing_DetailsActivity.this,body.getMsg());
                            infoview();
                        }else{
                            ToastUtils.getToast(Yaoqing_DetailsActivity.this,body.getMsg());
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
