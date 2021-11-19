package com.idnp.proyectofinal.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.idnp.proyectofinal.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        lanzarThread();
    }
    private void lanzarThread(){
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000); //espera de 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };

        timer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}