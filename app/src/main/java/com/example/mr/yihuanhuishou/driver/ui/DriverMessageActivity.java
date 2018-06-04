package com.example.mr.yihuanhuishou.driver.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.driver.fragment.DriverGonggaoFragment;
import com.example.mr.yihuanhuishou.driver.fragment.DriverMessageFragment;
import com.example.mr.yihuanhuishou.driver.fragment.DriverTongzhiFragment;
import com.example.mr.yihuanhuishou.fragment.Frie_Msg_Fragment;
import com.example.mr.yihuanhuishou.fragment.Inform_msg_fragment;
import com.example.mr.yihuanhuishou.fragment.Jumin_Msg_feagment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverMessageActivity extends BaseActivity {
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    List<String> tabList = new ArrayList<>();
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.jumsg)
    TextView jumsg;
    @BindView(R.id.xian2)
    View xian2;
    @BindView(R.id.rela2)
    RelativeLayout rela2;
    @BindView(R.id.firemsg)
    TextView firemsg;
    @BindView(R.id.xian3)
    View xian3;
    @BindView(R.id.rela3)
    RelativeLayout rela3;
    @BindView(R.id.tongmsg)
    TextView tongmsg;
    @BindView(R.id.xian4)
    View xian4;
    @BindView(R.id.rela4)
    RelativeLayout rela4;
    @BindView(R.id.fl)
    FrameLayout fl;
    private int msg;
    private Fragment currfit;
    private DriverMessageFragment driverMessageFragment;
    private DriverTongzhiFragment driverTongzhiFragment;
    private DriverGonggaoFragment driverGonggaoFragment;


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){
        msg = eveen.getMsg();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_message);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的消息");

        if(msg ==1){
            xian2.setVisibility(View.VISIBLE);
            xian3.setVisibility(View.GONE);
            xian4.setVisibility(View.GONE);
            jumsg.setTextColor(getResources().getColor(R.color.colorAccent));
            firemsg.setTextColor(getResources().getColor(R.color.black));
            tongmsg.setTextColor(getResources().getColor(R.color.black));
            if(driverMessageFragment == null){
                driverMessageFragment = new DriverMessageFragment();
            }
            AddFragment(driverMessageFragment);
        }else if(msg==2){
            xian2.setVisibility(View.GONE);
            xian3.setVisibility(View.VISIBLE);
            xian4.setVisibility(View.GONE);
            jumsg.setTextColor(getResources().getColor(R.color.black));
            firemsg.setTextColor(getResources().getColor(R.color.colorAccent));
            tongmsg.setTextColor(getResources().getColor(R.color.black));
            if(driverTongzhiFragment == null){
                driverTongzhiFragment = new DriverTongzhiFragment();
            }
            AddFragment(driverTongzhiFragment);
        }else if(msg==3){
            xian2.setVisibility(View.GONE);
            xian3.setVisibility(View.GONE);
            xian4.setVisibility(View.VISIBLE);
            jumsg.setTextColor(getResources().getColor(R.color.black));
            firemsg.setTextColor(getResources().getColor(R.color.black));
            tongmsg.setTextColor(getResources().getColor(R.color.colorAccent));
            if(driverGonggaoFragment == null){
                driverGonggaoFragment = new DriverGonggaoFragment();
            }
            AddFragment(driverGonggaoFragment);
        }
    }

    /*
      * 动态添加fragment方法
      * */
    public void AddFragment(Fragment f){
        FragmentManager childFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);

        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }

    @OnClick({R.id.rela2, R.id.rela3, R.id.rela4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rela2:
                xian2.setVisibility(View.VISIBLE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.GONE);
                jumsg.setTextColor(getResources().getColor(R.color.colorAccent));
                firemsg.setTextColor(getResources().getColor(R.color.black));
                tongmsg.setTextColor(getResources().getColor(R.color.black));

                if(driverMessageFragment == null){
                    driverMessageFragment = new DriverMessageFragment();
                }
                AddFragment(driverMessageFragment);
                break;
            case R.id.rela3:
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.VISIBLE);
                xian4.setVisibility(View.GONE);
                jumsg.setTextColor(getResources().getColor(R.color.black));
                firemsg.setTextColor(getResources().getColor(R.color.colorAccent));
                tongmsg.setTextColor(getResources().getColor(R.color.black));
                if(driverTongzhiFragment == null){
                    driverTongzhiFragment = new DriverTongzhiFragment();
                }
                AddFragment(driverTongzhiFragment);
                break;
            case R.id.rela4:
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.VISIBLE);
                jumsg.setTextColor(getResources().getColor(R.color.black));
                firemsg.setTextColor(getResources().getColor(R.color.black));
                tongmsg.setTextColor(getResources().getColor(R.color.colorAccent));
                if(driverGonggaoFragment == null){
                    driverGonggaoFragment = new DriverGonggaoFragment();
                }
                AddFragment(driverGonggaoFragment);
                break;
        }
    }
}
