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

        //LatLng inicio = new LatLng(-16.3988006,-71.5390964);
        //Inicializamos la View
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        //Inicializamos el fragment del mapa
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);


        // Eventos clickear info marcador
        //map.setOnMarkerClickListener(this);

        //Prueba Marcadores
        /*for(int i=0;i<places.size();i++){
            LatLng a = new LatLng(places.get(i).getLat(),places.get(i).getLong());
            String title = places.get(i).getPlaceName();
            map.addMarker(new MarkerOptions().position(a).title(title).snippet("My Snippet"+"\n"+"1st Line Text"+"\n"+"2nd Line Text"+"\n"+"3rd Line Text").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }*/


        //Sincronizamos para mostrar el mapa
        /*
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(@NonNull LatLng latLng) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);
                            markerOptions.title("Estás aquí");
                            googleMap.clear();
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    latLng,10
                            ));
                            googleMap.addMarker(markerOptions);
                        }
                    });
                }
        });*/
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }
    /*public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng inicio = new LatLng(-16.3988006, -71.5390964);
        map.moveCamera(CameraUpdateFactory.newLatLng(inicio));
    }*/

    /*Método que carga el mapa en el fragment, añade los marcadores y mueve la posicion de la cámara*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);
        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        LatLng inicio = new LatLng(-16.3988006,-71.5390964);
        map.animateCamera(CameraUpdateFactory.zoomTo(20.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(inicio));
        for (int i = 0; i < places.size(); i++) {
            LatLng a = new LatLng(places.get(i).getLat(),places.get(i).getLong());
            String title = places.get(i).getPlaceName();
            String vaccineType = places.get(i).getVaccineName();

            // below line is use to add marker to each location of our array list.
            MarkerOptions mko = new MarkerOptions();
            mko.position(a).title(title);
            mko.position(a).snippet(vaccineType);
            map.addMarker(mko);

            // below lin is use to zoom our camera on map.
            //map.animateCamera(CameraUpdateFactory.zoomTo(20.0f));

            // below line is use to move our camera to the specific location.
            //map.moveCamera(CameraUpdateFactory.newLatLng(a));
        }
        /*
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(TAG,"latitude: "+ marker.getPosition().latitude);
                Log.d("latitude ", "latitud: " + marker.getPosition().latitude);
                Intent intent = new Intent(getActivity(), PlaceDetailActivity.class);
                intent.putExtra("PLACE_NAME", marker.getTitle());
                intent.putExtra("VACCINE_TYPE", marker.getSnippet());
                intent.putExtra("EXTRA_LATITUD", marker.getPosition().latitude);
                intent.putExtra("EXTRA_LONGITUD", marker.getPosition().longitude);
                startActivity(intent);

                return false;
            }
        });*/
    }
    /* Falta probar pasar a otro fragment para mostrar details*/
    /*No funcional - en prueba*/
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

    public void prueba(View view){
        Intent irdetalles = new Intent(getActivity(),PlaceDetailActivity.class);
        startActivity(irdetalles);
    }
}