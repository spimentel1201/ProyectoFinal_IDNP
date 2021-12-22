package com.idnp.proyectofinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.idnp.proyectofinal.ui.LoginActivity;
import com.idnp.proyectofinal.ui.SignUpActivity;
import com.idnp.proyectofinal.db.DbUsers;

public class SignUpViewModel extends BaseObservable {
    private Context context;
    private static final String TAG = "SignUpActivivty";
    public String nombres, apellidos, correo, password;
    public int telefono, dni;

    public SignUpViewModel(Context contexto){
        context = contexto;
    }

    public void registrandoUsuario(){
        if(!nombres.equals("") && !correo.equals("")) {

            DbUsers dbUsuarios = new DbUsers(context);
            long id = dbUsuarios.agregarUsuario(nombres, apellidos, telefono, dni, correo, password);

            if (id > 0) {
                Toast.makeText(context, "REGISTRO EXITOSO ", Toast.LENGTH_LONG).show();
                limpiar();
                goToMenu();
            } else {
                Log.d(TAG,Long.toString(id));
                Toast.makeText(context, "ERROR AL GUARDAR REGISTRO" + Long.toString(id), Toast.LENGTH_LONG).show();
                Log.d(TAG,Long.toString(id));
            }
        } else {
            Toast.makeText(context, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }

        /*Redirección*/
        Intent go = new Intent(context, LoginActivity.class);
        context.startActivity(go);
    }

    private void limpiar() {
        nombres = "";
        apellidos = "";
        telefono = 0;
        dni = 0;
        correo = "";
        password = "";
    }
    public void goToMenu(){
        Intent go = new Intent(context, LoginActivity.class);
        context.startActivity(go);
    }
}