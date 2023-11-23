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
    Button button_back0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_infocita);
        especialista=(TextView) findViewById(R.id.textespe);
        fecha = (TextView)findViewById(R.id.textfecha);
        hora = (TextView)findViewById(R.id.texthr);
        button_back0 = (Button)findViewById(R.id.button_back0);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombres");
        String ape = intent.getStringExtra("apellido");
        String fech = intent.getStringExtra("fecha");
        String hr = intent.getStringExtra("hora");
        especialista.setText("" + nombre + " " + ape);
        fecha.setText(""+fech);
        hora.setText(""+hr);

        button_back0.setOnClickListener(new View.OnClickListener() {  //////////////////
            @Override
            public void onClick(View v) {
                Intent button_back0 = new Intent(infocita.this, detalles_cita.class);
                startActivity(button_back0);
            }
        });
    }

}