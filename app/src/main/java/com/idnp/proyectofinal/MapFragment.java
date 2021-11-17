package com.idnp.proyectofinal;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private MapViewModel mViewModel;
    private Marker markerPais;
    GoogleMap map;
    ArrayList<VaccinationPlace> places;
    LatLng inicio;
    private static final String TAG = "MyActivity";

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Prueba Marcadores en Mapa
        places = new ArrayList<>();
        VaccinationPlace a1 = new VaccinationPlace("Universidad Católica Santa Maria","Pfizer",-16.4060596,-71.5476231);
        VaccinationPlace a2 = new VaccinationPlace("Palacio del Deporte - Arequipa","AstraZeneca",-16.4330838,-71.5307416);
        VaccinationPlace a3 = new VaccinationPlace("LIGA PERUANA DE LUCHA CONTRA EL CANCER","Pfizer",-16.4090474,-71.537451);
        VaccinationPlace a4 = new VaccinationPlace("HOSPITAL REGIONAL PNP AREQUIPA","Sinopharm",-16.3426764,-71.5427212);
        VaccinationPlace a5 = new VaccinationPlace("CLINICA AREQUIPA S.A.","Sinopharm",-16.39230167,-71.53996667);
        VaccinationPlace a6 = new VaccinationPlace("LABORATORIO REFERENCIAL REGIONAL AREQUIPA","Pfizer",-16.414764,-71.529471);
        VaccinationPlace a7 = new VaccinationPlace("Colegio Juana Cervantes","AstraZeneca",-16.40536804,-71.54437967);
        VaccinationPlace a8 = new VaccinationPlace("Estadio UNSA","Sinopharm",-16.4070129,-71.520449);
        VaccinationPlace a9 = new VaccinationPlace("Plaza Mayta Capac","Pfizer",-16.3943548,-71.5243219);
        VaccinationPlace a10 = new VaccinationPlace("Colegio Guillermo Mercado Barroso","Pfizer",-16.3725036,-71.5130305);
        places.add(a1);
        places.add(a2);
        places.add(a3);
        places.add(a4);
        places.add(a5);
        places.add(a6);
        places.add(a7);
        places.add(a8);
        places.add(a9);
        places.add(a10);

        //Inicializamos la View
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        //Inicializamos el fragment del mapa
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }

    /*Método que carga el mapa en el fragment, añade los marcadores y mueve la posicion de la cámara*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);
        LatLng inicio = new LatLng(-16.3988006,-71.5390964);
        map.animateCamera(CameraUpdateFactory.zoomTo(20.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(inicio));
        /*Agregar los centros de vacunación al mapa haciendo uso de marcadores
            los cuales tienen información del lugar*/
        for (int i = 0; i < places.size(); i++) {
            LatLng a = new LatLng(places.get(i).getLat(),places.get(i).getLong());
            String title = places.get(i).getPlaceName();
            String vaccineType = places.get(i).getVaccineName();
            MarkerOptions mko = new MarkerOptions();
            mko.position(a).title(title);
            mko.position(a).snippet(vaccineType);
            map.addMarker(mko);
        }
    }
    /* Métood para pasar al activity para mostrar details*/
    public boolean onMarkerClick(@NonNull Marker marker) {

            marker.showInfoWindow();
            //Log.i(TAG,"nombr: "+ places.get(3).getPlaceName());
            Intent intent = new Intent(getActivity(), PlaceDetailActivity.class);
            intent.putExtra("PLACE_NAME", marker.getTitle());
            intent.putExtra("VACCINE_TYPE", marker.getSnippet());
            intent.putExtra("EXTRA_LATITUD", marker.getPosition().latitude);
            intent.putExtra("EXTRA_LONGITUD", marker.getPosition().longitude);
            startActivity(intent);

        return false;
    }
}