package com.idnp.proyectofinal.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.idnp.proyectofinal.BR;
import com.idnp.proyectofinal.ui.MenuActivity;
import com.idnp.proyectofinal.ui.SignUpActivity;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.models.User;

import java.util.ArrayList;

public class LoginViewModel extends BaseObservable {
    DbUsers DbUsers;
    public String usuario;
    public String password;

    ArrayList<User> usuarios = null;

    private String successMessage = "Login exitoso";
    private String errorMessage = "Datos incorrectos, ingresando como invitado";
    private Context context;
    private int identificador;

    @Bindable
    public String toastMessage = null;

    public LoginViewModel(Context contexto){
        context = contexto;
        DbUsers = new DbUsers(context);
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void verificarLogin() {
        if (verificandoSesion()) {
            setToastMessage(successMessage);
            Intent go = new Intent(context, MenuActivity.class);
            go.putExtra("idUser",usuarios.get(identificador).getId());
            guardarSP(usuarios.get(identificador).getId());
            context.startActivity(go);
        }
        else {
            setToastMessage(errorMessage);
            irInvitado();
        }
    }

    public void registrarUsuario() {
        Intent go = new Intent(context, SignUpActivity.class);
        context.startActivity(go);
    }

    public boolean verificandoSesion() {
        usuarios = DbUsers.mostrarUsuarios();

        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getCorreo_electronico().equals(usuario) && usuarios.get(i).getContraseña().equals(password)){
                identificador = i;
                return true;
            } else{
                return false;
            }
        }
        return false;
    }

    public void guardarSP(int id) {
        SharedPreferences preferencias = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString("idUser", Integer.toString(id));
        obj_editor.commit();
    }

    public void irInvitado(){
        Intent go = new Intent(context, MenuActivity.class);
        context.startActivity(go);
    }
}