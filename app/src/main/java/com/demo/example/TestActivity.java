package com.demo.example;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.otaliastudios.cameraview.CameraLogger;

public class TestActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        CameraLogger.registerLogger(new CameraLogger.Logger() {
            @Override
            public void log(int level, @NonNull String tag, @NonNull String message, @Nullable Throwable throwable) {
                Log.d("!=!", "message===" + message);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= 22 || Settings.canDrawOverlays(TestActivity.this)) {

                    if (ContextCompat.checkSelfPermission(TestActivity.this, "android.permission.CAMERA") != 0) {
                        ActivityCompat.requestPermissions(TestActivity.this, new String[]{"android.permission.CAMERA"}, 23);
                    } else {
                        startService(new Intent(TestActivity.this, TestService.class));
                        finish();
                    }
                } else {
                    Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION",
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 1);
                }

            }
        });

    }

}
