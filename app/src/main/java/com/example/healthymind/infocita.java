package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class infocita extends AppCompatActivity {
    TextView especialista, fecha, hora;
    Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infocita);
        especialista=(TextView) findViewById(R.id.textespe);
        fecha = (TextView)findViewById(R.id.textfecha);
        hora = (TextView)findViewById(R.id.texthr);
        pagar = (Button)findViewById(R.id.btnpagar);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombres");
        String ape = intent.getStringExtra("apellido");
        String fech = intent.getStringExtra("fecha");
        String hr = intent.getStringExtra("hora");
        String id = intent.getStringExtra("id");
        String curp_espe = intent.getStringExtra("curp_espe");
        String date = intent.getStringExtra("date");
        especialista.setText("" + nombre + " " + ape);
        fecha.setText(""+fech);
        hora.setText(""+hr);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(infocita.this, pago.class);
                intent.putExtra("fecha",fech);
                intent.putExtra("hora", hr);
                intent.putExtra("id",id);
                intent.putExtra("curp_espe", curp_espe);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

    }
}