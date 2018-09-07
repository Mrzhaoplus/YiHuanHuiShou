package com.bjxf.zxing.view;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import com.bjxf.zxing.R;
import com.bjxf.zxing.qr.CameraManager;
import com.bjxf.zxing.qr.CaptureActivityHandler;
import com.bjxf.zxing.qr.InactivityTimer;
import com.bjxf.zxing.qr.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

/**
 * 扫码进入界面
 * 
 * @author   admin
 *
 */
public class SweepCodeActivity extends BaseActivity implements SurfaceHolder.Callback{

	private TitleView title_sweep_code;
//	private LinearLayout flash_btn;
	private CaptureActivityHandler handler;// 消息中心
    private ViewfinderView viewfinderView;// 绘制扫描区域
    private boolean hasSurface;// 控制调用相机属性
    private Vector<BarcodeFormat> decodeFormats;// 存储二维格式的数组
    private String characterSet;// 字符集
    private InactivityTimer inactivityTimer;// 相机扫描刷新timer
    private MediaPlayer mediaPlayer;// 播放器
    private boolean playBeep;// 声音布尔
    private static final float BEEP_VOLUME = 0.10f;// 声音大小
    private boolean vibrate;// 振动布尔
    private boolean isTorchOn = true;
    private static final long VIBRATE_DURATION = 200L;
    private View sweep_code_view;
//    private RelativeLayout rl_smq;
//    private ProgressBar pb_jd;
//    private LinearLayout ll_pro;
//    private TextView tv_jd;
//    private TextView tv_kaisuo;
    private boolean pk;
    public static String reString="RESULTE";
	@Override
	public void setStateArgument() {
		// TODO Auto-generated method stub		
		setState(true, android.R.color.transparent);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_qrcode_capture_layout);
		CameraManager.init(SweepCodeActivity.this);
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//        flash_btn=(LinearLayout) findViewById(R.id.flash_btn);
		title_sweep_code=(TitleView) findViewById(R.id.title_sweep_code);
		sweep_code_view=findViewById(R.id.sweep_code_view);
		pk=getIntent().getBooleanExtra("pk", false);
		sweep_code_view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, getStatusHeight(SweepCodeActivity.this)));
		sweep_code_view.setBackgroundResource(android.R.color.transparent);
		title_sweep_code.setTitle("扫一扫");
		title_sweep_code.setBackGround(android.R.color.transparent);
		title_sweep_code.setLeftImageResource(R.drawable.fanhui_2x);
		title_sweep_code.setRightTopTextVisibility(View.INVISIBLE);
		inactivityTimer = new InactivityTimer(this);
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		title_sweep_code.getLeftImg().setOnClickListener(listener);
		
		title_sweep_code.setRightClickListener(listener);
		
//		flash_btn.setOnClickListener(listener);
		
		
	}
	@Override
	public void Resume() {
		// TODO Auto-generated method stub
		super.Resume();
		// 初始化相机画布
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
        // 声音
        playBeep = true;
        // 初始化音频管理器
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        // 振动
        vibrate = true;
		
	}
	@Override
	public void Pause() {
		// TODO Auto-generated method stub
		super.Pause();
		
		if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
		
		
		
        CameraManager.get().closeDriver();
        
        
	}
	
	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		super.Destroy();
		// 停止相机扫描刷新timer
        inactivityTimer.shutdown();
        
        
	}
	
	@Override
	public void onClear() {
		// TODO Auto-generated method stub
		super.onClear();
		
	}
	
	 void restartCamera(){
	 
	        viewfinderView.setVisibility(View.VISIBLE);
	        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
	        SurfaceHolder surfaceHolder = surfaceView.getHolder();
	        if (hasSurface) {
	            initCamera(surfaceHolder);
	        } else {
	            surfaceHolder.addCallback(this);
	            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        }
	        decodeFormats = null;
	        characterSet = null;
	        // 声音
	        playBeep = true;
	        // 初始化音频管理器
	        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
	        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
	            playBeep = false;
	        }
	        initBeepSound();
	        // 振动
	        vibrate = true;
	    }
	
	
	/**
     * 处理扫描结果
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if ("".equals(resultString)) {
            Toast.makeText(this, "二维码格式有误", Toast.LENGTH_SHORT)
                    .show();
        } else {//二维码正常
        	Log.e("TAG", "码："+resultString);
        	Intent intent = new Intent();
        	intent.setAction(reString);
        	intent.putExtra("resultString", resultString);
        	sendBroadcast(intent);
        	//扫码成功之后
        	finish();
        }
    }
    
    
    /**
     * 初始化相机
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int viewId=arg0.getId();
			if(viewId==R.id.title_img_left){
				finish();
			}else if(viewId==R.id.flash_btn){
				if (isTorchOn) {
                    isTorchOn = false;
                    CameraManager.start();
                } else {
                    isTorchOn = true;
                    CameraManager.stop();
                }
			}
			
		}
	};
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		if (!hasSurface) {
            hasSurface = true;
            initCamera(arg0);
        }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		hasSurface = false;
	}
	public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }
    
    /**
     * 声音设置
     */
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);
            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }
    
    /**
     * 结束后的声音
     */
    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}
