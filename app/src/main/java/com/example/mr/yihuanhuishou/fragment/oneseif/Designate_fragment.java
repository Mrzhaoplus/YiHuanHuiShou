package com.example.mr.yihuanhuishou.fragment.oneseif;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Designate_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Daijiedan_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Designate_fragment extends BaseFragment implements Designate_Adapter.getclick {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private CheckBox all_xuan;
    private Button designate;
    List<Order_Daijiedan_Bean.DataBean>mlist=new ArrayList<>();
    private Designate_Adapter designate_adapter;
    List<Boolean> pro=new ArrayList<>();
    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.designate_fragment;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        sp_view = contentView.findViewById(R.id.sp_view);
        recy_view = contentView.findViewById(R.id.recy_view);
        all_xuan = contentView.findViewById(R.id.all_xuan);
        designate = contentView.findViewById(R.id.designate);
        initdata();

    }

    @Override
    public void onResume() {
        super.onResume();
        mlist.clear();
        infoview();
    }

    private void infoview() {

        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("distribution","1");
        params.put("state","10");
        OkGo.<Order_Daijiedan_Bean>post(MyUrls.BASEURL + "/recyclers/order/recoveryList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Daijiedan_Bean>(getActivity(), Order_Daijiedan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Daijiedan_Bean> response) {
                        Order_Daijiedan_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Order_Daijiedan_Bean.DataBean> data = body.getData();
                            for (int i=0;i<data.size();i++){
                                pro.add(false);
                            }
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                            designate_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }
                    }
                });
    }
    private void initdata() {
        //选定公司
        designate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
            }
        });


        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlist.clear();
                        infoview();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));

        //设置适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        designate_adapter = new Designate_Adapter(getActivity(),mlist,pro);
        recy_view.setAdapter(designate_adapter);
        designate_adapter.getclick(this);
    }

    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_zhipai)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        TextView text_pause = dialog.getView(R.id.text_pause);
        final EditText firm = dialog.getView(R.id.edit_frim);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = firm.getText().toString().trim();
                if(TextUtils.isEmpty(trim)){
                    ToastUtils.getToast(getActivity(),"指派失败，请输入指派公司！");
                }else{
                    ToastUtils.getToast(getActivity(),"您已指派"+trim);
                    firm.setText("");

                }
                dialog.dismiss();
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

     //多选指定
    @Override
    public void click(List<String> cordnuber) {
        Log.e("===================",cordnuber.toString());
    }
}

