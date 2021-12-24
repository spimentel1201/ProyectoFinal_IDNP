package com.idnp.proyectofinal.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.databinding.ActivitySignUpBinding;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        binding.setSignViewModel(new SignUpViewModel(this));
        binding.executePendingBindings();

        getSupportActionBar().hide();
    }
}