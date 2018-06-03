package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home_detailActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.home_dingdan)
    ImageView dingdan;
    @BindView(R.id.home_address)
    TextView address;
    @BindView(R.id.jiao)
    LinearLayout jiao;
    @BindView(R.id.home_msg)
    ImageView msg;
    @BindView(R.id.home_lianxiren)
    ImageView lianxiren;
    @BindView(R.id.home_dingwei)
    ImageView dingwei;
    @BindView(R.id.home_xiangqing)
    ImageView xiangqing;
    @BindView(R.id.home_send)
    Button send;
    @BindView(R.id.home_jm)
    ImageView jumin;
    @BindView(R.id.home_card)
    ImageView card;
    @BindView(R.id.home_firm)
    ImageView firm;
    @BindView(R.id.home_ddxx)
    LinearLayout dd_msg;
    @BindView(R.id.home_qd)
    TextView qd;
    @BindView(R.id.map)
    MapView mMapView;

    private long preTime;
    boolean flag = true;
    private AMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
        initview(savedInstanceState);
    }

    private void initview(Bundle savedInstanceState) {
        //创建地图
        mMapView.onCreate(savedInstanceState);
        //显示地图
        if(map==null){
            map = mMapView.getMap();
        }
     /*   //显示定位
       MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        map.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //map.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        map.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）        //点击事件
        myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
*/
        dingdan.setOnClickListener(this);
        jiao.setOnClickListener(this);
        msg.setOnClickListener(this);
        lianxiren.setOnClickListener(this);
        dingwei.setOnClickListener(this);
        xiangqing.setOnClickListener(this);
        send.setOnClickListener(this);
        jumin.setOnClickListener(this);
        card.setOnClickListener(this);
        firm.setOnClickListener(this);
        qd.setOnClickListener(this);
        if (flag) {
            //注册
            EventBus.getDefault().register(this);
            flag = false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_dingwei eveen) {
        String city = eveen.getCity();
        address.setText(city);
    }

    /*
      返回键退出程序
    */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            BaseActivity.realBack();//删除所有引用
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //公司
            case R.id.home_firm:
                firm.setImageResource(R.drawable.home_firm_xuanzhong);
                card.setImageResource(R.drawable.home_qiche_ccchdpi);
                jumin.setImageResource(R.drawable.home_jm_xxxhdpi);
                send.setVisibility(View.VISIBLE);
                dd_msg.setVisibility(View.GONE);
                break;
            //汽车
            case R.id.home_card:
                firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                card.setImageResource(R.drawable.home_card_icon_xxxhdpi);
                jumin.setImageResource(R.drawable.home_jm_xxxhdpi);
                send.setVisibility(View.VISIBLE);
                dd_msg.setVisibility(View.GONE);
                break;
            //居民
            case R.id.home_jm:
                firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                card.setImageResource(R.drawable.home_qiche_ccchdpi);
                jumin.setImageResource(R.drawable.home_jm_icon_xxxhdpi);
                send.setVisibility(View.GONE);
                dd_msg.setVisibility(View.VISIBLE);
                break;
            //发送配送消息
            case R.id.home_send:
                ToastUtils.getToast(Home_detailActivity.this, "发送");
                break;
            //订单
            case R.id.home_dingdan:
                Intent intent2 = new Intent(Home_detailActivity.this, OrderActivity.class);
                startActivity(intent2);
                break;
            //消息
            case R.id.home_msg:
                Intent intent3 = new Intent(Home_detailActivity.this, MyMsgActivity.class);
                startActivity(intent3);
                break;
            //个人中心
            case R.id.home_lianxiren:
                Intent intent4 = new Intent(Home_detailActivity.this, MineActivity.class);
                startActivity(intent4);
                break;
            //定位
            case R.id.home_dingwei:
                ToastUtils.getToast(Home_detailActivity.this, "定位");
                break;
            //详情
            case R.id.home_xiangqing:
                Intent intent5 = new Intent(Home_detailActivity.this, Bill_DetailActivity.class);
                startActivity(intent5);
                break;
            //抢单
            case R.id.home_qd:
                Intent intent1 = new Intent(Home_detailActivity.this, Bill_DetailActivity.class);
                startActivity(intent1);
                break;
            //点击坐标
            case R.id.jiao:
                Intent intent = new Intent(Home_detailActivity.this, LocationActivity.class);
                intent.putExtra("address", address.getText().toString());
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}
