package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.CompanyBean;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.bean.LatLngBean;
import com.example.mr.yihuanhuishou.driver.ui.CompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.weight.MyInfoWindowAdapter;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import internal.org.java_websocket.client.WebSocketClient;
import internal.org.java_websocket.drafts.Draft_10;
import internal.org.java_websocket.handshake.ServerHandshake;

public class Home_detailActivity extends AppCompatActivity implements View.OnClickListener, AMapLocationListener {
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
    MapView map;
    List<String> list = new ArrayList<>();
    private long preTime;
    boolean flag = true;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    //声明mlocationClient对象
    private AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;
    private LatLng latLng;
    private View view;
    private List<LatLngBean> latLngBeanList;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<CompanyBean> companyBeanList;
    private SharedPreferences sp;
    private WebSocketClient mWebSocketClient;
    private AMapLocation amapLocation1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("======================","新消息："+msg.obj);
            dd_msg.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initview(savedInstanceState);
        initMap(savedInstanceState);
        initdata();

    }
//"ws://39.106.220.142/webSocketTest?token="+sp.getString(GGUtils.TOKEN,"")+"&longitude="+amapLocation1.getLongitude()+"&latitude="+amapLocation1.getLatitude()+""
//"ws://172.168.20.92:8080/EEC/webSocketTest?token="+sp.getString(GGUtils.TOKEN,"")+"&longitude="+amapLocation1.getLongitude()+"&latitude="+amapLocation1.getLatitude()+""
    private void initSocketClient() throws URISyntaxException {

        if(mWebSocketClient == null) {
            mWebSocketClient= new WebSocketClient(new URI("ws://39.106.220.142/webSocketTest?token="+sp.getString(GGUtils.TOKEN,"")+"&longitude="+amapLocation1.getLongitude()+"&latitude="+amapLocation1.getLatitude()+"")) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                     Log.e("====================","开启通道");
                }

                @Override
                public void onMessage(String s) {
                    Log.e("====================","接收消息："+s);
                    Message obtain = Message.obtain();
                    obtain.obj=s;
                    handler.sendMessage(obtain);
                }
                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.e("====================","通道关闭");
                    closeConnect();
                }

                @Override
                public void onError(Exception e) {
                    Log.e("====================","错误"+e);
                }
            };
            mWebSocketClient.connect();
        }

    }

    //断开连接
    private void closeConnect() {
        try {
            mWebSocketClient.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            mWebSocketClient = null;
        }
    }


    private void initdata() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
    }

    private void initview(Bundle savedInstanceState) {
//        //创建地图
//        mMapView.onCreate(savedInstanceState);
//        //显示地图
//        if(map==null){
//            map = mMapView.getMap();
//        }

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
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() > preTime + 2000) {
//            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
//            preTime = System.currentTimeMillis();
//        } else {
//            super.onBackPressed();//相当于finish()
//            BaseActivity.realBack();//删除所有引用
//        }
//    }

    private void initMap(Bundle savedInstanceState) {
        //创建地图
        map.onCreate(savedInstanceState);
        //显示地图
        if (aMap == null) aMap = map.getMap();
        //隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        //更改定位图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.driver_map_location_iv));
        //设置不显示范围圆圈
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色

        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();

        //marker标记
        initMarker("jumin");


    }

    private void initMarker(final String type) {
        if (TextUtils.equals("jumin", type)) {
            latLngBeanList = new ArrayList<>();
            latLngBeanList.add(new LatLngBean(40.045917, 116.287545, "15373228877", "jumin"));
            latLngBeanList.add(new LatLngBean(40.046917, 116.282945, "15215517733", "juminlou"));
            latLngBeanList.add(new LatLngBean(40.049917, 116.282745, "17611225576", "juminlou"));
            latLngBeanList.add(new LatLngBean(40.043217, 116.286745, "18977665431", "jumin"));
            latLngBeanList.add(new LatLngBean(40.040917, 116.288455, "13386559327", "jumin"));
            for (int i = 0; i < latLngBeanList.size(); i++) {
                final LatLngBean latLngBean = latLngBeanList.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(latLngBean.getLatitude(), latLngBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (latLngBean.getType()) {
                    case "jumin":
                        view = LayoutInflater.from(this).inflate(R.layout.view_jumin, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "juminlou":
                        view = LayoutInflater.from(this).inflate(R.layout.view_juminlou, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        } else if (TextUtils.equals("gongsi", type)) {
            companyBeanList = new ArrayList<>();
            companyBeanList.add(new CompanyBean(40.045917, 116.287545, "aldeady"));
            companyBeanList.add(new CompanyBean(40.046917, 116.282945, "not"));
            companyBeanList.add(new CompanyBean(40.049917, 116.282745, "not"));
            companyBeanList.add(new CompanyBean(40.043217, 116.286745, "aldeady"));
            companyBeanList.add(new CompanyBean(40.040917, 116.288455, "aldeady"));
            for (int i = 0; i < companyBeanList.size(); i++) {
                final CompanyBean companyBean = companyBeanList.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(companyBean.getLatitude(), companyBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (companyBean.getType()) {
                    case "aldeady":
                        view = LayoutInflater.from(this).inflate(R.layout.view_yiqianyue, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));

                        break;
                    case "not":
                        view = LayoutInflater.from(this).inflate(R.layout.view_not, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        } else if (TextUtils.equals("siji", type)) {//司机
            List<LatLngBean> latLngBeanList1 = new ArrayList<>();
            latLngBeanList1.add(new LatLngBean(40.045917, 116.287545, "15373228877", "qita"));
            latLngBeanList1.add(new LatLngBean(40.046917, 116.282945, "15215517733", "jiameng"));
            latLngBeanList1.add(new LatLngBean(40.049917, 116.282745, "17611225576", "yijiedan"));
            latLngBeanList1.add(new LatLngBean(40.043217, 116.286745, "18977665431", "qita"));
            latLngBeanList1.add(new LatLngBean(40.040917, 116.288455, "13386559327", "jiameng"));
            for (int i = 0; i < latLngBeanList1.size(); i++) {
                final LatLngBean latLngBean = latLngBeanList1.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(latLngBean.getLatitude(), latLngBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (latLngBean.getType()) {
                    case "qita":
                        view = LayoutInflater.from(this).inflate(R.layout.view_qita, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "jiameng":
                        view = LayoutInflater.from(this).inflate(R.layout.view_jiameng, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "yijiedan":
                        view = LayoutInflater.from(this).inflate(R.layout.view_yijiedan, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (TextUtils.equals("jumin", type)) {
                    showJuminDialog();
                } else if (TextUtils.equals("siji", type)) {
                    startActivity(new Intent(Home_detailActivity.this,CarDetailActivity.class));
                } else if (TextUtils.equals("gongsi", type)) {
                    String title = marker.getTitle();
                    int i = Integer.parseInt(title);
                    final CompanyBean companyBean = companyBeanList.get(i);
                    switch (companyBean.getType()) {
                        case "aldeady":
                            Intent intent = new Intent(Home_detailActivity.this, HomeCompanyDetailActivity.class);
                            intent.putExtra("flag",0);
                            startActivity(intent);
                            break;
                        case "not":
                            Intent intent1 = new Intent(Home_detailActivity.this, HomeCompanyDetailActivity.class);
                            intent1.putExtra("flag",1);
                            startActivity(intent1);
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void showJuminDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_jumin_layout)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        TextView ckdt = mDialog.getView(R.id.ckdt);
        TextView itme_size = mDialog.getView(R.id.itme_size);
        ckdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        itme_size.setText(list.size()+"个");
        RecyclerView recyclerView = mDialog.getView(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        JuminAdapter juminAdapter = new JuminAdapter(R.layout.item_jumin_layout,list);

        recyclerView.setAdapter(juminAdapter);
        juminAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDialog.dismiss();
                startActivity(new Intent(Home_detailActivity.this,Xuqiu_DetailActivity.class));
            }
        });
    }

    private class JuminAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public JuminAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            LinearLayout qiangdan = helper.getView(R.id.qiangdan);
            qiangdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    startActivity(new Intent(Home_detailActivity.this,QiangDan_DetailsActivity.class));
                }
            });

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
                aMap.clear();
                initMarker("gongsi");
                break;
            //汽车
            case R.id.home_card:
                firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                card.setImageResource(R.drawable.home_card_icon_xxxhdpi);
                jumin.setImageResource(R.drawable.home_jm_xxxhdpi);
                send.setVisibility(View.VISIBLE);
                aMap.clear();
                initMarker("siji");
                break;
            //居民
            case R.id.home_jm:
                firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                card.setImageResource(R.drawable.home_qiche_ccchdpi);
                jumin.setImageResource(R.drawable.home_jm_icon_xxxhdpi);
                send.setVisibility(View.GONE);
                aMap.clear();
                initMarker("jumin");
                break;
            //发送配送消息
            case R.id.home_send:
                startActivity(new Intent(Home_detailActivity.this, PostDemandActivity.class));
                break;
            //订单
            case R.id.home_dingdan:
                sendMassage();
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
                if (latLng != null) aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                break;
            //详情
            case R.id.home_xiangqing:
                showJuminDialog();
                break;
            //抢单
            case R.id.home_qd:
                Intent intent1 = new Intent(Home_detailActivity.this, Bill_DetailActivity.class);
                intent1.putExtra("longitude",amapLocation1.getLongitude());
                intent1.putExtra("lotitude",amapLocation1.getLatitude());
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

    private void sendMassage() {
        mWebSocketClient.send("发送消息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
        /*if (mSocketClient != null) {
            mSocketClient.close();
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        amapLocation1 = amapLocation;
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                Logger.i("定位结果回调：" + "\n" +
                        "定位来源：" + amapLocation.getLocationType() + "\n" +
                        "纬度：" + amapLocation.getLatitude() + "\n" +
                        "经度：" + amapLocation.getLongitude() + "\n" +
                        "城市信息:" + amapLocation.getCity() + "\n" +
                        "定位时间：" + df.format(date));
                latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                Logger.i(latLng.toString());
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
        try {
            initSocketClient();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
