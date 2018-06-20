package com.example.mr.yihuanhuishou.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Home_detailActivity;
import com.example.mr.yihuanhuishou.activity.LocationActivity;
import com.example.mr.yihuanhuishou.activity.OrderActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.driver.fragment.BaseFragment;
import com.example.mr.yihuanhuishou.driver.fragment.HomeListFragment;
import com.example.mr.yihuanhuishou.driver.fragment.HomeMapFragment;
import com.example.mr.yihuanhuishou.driver.ui.DriverMessageActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverOrderActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverPersonActivity;
import com.example.mr.yihuanhuishou.driver.ui.MyDriverMessageActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverHomeActivity extends BaseActivity {

    @BindView(R.id.home_dingdan)
    ImageView homeDingdan;
    @BindView(R.id.home_lianxiren)
    ImageView homeLianxiren;
    @BindView(R.id.home_msg)
    ImageView homeMsg;
    @BindView(R.id.home_address)
    TextView homeAddress;
    @BindView(R.id.jiao)
    LinearLayout jiao;
    @BindView(R.id.ding)
    RelativeLayout ding;
    @BindView(R.id.home_ditu_tv)
    ImageView homeDituTv;
    @BindView(R.id.home_liebiao_tv)
    ImageView homeLiebiaoTv;
    @BindView(R.id.home_ditu_ll)
    LinearLayout homeDituLl;
    @BindView(R.id.home_liebiao_ll)
    LinearLayout homeLiebiaoLl;
    private BaseFragment baseFragment;
    private HomeMapFragment homeMapFragment;
    private HomeListFragment homeListFragment;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_dingwei eveen) {
        String city = eveen.getCity();
        homeAddress.setText(city);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        homeMapFragment = new HomeMapFragment();
        addFragments(homeMapFragment);
        initView();
    }

    private void addFragments(BaseFragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (baseFragment != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(baseFragment);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl_content, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        baseFragment = f;
    }

    private void initView() {
        //TODO 测试账户密码，记得修改
        EMClient.getInstance().login("test02","123",new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Logger.e("登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Logger.e("登录聊天服务器失败！"+message);
            }
        });
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }
        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(DriverHomeActivity.this)){
                            //连接不到聊天服务器
                        } else{
                            //当前网络不可用，请检查网络设置
                        }
                    }
                }
            });
        }
    }

    @OnClick(R.id.home_dingdan)
    public void onHomeDingdanClicked() {
        startActivity(new Intent(this,DriverOrderActivity.class));
    }

    @OnClick(R.id.home_lianxiren)
    public void onHomeLianxirenClicked() {
        startActivity(new Intent(this,DriverPersonActivity.class));
    }

    @OnClick(R.id.home_msg)
    public void onHomeMsgClicked() {
        startActivity(new Intent(this,MyDriverMessageActivity.class));
    }

    @OnClick(R.id.jiao)
    public void onJiaoClicked() {
        Intent intent = new Intent(DriverHomeActivity.this, LocationActivity.class);
        intent.putExtra("address", homeAddress.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.home_ditu_ll)
    public void onHomeDituLlClicked() {
        if (homeMapFragment == null) {
            homeMapFragment = new HomeMapFragment();
        }
        addFragments(homeMapFragment);
        homeDituTv.setImageResource(R.drawable.driver_map_true_iv);
        homeLiebiaoTv.setImageResource(R.drawable.driver_liebiao_false_iv);
    }

    @OnClick(R.id.home_liebiao_ll)
    public void onHomeLiebiaoLlClicked() {
        if (homeListFragment == null) {
            homeListFragment = new HomeListFragment();
        }
        addFragments(homeListFragment);
        homeDituTv.setImageResource(R.drawable.driver_map_false_iv);
        homeLiebiaoTv.setImageResource(R.drawable.driver_liebiao_true_iv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}


/*RatingBarView ratingBarView = helper.getView(R.id.ratingBarView);
            ratingBarView.setRatingCount(5);//设置RatingBarView总数
            ratingBarView.setSelectedCount(2);//设置RatingBarView选中数
            ratingBarView.setSelectedIconResId(R.drawable.start_check);//设置RatingBarView选中的图片id
            ratingBarView.setNormalIconResId(R.drawable.start_nocheck);//设置RatingBarView正常图片id
            ratingBarView.setClickable(false);//设置RatingBarView是否可点击
            ratingBarView.setChildPadding(2);//设置RatingBarView的子view的padding
            ratingBarView.setChildMargin(2);//设置RatingBarView的子view左右之间的margin
            ratingBarView.setChildDimension(22);//设置RatingBarView的子view的宽高尺寸
            ratingBarView.setOnRatingBarClickListener(new RatingBarView.RatingBarViewClickListener() {
                @Override
                public void onRatingBarClick(LinearLayout parent, View childView, int position) {
                    Log.e("tag", String.valueOf(childView instanceof ImageView) + "," + position);
                }
            });
            if (helper.getAdapterPosition()==4){
                helper.getView(R.id.tv_lookmore).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_lookmore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, AgentListActivity.class));
                    }
                });
            }*/