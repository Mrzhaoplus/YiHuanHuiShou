package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.fragment.Frie_Msg_Fragment;
import com.example.mr.yihuanhuishou.fragment.Inform_msg_fragment;
import com.example.mr.yihuanhuishou.fragment.Jumin_Msg_feagment;
import com.example.mr.yihuanhuishou.fragment.huishou.Shangmen_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Wancheng_fragment;
import com.example.mr.yihuanhuishou.fragment.huishou.Zhifu_fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Msg_neirongActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
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
    private boolean flag=true;
    private Fragment currfit;
    private Jumin_Msg_feagment jumin_msg_feagment;
    private int msg;
    private Frie_Msg_Fragment frie_msg_fragment;
    private Inform_msg_fragment inform_msg_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_neirong);
        ButterKnife.bind(this);
        initview();

    }
    private void initview() {
        //事件
        rela2.setOnClickListener(this);
        rela3.setOnClickListener(this);
        rela4.setOnClickListener(this);
        beak.setOnClickListener(this);

        //注册
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag =false;
        }
        if(msg ==1){
            xian2.setVisibility(View.VISIBLE);
            xian3.setVisibility(View.GONE);
            xian4.setVisibility(View.GONE);
            jumsg.setTextColor(getResources().getColor(R.color.colorAccent));
            firemsg.setTextColor(getResources().getColor(R.color.black));
            tongmsg.setTextColor(getResources().getColor(R.color.black));
            if(jumin_msg_feagment==null){
                jumin_msg_feagment = new Jumin_Msg_feagment();
            }
            AddFragment(jumin_msg_feagment);
        }else if(msg==2){
            xian2.setVisibility(View.GONE);
            xian3.setVisibility(View.VISIBLE);
            xian4.setVisibility(View.GONE);
            jumsg.setTextColor(getResources().getColor(R.color.black));
            firemsg.setTextColor(getResources().getColor(R.color.colorAccent));
            tongmsg.setTextColor(getResources().getColor(R.color.black));
            if(frie_msg_fragment==null){
                frie_msg_fragment = new Frie_Msg_Fragment();
            }
            AddFragment(frie_msg_fragment);
        }else if(msg==3){
            xian2.setVisibility(View.GONE);
            xian3.setVisibility(View.GONE);
            xian4.setVisibility(View.VISIBLE);
            jumsg.setTextColor(getResources().getColor(R.color.black));
            firemsg.setTextColor(getResources().getColor(R.color.black));
            tongmsg.setTextColor(getResources().getColor(R.color.colorAccent));
            if(inform_msg_fragment==null){
                inform_msg_fragment = new Inform_msg_fragment();
            }
            AddFragment(inform_msg_fragment);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_fragment eveen){
        msg = eveen.getMsg();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
               case R.id.beak:
                   finish();
                   break;
            case R.id.rela2:
                xian2.setVisibility(View.VISIBLE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.GONE);
                jumsg.setTextColor(getResources().getColor(R.color.colorAccent));
                firemsg.setTextColor(getResources().getColor(R.color.black));
                tongmsg.setTextColor(getResources().getColor(R.color.black));

                if(jumin_msg_feagment==null){
                    jumin_msg_feagment = new Jumin_Msg_feagment();
                }
                AddFragment(jumin_msg_feagment);
                break;

            case R.id.rela3:
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.VISIBLE);
                xian4.setVisibility(View.GONE);
                jumsg.setTextColor(getResources().getColor(R.color.black));
                firemsg.setTextColor(getResources().getColor(R.color.colorAccent));
                tongmsg.setTextColor(getResources().getColor(R.color.black));
                if(frie_msg_fragment==null){
                    frie_msg_fragment = new Frie_Msg_Fragment();
                }
                AddFragment(frie_msg_fragment);
                break;
            case R.id.rela4:
                xian2.setVisibility(View.GONE);
                xian3.setVisibility(View.GONE);
                xian4.setVisibility(View.VISIBLE);
                jumsg.setTextColor(getResources().getColor(R.color.black));
                firemsg.setTextColor(getResources().getColor(R.color.black));
                tongmsg.setTextColor(getResources().getColor(R.color.colorAccent));
                if(inform_msg_fragment==null){
                    inform_msg_fragment = new Inform_msg_fragment();
                }
                AddFragment(inform_msg_fragment);
                break;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
