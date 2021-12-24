package com.idnp.proyectofinal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> resultado;

    public HomeViewModel(){
        resultado = new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void mostrarCifras(String data){
        //resultado.setValue();
    }

}