package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_paciente extends AppCompatActivity {
    Button backregistro, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_paciente);
        backregistro =(Button) findViewById(R.id.btnBackregister);
        login = (Button) findViewById(R.id.btnIngresar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_paciente.this, ini_paciente.class));
            }
        });
    }
}