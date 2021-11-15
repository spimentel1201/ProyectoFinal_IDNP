    package com.idnp.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

    public class LoginActivity extends AppCompatActivity {
        private CheckBox opcionMantenerSesion;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            getSupportActionBar().hide();
            opcionMantenerSesion = (CheckBox) findViewById(R.id.checkMantenerSesion);
        }
        /*Botón de redirección a registro*/
        public void irRegistrarme(View view){
            Intent go = new Intent(this,SignUpActivity.class);
            startActivity(go);
        }
        /*Boton Comprobar inicio de sesión y redirección a Menu*/
        public void iniciarSesion(View view){
            if(opcionMantenerSesion.isChecked()){
              /*Guardar las preferencias de usuario*/
            }
            /*Codigo que comprueba los datos*/
            Intent go = new Intent(this, MenuActivity.class);
            startActivity(go);
        }
}