package com.example.healthymind;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class Activity_CreatePost extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore mFirestore;
    EditText titulo, texto;
    Button publicar;
    //Button uploadfoto;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);


        mFirestore = FirebaseFirestore.getInstance();
        publicar = (Button)findViewById(R.id.btnenviar);
        titulo = (EditText) findViewById(R.id.tituloET);
        texto =(EditText) findViewById(R.id.textoET);

        back = (ImageView) findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titulo.getText().toString().trim();
                String text = texto.getText().toString().trim();

                if(title.equals("") || text.equals("")){
                    Toast.makeText(Activity_CreatePost.this, "Llena los campos correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> post = new HashMap<>();
                    post.put("titulo", title);
                    post.put("texto", text);

                    // Add a new document with a generated ID
                    db.collection("publicaciones")
                            .add(post)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Activity_CreatePost.this, "Exitoso", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent( Activity_CreatePost.this, ini_espe.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Activity_CreatePost.this, " NO exitoso", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });






    }
}