package com.idnp.proyectofinal.ui;

import androidx.databinding.DataBindingUtil;
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

import com.idnp.proyectofinal.databinding.ProfileFragmentBinding;
import com.idnp.proyectofinal.viewmodel.ProfileViewModel;
import com.idnp.proyectofinal.R;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle datosRecuperados = getArguments();

        ProfileFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        View view = binding.getRoot();

        binding.setProfileViewModel(new ProfileViewModel(this, datosRecuperados));
        return view;
    }
}