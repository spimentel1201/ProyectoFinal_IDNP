package com.idnp.proyectofinal.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.idnp.proyectofinal.R;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;  //Firebase
    DatabaseReference databaseReference;    //Firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        inicializarFirebase();  //firebase
        lanzarThread();
    }
    /*
    * Método para inicializar bd firebase
    * */
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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