package com.idnp.proyectofinal.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idnp.proyectofinal.adapters.ListVaccinePlacesAdapter;
import com.idnp.proyectofinal.models.VaccinationPlace;
import com.idnp.proyectofinal.viewmodel.PlacesViewModel;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.adapters.ListUsersAdapter;
import com.idnp.proyectofinal.db.DbUsers;
import com.idnp.proyectofinal.models.User;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {

    private PlacesViewModel mViewModel;
    private static final String TAG = "MyActivity";
    RecyclerView listaU;
    DbUsers dbUsers2;
    ArrayList<VaccinationPlace> listaPrueba;
    ListVaccinePlacesAdapter adapter;

    public static PlacesFragment newInstance() {
        return new PlacesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places_fragment, container, false);
        listaU= (RecyclerView) view.findViewById(R.id.listaUsuarios);
        listaU.setLayoutManager(new LinearLayoutManager(getContext()));
        listaPrueba = new ArrayList<>();
        listaPrueba = MapFragment.getList();
        adapter = new ListVaccinePlacesAdapter(listaPrueba);
        listaU.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlacesViewModel.class);
        // TODO: Use the ViewModel
    }

}