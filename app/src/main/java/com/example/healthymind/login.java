package com.example.healthymind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    Button backregistro, login;
    EditText mail, pass;
    String email, passW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backregistro =(Button) findViewById(R.id.btnBackregister);
        login = (Button) findViewById(R.id.btnIngresar);
        mail = (EditText) findViewById(R.id.setEmail);
        pass = (EditText) findViewById(R.id.setContrasena);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://healty-psycho-default-rtdb.firebaseio.com/");


                email = mail.getText().toString();
                passW = pass.getText().toString();


            }
        });

        backregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, seleccionregistro.class));
            }
        });
    }
}

