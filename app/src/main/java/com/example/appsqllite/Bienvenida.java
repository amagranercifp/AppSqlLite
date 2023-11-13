package com.example.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.appsqllite.db.AlumnosDB;

public class Bienvenida extends AppCompatActivity {

    TextView tvBienvenida,tvUsuario;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        tvBienvenida = (TextView) findViewById(R.id.tvBienvenida) ;
        tvUsuario = (TextView) findViewById(R.id.tvUsuario) ;

        Intent i = getIntent();

        String email = i.getStringExtra("email");

        //Con el email, consultamos BD y recuperamos los datos del alumno.

        AlumnosDB alumnoDb = new AlumnosDB(Bienvenida.this);

        Alumno a = alumnoDb.buscarAlumno(email);

        tvBienvenida.setText("Bienvenida");
        //Si el usuario es sexo masculino
        if(a.getSexo() == 0){
            tvBienvenida.setText("Bienvenido");
        }

        String nombre = a.getNombre();

        tvUsuario.setText(nombre);


        // Código para que tras 3 segundos el activity cree un intent de forma automática
        // para saltar a otro activity
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Bienvenida.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}