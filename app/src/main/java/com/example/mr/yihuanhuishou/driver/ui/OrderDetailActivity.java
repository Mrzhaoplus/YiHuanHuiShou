package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.fragment.DriverAllOrderFragment;
import com.example.mr.yihuanhuishou.driver.weight.CommonPopupWindow;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.ddbh_tv)
    TextView ddbhTv;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.bzdtm_tv)
    TextView bzdtmTv;
    @BindView(R.id.fplx_tv)
    TextView fplxTv;
    @BindView(R.id.fpsl_tv)
    TextView fpslTv;
    @BindView(R.id.fpzj_tv)
    TextView fpzjTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.fbsj_tv)
    TextView fbsjTv;
    @BindView(R.id.fbsj_ll)
    LinearLayout fbsjLl;
    @BindView(R.id.jdsj_tv)
    TextView jdsjTv;
    @BindView(R.id.jdsj_ll)
    LinearLayout jdsjLl;
    @BindView(R.id.qhsj_tv)
    TextView qhsjTv;
    @BindView(R.id.qhsj_ll)
    LinearLayout qhsjLl;
    @BindView(R.id.shsj_tv)
    TextView shsjTv;
    @BindView(R.id.shsj_ll)
    LinearLayout shsjLl;
    @BindView(R.id.pswcsj_tv)
    TextView pswcsjTv;
    @BindView(R.id.pswcsj_ll)
    LinearLayout pswcsjLl;
    @BindView(R.id.view_03)
    TextView view03;
    @BindView(R.id.zffs_tv)
    TextView zffsTv;
    @BindView(R.id.zffs_rl)
    RelativeLayout zffsRl;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.middle_tv)
    TextView middleTv;
    @BindView(R.id.middle2_tv)
    TextView middle2Tv;
    @BindView(R.id.left_tv)
    TextView leftTv;
    private String state;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        state = getIntent().getStringExtra("state");
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("订单详情");
        switch (state){
            case "全部":
                stateTv.setText(state);
                jdsjLl.setVisibility(View.VISIBLE);
                qhsjLl.setVisibility(View.VISIBLE);
                rightTv.setText("取货");
                middleTv.setVisibility(View.VISIBLE);
                middleTv.setText("取消订单");
                leftTv.setVisibility(View.VISIBLE);
                break;
            case "待取货":
                jdsjLl.setVisibility(View.VISIBLE);
                qhsjLl.setVisibility(View.VISIBLE);
                rightTv.setText("取货");
                middleTv.setVisibility(View.VISIBLE);
                middleTv.setText("取消订单");
                leftTv.setVisibility(View.VISIBLE);
                break;
            case "配送中":
                fbsjLl.setVisibility(View.VISIBLE);
                jdsjLl.setVisibility(View.VISIBLE);
                shsjLl.setVisibility(View.VISIBLE);
                zffsRl.setVisibility(View.VISIBLE);
                rightTv.setText("配送完成");
                middle2Tv.setVisibility(View.VISIBLE);
                break;
            case "已完成":
                fbsjLl.setVisibility(View.VISIBLE);
                jdsjLl.setVisibility(View.VISIBLE);
                shsjLl.setVisibility(View.VISIBLE);
                pswcsjLl.setVisibility(View.VISIBLE);
                zffsRl.setVisibility(View.VISIBLE);
                rightTv.setText("删除订单");
                break;
            case "已取消":
                fbsjLl.setVisibility(View.VISIBLE);
                rightTv.setText("删除订单");
                break;
        }
    }

    @OnClick({R.id.title_back_iv, R.id.right_tv, R.id.middle_tv, R.id.middle2_tv, R.id.left_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.right_tv:
                if (TextUtils.equals("待取货",state) || TextUtils.equals("全部",state)) showDialog();
                if (TextUtils.equals("配送中",state)) finish();
                if (TextUtils.equals("已完成",state) || TextUtils.equals("已取消",state)) showDelDialog("是否删除此订单");
                break;
            case R.id.middle_tv:
                if (TextUtils.equals("待取货",state) || TextUtils.equals("全部",state)) showDelDialog("是否取消此订单");
                break;
            case R.id.middle2_tv:
                showTelDialog();
                break;
            case R.id.left_tv:
                showTelDialog();
                break;
        }
    }

    private void showTelDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_tel)
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
        mDialog.getView(R.id.dianlian_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.zaixian_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
    }

    private void showDelDialog(String content) {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_del)
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
        mDialog.setCancelable(true);
        mDialog.show();
        TextView contentTv = mDialog.getView(R.id.content_tv);
        contentTv.setText(content);
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.query_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                finish();
            }
        });
    }

    private void showDialog() {
        mBuilder = new BaseDialog.Builder(this);
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

    private void showPaySelctorDialog() {
        mBuilder = new BaseDialog.Builder(this);
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
        mBuilder = new BaseDialog.Builder(this);
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
                imageView.setImageResource(R.drawable.driver_qhcg);
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
        mBuilder = new BaseDialog.Builder(this);
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
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
                        recycle_view.addItemDecoration(new DividerItemDecoration(OrderDetailActivity.this, DividerItemDecoration.VERTICAL_LIST));
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
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down1)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
                        recycle_view.addItemDecoration(new DividerItemDecoration(OrderDetailActivity.this, DividerItemDecoration.VERTICAL_LIST));
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
}
