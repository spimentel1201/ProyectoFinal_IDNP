    package com.idnp.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.models.User;

import java.util.ArrayList;

    public class LoginActivity extends AppCompatActivity {
        private CheckBox opcionMantenerSesion;
        EditText txt_CorreoL,txt_ContraL;
        ArrayList<User> usuarios;
        private Cursor test;
        DbUsers DbUsers;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            getSupportActionBar().hide();
            opcionMantenerSesion = (CheckBox) findViewById(R.id.checkMantenerSesion);
            txt_CorreoL = findViewById(R.id.txtCorreoLogin);
            txt_ContraL = findViewById(R.id.txtContraLogin);
            DbUsers = new DbUsers(LoginActivity.this);
        }
        /*Botón de redirección a registro*/
        public void irRegistrarme(View view){
            Intent go = new Intent(this,SignUpActivity.class);
            startActivity(go);
        }
        /*Boton Comprobar inicio de sesión y redirección a Menu*/
        public void iniciarSesion(View view){
            verificarSesion();
        }
        public void verificarSesion(){
            String usuario = txt_CorreoL.getText().toString();
            String contraseña = txt_ContraL.getText().toString();
            usuarios = DbUsers.mostrarUsuarios();
            for(int i=0;i<usuarios.size();i++){
                if(usuarios.get(i).getCorreo_electronico().equals(usuario) && usuarios.get(i).getContraseña().equals(contraseña) && opcionMantenerSesion.isChecked()){
                    Intent go = new Intent(this, MenuActivity.class);
                    go.putExtra("idUser",usuarios.get(i).getId());
                    guardarSP(usuarios.get(i).getId());
                    startActivity(go);
                }else{
                    Toast toast = Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        public void guardarSP(int id) {
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferencias.edit();
            obj_editor.putString("idUser", Integer.toString(id));
            obj_editor.commit();
            finish();
        }
        public void irInvitado(View view){
            Intent go = new Intent(this, MenuActivity.class);
            startActivity(go);
        }
}