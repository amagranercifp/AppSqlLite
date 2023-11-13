package com.example.appsqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlumnoDbHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Alumnos.db";

    public static final String TABLE_ALUMNOS ="t_alumnos";

    public AlumnoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Comandos SQL de creación de nuestra tabla
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNOS + " ("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre" + " TEXT NOT NULL,"
                + "email" + " TEXT NOT NULL,"
                + "avatar" + " TEXT NOT NULL,"
                + "password" + " TEXT NOT NULL,"
                + "sexo" + " TEXT NOT NULL,"
                + "UNIQUE (" + "id" + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
        db.execSQL("DROP TABLE " + TABLE_ALUMNOS);
        onCreate(db);
    }


}
