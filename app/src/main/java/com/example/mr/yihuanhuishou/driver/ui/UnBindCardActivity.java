package com.example.mr.yihuanhuishou.driver.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Back_card_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnBindCardActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    List<Back_card_Bean.DataBean> list = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences spp;
    private BankAdapter bankAdapter;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_bind_card);
        ButterKnife.bind(this);
        state = getIntent().getIntExtra("state", 0);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        spp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        infoview();
    }

    private void infoview() {
        if(state==1){
            HttpParams params = new HttpParams();
            params.put("userId", sp.getInt(GGUtils.USER_id,0));
            params.put("userType","1");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(UnBindCardActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                bankAdapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            }

                        }
                    });
        }else if(state==2){
            HttpParams params = new HttpParams();
            params.put("userId", spp.getInt(GGUtils.DrUSER_id,0));
            params.put("userType","2");
            OkGo.<Back_card_Bean>post(MyUrls.BASEURL + "/resident/selectbankCard")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Back_card_Bean>(UnBindCardActivity.this, Back_card_Bean.class) {
                        @Override
                        public void onSuccess(Response<Back_card_Bean> response) {
                            Back_card_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Back_card_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    list.addAll(data);
                                }
                                bankAdapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            }

                        }
                    });
        }

    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("解绑银行卡");

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bankAdapter = new BankAdapter(R.layout.item_bank_layout,list);
        recyclerView.setAdapter(bankAdapter);
        bankAdapter.setOnItemClickListener(this);
    }

    private void showDelDialog(final int id, final int userid) {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_bank_del)
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
        //取消
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        //确定
        mDialog.getView(R.id.query_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                initdata(id,userid);
            }
        });
    }

    private void initdata(int id,int userid) {
        HttpParams params = new HttpParams();
        params.put("userId", userid);
        params.put("id",id);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/resident/unbindBankCard")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(UnBindCardActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                            infoview();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(UnBindCardActivity.this, body.getMsg());
                        }

                    }
                });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showDelDialog(list.get(position).getId(),list.get(position).getUserId());
    }

    private class BankAdapter extends BaseQuickAdapter<Back_card_Bean.DataBean,BaseViewHolder>{

        public BankAdapter(int layoutResId, @Nullable List<Back_card_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Back_card_Bean.DataBean item) {
            ImageView bankiv = helper.getView(R.id.item_img_iv);
            TextView bank_tv = helper.getView(R.id.item_name_tv);
            TextView bank_num_tv = helper.getView(R.id.item_num_tv);
            Glide.with(UnBindCardActivity.this).load(item.getEecBankcardtype().getBankImage()).into(bankiv);
            bank_tv.setText(item.getEecBankcardtype().getBankName());
            String cardNumber = item.getCardNumber();
            bank_num_tv.setText(cardNumber.substring(0,4)+" "+cardNumber.substring(4,8)+" "+"*******"+" "+cardNumber.substring(15,cardNumber.length()));

        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
