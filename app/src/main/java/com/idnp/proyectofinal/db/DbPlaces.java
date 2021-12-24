package com.idnp.proyectofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.idnp.proyectofinal.models.User;
import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;
import java.util.List;

public class DbPlaces extends DbHelper {

    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<VaccinationPlace> x;

    public DbPlaces(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long agregarCentro(String nombre,Double latitud, Double longitud, String tipoVacuna) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombres", nombre);
            values.put("latitud", latitud);
            values.put("longitud", longitud);
            values.put("tipo_vacuna", tipoVacuna);

            id = db.insert(TABLE_CENTROS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<VaccinationPlace> mostrarCentros() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<VaccinationPlace> listaCentros = new ArrayList<>();
        VaccinationPlace centro;
        Cursor cursorCentros;

        cursorCentros = db.rawQuery("SELECT * FROM " + TABLE_CENTROS + " ORDER BY nombres ASC", null);

        if (cursorCentros.moveToFirst()) {
            do {
                centro = new VaccinationPlace();
                centro.setId(cursorCentros.getInt(0));
                centro.setPlaceName(cursorCentros.getString(1));
                centro.setCc(cursorCentros.getDouble(2),cursorCentros.getDouble(3));
                centro.setVaccineName(cursorCentros.getString(4));
                listaCentros.add(centro);
            } while (cursorCentros.moveToNext());
        }

        cursorCentros.close();

        return listaCentros;
    }

    public VaccinationPlace verCentro(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        VaccinationPlace centro = null;
        Cursor cursorCentros;

        cursorCentros = db.rawQuery("SELECT * FROM " + TABLE_CENTROS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorCentros.moveToFirst()) {
            centro = new VaccinationPlace();
            centro.setId(cursorCentros.getInt(0));
            centro.setPlaceName(cursorCentros.getString(1));
            centro.setCc(cursorCentros.getDouble(2),cursorCentros.getDouble(3));
            centro.setVaccineName(cursorCentros.getString(4));
        }

        cursorCentros.close();

        return centro;
    }

    /*public boolean editarCentro(int id, String nombres, String apellidos, String telefono, String dni, String correo_electronico, String contraseña) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET nombres = '"+ nombres+"', apellidos = '"+ apellidos+"', telefono = '"+ telefono+"', dni = '"+ dni+"', contraseña = '"+ contraseña+"' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }*/

    public boolean eliminarCentro(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CENTROS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}