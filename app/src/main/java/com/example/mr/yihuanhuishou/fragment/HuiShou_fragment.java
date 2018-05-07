package com.example.mr.yihuanhuishou.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Shangmen_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Wancheng_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Zhifu_fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class HuiShou_fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout rela1;
    private RelativeLayout rela2;
    private RelativeLayout rela3;
    private RelativeLayout rela4;
    private View xian1;
    private View xian2;
    private View xian3;
    private View xian4;
    private FrameLayout fl;
    private TextView wancheng;
    private TextView all;
    private TextView shangmen;
    private TextView zhifu;
    private Fragment currfit;
    private Shangmen_fragment shangmen_fragment;
    private Zhifu_fragment zhifu_fragment;
    private Wancheng_fragment wancheng_fragment;
    private boolean flag=true;
    private int msg;

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.huishou_fragment;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        rela1 = contentView.findViewById(R.id.rela1);
        rela2 = contentView.findViewById(R.id.rela2);
        rela3 = contentView.findViewById(R.id.rela3);
        rela4 = contentView.findViewById(R.id.rela4);
        all = contentView.findViewById(R.id.all);
        shangmen = contentView.findViewById(R.id.shangmen);
        zhifu = contentView.findViewById(R.id.zhifu);
        wancheng = contentView.findViewById(R.id.wancheng);
        xian1 = contentView.findViewById(R.id.xian1);
        xian2 = contentView.findViewById(R.id.xian2);
        xian3 = contentView.findViewById(R.id.xian3);
        xian4 = contentView.findViewById(R.id.xian4);
        fl = contentView.findViewById(R.id.fl);
        rela1.setOnClickListener(this);
        rela2.setOnClickListener(this);
        rela3.setOnClickListener(this);
        rela4.setOnClickListener(this);

        if(shangmen_fragment==null){
            shangmen_fragment = new Shangmen_fragment();
        }
        AddFragment(shangmen_fragment);
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag =false;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){
        msg = eveen.getMsg();
        if(msg==1){

            xian1.setVisibility(View.VISIBLE);
            xian2.setVisibility(View.GONE);
            xian3.setVisibility(View.GONE);
            xian4.setVisibility(View.GONE);
            all.setTextColor(getResources().getColor(R.color.colorAccent));
            shangmen.setTextColor(getResources().getColor(R.color.black));
            zhifu.setTextColor(getResources().getColor(R.color.black));
            wancheng.setTextColor(getResources().getColor(R.color.black));
            AddFragment(new Shangmen_fragment());
        }else{
            if(shangmen_fragment==null){
                shangmen_fragment = new Shangmen_fragment();
            }
            AddFragment(shangmen_fragment);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //全部
            case R.id.rela1:
                xian1.setVisibility(View.VISIBLE);
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.GONE);
                all.setTextColor(getResources().getColor(R.color.colorAccent));
                shangmen.setTextColor(getResources().getColor(R.color.black));
                zhifu.setTextColor(getResources().getColor(R.color.black));
                wancheng.setTextColor(getResources().getColor(R.color.black));
                if(shangmen_fragment==null){
                    shangmen_fragment = new Shangmen_fragment();
                }
                AddFragment(shangmen_fragment);
                break;
            //待上门
            case R.id.rela2:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.VISIBLE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.GONE);
                all.setTextColor(getResources().getColor(R.color.black));
                shangmen.setTextColor(getResources().getColor(R.color.colorAccent));
                zhifu.setTextColor(getResources().getColor(R.color.black));
                wancheng.setTextColor(getResources().getColor(R.color.black));
                if(shangmen_fragment==null){
                    shangmen_fragment = new Shangmen_fragment();
                }
                AddFragment(shangmen_fragment);
                break;
                //待支付
            case R.id.rela3:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.VISIBLE);
                xian4.setVisibility(View.GONE);
                all.setTextColor(getResources().getColor(R.color.black));
                shangmen.setTextColor(getResources().getColor(R.color.black));
                zhifu.setTextColor(getResources().getColor(R.color.colorAccent));
                wancheng.setTextColor(getResources().getColor(R.color.black));
                if(zhifu_fragment==null){
                    zhifu_fragment = new Zhifu_fragment();
                }
                AddFragment(zhifu_fragment);
                break;
            case R.id.rela4:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.VISIBLE);
                all.setTextColor(getResources().getColor(R.color.black));
                shangmen.setTextColor(getResources().getColor(R.color.black));
                zhifu.setTextColor(getResources().getColor(R.color.black));
                wancheng.setTextColor(getResources().getColor(R.color.colorAccent));
                if(wancheng_fragment==null){
                    wancheng_fragment = new Wancheng_fragment();
                }
                AddFragment(wancheng_fragment);
                break;
        }
    }
    /*
       * 动态添加fragment方法
       * */
    public void AddFragment(Fragment f){
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);

        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
