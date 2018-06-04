package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPassWordActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.oldpass_et)
    EditText oldpassEt;
    @BindView(R.id.newpass_et)
    EditText newpassEt;
    @BindView(R.id.querypass_et)
    EditText querypassEt;
    @BindView(R.id.edit_tv)
    TextView editTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("修改密码");

    }

    private void showPayFinishDialog(String type) {
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
        if (TextUtils.equals("success",type)){
            imageView.setImageResource(R.drawable.driver_qhcg_iv);
            contentTv.setText("密码修改成功");
        }else if (TextUtils.equals("filed",type)){
            imageView.setImageResource(R.drawable.xgsb_iv);
            contentTv.setText("密码修改失败，请重新修改");
        }else {
            imageView.setImageResource(R.drawable.mmcw_iv);
            contentTv.setText("旧密码不正确，请重新输入");
        }
        mDialog.show();
        queryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                finish();
            }
        });

    }

    @OnClick({R.id.title_back_iv, R.id.edit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.edit_tv:
                showPayFinishDialog("success");
                break;
        }
    }
}
