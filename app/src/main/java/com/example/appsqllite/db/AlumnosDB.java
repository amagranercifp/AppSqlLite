package com.example.appsqllite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appsqllite.Alumno;

import java.util.ArrayList;

public class AlumnosDB extends AlumnoDbHelper {

    Context context;

    SQLiteDatabase db;
    AlumnoDbHelper dbHelper;

    ArrayList<Alumno> listAlumnos;

    public AlumnosDB(Context context) {

        super(context);
        this.context = context;
    }

    public long insertarAlumno(String nombre, String email,int avatar,String password,int sexo){
        long id=0;

        try{
            dbHelper = new AlumnoDbHelper(context);
            db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("email",email);
            values.put("avatar",avatar);
            values.put("password",password);
            values.put("sexo",sexo);

            id = db.insert(TABLE_ALUMNOS,null,values);

        }catch(Exception ex){
            ex.toString();
        }


        return id;

    }

    public Alumno buscarAlumno(String email){
        //metodo que averigua si el usuario introducido en el formulario
        //existe en la bd

        listAlumnos = selectUsuarios();

        for(Alumno a:listAlumnos){
            if(a.getEmail().equals(email)){
                return a;
            }
        }
        return null;
    }

    public ArrayList<Alumno> selectUsuarios(){
        ArrayList<Alumno> lista=new ArrayList<Alumno>();

        try{
            dbHelper = new AlumnoDbHelper(context);
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from " +TABLE_ALUMNOS,null);

            //Creamos el arrayList a partir de cada fila que devuelve la consulta de datos.
            if(cursor != null && cursor.moveToFirst()){
                do{

                    String nombre=cursor.getString(1);
                    String email = cursor.getString(2);
                    int avatar =  cursor.getInt(3);
                    String password = cursor.getString(4);
                    int sexo = cursor.getInt(5);
                    Alumno a = new Alumno(nombre,email,avatar,password,sexo);
                    lista.add(a);
                }while(cursor.moveToNext());
            }

            return lista;
        }catch(Exception ex){
            ex.toString();
            return null;
        }


    }

    public int logIn(String email, String password){

        //Devuelve 0 si el usuario no lo encuentra
        //Devuelve 1 si el usuario esta en la BD

        try{
            dbHelper = new AlumnoDbHelper(context);
            db = dbHelper.getWritableDatabase();
            String query = "select * from " +TABLE_ALUMNOS+" where email= '"+email+"'";
            Log.d("AlumnosDB",query);
            Cursor cursor = db.rawQuery(query,null);

            //Creamos el arrayList a partir de cada fila que devuelve la consulta de datos.
            if(cursor != null && cursor.moveToFirst()){
                do{
                    String colEmail = cursor.getString(2);
                    String colPassword = cursor.getString(4);
                    if(colEmail.equals(email) && colPassword.equals(password)){
                        return 1;
                    }
                }while(cursor.moveToNext());
            }
            return 0;
        }catch(Exception ex){
            ex.toString();
            return 0;
        }

    }

}
