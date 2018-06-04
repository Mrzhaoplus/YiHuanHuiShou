package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.driver.ui.OrderDetailActivity;
import com.example.mr.yihuanhuishou.driver.weight.CommonPopupWindow;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/22.
 */

public class DriverAllOrderFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.sp_view)
    SpringView spView;
    Unbinder unbinder;
    private String state = "全部";
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private TextView leibieTv;
    private EditText shumuEt;
    private EditText zongjiaEt;
    private LinearLayout leibieLl;
    private TextView shumuTv;
    private List<String> list = new ArrayList<>();
    private RelativeLayout shumuRl;
    private TextView leftTv;

    @SuppressLint("ValidFragment")
    public DriverAllOrderFragment(String state) {
        if (!TextUtils.isEmpty(state)){
            this.state = state;
        }
    }

    public DriverAllOrderFragment() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shangmen_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {
        //刷新加载
        spView.setType(SpringView.Type.FOLLOW);
        spView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                spView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                spView.onFinishFreshAndLoad();
            }
        });
        spView.setFooter(new DefaultFooter(getActivity()));
        spView.setHeader(new DefaultHeader(getActivity()));

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(mContext));
        AllOrderAdapter allOrderAdapter = new AllOrderAdapter(R.layout.item_all_order,list);
        recyView.setAdapter(allOrderAdapter);
        allOrderAdapter.setOnItemClickListener(this);
        allOrderAdapter.setOnItemChildClickListener(this);
    }

    public class AllOrderAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public AllOrderAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            leftTv = helper.getView(R.id.item_left_tv);
            switch (state){
                case "待取货":
                    helper.setText(R.id.state_tv,"待取货")
                            .setText(R.id.item_right_tv,"收货")
                            .setText(R.id.item_left_tv,"取消订单");
                    break;
                case "配送中":
                    helper.setText(R.id.state_tv,"配送中")
                            .setText(R.id.item_right_tv,"配送完成")
                            .setVisible(R.id.item_left_tv,false);
                    break;
                case "已完成":
                    helper.setText(R.id.state_tv,"已完成")
                            .setText(R.id.item_right_tv,"删除订单")
                            .setVisible(R.id.item_left_tv,false);
                    break;
                case "已取消":
                    helper.setText(R.id.state_tv,"已取消")
                            .setText(R.id.item_right_tv,"删除订单")
                            .setVisible(R.id.item_left_tv,false);
                    break;
            }
            helper.addOnClickListener(R.id.item_right_tv)
                    .addOnClickListener(R.id.item_left_tv);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext,OrderDetailActivity.class);
        intent.putExtra("state",state);
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_right_tv:
                if (TextUtils.equals("待取货",state) || TextUtils.equals("全部",state)){
                    showDialog();
                }else {
                    Intent intent = new Intent(mContext,OrderDetailActivity.class);
                    intent.putExtra("state",state);
                    startActivity(intent);
                }
                break;
            case R.id.item_left_tv:
                if (TextUtils.equals("取消订单",leftTv.getText().toString())){
                    Intent intent = new Intent(mContext,OrderDetailActivity.class);
                    intent.putExtra("state",state);
                    startActivity(intent);
                }
                break;
        }
    }

    private void showDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_order)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.nomal_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        mDialog.setCancelable(false);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(this);
        mDialog.getView(R.id.query_tv).setOnClickListener(this);
        leibieLl = mDialog.getView(R.id.leibie_ll);
        leibieLl.setOnClickListener(this);
        leibieTv = mDialog.getView(R.id.leibie_tv);
        shumuEt = mDialog.getView(R.id.shumu_et);
        shumuTv = mDialog.getView(R.id.shumu_tv);
        shumuRl = mDialog.getView(R.id.shumu_rl);
        shumuRl.setOnClickListener(this);
        zongjiaEt = mDialog.getView(R.id.zongjia_et);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancle_tv:
                mDialog.dismiss();
                break;
            case R.id.query_tv:
                mDialog.dismiss();
                showPayDialog();
                break;
            case R.id.leibie_ll:
                list.clear();
                list.add("衣服");
                list.add("金属");
                list.add("塑料瓶");
                list.add("酒瓶");
                list.add("铜");
                showPopup(leibieLl,list);
                break;
            case R.id.shumu_rl:
                list.clear();
                list.add("个");
                list.add("Kg");
                list.add("包");
                showPopup1(shumuRl,list);
                break;
            case R.id.self_pay_tv:
                mDialog.dismiss();
                showPaySelctorDialog();
                break;
            case R.id.company_pay_tv:
                mDialog.dismiss();
                showPayFinishDialog("company",true);
                break;
            case R.id.balance_pay_tv:
                mDialog.dismiss();
                showPayFinishDialog("self",false);
                break;
            case R.id.ali_pay_tv:
                ToastUtils.getToast(mContext,"支付宝支付");
                break;
            case R.id.wechat_pay_tv:
                ToastUtils.getToast(mContext,"微信支付");
                break;
        }
    }

    private void showPaySelctorDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay_selector)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(this);
        mDialog.getView(R.id.balance_pay_tv).setOnClickListener(this);
        mDialog.getView(R.id.ali_pay_tv).setOnClickListener(this);
        mDialog.getView(R.id.wechat_pay_tv).setOnClickListener(this);
    }

    private void showPayFinishDialog(String type,boolean isPay) {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay_finish)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.nomal_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        mDialog.setCancelable(false);

        ImageView imageView = mDialog.getView(R.id.item_img_iv);
        TextView contentTv = mDialog.getView(R.id.item_content_tv);
        TextView queryTv = mDialog.getView(R.id.item_query_tv);
        if (TextUtils.equals("company",type)){
            if (isPay){
                imageView.setImageResource(R.drawable.driver_qhcg_iv);
                contentTv.setText("取货完成");
                queryTv.setText("确认");
            }else {
                imageView.setImageResource(R.drawable.driver_qhsb_iv);
                contentTv.setText("取货失败");
                queryTv.setText("请重新操作");
            }
        }else {
            if (isPay){
                imageView.setImageResource(R.drawable.driver_pay_true_iv);
                contentTv.setText("余额支付成功");
                queryTv.setText("确认");
            }else {
                imageView.setImageResource(R.drawable.driver_pay_false_iv);
                contentTv.setText("余额支付失败");
                queryTv.setText("重新选择支付方式");
            }
        }
        mDialog.show();

        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

    }

    private void showPayDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_pay)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(this);
        mDialog.getView(R.id.self_pay_tv).setOnClickListener(this);
        mDialog.getView(R.id.company_pay_tv).setOnClickListener(this);
    }

    private void showPopup(View view, final List<String> list) {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
                        recycle_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                        PopupAdapter mAdapter = new PopupAdapter(R.layout.item_pop_textview, list);
                        recycle_view.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                leibieTv.setText(list.get(position));
//                                if (i == 2){
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                                        shumuTv.setBackground(getResources().getDrawable(R.color.colorPrimary));
//                                    }
//                                    shumuTv.setText(list.get(position));
//
//                                }
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
    }

    private class PopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PopupAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_pop, item);
        }
    }

    private void showPopup1(View view, final List<String> list) {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popup_down1)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
                        recycle_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                        PopupAdapter mAdapter = new PopupAdapter(R.layout.item_popup_textview1, list);
                        recycle_view.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                shumuTv.setBackground(getResources().getDrawable(R.color.colorPrimary));
                                shumuTv.setText(list.get(position));
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
