package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.fragment.MyChatFragment;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity {

    @BindView(R.id.chat_framelayout)
    FrameLayout chatFramelayout;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        user_id = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        initView();
    }

    private void initView() {
        //EaseUi封装好的聊天界面
        MyChatFragment chatFragment = new MyChatFragment(user_id);
        //将参数传递给聊天界面
        chatFragment.setArguments(getIntent().getExtras());
        //加载聊天界面fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.chat_framelayout,chatFragment).commit();
    }
}
