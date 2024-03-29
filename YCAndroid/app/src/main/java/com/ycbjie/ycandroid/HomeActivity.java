package com.ycbjie.ycandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ycbjie.ycandroid.channel.ChannelActivity;
import com.ycbjie.ycandroid.container.FlutterContainerActivity;
import com.ycbjie.ycandroid.container.FlutterViewActivity4;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.BinaryMessenger;
//TODO 注意这两个是不一样的
//import io.flutter.view.FlutterView;
import io.flutter.embedding.android.FlutterView;

import static androidx.core.view.ViewCompat.getDisplay;

/**
 * @author yc
 */
public class HomeActivity extends AppCompatActivity {

    private TextView tvContainer;
    private TextView tvChannel;
    private TextView tvTool;
    private TextView tvInfo;
    private FrameLayout frameLayout;
    private FlutterView flutterView;
    private FlutterView flutterViewAbout;
    private FlutterEngine flutterEngine;
    private BinaryMessenger binaryMessenger;


    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity,
                        PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContainer = findViewById(R.id.tv_container);
        tvChannel = findViewById(R.id.tv_channel);
        tvTool = findViewById(R.id.tv_tool);
        frameLayout = findViewById(R.id.rl_flutter);
        tvInfo = findViewById(R.id.tv_info);

        initListener();
        addFlutterView();
        verifyStoragePermissions(this);
        test();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flutterEngine.getLifecycleChannel().appIsResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flutterEngine.getLifecycleChannel().appIsInactive();
    }

    @Override
    protected void onStop() {
        super.onStop();
        flutterEngine.getLifecycleChannel().appIsPaused();
    }


    private void initListener() {
        tvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FlutterContainerActivity.class));
            }
        });
        tvChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChannelActivity.class));
            }
        });
        tvTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FlutterViewActivity4.class));
            }
        });
    }

    private void addFlutterView() {
        flutterEngine = new FlutterEngine(this);
        binaryMessenger = flutterEngine.getDartExecutor().getBinaryMessenger();
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // 通过FlutterView引入Flutter编写的页面
        flutterView = new FlutterView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.addView(flutterView, lp);
        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(flutterEngine);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 是当某个按键被按下是触发。所以也有人在点击返回键的时候去执行该方法来做判断
     */
//    private long time;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //双击返回桌面
//            if ((System.currentTimeMillis() - time > 2000)) {
//                time = System.currentTimeMillis();
//                //Toast.makeText(this,"再按一次回到桌面",Toast.LENGTH_LONG).show();
//            } else {
//                //finish();
//                //可将activity 退到后台，注意不是finish()退出。
//                //判断Activity是否是task根
//                //使用moveTaskToBack是为了让app退出时，不闪屏，退出柔和一些
//                if (this.isTaskRoot()){
//                    //参数为false——代表只有当前activity是task根，指应用启动的第一个activity时，才有效;
//                    moveTaskToBack(false);
//                } else {
//                    //参数为true——则忽略这个限制，任何activity都可以有效。
//                    //使用此方法，便不会执行Activity的onDestroy()方法
//                    moveTaskToBack(true);
//                }
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }



    @SuppressLint("SetTextI18n")
    private void test(){
        int heightPixels = getHeightPixels(this);
        int widthPixels = getWidthPixels(this);
        int height = px2dip(this,heightPixels);
        int width = px2dip(this,widthPixels);
        tvInfo.setText("宽："+widthPixels + " 高："+heightPixels +" 单位dp：" + "宽："+width + " 高："+height);
    }

    public static int getWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getHeightPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



}
