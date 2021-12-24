package com.idnp.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.db.DbUsers;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivivty";
    EditText txt_nombres,txt_apellidos,txt_dni,txt_telefono,txt_correo,txt_contraseña;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        txt_nombres = findViewById(R.id.txtNombres);
        txt_apellidos = findViewById(R.id.txtApellidos);
        txt_telefono = findViewById(R.id.txtTelefono);
        txt_dni = findViewById(R.id.txtDni);
        txt_correo = findViewById(R.id.txtCorreoElectronico);
        txt_contraseña = findViewById(R.id.txtContra);
    }
    /*Boton para registrar un usuario en bd y redireccionar a ventana de login*/
    public void registrarUsuario(View view){
        if(!txt_nombres.getText().toString().equals("") && !txt_correo.getText().toString().equals("")) {

            DbUsers dbUsuarios = new DbUsers(SignUpActivity.this);
            long id = dbUsuarios.agregarUsuario(txt_nombres.getText().toString(),txt_apellidos.getText().toString(), Integer.parseInt(txt_telefono.getText().toString()),Integer.parseInt(txt_dni.getText().toString()), txt_correo.getText().toString(),txt_contraseña.getText().toString());

            if (id > 0) {
                Toast.makeText(SignUpActivity.this, "REGISTRO EXITOSO ", Toast.LENGTH_LONG).show();
                limpiar();
                goToMenu();
            } else {
                Toast.makeText(SignUpActivity.this, "ERROR AL GUARDAR REGISTRO" , Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(SignUpActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }

        /*Redirección*/
        Intent go = new Intent(this, LoginActivity.class);
        startActivity(go);
    }
    private void limpiar() {
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_telefono.setText("");
        txt_dni.setText("");
        txt_correo.setText("");
        txt_contraseña.setText("");
    }
    public void goToMenu(){
        Intent go = new Intent(this,LoginActivity.class);
        startActivity(go);
    }
}