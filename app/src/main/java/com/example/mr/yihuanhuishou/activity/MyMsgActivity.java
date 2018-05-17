package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Mymsg_Adapter;
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

public class MyMsgActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.jumsg)
    LinearLayout jumsg;
    @BindView(R.id.firemsg)
    LinearLayout firemsg;
    @BindView(R.id.tongmsg)
    LinearLayout tongmsg;
    @BindView(R.id.swip_recycler)
    SwipeMenuRecyclerView mrecycler;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        //点击事件
        beak.setOnClickListener(this);
        jumsg.setOnClickListener(this);
        firemsg.setOnClickListener(this);
        tongmsg.setOnClickListener(this);
        //循环添加集合
        for (int i=0;i<8;i++){
            list.add("");
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final Mymsg_Adapter mymsg_adapter = new Mymsg_Adapter(MyMsgActivity.this,list);
        // 设置监听器。
        mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);

        mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                list.remove( menuBridge.getAdapterPosition());
                menuBridge.closeMenu();
                mymsg_adapter.notifyDataSetChanged();
            }
        });
        mrecycler.setAdapter(mymsg_adapter);
    }
        // 创建菜单:
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
//            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyMsgActivity.this); // 各种文字和图标属性设置。
                deleteItem.setWeight(100);
                deleteItem.setHeight(260);
                deleteItem.setText("   删除   ");
                deleteItem.setTextSize(14);
                deleteItem.setBackgroundColor(getResources().getColor(R.color.red));
                deleteItem.setTextColor(Color.WHITE);
                rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
            }
        };

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.beak:
                finish();
                break;
            case R.id.jumsg:
                EventBus.getDefault().postSticky(new Event_fragment(1));
                intent = new Intent(MyMsgActivity.this, Msg_neirongActivity.class);
                startActivity(intent);
                break;
            case R.id.firemsg:
                EventBus.getDefault().postSticky(new Event_fragment(2));
                intent = new Intent(MyMsgActivity.this, Msg_neirongActivity.class);
                startActivity(intent);
                break;
            case R.id.tongmsg:
                EventBus.getDefault().postSticky(new Event_fragment(3));
                intent = new Intent(MyMsgActivity.this, Msg_neirongActivity.class);
                startActivity(intent);
                break;
        }
    }
}




