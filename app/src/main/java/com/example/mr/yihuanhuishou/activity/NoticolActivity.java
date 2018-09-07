package com.example.mr.yihuanhuishou.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.NoticolAdapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XitongMassage_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticolActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recy_view;
    @BindView(R.id.sp_view)
    SpringView sp_view;
    List<XitongMassage_Bean.DataListBean> list=new ArrayList<>();
    private int pageNo=1;
    private int pageCount=10;
    private NoticolAdapter noticolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticol);
        ButterKnife.bind(this);
        initdata();

    }

    @Override
    protected void onResume() {
        super.onResume();
        infoview();
    }

    private void infoview() {
        list.clear();
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN,""));
        params.put("pageNo",pageNo);
        params.put("pageCount",pageCount);
        OkGo.<XitongMassage_Bean>post(MyUrls.BASEURL + "/recyclers/info/systemNotice")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<XitongMassage_Bean>(NoticolActivity.this, XitongMassage_Bean.class) {
                    @Override
                    public void onSuccess(Response<XitongMassage_Bean> response) {
                        XitongMassage_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<XitongMassage_Bean.DataListBean> dataList = body.getDataList();
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }
                            noticolAdapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(NoticolActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(NoticolActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(NoticolActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(NoticolActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(NoticolActivity.this, body.getMsg());
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

        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo=1;
                        pageCount=10;
                        infoview();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        pageCount=10+pageCount;
                        infoview();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(this));
        recy_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        noticolAdapter = new NoticolAdapter(this,list);
        recy_view.setAdapter(noticolAdapter);


    }
}
