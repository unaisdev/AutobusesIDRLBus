package com.example.seretide.autobusesidrlbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.seretide.autobusesidrlbus.models.Autobus;
import com.example.seretide.autobusesidrlbus.models.GeoPoint;
import com.example.seretide.autobusesidrlbus.models.Linea;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Linea> lineasUp = new ArrayList<>();
    public static ArrayList<Autobus> autobusesUp = new ArrayList<>();
    private ConnServer connServer;
    private CommServer commServer;
    public static String[] lineas = {"A - 15", "H - 25"};

    private EditText matriculaBus;
    private Spinner spinnerLinea;
    private Button bLanzar;
    private Button bStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spinnerLinea = (Spinner) findViewById(R.id.linea);
        matriculaBus = (EditText) findViewById(R.id.matriculaBus);
        bLanzar = (Button) findViewById(R.id.botonLanzar);
        bStop = (Button) findViewById(R.id.botonStop);

        connServer = new ConnServer();
        connServer.execute();


        Linea lineaA15 = null;
        Linea lineaH25 = null;

        try {
            lineaA15 = new Linea("LineaA-15.json", "#d00000", this);
            lineaH25 = new Linea("LineaH-25.json", "#08124c", this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lineasUp.add(lineaA15);
        lineasUp.add(lineaH25);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lineas);
        spinnerLinea.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        bLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarAutobus();
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commServer.gethListen().removeCallbacksAndMessages(commServer.getRunnableListener());
            }
        });
    }

    private void lanzarAutobus(){
        System.out.println(spinnerLinea.getSelectedItem().toString());

        Autobus autobus = new Autobus(matriculaBus.getText().toString(), new GeoPoint(1,1), "", true);
        for(Linea linea: lineasUp){

            if(spinnerLinea.getSelectedItem().toString().equals(linea.getNombre())){
                System.out.println("HEY");

                commServer = connServer.getCommServer();

                commServer.initMovements(autobus, linea);
                bLanzar.setVisibility(View.INVISIBLE);
                bStop.setVisibility(View.VISIBLE);
            }
        }

    }


}
