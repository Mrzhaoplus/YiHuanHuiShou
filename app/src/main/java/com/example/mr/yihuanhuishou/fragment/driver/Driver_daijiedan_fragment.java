package com.example.mr.yihuanhuishou.fragment.driver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.LogonActivity;
import com.example.mr.yihuanhuishou.activity.MainActivity;
import com.example.mr.yihuanhuishou.adapter.Driver_all_adapter;
import com.example.mr.yihuanhuishou.adapter.Driver_daijiedan_adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.jsonbean.Login_Bean;
import com.example.mr.yihuanhuishou.jsonbean.Order_Daijiedan_Bean;
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

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_daijiedan_fragment extends BaseFragment {
    private SpringView sp_view;
    private RecyclerView recy_view;
    private Driver_daijiedan_adapter driver_all_adapter;
    private SharedPreferences sp;
   List<Order_Daijiedan_Bean.DataBean>mList=new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        sp_view = contentView.findViewById(R.id.sp_view);
        recy_view = contentView.findViewById(R.id.recy_view);
        sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        initdata();
        infoview();
    }

    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("distribution","0");
        params.put("state","0");
        OkGo.<Order_Daijiedan_Bean>post(MyUrls.BASEURL + "/recyclers/order/recoveryList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Daijiedan_Bean>(getActivity(), Order_Daijiedan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Daijiedan_Bean> response) {
                        Order_Daijiedan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Order_Daijiedan_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mList.addAll(data);
                            }
                            driver_all_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }

                    }
                });


    }

    private void initdata() {
        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        driver_all_adapter = new Driver_daijiedan_adapter(getActivity(),mList);
        recy_view.setAdapter(driver_all_adapter);
    }
}
