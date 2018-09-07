package com.example.mr.yihuanhuishou.driver.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.siji.Gonggao_Bean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/29.
 */

public class DriverGonggaoFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.sp_view)
    SpringView spView;
    Unbinder unbinder;
    private int pageNo=1;
    private int pagesize=10;
    private SharedPreferences sp;
    List<Gonggao_Bean.DataBean.DataListBean> list = new ArrayList<>();
    private MessageAdapter messageAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment,null);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        list.clear();
        initData();
        infoview();
        return view;
    }

    private void infoview() {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("pageNo", pageNo);
        params3.put("pageCount", pagesize);
        OkGo.<Gonggao_Bean>get(MyUrls.BASEURL + "/driverMine/systemNotice")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Gonggao_Bean>(getActivity(), Gonggao_Bean.class) {
                    @Override
                    public void onSuccess(Response<Gonggao_Bean> response) {
                        Gonggao_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Gonggao_Bean.DataBean.DataListBean> dataList = body.getData().getDataList();
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }
                            messageAdapter.notifyDataSetChanged();
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

    private void initData() {
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
        spView.setFooter(new DefaultFooter(getActivity()));
        spView.setHeader(new DefaultHeader(getActivity()));
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(mContext));
        messageAdapter = new MessageAdapter(R.layout.item_message,list);
        recyView.setAdapter(messageAdapter);
        messageAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private class MessageAdapter extends BaseQuickAdapter<Gonggao_Bean.DataBean.DataListBean,BaseViewHolder>{

        public MessageAdapter(int layoutResId, @Nullable List<Gonggao_Bean.DataBean.DataListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Gonggao_Bean.DataBean.DataListBean item) {

            TextView title = helper.getView(R.id.item_content_tv);
            title.setText(item.getTitle());
            int isRead = item.getIsRead();
            if(isRead==0){
                helper.setVisible(R.id.dian,true);
            }else{
                helper.setVisible(R.id.dian,false);
            }
        }
    }
}
