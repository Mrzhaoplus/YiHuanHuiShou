package com.example.mr.yihuanhuishou.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Tongguo_Fragment_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/21.
 */

public class TongGuo_Fragment extends BaseFragment {

    private SpringView springView;
    private RecyclerView mrecycler;
    List<String> list=new ArrayList<>();
    private int state=1;
    private Tongguo_Fragment_Adapter tongguo_fragment_adapter;
    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.tongguo_shenhe_fragment;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        springView = contentView.findViewById(R.id.spring_view);
        mrecycler = contentView.findViewById(R.id.recy_view);
        initdata();
    }

    private void initdata() {
        for(int i=0;i<10;i++){
            list.add("");
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        tongguo_fragment_adapter = new Tongguo_Fragment_Adapter(getActivity(),list,state);
        mrecycler.setAdapter(tongguo_fragment_adapter);
    }
}
