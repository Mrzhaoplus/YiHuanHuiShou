package com.example.mr.yihuanhuishou.fragment.huishou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Pay_DetailsActivity;
import com.example.mr.yihuanhuishou.adapter.ZhiFu_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;
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
 * Created by Mr赵 on 2018/5/4.
 */

public class Zhifu_fragment extends BaseFragment implements View.OnClickListener, ZhiFu_Adapter.Checkbox_click {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private CheckBox all_xuan;
    private TextView price;
    private Button tijiao;
    private List<Recy_Shangmen_Bean.DataListBean> list = new ArrayList<>();
    private ZhiFu_Adapter zhiFu_adapter;
    private int pageNo=1;
    private int pageCount=10;
    List<Boolean> pro=new ArrayList<>();
    private double yihuan1;

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.zhifu_fragment;
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
        price = contentView.findViewById(R.id.price);
        tijiao = contentView.findViewById(R.id.tijiao);

        initdata();
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        infoview();
    }

    private void infoview() {
        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("pageNo",pageNo);
        params.put("pageCount",pageCount);
        params.put("state","3");
        OkGo.<Recy_Shangmen_Bean>post(MyUrls.BASEURL + "/recyclers/order/wasteList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Recy_Shangmen_Bean>(getActivity(), Recy_Shangmen_Bean.class) {
                    @Override
                    public void onSuccess(Response<Recy_Shangmen_Bean> response) {
                        Recy_Shangmen_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Recy_Shangmen_Bean.DataListBean> dataList = body.getDataList();
                            for (int i=0;i<dataList.size();i++){
                                pro.add(false);
                            }
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }
                            zhiFu_adapter.notifyDataSetChanged();
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
        tijiao.setOnClickListener(this);

        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         pageNo=1;
                         pageCount=10;
                        list.clear();
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
                        pageNo++;
                        pageCount=pageCount+10;
                        infoview();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        zhiFu_adapter = new ZhiFu_Adapter(mContext,list,pro);
        recy_view.setAdapter(zhiFu_adapter);
        zhiFu_adapter.getclick(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tijiao:
                String qian = price.getText().toString();
                double money = Double.parseDouble(qian);
                Intent intent = new Intent(getActivity(), Pay_DetailsActivity.class);
                intent.putExtra("money",money);
                intent.putExtra("yihuan",yihuan1);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void getclick(List<String> biannum, double tem, double yihuan) {
        yihuan1 = yihuan;
        Log.e("==================",biannum.toString());
        Log.e("==================",tem+"");
        Log.e("==================",yihuan+"");
        price.setText(tem+"");
    }
}
