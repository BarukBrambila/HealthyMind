package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class perfil_espe1 extends AppCompatActivity {
    TextView nom, email1;
    CircleImageView img;
    Button cerrar, editar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_espe1);
        nom=(TextView)findViewById(R.id.txtnombre);
        email1 =(TextView)findViewById(R.id.txtemail);
        cerrar=(Button)findViewById(R.id.cerrarsesionbtn_espe);
        editar=(Button)findViewById(R.id.btneditar);
        img =(CircleImageView)findViewById(R.id.imageView);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottom_date);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            db.collection("users-especialista")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String nombre = document.getString("nombres");
                                    String ape = document.getString("apellido");
                                    nom.setText(""+nombre+" "+ape);
                                    String correo = document.getString("email");
                                    email1.setText(""+correo);
                                    String url = document.getString("foto");

                                    Glide.with(perfil_espe1.this).load(url).into(img);
                                    editar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String curp = document.getId();
                                            i = new Intent(perfil_espe1.this, editarperfil_espe.class);
                                            i.putExtra("id",curp);
                                            startActivity(i);
                                        }
                                    });



                                }
                            } else {
                                //Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(perfil_espe1.this, bienvenidan.class));
            }
        });



        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_perfil:
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), ini_espe.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_date:
                    startActivity(new Intent(getApplicationContext(), citasespecialista.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

            }
            return false;
        });

        Button button = findViewById(R.id.TYC2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(perfil_espe1.this,pdfActivity.class);
                startActivity(intent);



            }
        });
    }
}