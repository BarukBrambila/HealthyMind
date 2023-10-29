package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class seleccioniniciarsesion extends AppCompatActivity {
    Button btnpaciente, btnespecialista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccioniniciarsesion);
        btnpaciente = (Button) findViewById(R.id.buttonpaciente);
        btnespecialista =(Button) findViewById(R.id.buttonesp);
        btnpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seleccioniniciarsesion.this, login_paciente.class));
            }
        });
        btnespecialista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seleccioniniciarsesion.this, login.class));
            }
        });
    }
}