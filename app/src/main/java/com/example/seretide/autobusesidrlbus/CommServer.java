package com.example.seretide.autobusesidrlbus;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.seretide.autobusesidrlbus.models.Autobus;
import com.example.seretide.autobusesidrlbus.models.GeoPoint;
import com.example.seretide.autobusesidrlbus.models.Linea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// CLASE DE COMUNICACION CON EL SERVIDOR

public class CommServer {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Autobus autobus;
    private Linea linea;
    private Handler hListen;

    private Runnable runnableListener = new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("MANDANDO A SERVIDOR");

                for (GeoPoint punto : linea.puntosRuta) {
                    dataOutputStream.writeUTF("movBus:" + autobus.getNombre() + "| " + punto.getLatitude() + ", " + punto.getLongitude());
                    Thread.sleep(500);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public CommServer(DataInputStream dataInputStream, DataOutputStream dataOutputStream){
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public void initMovements(Autobus autobus, Linea linea){
        this.autobus = autobus;
        this.linea = linea;

        //Necesitamos hacer uso de hTread para crear un proceso, y asignarle los runnables que debe ejecutar
        HandlerThread hTListen = new HandlerThread("listening");
        hTListen.start();

        //Metemos en la cola el Runnable
        hListen = new Handler(hTListen.getLooper());
        hListen.post(runnableListener);
    }

    public Handler gethListen() {
        return hListen;
    }

    public void sethListen(Handler hListen) {
        this.hListen = hListen;
    }

    public Runnable getRunnableListener() {
        return runnableListener;
    }

    public void setRunnableListener(Runnable runnableListener) {
        this.runnableListener = runnableListener;
    }
}
