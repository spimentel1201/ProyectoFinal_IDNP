package com.idnp.proyectofinal.viewmodel;
/*
import android.content.Context;
import android.content.Intent;

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
    private String errorMessage = "Datos incorrectos";
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
            context.startActivity(go);
        }
        else
            setToastMessage(errorMessage);
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
}*/