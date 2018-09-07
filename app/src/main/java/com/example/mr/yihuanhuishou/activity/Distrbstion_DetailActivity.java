package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Details_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Distrbstion_DetailActivity extends BaseActivity implements View.OnClickListener, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.dis_bh)
    TextView bianhao;
    @BindView(R.id.dis_state)
    TextView state;
    @BindView(R.id.dis_lx)
    TextView sort;
    @BindView(R.id.dis_zl)
    TextView weight;
    @BindView(R.id.dis_zj)
    TextView price;
    @BindView(R.id.dis_name)
    TextView name;
    @BindView(R.id.dis_tel)
    TextView tel;
    @BindView(R.id.dis_dizhi)
    TextView address;
    @BindView(R.id.dis_qu_time)
    TextView qu_time;
    @BindView(R.id.dis_time)
    TextView fa_time;
    @BindView(R.id.dis_jie_time)
    TextView jie_time;
    @BindView(R.id.dis_qu_time2)
    TextView qu_time2;
    @BindView(R.id.dis_Lpnum)
    TextView Lpnum;
    @BindView(R.id.map)
    MapView map;
    private SharedPreferences sp;
    private int id;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private String detailAddress;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distrbstion__detail);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        id = getIntent().getIntExtra("id", 0);
        initview();
        infoview();
        initdata(savedInstanceState);

    }

    private void initdata(Bundle savedInstanceState) {
        //创建地图
        map.onCreate(savedInstanceState);
        //显示地图
        if (aMap == null) {
            aMap = map.getMap();
        }
        aMap.getUiSettings().setZoomControlsEnabled(false);
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        //更改定位图标
        //myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.driver_map_location_iv));
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

    }

    private void infoview() {
       Log.e("============",id+"");
        HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<Order_Details_Bean>post(MyUrls.BASEURL + "/recyclers/order/single")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Order_Details_Bean>(Distrbstion_DetailActivity.this, Order_Details_Bean.class) {
                    @Override
                    public void onSuccess(Response<Order_Details_Bean> response) {
                        Order_Details_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Order_Details_Bean.EecDemandinfoBean demandinfo = body.getEecDemandinfo();
                            Order_Details_Bean.EecRecoveryOrderBean recoveryOrder = body.getEecRecoveryOrder();
                            bianhao.setText(recoveryOrder.getOrderNumber());
                            sort.setText(recoveryOrder.getVarieties());
                            weight.setText(recoveryOrder.getCount()+""+recoveryOrder.getUnit());
                            price.setText(demandinfo.getTotalPrice()+"元");
                            detailAddress = demandinfo.getDetailAddress();
                            address.setText(demandinfo.getDetailAddress());
                            long createDate = recoveryOrder.getCreateDate();
                            String dateToString = getDateToString(String.valueOf(createDate/1000));
                            fa_time.setText(dateToString);
                            long comeDate = demandinfo.getComeDate();
                            qu_time2.setText(getDateToString(String.valueOf(comeDate/1000)));
                            long receiptDate = recoveryOrder.getReceiptDate();
                            jie_time.setText(getDateToString(String.valueOf(receiptDate/1000)));
                            Lpnum.setText(recoveryOrder.getCarPlate());
                            String comeType = demandinfo.getComeType();
                            if(comeType.equals("1")){
                                qu_time.setText("预约时间");
                            }else{
                                qu_time.setText("立即上门");
                            }

                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Distrbstion_DetailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Distrbstion_DetailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Distrbstion_DetailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Distrbstion_DetailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Distrbstion_DetailActivity.this, body.getMsg());
                        }
                    }
                    @Override
                    public void onError(Response<Order_Details_Bean> response) {
                        super.onError(response);
                        Order_Details_Bean body = response.body();
                        Log.e("=================","失败"+body);
                    }

                });
    }

    private void GeocodeSearch(String city) {
        //构造 GeocodeSearch 对象，并设置监听。
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(city, city);
        geocodeSearch.getFromLocationNameAsyn(query);

    }

    private void initview() {
        beak.setOnClickListener(this);
        if(!TextUtils.isEmpty(address.getText().toString())){
            GeocodeSearch(detailAddress);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
        }
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
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
                        "定位时间：" + df.format(date) + "\n" +
                        "区" + amapLocation.getCountry()
                );
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
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
                //获取到的经纬度
                LatLonPoint latLongPoint = address.getLatLonPoint();
                lat = latLongPoint.getLatitude();
                lon = latLongPoint.getLongitude();
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng
                        (lat, lon), 13.0f));
            }
        }
    }
}
