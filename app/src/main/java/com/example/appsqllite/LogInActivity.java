package com.example.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsqllite.db.AlumnosDB;

public class LogInActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText etEmail,etPassword;
    Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnIniciarSesion){

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            AlumnosDB alumnoDb = new AlumnosDB(LogInActivity.this);

            if(alumnoDb.logIn(email,password) == 0){
                Toast.makeText(LogInActivity.this,"USUARIO NO REGISTRADO", Toast.LENGTH_LONG).show();
                Log.d("LOGINACTIVITY","USUARIO NO REGISTRADO");

            }
            else{
                Toast.makeText(LogInActivity.this,"LOG IN CORRECTO", Toast.LENGTH_LONG).show();
                Log.d("LOGINACTIVITY","LOG IN CORRECTO");
                Intent i = new Intent(this, Bienvenida.class);
                //pasamos el email para que en el siguiente activity recupere toda su info
                i.putExtra("email",email);
                startActivity(i);

            }


        }
    }
}