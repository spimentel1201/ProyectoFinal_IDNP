package com.idnp.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.idnp.proyectofinal.databinding.ActivityLoginBinding;
import com.idnp.proyectofinal.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private CheckBox opcionMantenerSesion;
    private Cursor test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoginViewModel(new LoginViewModel(this));
        binding.executePendingBindings();

        getSupportActionBar().hide();
        opcionMantenerSesion = (CheckBox) findViewById(R.id.checkMantenerSesion);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}