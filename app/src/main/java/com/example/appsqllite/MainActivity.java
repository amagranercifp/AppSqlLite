package com.example.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appsqllite.db.AlumnoDbHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistrarse, btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        AlumnoDbHelper dbHelper = new AlumnoDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db!=null){
            Toast.makeText(this,"BD creada correctamente",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"BD No se ha creado",Toast.LENGTH_SHORT).show();
        }

        btnRegistrarse.setOnClickListener(this);
        btnIniciarSesion.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegistrarse){
            Intent i = new Intent(this, RegistrarUsuarios.class);
            startActivity(i);
        }
        if(v.getId() == R.id.btnIniciarSesion){
            Intent i = new Intent(this, LogInActivity.class);
            startActivity(i);
        }
    }
}