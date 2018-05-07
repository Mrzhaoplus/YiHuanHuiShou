package com.example.mr.yihuanhuishou.fragment.huishou;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Wancheng_Adapter;
import com.example.mr.yihuanhuishou.adapter.ZhiFu_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Wancheng_fragment extends BaseFragment {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private CheckBox all_xuan;
    private TextView price;
    private Button tijiao;

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;
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


        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

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
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        Wancheng_Adapter wancheng_adapter = new Wancheng_Adapter(mContext);
        recy_view.setAdapter(wancheng_adapter);

    }
}
