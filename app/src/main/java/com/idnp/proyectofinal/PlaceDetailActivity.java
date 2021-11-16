package com.idnp.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;

public class PlaceDetailActivity extends AppCompatActivity {
    ArrayList<VaccinationPlace> d;
    TextView no,la,lo,vac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        getSupportActionBar().hide();
        no = (TextView) findViewById(R.id.placename);
        la = (TextView) findViewById(R.id.latitude);
        lo = (TextView) findViewById(R.id.longitude);
        vac = (TextView) findViewById(R.id.vaccineName);
        // Extraer lat. y lng.
        Intent intent = getIntent();
        String placename = intent.getStringExtra("PLACE_NAME");
        String vaccinename = intent.getStringExtra("VACCINE_TYPE");
        Double lat = intent.getDoubleExtra("EXTRA_LATITUD", 0);
        Double longg = intent.getDoubleExtra("EXTRA_LONGITUD", 0);
        no.setText(placename);
        la.setText(Double.toString(lat));
        lo.setText(Double.toString(longg));
        vac.setText(vaccinename);
    }
}