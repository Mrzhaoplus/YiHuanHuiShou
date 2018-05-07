package com.example.mr.yihuanhuishou.fragment.huishou;

import android.content.Intent;
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
import com.example.mr.yihuanhuishou.adapter.Shangmen_Adapter;
import com.example.mr.yihuanhuishou.adapter.ZhiFu_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Zhifu_fragment extends BaseFragment implements View.OnClickListener {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private CheckBox all_xuan;
    private TextView price;
    private Button tijiao;
    private boolean flag=true;

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
        tijiao.setOnClickListener(this);



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
        ZhiFu_Adapter zhiFu_adapter = new ZhiFu_Adapter(mContext);
        recy_view.setAdapter(zhiFu_adapter);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tijiao:
                String qian = price.getText().toString();
                int money = Integer.parseInt(qian);
                Intent intent = new Intent(getActivity(), Pay_DetailsActivity.class);
                intent.putExtra("money",money);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
    }
}
