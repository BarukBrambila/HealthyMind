package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class perfilespecialista extends AppCompatActivity {
    TextView nom, espe;
    CircleImageView img;
    Button agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilespecialista);
        nom = (TextView)findViewById(R.id.nombretxt);
        espe = (TextView)findViewById(R.id.Especialidad);
        img=(CircleImageView) findViewById(R.id.image_perfil);
        agenda=(Button)findViewById(R.id.veragenda);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(perfilespecialista.this, ""+id, Toast.LENGTH_LONG).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            db.collection("users-especialista")
                    .whereEqualTo("rfc",id)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    String nombre = document.getString("nombres");
                                    String ape = document.getString("apellido");
                                    nom.setText(""+ nombre + " "+ape);
                                    String url = document.getString("foto");
                                    Glide.with(perfilespecialista.this).load(url).into(img);

                                }
                            }
                        }
                    });
            agenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(perfilespecialista.this, detalles_cita.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

    }
}