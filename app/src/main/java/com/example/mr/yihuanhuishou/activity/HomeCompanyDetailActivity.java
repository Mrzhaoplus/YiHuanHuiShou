package com.example.mr.yihuanhuishou.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.ZhiPai_wupin_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.Xitong_Recycler_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeCompanyDetailActivity extends BaseActivity implements ZhiPai_wupin_Adapter.Onclickflag {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_more_iv)
    ImageView titleMoreIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.zhipai_tv)
    TextView zhipaiTv;
     List<Xitong_Recycler_Bean.DataBean> mlist=new ArrayList<>();
    private List<Integer> postion1;
    private int flag;
    private ZhiPai_wupin_Adapter zhiPai_wupin_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_company_detail);
        ButterKnife.bind(this);
        flag = getIntent().getIntExtra("flag", 0);
        initView();
        infoview();
    }

    private void infoview() {
        mlist.clear();
        OkGo.<Xitong_Recycler_Bean>post(MyUrls.BASEURL + "/recyclers/info/allVarieties")
                .tag(this)
                .execute(new DialogCallback<Xitong_Recycler_Bean>(HomeCompanyDetailActivity.this, Xitong_Recycler_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xitong_Recycler_Bean> response) {
                        Xitong_Recycler_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Xitong_Recycler_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HomeCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HomeCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HomeCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HomeCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HomeCompanyDetailActivity.this, body.getMsg());
                        }

                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("公司详情");
        titleMoreIv.setVisibility(View.VISIBLE);

        List<String> list = new ArrayList<>();
        list.add("衣   物：2元/斤");
        list.add("鞋   类：1元/斤");
        list.add("纸   箱：3元/斤");
        list.add("金   属：5元/斤");
        list.add("玻   瓶：1元/斤");
        list.add("大   电：10元/斤");
        list.add("塑   瓶：1元/斤");
        list.add("手机数码：20/斤");

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ContentAdapter contentAdapter = new ContentAdapter(R.layout.item_gongsi_detail,list);
        recyclerView.setAdapter(contentAdapter);
    }

    @Override
    public void flag(List<Integer> postion) {
        postion1 = postion;
    }


    private class ContentAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public ContentAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView textView = helper.getView(R.id.content_tv);
            helper.setText(R.id.content_tv,item);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_more_iv, R.id.zhipai_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_more_iv:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.zhipai_tv:
                startActivity(new Intent(HomeCompanyDetailActivity.this,Dingdan_itmeActivity.class));
                break;
        }
    }

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_itme_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tel = dialog.findViewById(R.id.tel);
        TextView zaixian = dialog.findViewById(R.id.zaixian);
        TextView relieve = dialog.findViewById(R.id.relieve);
        TextView jiaru = dialog.findViewById(R.id.jiaru);

        if(flag==1){
            relieve.setVisibility(View.GONE);
            jiaru.setVisibility(View.VISIBLE);
        }
        jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ZhipaiDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });


        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        zaixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        relieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });
    }

    //解除公司
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_jiechu)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
       TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ToastUtils.getToast(HomeCompanyDetailActivity.this,"解除");
                finish();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //指派订单
    private void ZhipaiDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_zpdd)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        RecyclerView recyclerView = dialog.getView(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeCompanyDetailActivity.this,3));
        recyclerView.setNestedScrollingEnabled(false);
        zhiPai_wupin_adapter = new ZhiPai_wupin_Adapter(HomeCompanyDetailActivity.this,mlist);
        recyclerView.setAdapter(zhiPai_wupin_adapter);
        zhiPai_wupin_adapter.getclick(this);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
                startActivity(new Intent(HomeCompanyDetailActivity.this,Recycler_FirmActivity.class));
            }
        });
        dialog.show();
    }
}
