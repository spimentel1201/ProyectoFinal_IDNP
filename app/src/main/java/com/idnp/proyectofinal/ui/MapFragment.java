package com.idnp.proyectofinal.ui;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idnp.proyectofinal.viewmodel.MapViewModel;
import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,GoogleMap.OnMarkerClickListener{

    private MapViewModel mViewModel;
    private Marker markerPais;
    GoogleMap map;
    public static ArrayList<VaccinationPlace> places;
    ArrayList<VaccinationPlace> fb;
    LatLng inicio;
    FirebaseDatabase firebaseDatabase;  //Firebase
    DatabaseReference databaseReference;    //Firebase
    private static final String TAG = "MyActivity";

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inicializarFirebase();
        fb = new ArrayList<>();
        getVaccinationPlacesFirebase();
        //Prueba Marcadores en Mapa
        places = getPlacesx();
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
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT)
                .show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
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

    public ArrayList<VaccinationPlace> getPlacesx(){
        ArrayList<VaccinationPlace> miLista = new ArrayList<>();
        VaccinationPlace a1 = new VaccinationPlace(1,"Universidad Católica Santa Maria","Pfizer",-16.4060596,-71.5476231);
        VaccinationPlace a2 = new VaccinationPlace(2,"Palacio del Deporte - Arequipa","AstraZeneca",-16.4330838,-71.5307416);
        VaccinationPlace a3 = new VaccinationPlace(3,"LIGA PERUANA DE LUCHA CONTRA EL CANCER","Pfizer",-16.4090474,-71.537451);
        VaccinationPlace a4 = new VaccinationPlace(4,"HOSPITAL REGIONAL PNP AREQUIPA","Sinopharm",-16.3426764,-71.5427212);
        VaccinationPlace a5 = new VaccinationPlace(5,"CLINICA AREQUIPA S.A.","Sinopharm",-16.39230167,-71.53996667);
        VaccinationPlace a6 = new VaccinationPlace(6,"LABORATORIO REFERENCIAL REGIONAL AREQUIPA","Pfizer",-16.414764,-71.529471);
        VaccinationPlace a7 = new VaccinationPlace(7,"Colegio Juana Cervantes","AstraZeneca",-16.40536804,-71.54437967);
        VaccinationPlace a8 = new VaccinationPlace(8,"Estadio UNSA","Sinopharm",-16.4070129,-71.520449);
        VaccinationPlace a9 = new VaccinationPlace(9,"Plaza Mayta Capac","Pfizer",-16.3943548,-71.5243219);
        VaccinationPlace a10 = new VaccinationPlace(10,"Colegio Guillermo Mercado Barroso","Pfizer",-16.3725036,-71.5130305);
        VaccinationPlace a11 = new VaccinationPlace(11,"Hospital Alto Inclán","Pfizer",-17.0186674,-72.0024083);
        VaccinationPlace a12 = new VaccinationPlace(12, "Centro Vacunacion Prueba","Pfizer",-17.0173742,-72.0050291);
        miLista.add(a1);
        miLista.add(a2);
        miLista.add(a3);
        miLista.add(a4);
        miLista.add(a5);
        miLista.add(a6);
        miLista.add(a7);
        miLista.add(a8);
        miLista.add(a9);
        miLista.add(a10);
        miLista.add(a11);
        miLista.add(a12);
        return miLista;
    }
    public void inicializarFirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public void cargarFirebase(){
        for (int i = 0; i < places.size(); i++) {
            databaseReference.child("VaccinationPlaces").child(Integer.toString(places.get(i).getId())).setValue(places.get(i));
        }
    }

    public void getVaccinationPlacesFirebase() {
        ArrayList<VaccinationPlace> fb2 = new ArrayList<>();
        databaseReference.child("VaccinationPlaces").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //fb.clear();
                if(snapshot.exists()) {
                    for (DataSnapshot objSnaptshot : snapshot.getChildren()) {
                        VaccinationPlace vp = objSnaptshot.getValue(VaccinationPlace.class);
                        fb2.add(vp);
                        Log.i(TAG,vp.getVaccineName());
                    }

                }else{
                    Toast.makeText(getContext(),"Snapshot inválido",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static ArrayList<VaccinationPlace> getList(){
        return places;
    }
}