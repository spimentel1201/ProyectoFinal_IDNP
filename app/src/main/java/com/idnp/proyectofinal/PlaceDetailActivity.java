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
    TextView no,la,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        no = (TextView) findViewById(R.id.placename);
        la = (TextView) findViewById(R.id.latitude);
        lo = (TextView) findViewById(R.id.longitude);
        // Extraer lat. y lng.
        Intent intent = getIntent();
        Double lat = intent.getDoubleExtra("EXTRA_LATITUD", 0);
        Double longg = intent.getDoubleExtra("EXTRA_LONGITUD", 0);
        la.setText(Double.toString(lat));
        lo.setText(Double.toString(longg));
    }
}