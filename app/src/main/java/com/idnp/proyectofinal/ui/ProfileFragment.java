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
        if (datosRecuperados == null) {
            // No hay datos, manejar excepción
            Toast.makeText(getActivity(), "No hay datos ", Toast.LENGTH_LONG).show();;
        }
        String prof_nom = datosRecuperados.getString("nombreU");
        String prof_cor = datosRecuperados.getString("correoU");
        String prof_dni = datosRecuperados.getString("dniU");
        Log.d("ProfileFragment", "El nombre: " + prof_nom);
        Log.d("ProfileFragment", "El correo: " + prof_cor);
        Log.d("ProfileFragment", "El dni: " + prof_dni);
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
    public void nohacernada(){

    }

}