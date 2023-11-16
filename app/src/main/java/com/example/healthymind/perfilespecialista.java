package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class perfilespecialista extends AppCompatActivity {
    TextView nom, espe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilespecialista);
        nom = (TextView)findViewById(R.id.nombretxt);
        espe = (TextView)findViewById(R.id.Especialidad);
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
                                    nom.setText(""+nombre);
                                    String especialidad = document.getString("especialidad");
                                    espe.setText(""+especialidad);



                                }
                            } else {
                                //Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottom_date);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_perfil:
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), ini_espe.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_notis:
                    startActivity(new Intent(getApplicationContext(), notificaciones.class));
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
    }
}