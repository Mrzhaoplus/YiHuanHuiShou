package com.example.mr.yihuanhuishou.driver.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.siji.Baozhuang_all_N_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author power
 * @date 2018/6/1 下午4:54
 * @description:
 */
public class PagerNotFragment extends BaseFragment {
    @BindView(R.id.zongji_tv)
    TextView zongjiTv;
    @BindView(R.id.first_recyclerView)
    RecyclerView firstRecyclerView;
    Unbinder unbinder;
    List<Baozhuang_all_N_Bean.DataBean.ListBeanX> firstList = new ArrayList<>();
    private FirstAdapter firstAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packge_not_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        infoview();
    }

    private void infoview() {
        firstList.clear();
        final SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params3.put("inboundOutbound","false");
        OkGo.<Baozhuang_all_N_Bean>get(MyUrls.BASEURL + "/driverMine/packList")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Baozhuang_all_N_Bean>(getActivity(), Baozhuang_all_N_Bean.class) {
                    @Override
                    public void onSuccess(Response<Baozhuang_all_N_Bean> response) {
                        Baozhuang_all_N_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            zongjiTv.setText("总计："+body.getData().getCount()+"个");
                            List<Baozhuang_all_N_Bean.DataBean.ListBeanX> list = body.getData().getList();
                            if(list.size()>0){
                                firstList.addAll(list);
                            }
                            firstAdapter.notifyDataSetChanged();

                        }
                    }
                });

    }

    private void initData() {
        firstRecyclerView.setNestedScrollingEnabled(false);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        firstAdapter = new FirstAdapter(R.layout.item_first_not_layout,firstList);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private class FirstAdapter extends BaseQuickAdapter<Baozhuang_all_N_Bean.DataBean.ListBeanX,BaseViewHolder>{

        public FirstAdapter(int layoutResId, @Nullable List<Baozhuang_all_N_Bean.DataBean.ListBeanX> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_all_N_Bean.DataBean.ListBeanX item) {
            RecyclerView secondRecycler = helper.getView(R.id.second_recyclerView);
            TextView shijian = helper.getView(R.id.shijian);
            long createDate = item.getCreateDate();
            String dateToString = getDateToString(String.valueOf(createDate / 1000));
            shijian.setText(dateToString);
            List<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean> secondList = item.getList();

            secondRecycler.setNestedScrollingEnabled(false);
            secondRecycler.setLayoutManager(new LinearLayoutManager(mContext));
            SecondAdapter secondAdapter = new SecondAdapter(R.layout.item_second_not_layout,secondList);
            secondRecycler.setAdapter(secondAdapter);
        }
    }

    private class SecondAdapter extends BaseQuickAdapter<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean,BaseViewHolder>{

        public SecondAdapter(int layoutResId, @Nullable List<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean item) {
            RecyclerView threeRecycler = helper.getView(R.id.three_recyclerView);
            TextView barname = helper.getView(R.id.barname);
            barname.setText(item.getEecVarietiesName());
            List<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean> threeList = item.getPackingBagList();
            threeRecycler.setNestedScrollingEnabled(false);
            threeRecycler.setLayoutManager(new GridLayoutManager(mContext,4));
            ThreeAdapter threeAdapter = new ThreeAdapter(R.layout.item_three_not_layout,threeList);
            threeRecycler.setAdapter(threeAdapter);
        }
    }
    private class ThreeAdapter extends BaseQuickAdapter<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean,BaseViewHolder>{

        public ThreeAdapter(int layoutResId, @Nullable List<Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_all_N_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean item) {
            TextView barcord = helper.getView(R.id.barcord);
            barcord.setText(item.getBarCode());
        }
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
