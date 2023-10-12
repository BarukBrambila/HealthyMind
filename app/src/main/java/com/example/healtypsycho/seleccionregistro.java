package com.example.healtypsycho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class seleccionregistro extends AppCompatActivity {
    Button paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionregistro);
        paciente =(Button) findViewById(R.id.buttonpaciente);

        paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seleccionregistro.this, registropaciente.class));

            }
        });

    }
}