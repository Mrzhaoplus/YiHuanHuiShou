package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Huanxin_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

/**
 * @author power
 * @date 2018/6/13 下午3:18
 * @description:
 */
public class MyChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    private String userid;
    private String photoPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, chatType != EaseConstant.CHATTYPE_CHATROOM);
    }

    @SuppressLint("ValidFragment")
    public MyChatFragment(String userid) {
        this.userid = userid;
    }

    public MyChatFragment() {
    }

    @Override
    protected void setUpView() {
        HttpParams params = new HttpParams();
        params.put("imUser",userid);
        OkGo.<Driver_Huanxin_Bean>post(MyUrls.BASEURL + "/app/company/getPersonInfoByHX")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driver_Huanxin_Bean>(getActivity(), Driver_Huanxin_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driver_Huanxin_Bean> response) {
                        Driver_Huanxin_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            titleBar.setTitle(body.getData().getName());
                            photoPath = body.getData().getHeadImage();
                        } else{
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }
                    }
                });
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

       /* //设置要发送扩展消息用户昵称
        message.setAttribute("userName", "Power回收司机");
        Log.e("====================","Power回收司机");
        //设置要发送扩展消息用户头像
        message.setAttribute("face", "http://img5.duitang.com/uploads/item/201508/30/20150830132007_TjANX.thumb.224_0.jpeg");*/

    }
    @Override
    public void onEnterToChatDetails() {

    }
    @Override
    public void onAvatarClick(String username) {
        Log.e("=======================","111111111111111");
    }
    @Override
    public void onAvatarLongClick(String username) {
        Log.e("=======================","222222222222222");
    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        Log.e("=======================","33333333333333");
        return false;
    }
    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        Log.e("=======================","555555555555555");
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {

        return null;
    }
}
