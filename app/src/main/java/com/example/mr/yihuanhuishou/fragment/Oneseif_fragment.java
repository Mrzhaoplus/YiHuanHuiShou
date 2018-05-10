package com.example.mr.yihuanhuishou.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Shangmen_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Wancheng_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Zhifu_fragment;
import com.example.mr.yihuanhuishou.fragment.oneseif.Designate_fragment;
import com.example.mr.yihuanhuishou.fragment.oneseif.Oneseif_Success_fragment;
import com.example.mr.yihuanhuishou.fragment.oneseif.Wait_Pay_fragment;

/**
 * Created by Mr赵 on 2018/5/8.
 */

public class Oneseif_fragment extends BaseFragment implements View.OnClickListener {

    private Fragment currfit;
    private TextView success;
    private RelativeLayout rela1;
    private RelativeLayout rela2;
    private RelativeLayout rela3;
    private RelativeLayout rela4;
    private View xian1;
    private View xian2;
    private View xian3;
    private View xian4;
    private TextView all;
    private TextView xuanding;
    private TextView pay;
    private Designate_fragment designate_fragment;
    private Wait_Pay_fragment wait_pay_fragment;
    private Oneseif_Success_fragment oneseif_success_fragment;

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.oneseif_fragment;
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
        xian1 = contentView.findViewById(R.id.xian1);
        xian2 = contentView.findViewById(R.id.xian2);
        xian3 = contentView.findViewById(R.id.xian3);
        xian4 = contentView.findViewById(R.id.xian4);
        all = contentView.findViewById(R.id.all);
        xuanding = contentView.findViewById(R.id.wait_daiding);
        pay = contentView.findViewById(R.id.wait_pay);
        success = contentView.findViewById(R.id.wancheng);
        rela1.setOnClickListener(this);
        rela2.setOnClickListener(this);
        rela3.setOnClickListener(this);
        rela4.setOnClickListener(this);
        //默认页面
        if(designate_fragment==null){
            designate_fragment = new Designate_fragment();
        }
        AddFragment(designate_fragment);
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
                xuanding.setTextColor(getResources().getColor(R.color.black));
                pay.setTextColor(getResources().getColor(R.color.black));
                success.setTextColor(getResources().getColor(R.color.black));

                break;
            //待选定
            case R.id.rela2:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.VISIBLE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.GONE);
                all.setTextColor(getResources().getColor(R.color.black));
                xuanding.setTextColor(getResources().getColor(R.color.colorAccent));
                pay.setTextColor(getResources().getColor(R.color.black));
                success.setTextColor(getResources().getColor(R.color.black));
                if(designate_fragment==null){
                    designate_fragment = new Designate_fragment();
                }
                AddFragment(designate_fragment);
                break;
            //待支付
            case R.id.rela3:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.VISIBLE);
                xian4.setVisibility(View.GONE);
                all.setTextColor(getResources().getColor(R.color.black));
                xuanding.setTextColor(getResources().getColor(R.color.black));
                pay.setTextColor(getResources().getColor(R.color.colorAccent));
                success.setTextColor(getResources().getColor(R.color.black));
                if(wait_pay_fragment==null){
                    wait_pay_fragment = new Wait_Pay_fragment();
                }
                AddFragment(wait_pay_fragment);
                break;
            case R.id.rela4:
                xian1.setVisibility(View.GONE);
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.VISIBLE);
                all.setTextColor(getResources().getColor(R.color.black));
                xuanding.setTextColor(getResources().getColor(R.color.black));
                pay.setTextColor(getResources().getColor(R.color.black));
                success.setTextColor(getResources().getColor(R.color.colorAccent));
                if(oneseif_success_fragment==null){
                    oneseif_success_fragment = new Oneseif_Success_fragment();
                }
                AddFragment(oneseif_success_fragment);
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
}
