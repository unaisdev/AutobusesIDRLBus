package com.example.seretide.autobusesidrlbus.models;

public class Autobus {
    private String nombre;
    private Boolean enUso;
    private GeoPoint punto;
    private String info;

    public Autobus(String nombre, GeoPoint punto, String info, Boolean enUso) {
        this.nombre = nombre;
        this.punto = punto;
        this.info = info;
        this.enUso = enUso;
    }

    public Boolean getEnUso() {
        return enUso;
    }

    public void setEnUso(Boolean enUso) {
        this.enUso = enUso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GeoPoint getPunto() {
        return punto;
    }

    public void setPunto(GeoPoint punto) {
        this.punto = punto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
