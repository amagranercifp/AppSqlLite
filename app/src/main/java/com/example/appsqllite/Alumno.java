package com.example.appsqllite;

import java.util.UUID;

public class Alumno {

    private String id;
    private  String nombre;
    private String email;
    private int avatar;

    private String password;
    private int sexo; //Hombre->0 // Femenino->1

    public Alumno(String nombre, String email, int avatar, String password,int sexo) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.sexo = sexo;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
