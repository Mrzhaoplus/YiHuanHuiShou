package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Msg_neirongActivity;
import com.example.mr.yihuanhuishou.activity.MyMsgActivity;
import com.example.mr.yihuanhuishou.adapter.Mymsg_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDriverMessageActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.swip_recycler)
    SwipeMenuRecyclerView swipRecycler;
    @BindView(R.id.xiaoxi_ll)
    LinearLayout xiaoxiLl;
    @BindView(R.id.tongzhi_ll)
    LinearLayout tongzhiLl;
    @BindView(R.id.gonggao_ll)
    LinearLayout gonggaoLl;
    List<String> list = new ArrayList<>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_driver_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的消息");
        //循环添加集合
        for (int i=0;i<8;i++){
            list.add("");
        }
        swipRecycler.setNestedScrollingEnabled(false);
        swipRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final Mymsg_Adapter mymsg_adapter = new Mymsg_Adapter(MyDriverMessageActivity.this,list);
        // 设置监听器。
        swipRecycler.setSwipeMenuCreator(mSwipeMenuCreator);

        swipRecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                list.remove( menuBridge.getAdapterPosition());
                menuBridge.closeMenu();
                mymsg_adapter.notifyDataSetChanged();
            }
        });
        swipRecycler.setAdapter(mymsg_adapter);
    }

    // 创建菜单:
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
//            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyDriverMessageActivity.this); // 各种文字和图标属性设置。
            deleteItem.setWeight(100);
            deleteItem.setHeight(260);
            deleteItem.setText("   删除   ");
            deleteItem.setTextSize(14);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.red));
            deleteItem.setTextColor(Color.WHITE);
            rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
        }
    };

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.xiaoxi_ll, R.id.tongzhi_ll, R.id.gonggao_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiaoxi_ll:
                EventBus.getDefault().postSticky(new Event_fragment(1));
                intent = new Intent(MyDriverMessageActivity.this, DriverMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.tongzhi_ll:
                EventBus.getDefault().postSticky(new Event_fragment(2));
                intent = new Intent(MyDriverMessageActivity.this, DriverMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.gonggao_ll:
                EventBus.getDefault().postSticky(new Event_fragment(3));
                intent = new Intent(MyDriverMessageActivity.this, DriverMessageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
