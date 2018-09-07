package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.bean.CompanyBean;
import com.example.mr.yihuanhuishou.bean.LatLngBean;
import com.example.mr.yihuanhuishou.driver.DriverHomeActivity;
import com.example.mr.yihuanhuishou.driver.ui.CompanyDetailActivity;
import com.example.mr.yihuanhuishou.driver.ui.QiangdanDetailActivity;
import com.example.mr.yihuanhuishou.driver.weight.MyInfoWindowAdapter;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by power on 2018/5/21.
 */

public class HomeMapFragment extends BaseFragment implements AMapLocationListener {
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
    Unbinder unbinder;

    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    //声明mlocationClient对象
    private AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;
    private LatLng latLng;
    private View view;
    private MyInfoWindowAdapter infoWindowAdapter;
    private View infoWindow = null;
    private boolean isCompany = false;//回收公司和回收人员地图marker切换
    private List<LatLngBean> latLngBeanList;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_map_fragment,null);
        unbinder = ButterKnife.bind(this, view);
        initMap(savedInstanceState);
        return view;
    }

    @Override
    protected void initLazyData() {
    }


    private void initMap(Bundle savedInstanceState) {
        //创建地图
        map.onCreate(savedInstanceState);
        //显示地图
        if(aMap == null) aMap = map.getMap();
        //隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        //更改定位图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.driver_map_location_iv));
        //设置不显示范围圆圈
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色

        mlocationClient = new AMapLocationClient(mContext);
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
    }

    private void initMarker(final boolean isCompany) {
        if (!isCompany){
            latLngBeanList = new ArrayList<>();
            latLngBeanList.add(new LatLngBean(40.045917,116.287545,"15373228877","geren"));
            latLngBeanList.add(new LatLngBean(40.046917,116.282945,"15215517733","sanhu"));
            latLngBeanList.add(new LatLngBean(40.049917,116.282745,"17611225576","gongsi"));
            latLngBeanList.add(new LatLngBean(40.043217,116.286745,"18977665431","quhuo"));
            latLngBeanList.add(new LatLngBean(40.040917,116.288455,"13386559327","gongsi"));
            for (int i = 0; i < latLngBeanList.size(); i++) {
                infoWindowAdapter = new MyInfoWindowAdapter(mContext);
                aMap.setInfoWindowAdapter(infoWindowAdapter);
                final LatLngBean latLngBean = latLngBeanList.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(latLngBean.getLatitude(),latLngBean.getLongitude()))
                        .title(i+"")
                        .draggable(false);//设置Marker可拖动
                switch (latLngBean.getType()){
                    case "geren":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_geti,null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "sanhu":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_sanhu,null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "gongsi":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_gongsi,null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "quhuo":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_quhuo,null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        }else {
            List<CompanyBean> companyBeanList = new ArrayList<>();
            companyBeanList.add(new CompanyBean(40.045917,116.287545,"aldeady"));
            companyBeanList.add(new CompanyBean(40.046917,116.282945,"not"));
            companyBeanList.add(new CompanyBean(40.049917,116.282745,"not"));
            companyBeanList.add(new CompanyBean(40.043217,116.286745,"aldeady"));
            companyBeanList.add(new CompanyBean(40.040917,116.288455,"aldeady"));
            for (int i = 0; i < companyBeanList.size(); i++) {
                infoWindowAdapter = new MyInfoWindowAdapter(mContext);
                aMap.setInfoWindowAdapter(infoWindowAdapter);
                final CompanyBean companyBean = companyBeanList.get(i);
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(companyBean.getLatitude(), companyBean.getLongitude()))
                        .title(i + "")
                        .draggable(false);//设置Marker可拖动
                switch (companyBean.getType()) {
                    case "aldeady":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_aldeady, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                    case "not":
                        view = LayoutInflater.from(mContext).inflate(R.layout.view_not, null);
                        markerOption.icon(BitmapDescriptorFactory.fromView(view));
                        break;
                }
                aMap.addMarker(markerOption);
            }
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!marker.isInfoWindowShown()){
                    if (!isCompany){
                        String title = marker.getTitle();
                        final int i = Integer.parseInt(title);
                        if (infoWindow == null){
                            infoWindow = LayoutInflater.from(mContext).inflate(R.layout.view_qipao, null);
                        }
                        infoWindowAdapter.render(marker, infoWindow,latLngBeanList.get(i).getPhone());
                        marker.showInfoWindow();
                        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                showDialog(latLngBeanList.get(i).getPhone());
                            }
                        });
                    }else {
                        startActivity(new Intent(mContext,CompanyDetailActivity.class));
                    }

                }else {
                    marker.hideInfoWindow();
                }
                return true;
            }
        });
    }

    private void showDialog(final String content) {
        mBuilder = new BaseDialog.Builder(mContext);
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
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                Logger.i("定位结果回调："+"\n"+
                        "定位来源："+amapLocation.getLocationType()+"\n"+
                        "纬度："+amapLocation.getLatitude()+"\n"+
                        "经度："+amapLocation.getLongitude()+"\n"+
                        "城市信息:"+amapLocation.getCity()+"\n"+
                        "定位时间："+df.format(date));
                latLng = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
                Logger.i(latLng.toString());
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @OnClick(R.id.home_qd)
    public void onHomeQdClicked() {
        startActivity(new Intent(mContext,QiangdanDetailActivity.class));
    }

    @OnClick(R.id.home_ddxx)
    public void onHomeDdxxClicked() {
        startActivity(new Intent(mContext,QiangdanDetailActivity.class));
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
        if (aMap.getCameraPosition().zoom <= aMap.getMaxZoomLevel()) aMap.moveCamera(CameraUpdateFactory.zoomIn());
    }

    @OnClick(R.id.home_jian_iv)
    public void onHomeJianIvClicked() {//缩小地图
        if (aMap.getCameraPosition().zoom >= aMap.getMinZoomLevel()) aMap.moveCamera(CameraUpdateFactory.zoomOut());
    }

    @OnClick(R.id.huishou_tv)
    public void onHuishouTvClicked() {//回收人员
        Drawable drawable = getResources().getDrawable(R.drawable.driver_huishourenyuan_true);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        huishouTv.setCompoundDrawables(null,drawable,null,null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.driver_gongsi_false_iv);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable.getMinimumHeight());
        gongsiTv.setCompoundDrawables(null,drawable1,null,null);
        aMap.clear();
        initMarker(false);
    }

    @OnClick(R.id.gongsi_tv)
    public void onGongsiTvClicked() {//回收公司
        Drawable drawable = getResources().getDrawable(R.drawable.driver_huishourenyuan_false);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        huishouTv.setCompoundDrawables(null,drawable,null,null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.driver_gongsi_true_iv);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable.getMinimumHeight());
        gongsiTv.setCompoundDrawables(null,drawable1,null,null);
        aMap.clear();
        initMarker(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
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
        unbinder.unbind();
    }
}
