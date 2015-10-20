package com.ialway.android.flashlight.ui;

import com.ialway.android.flashlight.service.ManageLightService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, ManageLightService.class);
        startService(intent);
        this.finish();
    }
}
