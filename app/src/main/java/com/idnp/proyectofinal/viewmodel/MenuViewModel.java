package com.idnp.proyectofinal.viewmodel;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.idnp.proyectofinal.HomeFragment;
import com.idnp.proyectofinal.MapFragment;
import com.idnp.proyectofinal.MenuActivity;
import com.idnp.proyectofinal.ProfileFragment;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.models.User;

public class MenuViewModel extends AppCompatActivity {
    private Context context;

    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    int id_value;
    User x;
    DbUsers DbUsers;
    Bundle val;

    public MenuViewModel(Context contexto){
        context = contexto;
        DbUsers = new DbUsers(context);

        id_value = getIntent().getIntExtra("idUser",0);
        x = DbUsers.verUsuario(id_value);

        val = new Bundle();
        val.putString("nombreU",x.getNombres());
        val.putString("correoU",x.getCorreo_electronico());
        val.putString("dniU",x.getDni());

        loadFragment(homeFragment);
    }

    public void loadProfileFragment(){
        loadFragment(profileFragment);
    }

    public void loadMapFragment(){
        loadFragment(mapFragment);
    }

    public void loadHomeFragment(){
        loadFragment(homeFragment);
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(val);
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}
