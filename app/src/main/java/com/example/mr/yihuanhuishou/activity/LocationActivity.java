package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.CityAdapter;
import com.example.mr.yihuanhuishou.adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.mr.yihuanhuishou.adapter.ViewHolder;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.CityBean;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends BaseActivity {
    @BindView(R.id.sou)
    EditText sou;
    @BindView(R.id.beak)
    TextView beak;
    @BindView(R.id.seach_dw)
    TextView dw;
    private LinearLayoutManager mManager;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private SuspensionDecoration mDecoration;
    private RecyclerView cityRecycler;
    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    private List<CityBean> cityList;
    private CityAdapter cityAdapter;
    private String[] citydatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        String address = getIntent().getStringExtra("address");
        dw.setText(address);
        cityRecycler = (RecyclerView) findViewById(R.id.city_recycler);
        cityRecycler.setLayoutManager(mManager = new LinearLayoutManager(mContext));
        cityAdapter = new CityAdapter(this, cityList);
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                finish();
            }
        });
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(cityAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {

            }
        };
        cityRecycler.setAdapter(mHeaderAdapter);
        cityRecycler.addItemDecoration(mDecoration = new SuspensionDecoration(this, cityList).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        cityRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        initDatas(getResources().getStringArray(R.array.provinces));
        //点击取消返回
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        citydatas = data;
        cityList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            cityList.add(cityBean);
        }

        mIndexBar.setmSourceDatas(cityList)//设置数据
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                .invalidate();

        cityAdapter.setDatas(cityList);
        mHeaderAdapter.notifyDataSetChanged();
        mDecoration.setmDatas(cityList);
    }


}
