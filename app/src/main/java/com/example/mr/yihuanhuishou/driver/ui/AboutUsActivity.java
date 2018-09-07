package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Guanyuwomen_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.banben_tv)
    TextView banbenTv;
    @BindView(R.id.wechat_tv)
    TextView wechatTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.email_tv)
    TextView emailTv;
    @BindView(R.id.guanwang_tv)
    TextView guanwangTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("关于我们");
        OkGo.<Guanyuwomen_Bean>get(MyUrls.BASEURL + "/aboutus/aboutusList")
                .tag(this)
                .execute(new DialogCallback<Guanyuwomen_Bean>(AboutUsActivity.this, Guanyuwomen_Bean.class) {
                    @Override
                    public void onSuccess(Response<Guanyuwomen_Bean> response) {
                        Guanyuwomen_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Guanyuwomen_Bean.DataBean> data = body.getData();
                            wechatTv.setText(data.get(1).getContent());
                            phoneTv.setText(data.get(0).getContent());
                            emailTv.setText(data.get(2).getContent());
                            guanwangTv.setText(data.get(3).getContent());
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(AboutUsActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(AboutUsActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(AboutUsActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(AboutUsActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(AboutUsActivity.this, body.getMsg());
                        }
                    }
                });

    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
