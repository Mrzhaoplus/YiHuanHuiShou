package com.example.mr.yihuanhuishou.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Fire_Msg_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Massage_Bean;
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
 * Created by Mr赵 on 2018/5/14.
 */

public class Frie_Msg_Fragment extends BaseFragment {
    private SpringView sp_view;
    private RecyclerView recy_view;
    List<Massage_Bean.DataListBean> list=new ArrayList<>();
    private int pageNo=1;
    private int pageCount=10;
    private Fire_Msg_Adapter fire_msg_adapter;

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
        initdata();

    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        infoview();
    }

    private void infoview() {

        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN,""));
        params.put("type","2");
        params.put("pageNo",pageNo);
        params.put("pageCount",pageCount);
        OkGo.<Massage_Bean>post(MyUrls.BASEURL + "/recyclers/info/myNews")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Massage_Bean>(getActivity(), Massage_Bean.class) {
                    @Override
                    public void onSuccess(Response<Massage_Bean> response) {
                        Massage_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Massage_Bean.DataListBean> dataList = body.getDataList();
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }
                            fire_msg_adapter.notifyDataSetChanged();
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
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        fire_msg_adapter = new Fire_Msg_Adapter(getActivity(), list);
        recy_view.setAdapter(fire_msg_adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
