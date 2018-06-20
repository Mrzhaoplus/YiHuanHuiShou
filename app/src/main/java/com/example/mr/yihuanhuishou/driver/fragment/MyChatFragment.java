package com.example.mr.yihuanhuishou.driver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mr.yihuanhuishou.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.orhanobut.logger.Logger;

/**
 * @author power
 * @date 2018/6/13 下午3:18
 * @description:
 */
public class MyChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, chatType != EaseConstant.CHATTYPE_CHATROOM);
    }

    @Override
    protected void setUpView() {
        setChatFragmentHelper(this);
        super.setUpView();
        //隐藏右边删除聊天记录按钮
        titleBar.setRightLayoutVisibility(View.GONE);
        titleBar.setLeftImageResource(R.drawable.title_back_iv);
    }

    /**
     * @param message
     * 设置消息的扩展属性  我们要把用户信息和头像放到里边
     */
    @Override
    public void onSetMessageAttributes(EMMessage message) {
        //设置要发送扩展消息用户昵称
        message.setAttribute("userName", "Power回收司机");
        Logger.e("发送出去的userName：Power回收司机");
        //设置要发送扩展消息用户头像
        message.setAttribute("face", "http://img5.duitang.com/uploads/item/201508/30/20150830132007_TjANX.thumb.224_0.jpeg");
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
