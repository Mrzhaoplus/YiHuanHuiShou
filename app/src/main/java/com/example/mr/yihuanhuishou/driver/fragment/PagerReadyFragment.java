package com.example.mr.yihuanhuishou.driver.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.driver.ui.PersonInfoActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Baozhuang_Y_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Geren_Bean;
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
import butterknife.Unbinder;

/**
 * @author power
 * @date 2018/6/1 下午4:53
 * @description:
 */
public class PagerReadyFragment extends BaseFragment {
    @BindView(R.id.first_recyclerView)
    RecyclerView firstRecyclerView;
    Unbinder unbinder;
    List<Baozhuang_Y_Bean.DataBean> firstList = new ArrayList<>();
    private FirstAdapter firstAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packge_ready_fragment, null);
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
        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        OkGo.<Baozhuang_Y_Bean>get(MyUrls.BASEURL + "/driverMine/packGrantList")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Baozhuang_Y_Bean>(getActivity(), Baozhuang_Y_Bean.class) {
                    @Override
                    public void onSuccess(Response<Baozhuang_Y_Bean> response) {
                        Baozhuang_Y_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Baozhuang_Y_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                firstList.addAll(data);
                            }
                            firstAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void initData() {
        firstRecyclerView.setNestedScrollingEnabled(false);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        firstAdapter = new FirstAdapter(R.layout.item_first_layout,firstList);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private class FirstAdapter extends BaseQuickAdapter<Baozhuang_Y_Bean.DataBean,BaseViewHolder>{

        public FirstAdapter(int layoutResId, @Nullable List<Baozhuang_Y_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_Y_Bean.DataBean item) {
            RecyclerView secondRecyclerView = helper.getView(R.id.second_recyclerView);
            TextView  item_name_tv = helper.getView(R.id.item_name_tv);
            TextView  item_zongji_tv = helper.getView(R.id.item_zongji_tv);
            item_name_tv.setText(item.getName());
            item_zongji_tv.setText("总计："+item.getCount());
            List<Baozhuang_Y_Bean.DataBean.ListBeanX> secondList = item.getList();

            secondRecyclerView.setNestedScrollingEnabled(false);
            secondRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            SecondAdapter secondAdapter = new SecondAdapter(R.layout.item_second_layout,secondList);
            secondRecyclerView.setAdapter(secondAdapter);
        }
    }

    private class SecondAdapter extends BaseQuickAdapter<Baozhuang_Y_Bean.DataBean.ListBeanX,BaseViewHolder>{

        public SecondAdapter(int layoutResId, @Nullable List<Baozhuang_Y_Bean.DataBean.ListBeanX> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_Y_Bean.DataBean.ListBeanX item) {
            RecyclerView threeRecyclerView = helper.getView(R.id.three_recyclerView);
            TextView shijian = helper.getView(R.id.shijian);
            long createData = item.getCreateData();
            String dateToString = getDateToString(String.valueOf(createData / 1000));
            shijian.setText(dateToString);
            List<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean> threeList = item.getList();
            threeRecyclerView.setNestedScrollingEnabled(false);
            threeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            ThreeAdapter threeAdapter = new ThreeAdapter(R.layout.item_three_layout,threeList);
            threeRecyclerView.setAdapter(threeAdapter);
        }
    }

    private class ThreeAdapter extends BaseQuickAdapter<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean,BaseViewHolder>{

        public ThreeAdapter(int layoutResId, @Nullable List<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean item) {

            RecyclerView fourRecycler = helper.getView(R.id.four_recyclerView);
            TextView sort = helper.getView(R.id.sort);
             sort.setText(item.getEecVarietiesName());
            List<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean> fourList = item.getPackingBagList();

            fourRecycler.setNestedScrollingEnabled(false);
            fourRecycler.setLayoutManager(new GridLayoutManager(mContext,4));
            FourAdapter fourAdapter = new FourAdapter(R.layout.item_three_not_layout,fourList);
            fourRecycler.setAdapter(fourAdapter);
        }
    }

    private class FourAdapter extends BaseQuickAdapter<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean,BaseViewHolder>{

        public FourAdapter(int layoutResId, @Nullable List<Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Baozhuang_Y_Bean.DataBean.ListBeanX.ListBean.PackingBagListBean item) {
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
