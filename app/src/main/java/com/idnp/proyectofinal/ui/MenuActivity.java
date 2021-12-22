package com.idnp.proyectofinal.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.models.User;

public class MenuActivity extends AppCompatActivity {
    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    PlacesFragment placesFragment = new PlacesFragment();
    int id_value;
    User x;
    DbUsers DbUsers;
    Bundle val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        DbUsers = new DbUsers(MenuActivity.this);
        id_value = getIntent().getIntExtra("idUser",0);
        x = DbUsers.verUsuario(id_value);
        val = new Bundle();
        val.putString("nombreU",x.getNombres());
        val.putString("correoU",x.getCorreo_electronico());
        val.putString("dniU",Integer.toString(x.getDni()));

        //Referencia al bottomNavigation
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(homeFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    loadFragment(homeFragment);
                    return true;
                case R.id.navigation_location:
                    loadFragment(mapFragment);
                    return true;
                case R.id.navigation_profile:
                    loadFragment(profileFragment);
                    return true;
                case R.id.navigation_places:
                    loadFragment(placesFragment);

            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(val);
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}