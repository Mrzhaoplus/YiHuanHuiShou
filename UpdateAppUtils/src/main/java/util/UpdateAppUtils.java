package util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import customview.ConfirmDialog;
import feature.Callback;


/**
 * Created by Teprinciple on 2016/11/15.
 */
public class UpdateAppUtils {

    private final String TAG = "UpdateAppUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;

    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    private int serverVersionCode = 0;
    private String apkPath="";
    private String serverVersionName="";
    private boolean isForce = false; //是否强制更新
    private int localVersionCode = 0;
    private String localVersionName="";
    public static boolean showNotification = true;
    private String updateInfo = "";


    private UpdateAppUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
    }

    public static UpdateAppUtils from(Activity activity){
        return new UpdateAppUtils(activity);
    }

    public UpdateAppUtils checkBy(int checkBy){
        this.checkBy = checkBy;
        return this;
    }

    public UpdateAppUtils apkPath(String apkPath){
        this.apkPath = apkPath;
        return this;
    }

    public UpdateAppUtils downloadBy(int downloadBy){
        this.downloadBy = downloadBy;
        return this;
    }

    public UpdateAppUtils showNotification(boolean showNotification){
        this.showNotification = showNotification;
        return this;
    }

    public UpdateAppUtils updateInfo(String updateInfo){
        this.updateInfo = updateInfo;
        return this;
    }



    public UpdateAppUtils serverVersionCode(int serverVersionCode){
        this.serverVersionCode = serverVersionCode;
        return this;
    }

    public UpdateAppUtils serverVersionName(String  serverVersionName){
        this.serverVersionName = serverVersionName;
        return this;
    }

    public UpdateAppUtils isForce(boolean  isForce){
        this.isForce = isForce;
        return this;
    }

    //获取apk的版本号 currentVersionCode
    private  void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 版本名
            localVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        switch (checkBy){
            case CHECK_BY_VERSION_CODE:
                if (serverVersionCode >localVersionCode){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                    Toast.makeText(activity, "当前版本是最新版本", Toast.LENGTH_SHORT).show();
                }
                break;
            case CHECK_BY_VERSION_NAME:
                if (!serverVersionName.equals(localVersionName)){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;
        }

    }

    private void toUpdate() {

        realUpdate();

        //尝试在内部适配6.0
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED){
//            realUpdate();
//        }else {//申请权限
//            ActivityCompat.requestPermissions(activity,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//        }

    }

    private void realUpdate() {
        ConfirmDialog dialog = new ConfirmDialog(activity, new Callback() {
            @Override
            public void callback(int position) {
                switch (position){
                    case 0:  //cancle
                        if (isForce)System.exit(0);
                        break;

                    case 1:  //sure
                        if (downloadBy == DOWNLOAD_BY_APP) {
                            if (isWifiConnected(activity)){
                                DownloadAppUtils.downloadForAutoInstall(activity, apkPath, "demo.apk", serverVersionName);
                            }else {
                                new ConfirmDialog(activity, new Callback() {
                                    @Override
                                    public void callback(int position) {
                                        if (position==1){
                                            DownloadAppUtils.downloadForAutoInstall(activity, apkPath, "demo.apk", serverVersionName);
                                        }else {
                                            if (isForce)activity.finish();
                                        }
                                    }
                                }).setContent("目前手机不是WiFi状态\n确认是否继续下载更新？").show();
                            }

                        }else if (downloadBy == DOWNLOAD_BY_BROWSER){
                            DownloadAppUtils.downloadForWebView(activity,apkPath);
                        }
                        break;
                }
            }
        });

        String content = "发现新版本:"+serverVersionName+"\n是否下载更新?";
        if (!TextUtils.isEmpty(updateInfo)){
            content = "发现新版本:"+serverVersionName+"是否下载更新?\n\n"+updateInfo;
        }
        dialog .setContent(content);
        dialog.setCancelable(false);
        dialog.show();
    }




    //尝试在内部适配6.0 可能会引起内存泄露
//    public static void onRequestPermissionsResult(int requestCode,
//                                            @NonNull String[] permissions,
//                                            @NonNull int[] grantResults){
//        switch (requestCode){
//            case 1:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
////                    realUpdate();
//                    mHandler.sendEmptyMessage(1);
//                }else {
//                    //提示用户没有授予权限
//                }
//                break;
//        }
//    }



    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

}
