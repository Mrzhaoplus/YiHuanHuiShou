package com.bjxf.zxing.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;




import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

public abstract class BaseActivity extends FragmentActivity {

	private static final int PERMISSIONS_REQUEST_CODE = 0;
	
	private SharedPreferences preferences;
	
	public static List<Activity> activities =new ArrayList<Activity>();
	public static List<Activity> as =new ArrayList<Activity>();
	
	public SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		activities.add(this);
		setStateArgument();
		
		super.onCreate(arg0);
		
		initView();
		
//		getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
////                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		initData();
	}
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		start();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Stop();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Resume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Pause();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Destroy();
		super.onDestroy();
		onClear();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Restart();
	}
	
	
	public void setState(boolean isshow,int color){
		if(isshow){
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			StatusBarUtils.setWindowStatusBarColor(this,color);
		}
	}
	//初始化状�?
	public abstract void setStateArgument();
	
	
	public abstract void initView();//初始化布�?
	
	public abstract void initData();//初始化最起始数据
	
	public void start(){
		
	}
	
	public void Stop(){
		
	}
	
	public void Resume(){
		
	}
	public void Pause(){
		
	}
	public void Destroy(){
		
	}
	
	public void Restart(){
		
	}
	
	public void onClear(){
		
	}
	//设置Window窗口的�?�明�?
	public void setWindowTranslucence(double d){
		
		Window window = getWindow();
		
		LayoutParams attributes = window.getAttributes();
		attributes.alpha=(float) d;
		window.setAttributes(attributes);
		
	}
	
	
	public void setShowPop(PopupWindow popupWindow,View view){
		if(popupWindow!=null&&popupWindow.isShowing()){
			popupWindow.dismiss();
		}else{
			setWindowTranslucence(0.3);
			popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		}
	}
	
	
	//判断是否是第�?�?
	public boolean isfirstTime(){
		
		preferences=getSharedPreferences("user", MODE_PRIVATE);
		
		boolean isdy = preferences.getBoolean("isdy", false);
		
		return isdy;
	}
	
	//记录是否是第�?�?
	public void recorddy(boolean isdy){
		if(!isdy){
			preferences=getSharedPreferences("user", MODE_PRIVATE);
			Editor edit = preferences.edit();
			edit.putBoolean("isdy", true);
			edit.commit();
		}
	}
	
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		ActivityResult(arg0, arg1, arg2);
	}
	
	public void ActivityResult(int arg0, int arg1, Intent arg2){
		
	}
	//获取状�?�栏的高�?
	public static int getStatusHeight(Context context) {

	    int statusHeight = -1;
	    try {
	        Class clazz = Class.forName("com.android.internal.R$dimen");
	        Object object = clazz.newInstance();
	        int height = Integer.parseInt(clazz.getField("status_bar_height").get(object)
	                .toString());
	        statusHeight = context.getResources().getDimensionPixelSize(height);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return statusHeight;
	}
}
