package com.idnp.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;  //Firebase
    DatabaseReference databaseReference;    //Firebase
    private static final String TAG = "MyActivity";
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
                    checkSP();
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
    private void loadSharedPreferences(){
        SharedPreferences pref = getSharedPreferences("datos",Context.MODE_PRIVATE);
        String id = pref.getString("idUser","No hay informacion");
    }
    private void checkSP(){
        SharedPreferences pref = getSharedPreferences("datos",Context.MODE_PRIVATE);
        String id = pref.getString("idUser","No hay informacion");
        if(id != "No hay informacion"){
            Log.i("TAG","MI id es "+id);
            Intent go = new Intent(SplashScreenActivity.this,MenuActivity.class);
            go.putExtra("idUser",Integer.parseInt(id));
            startActivity(go);
        }else{
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}