package com.example.healthymind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    Button login;
    EditText mail, pass;
    String email, passW;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnIngresar);
        mail = (EditText) findViewById(R.id.setEmail);
        pass = (EditText) findViewById(R.id.setContrasena);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FirebaseDatabase database = FirebaseDatabase.getInstance("https://healty-psycho-default-rtdb.firebaseio.com/");

                //email = mail.getText().toString();
                //passW = pass.getText().toString();
                String correo = mail.getText().toString();
                String contra = pass.getText().toString();
                ingresar(correo, contra);
                //startActivity(new Intent(login.this, ini_espe.class));

            }
        });


    }

    private void ingresar(String correo, String contra) {
        mAuth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    i = new Intent(getApplicationContext(), ini_espe.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(login.this, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}

