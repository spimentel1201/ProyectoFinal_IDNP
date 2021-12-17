package com.idnp.proyectofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.idnp.proyectofinal.models.VaccinationPlace;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "sistema.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_CENTROS = "t_centros";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "telefono INT NOT NULL," +
                "dni INT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "contraseña TEXT NOT NULL)");
        //sqLiteDatabase.execSQL("insert into TABLE_USUARIOS(nombres,apellidos,telefono,dni,correo_electronico,contraseña) values('admin','admin','000111222','00011100','admin@admin.com','admin@@')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CENTROS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT NOT NULL," +
                "latitud DOUBLE NOT NULL," +
                "longitud DOUBLE NOT NULL," +
                "tipo_vacuna TEXT NOT NULL)");
        sqLiteDatabase.execSQL("insert into TABLE_CENTROS(nombres,latitud,longitud,tipo_vacuna) values('Universidad Católica Santa Maria',-16.4060596,-71.5476231,'Pfizer')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CENTROS);
        onCreate(sqLiteDatabase);

    }
}
