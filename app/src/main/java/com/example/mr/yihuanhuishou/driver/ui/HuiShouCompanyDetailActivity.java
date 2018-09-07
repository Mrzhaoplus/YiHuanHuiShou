package com.example.mr.yihuanhuishou.driver.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.HomeCompanyDetailActivity;
import com.example.mr.yihuanhuishou.activity.Recycler_FirmActivity;
import com.example.mr.yihuanhuishou.adapter.ZhiPai_wupin_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fengzhuang;
import com.example.mr.yihuanhuishou.jsonbean.siji.Huishoucompany_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.SpUtils;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuiShouCompanyDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.remove_tv)
    TextView removeTv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.centext)
    TextView centext;
    @BindView(R.id.dianhua_ll)
    LinearLayout dianhuaLl;
    @BindView(R.id.zaixian_ll)
    LinearLayout zaixianLl;
    @BindView(R.id.shenqing_lin)
    LinearLayout shenqingLin;
    @BindView(R.id.hezuo)
    LinearLayout hezuo;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    List<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean> list = new ArrayList<>();
    private SharedPreferences sp;
    private int id;
    private ContentAdapter contentAdapter;
    private List<Integer>pos=new ArrayList<>();
    private BaseDialog dialog;
    private String imUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_shou_company_detail);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        id = getIntent().getIntExtra("id", 0);
        initView();
        infoview();
    }

    private void infoview() {
        Log.e("===============",id+"");
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("companyId", id);
        OkGo.<Huishoucompany_Details_Bean>get(MyUrls.BASEURL + "/driverMine/companyDetails")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Huishoucompany_Details_Bean>(HuiShouCompanyDetailActivity.this, Huishoucompany_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Huishoucompany_Details_Bean> response) {
                        Huishoucompany_Details_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            Huishoucompany_Details_Bean.DataBean data = body.getData();
                            imUser = data.getImUser();
                            title.setText(data.getCompanyName());
                            centext.setText(body.getData().getIntro());
                            nameTv.setText(data.getCorporationName());
                            phoneTv.setText(data.getCorporationPhone());
                            addressTv.setText(data.getCompanyAddr());
                            int isJoin = data.getIsJoin();
                            if (isJoin == 0) {
                                hezuo.setVisibility(View.VISIBLE);
                                removeTv.setVisibility(View.GONE);
                            }else{
                                removeTv.setVisibility(View.VISIBLE);
                                hezuo.setVisibility(View.GONE);
                            }
                            List<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean> varietiesmanageList = data.getVarietiesmanageList();
                            if (varietiesmanageList.size() > 0) {
                                list.addAll(varietiesmanageList);
                            }
                            contentAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        }
                    }
                });

    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("公司详情");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        contentAdapter = new ContentAdapter(R.layout.item_gongsi_detail, list);
        recyclerView.setAdapter(contentAdapter);
    }

    private class ContentAdapter extends BaseQuickAdapter<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean, BaseViewHolder> {

        public ContentAdapter(int layoutResId, @Nullable List<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean item) {
            TextView textView = helper.getView(R.id.content_tv);
            String danwei = item.getDanwei();
            if(TextUtils.isEmpty(danwei)){
                helper.setText(R.id.content_tv, item.getRecycleCategoriesTitle()+ "：" + item.getRecycleCategoriesPrice() + "元");
            }else{
                helper.setText(R.id.content_tv, item.getRecycleCategoriesTitle()+ "：" + item.getRecycleCategoriesPrice() + "元/" + item.getDanwei());
            }

            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
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
                jiechu();
            }
        });
    }

    private void jiechu() {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("companyId", id);
        OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/driverMine/relieve")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(HuiShouCompanyDetailActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                            finish();
                            mDialog.dismiss();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        }
                    }
                });


    }

    @OnClick({R.id.title_back_iv, R.id.remove_tv,R.id.shenqing_lin,R.id.dianhua_ll,R.id.zaixian_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zaixian_ll:
                //设置要发送出去的昵称
                SpUtils.putString(HuiShouCompanyDetailActivity.this,"userName",sp.getString(GGUtils.DrUSER_NAME,""));
                //设置要发送出去的头像
                SpUtils.putString(HuiShouCompanyDetailActivity.this,"face", sp.getString(GGUtils.DrIMAGE_path,""));

                Intent intent = new Intent(HuiShouCompanyDetailActivity.this,ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID,imUser);
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);
                break;
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.remove_tv:
                showDelDialog("您确定要和公司解除合同吗？");
                break;
            case R.id.shenqing_lin:
                ZhipaiDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.dianhua_ll:
                String tell = phoneTv.getText().toString();
                hujiao(Gravity.CENTER,R.style.Alpah_aniamtion,tell);
                break;
        }
    }
    //指派订单
    private void hujiao(int grary, int animationStyle,String tell) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.hujiao_pop)
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
        TextView quxiao = dialog.getView(R.id.text_pause);
        final TextView phone = dialog.getView(R.id.tell);
        phone.setText(tell);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:"+phone.getText().toString());
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(HuiShouCompanyDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    //指派订单
    private void ZhipaiDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
//设置显示位置
//设置动画
//设置dialog的宽高
//设置触摸dialog外围是否关闭
//设置监听事件
        dialog = builder.setViewId(R.layout.dialog_zpdd)
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
        TextView quxiao = dialog.getView(R.id.quxiao);
        RecyclerView recyclerView = dialog.getView(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(HuiShouCompanyDetailActivity.this,3));
        recyclerView.setNestedScrollingEnabled(false);
        Hezuo_Adapter hezuo_adapter = new Hezuo_Adapter(R.layout.zhipaiwupin_adapter, list);
        recyclerView.setAdapter(hezuo_adapter);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos.size()>0){
                    dialog.dismiss();
                    jiaru();
                }else{
                    ToastUtils.getToast(HuiShouCompanyDetailActivity.this,"请选择回收种类！");
                }
            }
        });
        dialog.show();
    }

    private void jiaru() {
        Fengzhuang fengzhuang = new Fengzhuang();
        fengzhuang.setVarieties(pos);
        Gson gson = new Gson();
        String s = gson.toJson(fengzhuang);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("companyId", id);
        params.put("VmCode",s.substring(14,s.length()-2));
        OkGo.<Jiaoyan_Bean>get(MyUrls.BASEURL + "/driverMine/applyJoin")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(HuiShouCompanyDetailActivity.this, Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                            pos.clear();
                        } else{
                            ToastUtils.getToast(HuiShouCompanyDetailActivity.this, body.getMsg());
                        }
                    }
                });

    }

    private class Hezuo_Adapter extends BaseQuickAdapter<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean, BaseViewHolder> {

        public Hezuo_Adapter(int layoutResId, @Nullable List<Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Huishoucompany_Details_Bean.DataBean.VarietiesmanageListBean item) {
            CheckBox checkBox = helper.getView(R.id.check_wupin);
            checkBox.setText(item.getRecycleCategoriesTitle());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        pos.add(item.getId());
                    }else{
                        for (int i=0;i<pos.size();i++){
                            if(item.getId()==pos.get(i)){
                                pos.remove(i);
                            }
                        }
                    }
                }
            });
        }
    }
}
