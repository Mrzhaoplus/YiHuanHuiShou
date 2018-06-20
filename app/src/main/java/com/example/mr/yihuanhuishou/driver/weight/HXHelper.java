package com.example.mr.yihuanhuishou.driver.weight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.example.mr.yihuanhuishou.utils.SpUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.EMMessage.Status;
import com.hyphenate.chat.EMMessage.Type;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.util.EMLog;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class HXHelper {

    protected static final String TAG = "HXHelper";

	private EaseUI easeUI;

    /**
     * EMEventListener
     */
    protected EMMessageListener messageListener = null;
	private Map<String, EaseUser> contactList;
	private static HXHelper instance = null;
	private String username;
    private Context appContext;
    private LocalBroadcastManager broadcastManager;
    private ExecutorService executor;

	private HXHelper() {
        executor = Executors.newCachedThreadPool();
	}

	public synchronized static HXHelper getInstance() {
		if (instance == null) {
			instance = new HXHelper();
		}
		return instance;
	}

	/**
	 * init helper
	 *
	 * @param context
	 *            application context
	 */
	public void init(Context context) {
	    EMOptions options = initChatOptions();
	    //use default options if options is null
		if (EaseUI.getInstance().init(context, options)) {
		    appContext = context;
		    //debug mode, you'd better set it to false, if you want release your App officially.
		    EMClient.getInstance().setDebugMode(true);
		    //get easeui instance
		    easeUI = EaseUI.getInstance();
		    //to set user's profile and avatar
		    setEaseUIProviders();
            setGlobalListeners();
			broadcastManager = LocalBroadcastManager.getInstance(appContext);
		}
	}

    private EMOptions initChatOptions(){
        Log.d(TAG, "init HuanXin Options");

        EMOptions options = new EMOptions();
        // set if accept the invitation automatically
        options.setAcceptInvitationAlways(false);
        // set if you need read ack
        options.setRequireAck(true);
        // set if you need delivery ack
        options.setRequireDeliveryAck(false);
        return options;
    }


    protected void setEaseUIProviders() {
    	// set profile provider if you want easeUI to handle avatar and nickname
        easeUI.setUserProfileProvider(new EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });
    }

    private EaseUser getUserInfo(String username){
        //获取 EaseUser实例, 这里从内存中读取
        //如果你是从服务器中读读取到的，最好在本地进行缓存
        EaseUser user = null;
        //如果用户是本人，就设置自己的头像
        if(username.equals(EMClient.getInstance().getCurrentUser())){
            user = new EaseUser(username);
            user.setAvatar(SpUtils.getString(appContext,"face",""));
            user.setNick(SpUtils.getString(appContext,"userName",""));
            return user;
        }else {
            user = new EaseUser(username);
            user.setAvatar("http://img4q.duitang.com/uploads/item/201506/13/20150613215049_xiHNV.jpeg");
            user.setNick("Power公司端");
        }

        //收到别人的消息，设置别人的头像
//        if (contactList!=null && contactList.containsKey(username)){
//            user=contactList.get(username);
//        }else { //如果内存中没有，则将本地数据库中的取出到内存中
//            user=contactList.get(username);
//        }

        //如果用户不是你的联系人，则进行初始化
        if(user == null){
            user = new EaseUser(username);
            EaseCommonUtils.setUserInitialLetter(user);
        }
//        else {
//            if (TextUtils.isEmpty(user.getAvatar())){//如果名字为空，则显示环信号码
//                user.setNick(user.getUsername());
//            }
//        }
        Log.i("zcb","头像："+user.getAvatar());
        return user;
    }

    /**
     * get current user's id
     */
    public String getCurrentUsernName(){
        if(username == null){
            username = "test01";
        }
        return username;
    }

    /**
     * set global listener
     */
    protected void setGlobalListeners(){
        registerMessageListener();
    }
	
	 /**
     * Global listener
     * If this event already handled by an activity, you don't need handle it again
     * activityList.size() <= 0 means all activities already in background or not in Activity Stack
     */
    protected void registerMessageListener() {
    	messageListener = new EMMessageListener() {
            private BroadcastReceiver broadCastReceiver = null;

			@Override
			public void onMessageReceived(List<EMMessage> messages) {
			    for (EMMessage message : messages) {
                    EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
                    //接收并处理扩展消息
                    String userName=message.getStringAttribute("userName","");
                    String userPic=message.getStringAttribute("face","");
                    String hxIdFrom=message.getFrom();
                    com.orhanobut.logger.Logger.e("helper接收到的用户名："+userName+"helper头像："+userPic);
                    EaseUser easeUser=new EaseUser(hxIdFrom);
                    easeUser.setAvatar(userPic);
                    easeUser.setNick(userName);
                    
                }
			}
			
			@Override
			public void onCmdMessageReceived(List<EMMessage> messages) {
			    for (EMMessage message : messages) {
                    EMLog.d(TAG, "receive command message");
                    //get message body
                    EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
                    final String action = cmdMsgBody.action();//获取自定义action
                    //获取扩展属性 此处省略
                    //maybe you need get extension of your message
                    //message.getStringAttribute("");
                    EMLog.d(TAG, String.format("Command：action:%s,message:%s", action,message.toString()));
                }
			}

			@Override
			public void onMessageRead(List<EMMessage> messages) {
			}
			
			@Override
			public void onMessageDelivered(List<EMMessage> message) {
			}

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                for (EMMessage msg : messages) {
                    if(msg.getChatType() == ChatType.GroupChat && EaseAtMessageHelper.get().isAtMeMsg(msg)){
                        EaseAtMessageHelper.get().removeAtMeGroup(msg.getTo());
                    }
                    EMMessage msgNotification = EMMessage.createReceiveMessage(Type.TXT);
                    msgNotification.setFrom(msg.getFrom());
                    msgNotification.setTo(msg.getTo());
                    msgNotification.setUnread(false);
                    msgNotification.setMsgTime(msg.getMsgTime());
                    msgNotification.setLocalTime(msg.getMsgTime());
                    msgNotification.setChatType(msg.getChatType());
                    msgNotification.setStatus(Status.SUCCESS);
                    EMClient.getInstance().chatManager().saveMessage(msgNotification);
                }
            }

            @Override
			public void onMessageChanged(EMMessage message, Object change) {
                EMLog.d(TAG, "change:");
				EMLog.d(TAG, "change:" + change);
			}
		};
		
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

}
