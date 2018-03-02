package com.postarium.arm_avi.postarium;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Thread splash = new Thread(){
            public void run(){
                try {
                    sleep(1500);

                    Intent splash = new Intent(getBaseContext(),PanelActivity.class);
                    startActivity(splash);
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

                }catch (Exception ex){

                }
            }
        };
        splash.start();

    }

}
