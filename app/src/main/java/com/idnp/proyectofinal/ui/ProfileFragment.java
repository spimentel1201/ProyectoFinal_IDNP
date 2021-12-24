package com.idnp.proyectofinal.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.idnp.proyectofinal.viewmodel.ProfileViewModel;
import com.idnp.proyectofinal.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    Button cerrarSesion;
    private TextView p_nombre,p_correo,p_dni;
    private SharedPreferences.Editor editor;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        p_nombre = (TextView) view.findViewById(R.id.text_home);
        p_correo = (TextView) view.findViewById(R.id.text_correo);
        p_dni = (TextView) view.findViewById(R.id.text_dni);
        cerrarSesion = (Button) view.findViewById(R.id.cerrarSesion);
        Bundle datosRecuperados = getArguments();
        //Verificación si hay datos de usuario, en caso no los hubiera se llenan con datos de invitado la sección de perfil
        if (datosRecuperados == null) {
            p_nombre.setText("Usuario Invitado");
            p_correo.setText("invitado@test.com");
            p_dni.setText("00000000");
        }
        String prof_nom = datosRecuperados.getString("nombreU");
        String prof_cor = datosRecuperados.getString("correoU");
        String prof_dni = datosRecuperados.getString("dniU");
        //Asignación de datos a los textView de acuerdo al usuario
        p_nombre.setText(prof_nom);
        p_correo.setText(prof_cor);
        p_dni.setText(prof_dni);
        /*Falta funcionalidad Cierre de Sesión*/
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                cerrarSesion(v);
                                            }
                                        }
        );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    public void cerrarSesion(View view){
        /*Eliminar preferencias de usuario*/
        editor = getContext().getSharedPreferences("datos", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Intent cs = new Intent(getActivity(),LoginActivity.class);
        startActivity(cs);
    }
}