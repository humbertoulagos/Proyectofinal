package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Chronometer;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class tiempo extends AppCompatActivity {

    Chronometer crono;
    Button iniciar,terminar;
    TextView funcion;


    long Time=0;
    String currentTime="";
    Boolean activity=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);






    }
}