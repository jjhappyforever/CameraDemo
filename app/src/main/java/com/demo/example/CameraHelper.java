package com.demo.example;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.engine.CameraEngine;

public class CameraHelper {

    private CameraView cameraView;

    public void init(ViewGroup parent) {

        if (parent.findViewById(R.id.camera_view) != null) {
            return;
        }

        cameraView = (CameraView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cameraview, null);
        cameraView.addCameraListener(new CameraListener() {

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);

            }

            @Override
            public void onCameraError(@NonNull CameraException exception) {
                super.onCameraError(exception);
            }

            @Override
            public void onCameraClosed() {
                super.onCameraClosed();
            }

            @Override
            public void onCameraOpened(@NonNull CameraOptions options) {
                super.onCameraOpened(options);
            }

        });

        parent.addView(cameraView, new ViewGroup.LayoutParams(300, 300));

        cameraView.setFacing(Facing.FRONT);

    }

    public void onResume() {
        if (cameraView != null) {
            cameraView.open();
        }
    }

    public void onPause() {
        if (cameraView != null) {
            cameraView.close();
        }
    }

    public void onDestroy() {

        if (cameraView != null) {
            if (cameraView.getParent() != null) {
                ViewGroup parent = (ViewGroup) cameraView.getParent();
                parent.removeView(cameraView);
            }

            cameraView.close();
            cameraView.destroy();
            cameraView = null;

        }
    }

}
