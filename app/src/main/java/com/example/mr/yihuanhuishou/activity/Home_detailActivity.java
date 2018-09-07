package com.example.mr.yihuanhuishou.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.CompanyBean;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.LatLngBean;
import com.example.mr.yihuanhuishou.driver.DriverHomeActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverSettingActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fujin_Order_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fujin_jumin_Details_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fujinjumin_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.AppManager;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
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
import internal.org.java_websocket.handshake.ServerHandshake;

public class Home_detailActivity extends BaseActivity implements View.OnClickListener, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, AMap.OnCameraChangeListener {
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
    List<Fujin_jumin_Details_Bean.DataBean> list = new ArrayList<>();
    @BindView(R.id.dingwei)
    ImageView ding;
    private long preTime;
    boolean flag = true;
    private AMap aMap;

    private MyLocationStyle myLocationStyle;
    //声明mlocationClient对象
    private AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;
    private LatLng latLng;

    private List<Fujinjumin_Bean.DataBean> latLngBeanList = new ArrayList<>();;
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
            if(msg.what==0){
                dd_msg.setVisibility(View.VISIBLE);
                infoview();
                startAlarm(Home_detailActivity.this);
            }else{
                dd_msg.setVisibility(View.GONE);
            }
        }
    };
    //提示音
    private static void startAlarm(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (notification == null) return;
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }
    private GeocodeSearch geocodeSearch;
    private String addressName;
    private double lat;
    private double lon;
   private  int pageNum=1;
    private NotificationManager manager;
    private static final int NOTIFYID = 0;
    private EMMessageListener msgListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initview(savedInstanceState);
        initMap(savedInstanceState);
        initdata();
        huanxin();
        shouxiaoxi();
    }


    private void shouxiaoxi() {
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                Notification.Builder builder = new Notification.Builder(Home_detailActivity.this);
                builder.setTicker("您有新版消息，请注意查收");
                builder.setContentText(messages.get(0).getBody().toString());
                builder.setContentTitle("提示");
                builder.setSmallIcon(R.drawable.yihuan_logo);
                builder.setOngoing(false);
                builder.setDefaults(Notification.DEFAULT_ALL);
                Notification notify = builder.build();
                //点击通知启动的页面
                Intent intent = new Intent(Home_detailActivity.this, MyMsgActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Home_detailActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                       //启动通知
                        manager.notify(NOTIFYID, notify);
                    }
                });
                EventBus.getDefault().postSticky(new Event_fragment(3));
            }
            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {
            }
            @Override
            public void onMessageRead(List<EMMessage> list) {
            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {
                Log.e("======================", "444444");
            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {
                Log.e("======================", "555555");
            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {
                Log.e("======================", "6666666");
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void huanxin() {
        String isuser = sp.getString(GGUtils.ISUSER, "");
        if(!TextUtils.isEmpty(isuser)){
            //TODO 测试账户密码，记得修改
            EMClient.getInstance().login(sp.getString(GGUtils.ISUSER,""),sp.getString(GGUtils.ISPASS,""), new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    Log.e("===================","登录聊天服务器成功！");
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                }
                @Override
                public void onProgress(int progress, String status) {
                    Log.e("===================","登录聊天服务器成功！11111111");
                }
                @Override
                public void onError(int code, String message) {
                    Log.e("===================","登录聊天服务器失败！"+message);
                }
            });
            //注册一个监听连接状态的listener
            EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        }

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
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(Home_detailActivity.this)) {
                            //连接不到聊天服务器
                        } else {
                            //当前网络不可用，请检查网络设置
                        }
                    }
                }
            });
        }
    }
    private void infoview() {
        latLngBeanList.clear();
        int userid = sp.getInt(GGUtils.USER_id, 0);
        HttpParams params = new HttpParams();
        params.put("city",sp.getString(GGUtils.CITY,""));
        params.put("userId",userid);
        OkGo.<Fujinjumin_Bean>post(MyUrls.BASEURL + "/resident/residentOrder/nearbyResident")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Fujinjumin_Bean>(Home_detailActivity.this, Fujinjumin_Bean.class) {
                    @Override
                    public void onSuccess(Response<Fujinjumin_Bean> response) {
                        Fujinjumin_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Fujinjumin_Bean.DataBean> data = body.getData();
                            Log.e("================",data.size()+"");

                            for (int i = 0; i < data.size(); i++) {
                                MarkerOptions markerOption = new MarkerOptions();
                                Fujinjumin_Bean.DataBean dataBean = data.get(i);
                                markerOption.position(new LatLng(dataBean.getLatitude(), dataBean.getLongitude()))
                                        .title(i + "")
                                        .draggable(false);//设置Marker可拖动
                                if(dataBean.getNum()==1){
                                    View view = LayoutInflater.from(Home_detailActivity.this).inflate(R.layout.view_jumin, null);
                                    TextView name = view.findViewById(R.id.item_name_tv);
                                    TextView weight = view.findViewById(R.id.ratingBarView);
                                    String name1 = dataBean.getName();
                                    if(name1.length()>3){
                                        name.setText(name1.substring(0,3)+"...");
                                    }else{
                                        name.setText(name1);
                                    }
                                    weight.setText(dataBean.getNum()+"个");
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }else if(dataBean.getNum()>1){
                                    View view = LayoutInflater.from(Home_detailActivity.this).inflate(R.layout.view_juminlou, null);
                                    TextView name = view.findViewById(R.id.item_name_tv);
                                    TextView weight = view.findViewById(R.id.ratingBarView);
                                    String village = dataBean.getFloor();
                                    if(village.length()>3){
                                        name.setText(village.substring(0,3)+"...");
                                    }else{
                                        name.setText(village);
                                    }
                                    weight.setText(dataBean.getNum()+"个");
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }
                                aMap.addMarker(markerOption);
                            }

                            if(data.size()>0){
                                latLngBeanList.addAll(data);
                            }else{
                                dd_msg.setVisibility(View.GONE);
                            }

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        }

                    }
                });


    }

    //"ws://39.106.220.142/webSocketTest?token="+sp.getString(GGUtils.TOKEN,"")+"&longitude="+amapLocation1.getLongitude()+"&latitude="+amapLocation1.getLatitude()+""
//"ws://172.168.20.73:8080/EEC/webSocketTest?token="+sp.getString(GGUtils.TOKEN,"")+"&longitude="+amapLocation1.getLongitude()+"&latitude="+amapLocation1.getLatitude()+""
    private void initSocketClient() throws URISyntaxException {
        if (mWebSocketClient == null) {
            //    connect();
            mWebSocketClient = new WebSocketClient(new URI("ws://39.106.220.142/webSocketTest?token=" + sp.getString(GGUtils.TOKEN, "") + "&longitude=" + amapLocation1.getLongitude() + "&latitude=" + amapLocation1.getLatitude() + "")) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.e("====================", "开启通道");
                }

                @Override
                public void onMessage(String s) {
                    Log.e("====================", "接收消息：" + s);
                    Message obtain = Message.obtain();
                    obtain.what=0;
                    handler.sendMessage(obtain);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.e("====================", "通道关闭");
                    closeConnect();
                }
                @Override
                public void onError(Exception e) {
                    Log.e("====================", "错误" + e);
                }
            };
            mWebSocketClient.connect();
        }

    }
    //连接

    /*private void connect() {

        new Thread(){
            @Override
            public void run() {
                mWebSocketClient.connect();
            }
        }.start();

    }*/


    //断开连接
    private void closeConnect() {
        try {
            mWebSocketClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mWebSocketClient = null;
        }
    }


    private void initdata() {
       /* list.add("");
        list.add("");
        list.add("");
        list.add("");*/
    }

    private void GeocodeSearch(String city) {
//构造 GeocodeSearch 对象，并设置监听。
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(city, city);
        geocodeSearch.getFromLocationNameAsyn(query);
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
        int flag = eveen.getFlag();
        if (flag == 1) {
            String city = eveen.getCity();
            address.setText(city);
            if (address.getText().toString().equals(amapLocation1.getCity())) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng
                        (amapLocation1.getLatitude(), amapLocation1.getLongitude()), 13.0f));
            } else {
                GeocodeSearch(address.getText().toString());
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_fragment eveen) {
        int flag = eveen.getMsg();
        if(flag ==2){
            dd_msg.setVisibility(View.VISIBLE);
        }else if(flag==3){
            dd_msg.setVisibility(View.GONE);

        }
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
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        //设置地图拖动监听
        aMap.setOnCameraChangeListener(this);
        //更改定位图标
        // myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.driver_map_location_iv));
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
        // mLocationOption.setInterval(5000);
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
        if (!TextUtils.isEmpty(sp.getString(GGUtils.CITY, ""))) {
            address.setText(sp.getString(GGUtils.CITY, ""));
        }
    }
    private void initMarker(final String type) {
        if (TextUtils.equals("jumin", type)) {
            if(!TextUtils.isEmpty(sp.getString(GGUtils.CITY, ""))){
                infoview();
            }
        } else if (TextUtils.equals("gongsi", type)) {
           companyBeanList = new ArrayList<>();
           /*  companyBeanList.add(new CompanyBean(40.045917, 116.287545, "aldeady"));
            companyBeanList.add(new CompanyBean(40.046917, 116.282945, "not"));
            companyBeanList.add(new CompanyBean(40.049917, 116.282745, "not"));
            companyBeanList.add(new CompanyBean(40.043217, 116.286745, "aldeady"));
            companyBeanList.add(new CompanyBean(40.040917, 116.288455, "aldeady"));*/
         for (int i = 0; i < companyBeanList.size(); i++) {
                final CompanyBean companyBean = companyBeanList.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(companyBean.getLatitude(), companyBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (companyBean.getType()) {
                    case "aldeady":
                        View view = LayoutInflater.from(this).inflate(R.layout.view_yiqianyue, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "not":
                        View view1 = LayoutInflater.from(this).inflate(R.layout.view_not, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view1));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        } else if (TextUtils.equals("siji", type)) {//司机
            List<LatLngBean> latLngBeanList1 = new ArrayList<>();
           /* latLngBeanList1.add(new LatLngBean(40.045917, 116.287545, "15373228877", "qita"));
            latLngBeanList1.add(new LatLngBean(40.046917, 116.282945, "15215517733", "jiameng"));
            latLngBeanList1.add(new LatLngBean(40.049917, 116.282745, "17611225576", "yijiedan"));
            latLngBeanList1.add(new LatLngBean(40.043217, 116.286745, "18977665431", "qita"));
            latLngBeanList1.add(new LatLngBean(40.040917, 116.288455, "13386559327", "jiameng"));
            */for (int i = 0; i < latLngBeanList1.size(); i++) {
                final LatLngBean latLngBean = latLngBeanList1.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(latLngBean.getLatitude(), latLngBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (latLngBean.getType()) {
                    case "qita":
                        View view = LayoutInflater.from(this).inflate(R.layout.view_qita, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "jiameng":
                        View view1 = LayoutInflater.from(this).inflate(R.layout.view_jiameng, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view1));
                        break;
                    case "yijiedan":
                        View view2 = LayoutInflater.from(this).inflate(R.layout.view_yijiedan, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view2));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (TextUtils.equals("jumin", type)) {
                    String title = marker.getTitle();
                    int i = Integer.parseInt(title);
                    String village = latLngBeanList.get(i).getVillage();
                    String floor = latLngBeanList.get(i).getFloor();
                    fujin_details(village,floor);
                } else if (TextUtils.equals("siji", type)) {
                    startActivity(new Intent(Home_detailActivity.this, CarDetailActivity.class));
                } else if (TextUtils.equals("gongsi", type)) {
                    String title = marker.getTitle();
                    int i = Integer.parseInt(title);
                    final CompanyBean companyBean = companyBeanList.get(i);
                    switch (companyBean.getType()) {
                        case "aldeady":
                            Intent intent = new Intent(Home_detailActivity.this, HomeCompanyDetailActivity.class);
                            intent.putExtra("flag", 0);
                            startActivity(intent);
                            break;
                        case "not":
                            Intent intent1 = new Intent(Home_detailActivity.this, HomeCompanyDetailActivity.class);
                            intent1.putExtra("flag", 1);
                            startActivity(intent1);
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void fujin_details(String village, String floor) {
        list.clear();
        String shi = address.getText().toString();
        int userid = sp.getInt(GGUtils.USER_id, 0);
        HttpParams params = new HttpParams();
        params.put("city",shi);
        params.put("userId",userid);
        params.put("village",village);
        params.put("floor",floor);
        OkGo.<Fujin_jumin_Details_Bean>post(MyUrls.BASEURL + "/resident/residentOrder/nearbyResidentDetail")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Fujin_jumin_Details_Bean>(Home_detailActivity.this, Fujin_jumin_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Fujin_jumin_Details_Bean> response) {
                        Fujin_jumin_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Fujin_jumin_Details_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                            }
                            showJuminDialog();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        }

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
        final SpringView sp_view = mDialog.getView(R.id.sp_view);
        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNum=1;
                        list.clear();
                        String shi = address.getText().toString();
                        int userid = sp.getInt(GGUtils.USER_id, 0);
                        HttpParams params = new HttpParams();
                        params.put("city",shi);
                        params.put("userId",userid);
                        params.put("pageNum",pageNum);
                        OkGo.<Fujin_jumin_Details_Bean>post(MyUrls.BASEURL + "/resident/residentOrder/selectOptionOrder")
                                .tag(this)
                                .params(params)
                                .execute(new DialogCallback<Fujin_jumin_Details_Bean>(Home_detailActivity.this, Fujin_jumin_Details_Bean.class) {
                                    @Override
                                    public void onSuccess(Response<Fujin_jumin_Details_Bean> response) {
                                        Fujin_jumin_Details_Bean body = response.body();
                                        String code = body.getCode();
                                        if (code.equals("200")) {
                                            List<Fujin_jumin_Details_Bean.DataBean> data = body.getData();
                                            if(data.size()>0){
                                                list.addAll(data);
                                            }
                                        } else {
                                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                                        }
                                    }
                                });
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNum++;
                        String shi = address.getText().toString();
                        int userid = sp.getInt(GGUtils.USER_id, 0);
                        HttpParams params = new HttpParams();
                        params.put("city",shi);
                        params.put("userId",userid);
                        params.put("pageNum",pageNum);
                        OkGo.<Fujin_jumin_Details_Bean>post(MyUrls.BASEURL + "/resident/residentOrder/selectOptionOrder")
                                .tag(this)
                                .params(params)
                                .execute(new DialogCallback<Fujin_jumin_Details_Bean>(Home_detailActivity.this, Fujin_jumin_Details_Bean.class) {
                                    @Override
                                    public void onSuccess(Response<Fujin_jumin_Details_Bean> response) {
                                        Fujin_jumin_Details_Bean body = response.body();
                                        String code = body.getCode();
                                        if (code.equals("200")) {
                                            List<Fujin_jumin_Details_Bean.DataBean> data = body.getData();
                                            if(data.size()>0){
                                                list.addAll(data);
                                            }
                                        } else {
                                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                                        }
                                    }
                                });





                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        ckdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
       itme_size.setText(list.size()+ "个");
        RecyclerView recyclerView = mDialog.getView(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        JuminAdapter juminAdapter = new JuminAdapter(R.layout.item_jumin_layout, list);
        recyclerView.setAdapter(juminAdapter);
        juminAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDialog.dismiss();
                Intent intent = new Intent(Home_detailActivity.this, Xuqiu_DetailActivity.class);
                intent.putExtra("id",list.get(position).getOrderId());
                intent.putExtra("lon",amapLocation1.getLongitude());
                intent.putExtra("lat",amapLocation1.getLatitude());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
    }
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS) {
            if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                    && geocodeResult.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
                addressName = "经纬度值:" + address.getLatLonPoint() + "\n位置描述:"
                        + address.getFormatAddress();

                //获取到的经纬度
                LatLonPoint latLongPoint = address.getLatLonPoint();
                lat = latLongPoint.getLatitude();
                lon = latLongPoint.getLongitude();
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng
                        (lat, lon), 13.0f));
            }
        }
    }
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        ding.setVisibility(View.VISIBLE);
        latLng = cameraPosition.target;
        double latitude = latLng.latitude;
        double longitude = latLng.longitude;
        Log.e("===================", latitude + "");
        Log.e("===================", longitude + "");
    }
    private class JuminAdapter extends BaseQuickAdapter<Fujin_jumin_Details_Bean.DataBean, BaseViewHolder> {

        public JuminAdapter(int layoutResId, @Nullable List<Fujin_jumin_Details_Bean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Fujin_jumin_Details_Bean.DataBean item) {
            LinearLayout qiangdan = helper.getView(R.id.qiangdan);
            TextView name = helper.getView(R.id.name);
            TextView sort = helper.getView(R.id.sort);
            TextView address = helper.getView(R.id.address);
             name.setText(item.getName());
            sort.setText(item.getVarieties());
            address.setText(item.getDetail_addr());
            qiangdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
                    HttpParams params = new HttpParams();
                    params.put("id",item.getOrderId());
                    params.put("token",sp.getString(GGUtils.TOKEN,""));
                    OkGo.<Zhece_Bean>get(MyUrls.BASEURL + "/recyclers/order/robbingWasteOrder")
                            .tag(this)
                            .params(params)
                            .execute(new DialogCallback<Zhece_Bean>(Home_detailActivity.this, Zhece_Bean.class) {
                                @Override
                                public void onSuccess(Response<Zhece_Bean> response) {
                                    Zhece_Bean body = response.body();
                                    String code = body.getCode()+"";
                                    if (code.equals("200")) {
                                        Intent intent = new Intent(Home_detailActivity.this, QiangDan_DetailsActivity.class);
                                        intent.putExtra("msg",body.getMsg());
                                        startActivity(intent);
                                        ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                                    } else {
                                        ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                                    }
                                }
                            });
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
                info();
                break;
            //抢单
            case R.id.home_qd:
                Intent intent1 = new Intent(Home_detailActivity.this, Bill_DetailActivity.class);
                intent1.putExtra("longitude", amapLocation1.getLongitude());
                intent1.putExtra("lotitude", amapLocation1.getLatitude());
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
    private void info() {
        list.clear();
        String shi = address.getText().toString();
        int userid = sp.getInt(GGUtils.USER_id, 0);
        HttpParams params = new HttpParams();
        params.put("city",shi);
        params.put("userId",userid);
        params.put("pageNum",pageNum);
        OkGo.<Fujin_jumin_Details_Bean>post(MyUrls.BASEURL + "/resident/residentOrder/selectOptionOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Fujin_jumin_Details_Bean>(Home_detailActivity.this, Fujin_jumin_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Fujin_jumin_Details_Bean> response) {
                        Fujin_jumin_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Fujin_jumin_Details_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                            }
                            showJuminDialog();
                        } else {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        }
                    }
                });
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
        mWebSocketClient.close();

        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
        Log.e("=======================",sp.getString(GGUtils.TOKEN,""));
        if (!TextUtils.isEmpty(sp.getString(GGUtils.CITY, ""))) {
            address.setText(sp.getString(GGUtils.CITY, ""));
            //infoview();
        }
        aMap.clear();
        initMarker("jumin");
    }

    private void fujin() {
        HttpParams params = new HttpParams();
        params.put("longitude",amapLocation1.getLongitude());
        params.put("latitude",amapLocation1.getLatitude());
        params.put("pageCount",10);
        OkGo.<Fujin_Order_Bean>post(MyUrls.BASEURL + "/webSocketBusiness/getNearbyWasteOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Fujin_Order_Bean>(Home_detailActivity.this, Fujin_Order_Bean.class) {
                    @Override
                    public void onSuccess(Response<Fujin_Order_Bean> response) {
                        Fujin_Order_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            List<Fujin_Order_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                dd_msg.setVisibility(View.VISIBLE);
                            }else{
                                dd_msg.setVisibility(View.GONE);
                            }
                        } else {
                            ToastUtils.getToast(Home_detailActivity.this, body.getMsg());
                        }
                    }
                });

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
               // fujin();
                if (TextUtils.isEmpty(sp.getString(GGUtils.CITY, "")) || !sp.getString(GGUtils.CITY, "").equals(amapLocation.getCity())) {
                    address.setText(amapLocation.getCity());
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString(GGUtils.CITY, amapLocation.getCity());
                    edit.commit();
                }
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
    /*
      返回键退出程序
    */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            //super.onBackPressed();//相当于finish()
            BaseActivity.realBack();//删除所有引用
             AppManager.getAppManager().finishAllActivity();
        }
    }
}
