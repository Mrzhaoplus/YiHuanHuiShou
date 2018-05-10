package com.example.mr.yihuanhuishou.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.fragment.driver.Driver_all_fragment;
import com.example.mr.yihuanhuishou.fragment.driver.Driver_jiedan_fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_fragment extends BaseFragment {

    private TabLayout tab;
    private ViewPager vp;
    List<String> list=new ArrayList<>();

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.driver_fragment;
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        tab = contentView.findViewById(R.id.tab);
        vp = contentView.findViewById(R.id.vp_pager);

        list.add("全部");
        list.add("待接单");
        list.add("取货中");
        list.add("配送中");
        list.add("配送完");
        list.add("待支付");
        list.add("已支付");
        list.add("已取消");
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 1:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 2:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 3:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 4:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 5:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 6:
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                    case 7:
                        fragment= new Driver_all_fragment();
                        fragment= new Driver_all_fragment(list.get(position));
                        break;
                }
                return fragment;
            }
        });
        tab.setupWithViewPager(vp);
    }



}
