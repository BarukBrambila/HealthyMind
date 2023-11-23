package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pago_exitoso extends AppCompatActivity {

    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_exitoso);
        continuar =(Button) findViewById(R.id.continuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pago_exitoso.this, ini_paciente.class);
                startActivity(intent);
            }
        });
    }
}