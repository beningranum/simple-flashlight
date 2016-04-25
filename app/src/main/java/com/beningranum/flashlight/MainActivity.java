package com.beningranum.flashlight;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton flash;
    Camera cam;
    Camera.Parameters p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cam = Camera.open();
        p = cam.getParameters();

        flash = (ToggleButton) findViewById(R.id.flash);

        flash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                } else {
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }
                cam.setParameters(p);
                cam.startPreview();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cam = Camera.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cam.release();
    }
}
