package com.example.healtypsycho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bienvenidan extends AppCompatActivity {
    Button registrarse, inisesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenidan);
        inisesion =(Button) findViewById(R.id.inisesion);
        registrarse = (Button) findViewById(R.id.regist);

        inisesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bienvenidan.this, seleccioninisesion.class));

            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bienvenidan.this, seleccionregistro.class));
            }
        });
    }
}