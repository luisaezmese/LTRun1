package com.example.luisangel.ltrun.Usuarios;


public class Usuario {
    String id;
    String nombre;
    String email;
    String ciudad;
    String apodo;
    String fechanac;
    String marcaZap;
    String marcaReloj;
    String mejorTiempo;

    public Usuario() {


    }



    public Usuario(String id,String nombre, String apodo, String email, String ciudad,  String fechanac, String marcaZap, String marcaReloj, String mejorTiempo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.apodo = apodo;
        this.fechanac = fechanac;
        this.marcaZap = marcaZap;
        this.marcaReloj = marcaReloj;
        this.mejorTiempo = mejorTiempo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getMarcaZap() {
        return marcaZap;
    }

    public void setMarcaZap(String marcaZap) {
        this.marcaZap = marcaZap;
    }

    public String getMarcaReloj() {
        return marcaReloj;
    }

    public void setMarcaReloj(String marcaReloj) {
        this.marcaReloj = marcaReloj;
    }

    public String getMejorTiempo() {
        return mejorTiempo;
    }

    public void setMejorTiempo(String mejortiempo) {
        this.mejorTiempo = mejortiempo;
    }
}

