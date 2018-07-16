package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.JsonBean;
import com.example.mr.yihuanhuishou.utils.JsonFileReader;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.example.mr.yihuanhuishou.utils.Validator;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Address_bianjiActivity extends AppCompatActivity implements View.OnClickListener, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.baocun)
    TextView baocun;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.sheng)
    TextView sheng;
    @BindView(R.id.shi)
    TextView shi;
    @BindView(R.id.qu)
    TextView qu;
    @BindView(R.id.diqu)
    LinearLayout diqu;
    @BindView(R.id.name)
    EditText add_name;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.xiangxi)
    EditText xiangxi;
    private AMap aMap;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private AMapLocation aMapLocation;
    private LatLng latLng;
    private SharedPreferences sp;
    private int id;
    private String name;
    private String tell;
    private String province;
    private String city;
    private String counry;
    private String detailAddr;
    private GeocodeSearch geocodeSearch;
    private String addressName;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_bianji);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("add_name");
        tell = getIntent().getStringExtra("add_tel");
        province = getIntent().getStringExtra("add_sf");
        city = getIntent().getStringExtra("add_cs");
        counry = getIntent().getStringExtra("add_qu");
        detailAddr = getIntent().getStringExtra("add_xq");

        initdata(savedInstanceState);
        initJsonData();
    }

    private void initdata(Bundle savedInstanceState) {
        //创建地图
        map.onCreate(savedInstanceState);
        //显示地图
        if (aMap == null) {
            aMap = map.getMap();
        }

        beak.setOnClickListener(this);
        baocun.setOnClickListener(this);
        diqu.setOnClickListener(this);
        //赋值
        add_name.setText(name);
        tel.setText(tell);
        sheng.setText(province);
        shi.setText(city);
        qu.setText(counry);
        xiangxi.setText(detailAddr);

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.baocun:
                infodata();
                break;
            case R.id.diqu:
                showPickerView();
                break;
        }
    }

    private void infodata() {


        String name = add_name.getText().toString().trim();
        String tell = tel.getText().toString().trim();
        String add_sf = sheng.getText().toString().trim();
        String add_cs = shi.getText().toString().trim();
        String add_xq = qu.getText().toString().trim();
        String add_xx = xiangxi.getText().toString().trim();
        double latitude = aMapLocation.getLatitude();
        double longitude = aMapLocation.getLongitude();
        boolean mobileNO = Validator.isMobileNO(tell);
        if(mobileNO){
            HttpParams params = new HttpParams();
            params.put("token", sp.getString(GGUtils.TOKEN, ""));
            params.put("id", id);
            params.put("name", name);
            params.put("phoneNumber", tell);
            params.put("province", add_sf);
            params.put("city", add_cs);
            params.put("counry", add_xq);
            params.put("detailAddr", add_xx);
            params.put("longitude", lon);
            params.put("latitude", lat);
            OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/address/addOrUpdate")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Zhece_Bean>(Address_bianjiActivity.this, Zhece_Bean.class) {
                        @Override
                        public void onSuccess(Response<Zhece_Bean> response) {
                            Zhece_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                                finish();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(Address_bianjiActivity.this, body.getMsg());
                            }
                        }
                    });
        }else{
            ToastUtils.getToast(Address_bianjiActivity.this,"手机号码有误！");
        }

    }


    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                sheng.setText(options1Items.get(options1).getPickerViewText());
                shi.setText(options2Items.get(options1).get(options2));
                qu.setText(options3Items.get(options1).get(options2).get(options3));
                GeocodeSearch(options3Items.get(options1).get(options2).get(options3));
            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(16)
                .setOutSideCancelable(false)
                .build();
          /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void GeocodeSearch(String city) {
        //构造 GeocodeSearch 对象，并设置监听。
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(city, city);
        geocodeSearch.getFromLocationNameAsyn(query);
    }


    private void initJsonData() {   //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(this, "province_data.json");
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        aMapLocation = amapLocation;
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
                /*       //然后把经纬度传递给地图界面
                intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putDouble("Lat",lat);
                bundle.putDouble("Lng",lon);
                intent.putExtra("LAT",bundle);
                New_AddressActivity.this.setResult(8, intent);
                New_AddressActivity.this.finish();*/
            }

        }
    }
}
