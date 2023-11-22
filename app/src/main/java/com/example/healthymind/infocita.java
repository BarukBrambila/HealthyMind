package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class infocita extends AppCompatActivity {
    TextView especialista, fecha, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infocita);
        especialista=(TextView) findViewById(R.id.textespe);
        fecha = (TextView)findViewById(R.id.textfecha);
        hora = (TextView)findViewById(R.id.texthr);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombres");
        String ape = intent.getStringExtra("apellido");
        String fech = intent.getStringExtra("fecha");
        String hr = intent.getStringExtra("hora");
        especialista.setText("" + nombre + " " + ape);
        fecha.setText(""+fech);
        hora.setText(""+hr);
    }
}