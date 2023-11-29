package com.example.myapplication_bos_final;

import androidx.annotation.Nullable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BBDD extends SQLiteOpenHelper {
    public BBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PERSONAJESDND (" +
                " NOMBREJUGADOR TEXT ," +
                "NOMBREPERSONAJE TEXT ," +
                "CLASE TEXT,"+
                "FUERZA  INT," +
                "DESTREZA  INT," +
                "CONSTITUCION  INT," +
                "INTELIGENCIA  INT," +
                "SABIDURIA  INT," +
                "CARISMA  INT," +
                "HABILIDADES TEXT,"
                + "PRIMARY KEY (NOMBREJUGADOR, NOMBREPERSONAJE))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
