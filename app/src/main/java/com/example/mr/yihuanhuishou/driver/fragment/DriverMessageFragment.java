package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Yaoqing_DetailsActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverMessageActivity;
import com.example.mr.yihuanhuishou.driver.ui.PersonInfoActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Geren_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Gonggao_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.My_Massage_Bean;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/29.
 */

public class DriverMessageFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.sp_view)
    SpringView spView;
    Unbinder unbinder;
    private int pageNo=1;
    private int pagesize=10;
    List<My_Massage_Bean.DataBean.DataListBean> list = new ArrayList<>();
    private SharedPreferences sp;
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
        OkGo.<My_Massage_Bean>get(MyUrls.BASEURL + "/driverMine/myNotice")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<My_Massage_Bean>(getActivity(), My_Massage_Bean.class) {
                    @Override
                    public void onSuccess(Response<My_Massage_Bean> response) {
                        My_Massage_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<My_Massage_Bean.DataBean.DataListBean> dataList = body.getData().getDataList();
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }

                            messageAdapter.notifyDataSetChanged();
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
        if(list.get(position).getType().equals("3")){
            Intent intent = new Intent(getActivity(), Yaoqing_DetailsActivity.class);
            startActivity(intent);
        }

    }

    private class MessageAdapter extends BaseQuickAdapter<My_Massage_Bean.DataBean.DataListBean,BaseViewHolder>{

        public MessageAdapter(int layoutResId, @Nullable List<My_Massage_Bean.DataBean.DataListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, My_Massage_Bean.DataBean.DataListBean item) {
            TextView title = helper.getView(R.id.item_content_tv);
            title.setText(item.getContent());
            String isRead = item.getState();
            if(isRead.equals("0")){
                helper.setVisible(R.id.dian,true);
            }else if(isRead.equals("1")){
                helper.setVisible(R.id.dian,false);
            }
        }
    }
}
