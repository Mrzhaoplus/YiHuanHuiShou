package com.example.mr.yihuanhuishou.fragment.oneseif;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Oneseif_Wait_Pay_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Wait_Pay_fragment extends BaseFragment {

    private RecyclerView recy_view;
    private SpringView sp_view;

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
        recy_view = contentView.findViewById(R.id.recy_view);
        sp_view = contentView.findViewById(R.id.sp_view);
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
        Oneseif_Wait_Pay_Adapter oneseif_wait_pay_adapter = new Oneseif_Wait_Pay_Adapter(getActivity());
        recy_view.setAdapter(oneseif_wait_pay_adapter);
    }
}
