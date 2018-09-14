package com.example.mr.yihuanhuishou.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.bjxf.zxing.view.SweepCodeActivity;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Pop_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Xitong_Recycler_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDemandActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, AMapLocationListener, AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_right_tv)
    TextView titleRightTv;
    @BindView(R.id.peisong_left_rbt)
    RadioButton peisongLeftRbt;
    @BindView(R.id.peisong_right_rbt)
    RadioButton peisongRightRbt;
    @BindView(R.id.saoma_iv)
    ImageView saomaIv;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.leixing_tv)
    TextView leixingTv;
    @BindView(R.id.leixing_rl)
    RelativeLayout leixingRl;
    @BindView(R.id.view_03)
    TextView view03;
    @BindView(R.id.shuliang_tv)
    TextView shuliangTv;
    @BindView(R.id.view_02)
    LinearLayout view02;
    @BindView(R.id.shuliang_et)
    EditText shuliangEt;
    @BindView(R.id.view_04)
    TextView view04;
    @BindView(R.id.view_05)
    TextView view05;
    @BindView(R.id.zongjia_et)
    EditText zongjiaEt;
    @BindView(R.id.quhuo_left_rbt)
    RadioButton quhuoLeftRbt;
    @BindView(R.id.quhuo_right_rbt)
    RadioButton quhuoRightRbt;
    @BindView(R.id.select_address)
    TextView selectAddress;
    @BindView(R.id.address_tv)
    EditText addressTv;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.tiao_code)
    TextView tiaoCode;
    @BindView(R.id.pslx)
    RadioGroup pslx;
    @BindView(R.id.shijian)
    LinearLayout shijian;
    @BindView(R.id.xian)
    View xian;
    @BindView(R.id.qhdz)
    LinearLayout qhdz;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.add_lin)
    LinearLayout addLin;
    @BindView(R.id.dingwei)
    ImageView dingwei;
    private AMap aMap;
    private View inflate;
    private ListView list_view;
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private TextView queding;
    private TextView quxiao;
    private int index1;
    List<Xitong_Recycler_Bean.DataBean> mlist = new ArrayList<>();
    private String list_itme;
    private int TIAO_DEMO = 0;
    private BroadcastReceiver bdr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (SweepCodeActivity.reString.equals(action)) {
                String resultString = intent.getStringExtra("resultString");
                saomaIv.setVisibility(View.GONE);
                tiaoCode.setVisibility(View.VISIBLE);
                tiaoCode.setText(resultString);
            }
        }
    };
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private LatLng latLng;
    private TimePickerView pvTime1;
    private SharedPreferences sp;
    private String aLong;
    private int add_id;
    private String ala;
    private String time;
    private String comeType;
    private AMapLocation amapLocation1;
    private LatLonPoint latLonPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_demand);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SweepCodeActivity.reString);
        registerReceiver(bdr, intentFilter);
        initView(savedInstanceState);
        pslx.setOnCheckedChangeListener(this);
        rg.setOnCheckedChangeListener(this);
        initdataview();
    }

    private void initdataview() {
        mlist.clear();
        OkGo.<Xitong_Recycler_Bean>post(MyUrls.BASEURL + "/recyclers/info/allVarieties")
                .tag(this)
                .execute(new DialogCallback<Xitong_Recycler_Bean>(PostDemandActivity.this, Xitong_Recycler_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xitong_Recycler_Bean> response) {
                        Xitong_Recycler_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Xitong_Recycler_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                        } else {
                            ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                        }

                    }
                });



    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.peisong_left_rbt:
                shijian.setVisibility(View.VISIBLE);
                xian.setVisibility(View.VISIBLE);
                qhdz.setVisibility(View.VISIBLE);
                addressTv.setVisibility(View.VISIBLE);
                map.setVisibility(View.VISIBLE);
                addLin.setVisibility(View.VISIBLE);
                break;
            case R.id.peisong_right_rbt:
                shijian.setVisibility(View.GONE);
                xian.setVisibility(View.GONE);
                qhdz.setVisibility(View.GONE);
                addressTv.setVisibility(View.GONE);
                map.setVisibility(View.GONE);
                addLin.setVisibility(View.GONE);
                break;
            case R.id.quhuo_left_rbt:
                initTimePicker1();
                comeType = "0";
                break;
            case R.id.quhuo_right_rbt:
                comeType = "1";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                time = format.format(date);

                break;

        }
    }

    private void initTimePicker1() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        SimpleDateFormat formatter_year = new SimpleDateFormat("yyyy ");
        String year_str = formatter_year.format(curDate);
        int year_int = (int) Double.parseDouble(year_str);
        SimpleDateFormat formatter_mouth = new SimpleDateFormat("MM ");
        String mouth_str = formatter_mouth.format(curDate);
        int mouth_int = (int) Double.parseDouble(mouth_str);

        SimpleDateFormat formatter_day = new SimpleDateFormat("dd ");
        final String day_str = formatter_day.format(curDate);
        int day_int = (int) Double.parseDouble(day_str);


        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year_int, 1, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year_int + 1, mouth_int, day_int + (30 - day_int));


        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                time = getTime(date);

            }
        })

                .setType(new boolean[]{true, true, true, true, true, true}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDividerColor(Color.RED)
                .setTextColorCenter(Color.RED)//设置选中项的颜色
                .setTextColorOut(Color.BLUE)//设置没有被选中项的颜色
                .setContentSize(21)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                //.setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
        pvTime1.show();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    private void initView(Bundle savedInstanceState) {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("发布需求");
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("发布");

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
//设置地图拖动监听
        aMap.setOnCameraChangeListener(this);
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


    }


    @OnClick({R.id.dingwei,R.id.view_02, R.id.title_back_iv, R.id.title_right_tv, R.id.saoma_iv, R.id.leixing_rl, R.id.select_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_02:
                infoview();
                break;
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_right_tv:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String format1 = format.format(date);
                try {
                    if(TextUtils.isEmpty(time)){
                        ToastUtils.getToast(PostDemandActivity.this,"请完善信息后再发送！");
                    }else{
                        Date parse = format.parse(time);
                        Date parse1 = format.parse(format1);
                        if(comeType.equals("0")){
                            if(parse.getTime()<parse1.getTime()){
                                ToastUtils.getToast(PostDemandActivity.this,"取货时间错误，请重新选取时间！");
                                initTimePicker1();
                            }else{
                                fabuview();
                            }
                        }else{

                            fabuview();
                        }
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            //二维码扫描器
            case R.id.saoma_iv:
                startActivity(new Intent(this, SweepCodeActivity.class));
                break;
                //////////////////////////////
            case R.id.leixing_rl:
                showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.select_address:
                Intent intent = new Intent(this, Select_AddressActivity.class);
                intent.putExtra("state", 1);
                startActivityForResult(intent, TIAO_DEMO);
                break;
            case R.id.dingwei:
                setCurrentLocationDetails();
                break;

        }
    }

    private void setCurrentLocationDetails() {
        GeocodeSearch geocodeSearch = new GeocodeSearch(getApplicationContext());
        geocodeSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng(经纬度)，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 25, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
    }

    //发布接口
    private void fabuview() {
        String detailAddress = addressTv.getText().toString().trim();
        String barCode = tiaoCode.getText().toString().trim();
        String varieties = leixingTv.getText().toString();
        String unit = shuliangTv.getText().toString();
        String count = shuliangEt.getText().toString().trim();
        String totalPrice = zongjiaEt.getText().toString().trim();


        if (peisongLeftRbt.isChecked() == true) {
            if(TextUtils.isEmpty(detailAddress)||TextUtils.isEmpty(barCode)||TextUtils.isEmpty(varieties)||TextUtils.isEmpty(totalPrice)||TextUtils.isEmpty(count)){
                ToastUtils.getToast(PostDemandActivity.this,"请完善信息后再发送！");
            }else{
                if (add_id == 0) {
                    HttpParams params = new HttpParams();
                    params.put("token", sp.getString(GGUtils.TOKEN, ""));
                    params.put("distribution", "0");
                    params.put("detailAddress", detailAddress);
                    params.put("longitude", latLonPoint.getLongitude());
                    params.put("latitude", latLonPoint.getLatitude());
                    params.put("barCode", barCode);
                    params.put("varieties", varieties);
                    params.put("unit", unit);
                    params.put("count", count);
                    params.put("totalPrice", totalPrice);
                    params.put("comdateStr", time);
                    params.put("comeType", comeType);
                    OkGo.<Zhece_Bean>get(MyUrls.BASEURL + "/recyclers/demand/addOrUpdate")
                            .tag(this)
                            .params(params)
                            .execute(new DialogCallback<Zhece_Bean>(PostDemandActivity.this, Zhece_Bean.class) {
                                @Override
                                public void onSuccess(Response<Zhece_Bean> response) {
                                    Zhece_Bean body = response.body();
                                    String code = body.getCode();
                                    if (code.equals("200")) {
                                        ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                        Intent intent = new Intent(PostDemandActivity.this, OrderActivity.class);
                                        intent.putExtra("state", 1);
                                        startActivity(intent);
                                    } else  {
                                        ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                    }
                                }
                            });
                }
                HttpParams params = new HttpParams();
                params.put("token", sp.getString(GGUtils.TOKEN, ""));
                params.put("distribution", "0");
                params.put("addrId", add_id);
                params.put("detailAddress", detailAddress);
                params.put("longitude", aLong);
                params.put("latitude", ala);
                params.put("barCode", barCode);
                params.put("varieties", varieties);
                params.put("unit", unit);
                params.put("count", count);
                params.put("totalPrice", totalPrice);
                params.put("comdateStr", time);
                params.put("comeType", comeType);
                OkGo.<Zhece_Bean>get(MyUrls.BASEURL + "/recyclers/demand/addOrUpdate")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Zhece_Bean>(PostDemandActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                    Intent intent = new Intent(PostDemandActivity.this, OrderActivity.class);
                                    intent.putExtra("state", 1);
                                    startActivity(intent);
                                } else {
                                    ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                }
                            }
                        });
            }
        } else if (peisongRightRbt.isChecked() == true) {
            if(TextUtils.isEmpty(barCode)||TextUtils.isEmpty(varieties)||TextUtils.isEmpty(count)||TextUtils.isEmpty(totalPrice)){
                ToastUtils.getToast(PostDemandActivity.this,"请完善信息后再发送！");
            }else{
                HttpParams params = new HttpParams();
                params.put("token", sp.getString(GGUtils.TOKEN, ""));
                params.put("distribution", "1");
                params.put("barCode", barCode);
                params.put("varieties", varieties);
                params.put("unit", unit);
                params.put("count", count);
                params.put("totalPrice", totalPrice);
                OkGo.<Zhece_Bean>get(MyUrls.BASEURL + "/recyclers/demand/addOrUpdate")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Zhece_Bean>(PostDemandActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode();
                                if (code.equals("200")) {
                                    ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                    Intent intent = new Intent(PostDemandActivity.this, OrderActivity.class);
                                    intent.putExtra("state", 2);
                                    startActivity(intent);
                                } else {
                                    ToastUtils.getToast(PostDemandActivity.this, body.getMsg());
                                }
                            }
                        });
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIAO_DEMO && resultCode == RESULT_OK) {
            addressTv.setText(data.getStringExtra("add_details"));
            add_id = data.getIntExtra("id", 0);
            aLong = data.getStringExtra("long");
            ala = data.getStringExtra("la");
        }
    }
    private void infoview() {
        if (popupWindow == null) {
            inflate = LayoutInflater.from(this).inflate(R.layout.popwindow_listview, null);
            list_view = inflate.findViewById(R.id.list_view);
            popupWindow = new PopupWindow(inflate, 280, RadioGroup.LayoutParams.WRAP_CONTENT);
        }
        final List<String> list = new ArrayList<>();
        list.add("个");
        list.add("包");
        list.add("件");
        list.add("Kg");
        Pop_Adapter pop_adapter = new Pop_Adapter(this, list);
        list_view.setAdapter(pop_adapter);


        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view02, 0, 0);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.dismiss();
                shuliangTv.setText(list.get(i));
            }
        });
    }

    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(PostDemandActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.sharing_pop_item_view)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        quxiao = (TextView) dialog.findViewById(R.id.quxiao);
        queding = (TextView) dialog.findViewById(R.id.queding);
        wheelView = (WheelView) dialog.findViewById(R.id.wheelview);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index1 = 0;
                dialog.dismiss();
            }
        });

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index1 < 0 || index1 == 0) {
                    list_itme = mlist.get(0).getName();
                } else {
                    list_itme = mlist.get(index1).getName();
                }
                leixingTv.setText(list_itme);
                dialog.dismiss();
                index1 = 0;
            }
        });
        wheelView.setCyclic(false);
        wheelView.setAdapter(new ArrayWheelAdapter(mlist));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index1 = index;
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
        unregisterReceiver(bdr);
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
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        dingwei.setVisibility(View.VISIBLE);
        latLng = cameraPosition.target;
        double latitude = latLng.latitude;
        double longitude = latLng.longitude;
        latLonPoint = new LatLonPoint(latitude, longitude);
        Log.e("===================", latitude + "");
        Log.e("===================", longitude + "");
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        String formatAddress = regeocodeResult.getRegeocodeAddress().getFormatAddress();
        addressTv.setText(formatAddress);
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            boolean  hideInputResult =isShouldHideInput(v,ev);
            Log.v("hideInputResult","zzz-->>"+hideInputResult);
            if(hideInputResult){
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) PostDemandActivity.this
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(v != null){
                    if(imm.isActive()){
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            //之前一直不成功的原因是,getX获取的是相对父视图的坐标,getRawX获取的才是相对屏幕原点的坐标！！！
            Log.v("leftTop[]","zz--left:"+left+"--top:"+top+"--bottom:"+bottom+"--right:"+right);
            Log.v("event","zz--getX():"+event.getRawX()+"--getY():"+event.getRawY());
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
