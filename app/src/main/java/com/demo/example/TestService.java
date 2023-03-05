package com.demo.example;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;


public class TestService extends Service {

    WindowManager windowManager;
    CameraHelper cameraHelper = new CameraHelper();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showWindow();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showWindow() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            layoutParams = new WindowManager.LayoutParams(2038, 4195848, -3);
        } else {
            layoutParams = new WindowManager.LayoutParams(2002, 4195848, -3);
        }
        layoutParams.screenOrientation = 1;

        int widht = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        layoutParams.height = height / 2;
        layoutParams.width = widht;
        layoutParams.gravity = 17;

        final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.window_test, null);
        Button add = view.findViewById(R.id.add);
        Button remove = view.findViewById(R.id.remove);
        Button close = view.findViewById(R.id.close);
        FrameLayout parent = view.findViewById(R.id.parent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraHelper.init(parent);
                cameraHelper.onResume();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraHelper.onDestroy();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraHelper.onDestroy();
                windowManager.removeView(view);
            }
        });


        windowManager.addView(view, layoutParams);

        cameraHelper.onResume();

    }

}
