package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiangdanDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.juli_tv)
    TextView juliTv;
    @BindView(R.id.fplx_tv)
    TextView fplxTv;
    @BindView(R.id.fpsl_tv)
    TextView fpslTv;
    @BindView(R.id.qhsj_tv)
    TextView qhsjTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.fpzj_tv)
    TextView fpzjTv;
    @BindView(R.id.lijiqiangdan_tv)
    TextView lijiqiangdanTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiangdan_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("配送需求详情");
    }

    @OnClick({R.id.title_back_iv, R.id.lijiqiangdan_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.lijiqiangdan_tv:
                showPayFinishDialog("抢单成功",true);
                break;
        }
    }

    private void showPayFinishDialog(String type,boolean isSuccess) {
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
        if (isSuccess){
            imageView.setImageResource(R.drawable.driver_qhcg_iv);
            contentTv.setText(type);
        }else {
            imageView.setImageResource(R.drawable.driver_qhsb_iv);
            contentTv.setText(type);
        }
        mDialog.show();

        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

    }

}
