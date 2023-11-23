package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class login_paciente extends AppCompatActivity {
    Button backregistro, login;
    FirebaseFirestore mFirestore;
    EditText email, password;
    FirebaseAuth mAuth;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_paciente);
        backregistro =(Button) findViewById(R.id.btnBackregister);
        login = (Button) findViewById(R.id.btnIngresar);
        email =(EditText)findViewById(R.id.setEmail);
        password=(EditText)findViewById(R.id.setpasword);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = email.getText().toString();
                String pass = password.getText().toString();
                ingresar(correo, pass);
                // startActivity(new Intent(login_paciente.this, ini_paciente.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void ingresar(String correo, String pass) {
        mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    i = new Intent(getApplicationContext(), ini_paciente.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(login_paciente.this, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}