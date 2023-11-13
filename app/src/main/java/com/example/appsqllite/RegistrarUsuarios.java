package com.example.appsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appsqllite.db.AlumnosDB;

public class RegistrarUsuarios extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre,etEmail,etPassword;
    Button btnRegistrar;

    RadioGroup grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        grupo = (RadioGroup) findViewById(R.id.rgSexo);

        btnRegistrar.setOnClickListener(this);



    }

    public int comprobarSexo(View view) {

        //hombre -> 0
        //mujer -> 1

        if (grupo.getCheckedRadioButtonId() == R.id.rbMasculino) {

            Toast.makeText(this, "El usuario ha elegido sexo masculino", Toast.LENGTH_LONG).show();
            return 0;
        }
        else{
            Toast.makeText(this, "El usuario ha elegido sexo femenino", Toast.LENGTH_LONG).show();
            return 1;
        }
    }

    // Handles the row being being clicked
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnRegistrar){
            //Llamamos funcion de añadir items

            String nombre = etNombre.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            int sexo = comprobarSexo(view);

            AlumnosDB alumnoDb = new AlumnosDB(RegistrarUsuarios.this);


            if(alumnoDb.buscarAlumno(email)==null){
                //El alumno aun no esta registrado por lo que pasamos a insertarlo
                // El metodo insertarAlumno devuelve tipo long con el ID del registro añadido
                long id = alumnoDb.insertarAlumno(nombre,email,randomAvatar(),password,sexo);

                if(id > 0){
                    Toast.makeText(RegistrarUsuarios.this,"REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    Log.d("REGISTRARUSUARIOS","REGISTRO GUARDADO");
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(RegistrarUsuarios.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    Log.d("REGISTRARUSUARIOS","ERROR AL GUARDAR REGISTRO");
                }
            }
            else{
                Toast.makeText(RegistrarUsuarios.this,"EL USUARIO YA ESTA REGISTRADO", Toast.LENGTH_LONG).show();
                Log.d("REGISTRARUSUARIOS","EL USUARIO YA ESTA REGISTRADO");
            }



        }
    }

    private void limpiar(){
        etNombre.setText("");
        etEmail.setText("");
    }

    public int randomAvatar(){

        int[] avatares = new int[]{R.drawable.avatar1_chico,R.drawable.avatar2_chica,R.drawable.avatar3_chico,R.drawable.avatar4_chica};
        int indiceAleatorio = (int) (Math.random() * avatares.length) + 1;
        return avatares[indiceAleatorio%avatares.length];
    }
}