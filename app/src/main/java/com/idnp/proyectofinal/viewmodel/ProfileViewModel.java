package com.idnp.proyectofinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.idnp.proyectofinal.ui.LoginActivity;
import com.idnp.proyectofinal.ui.ProfileFragment;

public class ProfileViewModel extends ViewModel {
    private Context context;
    private SharedPreferences.Editor editor;
    public String nombre, correo, dni;

    public ProfileViewModel(ProfileFragment contexto, Bundle datos){
        context = contexto.getContext();

        Bundle datosRecuperados = datos;
        if (datosRecuperados == null) {
            // No hay datos, manejar excepción
            nombre = "Usuario Invitado";
            correo = "invitado@test.com";
            dni = "00000000";
        }
        nombre = datosRecuperados.getString("nombreU");
        correo = datosRecuperados.getString("correoU");
        dni = datosRecuperados.getString("dniU");
    }

    public void cerrarSesion(){
        editor = context.getSharedPreferences("datos", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Intent cs = new Intent(context.getApplicationContext(),LoginActivity.class);
        context.startActivity(cs);
    }
}