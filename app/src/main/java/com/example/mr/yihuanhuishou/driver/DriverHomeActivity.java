package com.example.mr.yihuanhuishou.driver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Home_detailActivity;
import com.example.mr.yihuanhuishou.activity.LocationActivity;
import com.example.mr.yihuanhuishou.activity.MyMsgActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.CompanyBean;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.LatLngBean;
import com.example.mr.yihuanhuishou.driver.fragment.BaseFragment;
import com.example.mr.yihuanhuishou.driver.fragment.HomeListFragment;
import com.example.mr.yihuanhuishou.driver.fragment.HomeMapFragment;
import com.example.mr.yihuanhuishou.driver.ui.CompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverOrderActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverPersonActivity;
import com.example.mr.yihuanhuishou.driver.ui.HuiShouCompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.ui.MyDriverMessageActivity;
import com.example.mr.yihuanhuishou.driver.ui.QiangdanDetailActivity;
import com.example.mr.yihuanhuishou.driver.weight.MyInfoWindowAdapter;
import com.example.mr.yihuanhuishou.driver.weight.RatingBarView;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fujinjumin_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Deiver_Gongsi_shuishou_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Recyclers_Bean;
import com.example.mr.yihuanhuishou.utils.AppManager;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import internal.org.java_websocket.client.WebSocketClient;
import internal.org.java_websocket.handshake.ServerHandshake;

public class DriverHomeActivity extends AppCompatActivity implements AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {

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
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.home_qd)
    TextView homeQd;
    @BindView(R.id.home_ddxx)
    RelativeLayout homeDdxx;
    @BindView(R.id.home_dingwei)
    ImageView homeDingwei;
    @BindView(R.id.home_jia_iv)
    ImageView homeJiaIv;
    @BindView(R.id.home_jian_iv)
    ImageView homeJianIv;
    @BindView(R.id.home_suofang)
    LinearLayout homeSuofang;
    @BindView(R.id.huishou_tv)
    TextView huishouTv;
    @BindView(R.id.gongsi_tv)
    TextView gongsiTv;
    @BindView(R.id.huishou_lin)
    LinearLayout huishouLin;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private BaseFragment baseFragment;
    private HomeMapFragment homeMapFragment;
    private HomeListFragment homeListFragment;
    private long preTime;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private MyInfoWindowAdapter infoWindowAdapter;
    private View infoWindow = null;
    private BaseDialog.Builder mBuilder;
    private BaseDialog mDialog;
    private LatLng latLng;
    private View view;
    private SharedPreferences sp;
    private AMapLocation amapLocation1;
    private GeocodeSearch geocodeSearch;
    private String addressName;
    private double lat;
    private double lon;
    List<Deiver_Gongsi_shuishou_Bean.DataBean> companyBeanList = new ArrayList<>();
    private List<Driver_Recyclers_Bean.DataBean> latLngBeanList = new ArrayList<>();
    private NotificationManager manager;
    private static final int NOTIFYID = 0;
    private EMMessageListener msgListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initMap(savedInstanceState);
        initView();
        shouxiaoxi();
    }

    private void shouxiaoxi() {
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Notification.Builder builder = new Notification.Builder(DriverHomeActivity.this);
                        builder.setTicker("您有新版消息，请注意查收");
                        builder.setContentText(messages.get(0).getBody().toString());
                        builder.setContentTitle("提示");
                        builder.setSmallIcon(R.drawable.yihuan_logo);
                        builder.setOngoing(false);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        Notification notify = builder.build();
                        //点击通知启动的页面
                        Intent intent = new Intent(DriverHomeActivity.this, MyMsgActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(DriverHomeActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);
                        //启动通知
                        manager.notify(NOTIFYID, notify);
                    }
                });
                EventBus.getDefault().postSticky(new Event_fragment(3));
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {
                Log.e("======================", "22222");
            }
            @Override
            public void onMessageRead(List<EMMessage> list) {
                Log.e("======================", "33333");
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_dingwei eveen) {
        int flag = eveen.getFlag();
        if (flag == 1) {
            String city = eveen.getCity();
            homeAddress.setText(city);
            if (homeAddress.getText().toString().equals(amapLocation1.getCity())) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng
                        (amapLocation1.getLatitude(), amapLocation1.getLongitude()), 13.0f));
            } else {
                GeocodeSearch(homeAddress.getText().toString());
            }
        }
    }
    private void GeocodeSearch(String city) {
        //构造 GeocodeSearch 对象，并设置监听。
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(city, city);
        geocodeSearch.getFromLocationNameAsyn(query);
    }
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
      //  myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.driver_map_location_iv));
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
        initMarker(false);

        if (!TextUtils.isEmpty(sp.getString(GGUtils.DrCITY, ""))) {
            homeAddress.setText(sp.getString(GGUtils.DrCITY, ""));
        }
    }

    private void initMarker(final boolean isCompany) {
        if (!isCompany) {
            if(!TextUtils.isEmpty(sp.getString(GGUtils.CITY, ""))){
                recycl();
            }
        } else {
            gongsi();
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!marker.isInfoWindowShown()) {
                    if (!isCompany) {
                        String title = marker.getTitle();
                        final int i = Integer.parseInt(title);
                        if (infoWindow == null) {
                            infoWindow = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_qipao, null);
                        }
                        Intent intent = new Intent(DriverHomeActivity.this, QiangdanDetailActivity.class);
                        intent.putExtra("id",latLngBeanList.get(i).getId());
                        startActivity(intent);
                        infoWindowAdapter.render(marker, infoWindow, latLngBeanList.get(i).getEecRecyclersaddr().getPhoneNumber());
                        marker.showInfoWindow();
                        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                showDialog(latLngBeanList.get(i).getEecRecyclersaddr().getPhoneNumber());
                            }
                        });

                    } else {
                        String title = marker.getTitle();
                        int i = Integer.parseInt(title);
                        Intent intent = new Intent(DriverHomeActivity.this, HuiShouCompanyDetailActivity.class);
                        intent.putExtra("id",companyBeanList.get(i).getCompany().getId());
                        startActivity(intent);
                    }

                } else {
                    marker.hideInfoWindow();
                }
                return true;
            }
        });
    }
///////////////////////////////////////////
private void recycl() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("city",sp.getString(GGUtils.DrCITY,""));
        params.put("latitude",sp.getString(GGUtils.Drlat,""));
        params.put("longitude",sp.getString(GGUtils.Drlon,""));
        OkGo.<Driver_Recyclers_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/getNearbyRecoveryOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driver_Recyclers_Bean>(this, Driver_Recyclers_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driver_Recyclers_Bean> response) {
                        Driver_Recyclers_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Driver_Recyclers_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                    if(data.get(0).getState().equals("0")){
                                        homeDdxx.setVisibility(View.VISIBLE);
                                    }else if(data.get(0).getState().equals("1")){
                                        homeDdxx.setVisibility(View.GONE);
                                    }
                            }else{
                                homeDdxx.setVisibility(View.GONE);
                            }
                            if(data.size()>0){
                                latLngBeanList.addAll(data);
                            }
                            for (int i = 0; i < data.size(); i++) {

                                infoWindowAdapter = new MyInfoWindowAdapter(DriverHomeActivity.this);
                                aMap.setInfoWindowAdapter(infoWindowAdapter);
                                Driver_Recyclers_Bean.DataBean latLngBean = data.get(i);
                                MarkerOptions markerOption = new MarkerOptions();
                                markerOption.position(new LatLng(latLngBean.getEecRecyclersaddr().getLatitude(), latLngBean.getEecRecyclersaddr().getLongitude()))
                                        .title(i + "")
                                        .draggable(false);//设置Marker可拖动
                                String recyclersType = data.get(i).getRecyclersType();
                                if(recyclersType.equals("0")){
                                    View  view = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_sanhu, null);
                                    TextView name = view.findViewById(R.id.item_name_tv);
                                    RatingBarView  rating = view.findViewById(R.id.ratingBarView);
                                    int grade = data.get(i).getGrade();
                                    rating.setSelectedCount(grade-1);
                                    name.setText(data.get(i).getEecRecyclersaddr().getName());
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }else if(recyclersType.equals("1")){
                                    View view = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_geti, null);
                                    TextView name = view.findViewById(R.id.item_name_tv);
                                    RatingBarView  rating = view.findViewById(R.id.ratingBarView);
                                    int grade = data.get(i).getGrade();
                                    rating.setSelectedCount(grade-1);
                                    name.setText(data.get(i).getEecRecyclersaddr().getName());
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }else if(recyclersType.equals("3")){
                                    View view = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_quhuo, null);
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }
                                aMap.addMarker(markerOption);
                            }
                        }
                    }
                });
    }





    private void gongsi() {
        companyBeanList.clear();
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        OkGo.<Deiver_Gongsi_shuishou_Bean>get(MyUrls.BASEURL + "/driver/home/CompanyList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Deiver_Gongsi_shuishou_Bean>(DriverHomeActivity.this, Deiver_Gongsi_shuishou_Bean.class) {
                    @Override
                    public void onSuccess(Response<Deiver_Gongsi_shuishou_Bean> response) {
                        Deiver_Gongsi_shuishou_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Deiver_Gongsi_shuishou_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                companyBeanList.addAll(data);
                            }
                            for (int i = 0; i < data.size(); i++) {
                                Deiver_Gongsi_shuishou_Bean.DataBean dataBean = data.get(i);

                                MarkerOptions markerOption = new MarkerOptions();
                                markerOption.position(new LatLng(dataBean.getCompany().getLatitude(), dataBean.getCompany().getLongitude()))
                                        .title(i + "")
                                        .draggable(false);//设置Marker可拖动
                                boolean isJoin = dataBean.getIsJoin();
                                if(isJoin==true){
                                    View view = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_aldeady, null);
                                    TextView name = view.findViewById(R.id.item_name_tv);
                                    name.setText(dataBean.getCompany().getCompanyTitle());
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view));
                                }else if(isJoin==false){
                                    View view1 = LayoutInflater.from(DriverHomeActivity.this).inflate(R.layout.view_not, null);
                                    TextView name1 = view1.findViewById(R.id.item_name);
                                    name1.setText(dataBean.getCompany().getCompanyTitle());
                                    markerOption.icon(BitmapDescriptorFactory.fromView(view1));
                                }
                                aMap.addMarker(markerOption);
                            }
                        }
                    }
                });


    }

    private void showDialog(final String content) {
        mBuilder = new BaseDialog.Builder(DriverHomeActivity.this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order_call)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.nomal_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        TextView contentTv = mDialog.getView(R.id.content_tv);
        contentTv.setText(content);
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.query_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + content);
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(DriverHomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });
    }

   /* @OnClick(R.id.home_qd)
    public void onHomeQdClicked() {
        startActivity(new Intent(DriverHomeActivity.this, QiangdanDetailActivity.class));
    }*/

    @OnClick(R.id.home_ddxx)
    public void onHomeDdxxClicked() {
        for (int i=0;i<latLngBeanList.size();i++){
            if(latLngBeanList.get(i).getState().equals("1")){
                Intent intent = new Intent(DriverHomeActivity.this, QiangdanDetailActivity.class);
                intent.putExtra("id",latLngBeanList.get(i).getId());
                startActivity(intent);
            }
        }

    }
    @OnClick(R.id.home_suofang)
    public void onHomeSuofangClicked() {
    }
    @OnClick(R.id.home_dingwei)
    public void onHomeDingweiClicked() {//点击定位
        if (latLng != null) aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
    }

    @OnClick(R.id.home_jia_iv)
    public void onHomeJiaIvClicked() {//放大地图
        if (aMap.getCameraPosition().zoom <= aMap.getMaxZoomLevel())
            aMap.moveCamera(CameraUpdateFactory.zoomIn());
    }
    @OnClick(R.id.home_jian_iv)
    public void onHomeJianIvClicked() {//缩小地图
        if (aMap.getCameraPosition().zoom >= aMap.getMinZoomLevel())
            aMap.moveCamera(CameraUpdateFactory.zoomOut());
    }
    @OnClick(R.id.huishou_tv)
    public void onHuishouTvClicked() {//回收人员
        Drawable drawable = getResources().getDrawable(R.drawable.driver_huishourenyuan_true);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        huishouTv.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.driver_gongsi_false_iv);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable.getMinimumHeight());
        gongsiTv.setCompoundDrawables(null, drawable1, null, null);
        aMap.clear();
        initMarker(false);
    }
    @OnClick(R.id.gongsi_tv)
    public void onGongsiTvClicked() {//回收公司
        Drawable drawable = getResources().getDrawable(R.drawable.driver_huishourenyuan_false);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        huishouTv.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.driver_gongsi_true_iv);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable.getMinimumHeight());
        gongsiTv.setCompoundDrawables(null, drawable1, null, null);
        aMap.clear();
        initMarker(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
        if (!TextUtils.isEmpty(sp.getString(GGUtils.DrCITY, ""))){
            aMap.clear();
            initMarker(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
        mlocationClient.stopLocation();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
        mlocationClient.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    private void initView() {
        String isuser = sp.getString(GGUtils.DrISUSER, "");
        if(!TextUtils.isEmpty(isuser)){
            //TODO 测试账户密码，记得修改
            EMClient.getInstance().login(sp.getString(GGUtils.DrISUSER,""), sp.getString(GGUtils.DrISPASS,""), new EMCallBack() {//回调
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
                    Logger.e("登录聊天服务器失败！" + message);
                }
            });
            //注册一个监听连接状态的listener
            EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        }

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
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putString(GGUtils.Drlon,amapLocation.getLongitude()+"");
                edit1.putString(GGUtils.Drlat,amapLocation.getLatitude()+"");
                edit1.commit();
                if (TextUtils.isEmpty(sp.getString(GGUtils.DrCITY, "")) || !sp.getString(GGUtils.DrCITY, "").equals(amapLocation.getCity())) {
                    homeAddress.setText(amapLocation.getCity());
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString(GGUtils.DrCITY, amapLocation.getCity());
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
    private WebSocketClient mWebSocketClient;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                homeDdxx.setVisibility(View.VISIBLE);
                recycl();
                startAlarm(DriverHomeActivity.this);
            }else{
                homeDdxx.setVisibility(View.GONE);
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
    private void initSocketClient() throws URISyntaxException {
        if (mWebSocketClient == null) {
            //    connect();ws://39.106.220.142/webSocketTest?
            //ws://172.168.20.70:8080/EEC/webSocketTest?
            mWebSocketClient = new WebSocketClient(new URI("ws://39.106.220.142/webSocketTest?token=" + sp.getString(GGUtils.DrTOKEN, "") + "&longitude=" + amapLocation1.getLongitude() + "&latitude=" + amapLocation1.getLatitude() + "")) {
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
                        if (NetUtils.hasNetwork(DriverHomeActivity.this)) {
                            //连接不到聊天服务器
                        } else {
                            //当前网络不可用，请检查网络设置
                        }
                    }
                }
            });
        }
    }

    @OnClick(R.id.home_dingdan)
    public void onHomeDingdanClicked() {
        startActivity(new Intent(this, DriverOrderActivity.class));
    }

    //个人中心L
    @OnClick(R.id.home_lianxiren)
    public void onHomeLianxirenClicked() {
        startActivity(new Intent(this, DriverPersonActivity.class));
    }
    @OnClick(R.id.home_msg)
    public void onHomeMsgClicked() {
        startActivity(new Intent(this, MyDriverMessageActivity.class));
    }

    @OnClick(R.id.jiao)
    public void onJiaoClicked() {
        Intent intent = new Intent(DriverHomeActivity.this, LocationActivity.class);
        intent.putExtra("address", homeAddress.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.home_ditu_ll)
    public void onHomeDituLlClicked() {
        flContent.setVisibility(View.GONE);
        map.setVisibility(View.VISIBLE);
        homeSuofang.setVisibility(View.VISIBLE);
        huishouLin.setVisibility(View.VISIBLE);
        homeDingwei.setVisibility(View.VISIBLE);
        homeDituTv.setImageResource(R.drawable.driver_map_true_iv);
        homeLiebiaoTv.setImageResource(R.drawable.driver_liebiao_false_iv);
    }
    @OnClick(R.id.home_liebiao_ll)
    public void onHomeLiebiaoLlClicked() {
        flContent.setVisibility(View.VISIBLE);
        map.setVisibility(View.GONE);
        homeSuofang.setVisibility(View.GONE);
        huishouLin.setVisibility(View.GONE);
        homeDingwei.setVisibility(View.GONE);
        if (homeListFragment == null) {
            homeListFragment = new HomeListFragment(amapLocation1.getLatitude(),amapLocation1.getLongitude(),amapLocation1.getCity());
        }
        addFragments(homeListFragment);
        homeDituTv.setImageResource(R.drawable.driver_map_false_iv);
        homeLiebiaoTv.setImageResource(R.drawable.driver_liebiao_true_iv);
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
