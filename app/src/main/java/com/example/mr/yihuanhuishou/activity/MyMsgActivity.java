package com.example.mr.yihuanhuishou.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Mymsg_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.driver.ui.ChatActivity;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.SpUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMsgActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.spring)
    SpringView spring;
    private EMMessageListener msgListener;
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
    List<String> list;
    private Map<String, EMConversation> map;
      private boolean flag=true;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        ButterKnife.bind(this);
        initview();
        initdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){

        int msgg = eveen.getMsg();
        if(msgg==3){
           initdata();
        }
    }
    private void initdata() {
        list = new ArrayList<>();
        map = EMClient.getInstance().chatManager().getAllConversations();
        Set<Map.Entry<String, EMConversation>> set = map.entrySet();
        for (Map.Entry<String, EMConversation> me : set) {
            String key = me.getKey();
            list.add(key);
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Mymsg_Adapter mymsg_adapter = new Mymsg_Adapter(MyMsgActivity.this, list, map);
        mrecycler.setAdapter(mymsg_adapter);
        mymsg_adapter.setOnMyItemClickListener(new Mymsg_Adapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                //设置要发送出去的昵称
                SpUtils.putString(MyMsgActivity.this, "userName", sp.getString(GGUtils.USER_NAME,""));
                //设置要发送出去的头像
                SpUtils.putString(MyMsgActivity.this, "face", sp.getString(GGUtils.IMAGE_path,""));
                Intent intent = new Intent(MyMsgActivity.this, ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, list.get(pos));
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);
            }
        });
    }
    private void initview() {
        //点击事件
        beak.setOnClickListener(this);
        jumsg.setOnClickListener(this);
        firemsg.setOnClickListener(this);
        tongmsg.setOnClickListener(this);

        // 设置监听器。
        mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);
        mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                EMClient.getInstance().chatManager().deleteConversation(list.get(menuBridge.getAdapterPosition()), true);
                list = new ArrayList<>();
                map = EMClient.getInstance().chatManager().getAllConversations();
                Set<Map.Entry<String, EMConversation>> set = map.entrySet();
                for (Map.Entry<String, EMConversation> me : set) {
                    String key = me.getKey();
                    list.add(key);
                }
                menuBridge.closeMenu();
                Mymsg_Adapter mymsg_adapter = new Mymsg_Adapter(MyMsgActivity.this, list, map);
                mrecycler.setAdapter(mymsg_adapter);
            }
        });
        //刷新加载
        spring.setType(SpringView.Type.FOLLOW);
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initview();
                        initdata();
                    }
                },0);
                spring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                spring.onFinishFreshAndLoad();
            }
        });
        spring.setFooter(new DefaultFooter(this));
        spring.setHeader(new DefaultHeader(this));
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
        switch (view.getId()) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}




