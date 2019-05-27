package com.example.seretide.autobusesidrlbus.models;


import java.util.ArrayList;

public class Parada {
    private GeoPoint geoPoint;
    private String nombre;
    private ArrayList<String> lineasAsociadas;

    public Parada(GeoPoint geoPoint, String nombre, ArrayList<String> lineasAsociadas) {
        this.geoPoint = geoPoint;
        this.nombre = nombre;
        this.lineasAsociadas = lineasAsociadas;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getLineasAsociadas() {
        return lineasAsociadas;
    }

    public void setLineasAsociadas(ArrayList<String> lineasAsociadas) {
        this.lineasAsociadas = lineasAsociadas;
    }

    public String imprimirParada(){
        String paradaMsg = "posParada: " + nombre + "| " + geoPoint.getLatitude() + ", " + geoPoint.getLongitude() + "|";

        for(String lineaAsociada: lineasAsociadas)
            paradaMsg += lineaAsociada + ",";

        paradaMsg.substring(0, paradaMsg.length()-2);

        return paradaMsg;
    }
}
