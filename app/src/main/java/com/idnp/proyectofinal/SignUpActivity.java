package com.idnp.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
    }
    /*Boton para registrar un usuario en bd y redireccionar a ventana de login*/
    public void registrarUsuario(View view){
        Intent go = new Intent(this, LoginActivity.class);
        startActivity(go);
    }
}