package com.example.mr.yihuanhuishou.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Fire_Msg_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/14.
 */

public class Frie_Msg_Fragment extends BaseFragment {
    private SpringView sp_view;
    private RecyclerView recy_view;
    List<String> list=new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        for (int i=0;i<8;i++){
            list.add("");
        }
        View contentView = getContentView();
        sp_view = contentView.findViewById(R.id.sp_view);
        recy_view = contentView.findViewById(R.id.recy_view);

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
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        Fire_Msg_Adapter fire_msg_adapter = new Fire_Msg_Adapter(getActivity(), list);
        recy_view.setAdapter(fire_msg_adapter);
    }
}
