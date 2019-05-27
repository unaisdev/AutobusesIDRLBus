package com.example.seretide.autobusesidrlbus;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnServer extends AsyncTask<Runnable, Void, Boolean>{

    private final int PORT_NUMBER = 7979;
    private final String NAME = "192.168.1.3";
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private CommServer commServer;

    @Override
    protected Boolean doInBackground(Runnable... runnables) {
        try {
            Socket socket = new Socket(NAME, PORT_NUMBER);

            System.out.println("SOCKET CONECTADO!");

            //Abrimos la tuberia de escucha
            dataInputStream = new DataInputStream(socket.getInputStream());
            //brObject = new ObjectInputStream(socket.getInputStream());

            //Abrimos la tuberia del envio, aunque probablemente no se use
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            this.commServer = new CommServer(dataInputStream, dataOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CommServer getCommServer(){
        return this.commServer;
    }
}
