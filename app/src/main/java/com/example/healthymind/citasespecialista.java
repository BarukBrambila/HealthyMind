package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class citasespecialista extends AppCompatActivity {
    DatePicker calendario;
    Button confirmar;
    String fecha;
    CheckBox hr1, hr2, hr3, hr4, hr5, hr6, hr7;
    FirebaseFirestore mFirestore;

    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citasespecialista);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottom_date);
        calendario = (DatePicker) findViewById(R.id.calendario);
        confirmar = (Button) findViewById(R.id.btnconfirmar);
        hr1=(CheckBox)findViewById(R.id.hr1);
        hr2=(CheckBox)findViewById(R.id.hr2);
        hr3=(CheckBox)findViewById(R.id.hr3);
        hr4=(CheckBox)findViewById(R.id.hr4);
        hr5=(CheckBox)findViewById(R.id.hr5);
        hr6=(CheckBox)findViewById(R.id.hr6);
        hr7=(CheckBox)findViewById(R.id.hr7);
        calendario.setMinDate(System.currentTimeMillis() - 1000);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String id = document.getId();
                                            hr1=(CheckBox)findViewById(R.id.hr1);
                                            String dayOfMonth = String.valueOf(calendario.getDayOfMonth());
                                            String mes = String.valueOf(calendario.getMonth()+1);
                                            String año = String.valueOf(calendario.getYear());
                                            fecha = dayOfMonth + mes + año;
                                            if (hr1.isChecked()){
                                                String n = "1";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","9:00");
                                                map.put("hora_final", "9:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "1";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr2.isChecked()){
                                                String n = "2";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","10:00");
                                                map.put("hora_final", "10:50");
                                                agendar(map, id, fecha, n);
                                            } else{
                                                String n = "2";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr3.isChecked()){
                                                String n = "3";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","11:00");
                                                map.put("hora_final", "11:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "3";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr4.isChecked()){
                                                String n = "4";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","16:00");
                                                map.put("hora_final", "16:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "4";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr5.isChecked()){
                                                String n = "5";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","17:00");
                                                map.put("hora_final", "17:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "5";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr6.isChecked()){
                                                String n = "6";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","18:00");
                                                map.put("hora_final", "18:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "6";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }
                                            if (hr7.isChecked()){
                                                String n = "7";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado", "disponible");
                                                map.put("hora_inicial","19:00");
                                                map.put("hora_final", "19:50");
                                                agendar(map, id, fecha, n);
                                            }else{
                                                String n = "7";
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("estado","");
                                                agendar(map, id, fecha, n);
                                            }

                                            //Toast.makeText(citasespecialista.this, ""+fecha, Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        //Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }

                                private void agendar(Map<String, Object> map, String id, String fecha, String n) {


                                    Map<String, Object> map1  = new HashMap<>();
                                    db.collection("users-especialista/"+id+"/agenda").document(fecha).set(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            db.collection("users-especialista/"+id+"/agenda/"+fecha+"/horarios").document(n).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(citasespecialista.this, "si", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(citasespecialista.this,"FALLO INSERCION DE DATOS",Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                        }
                                    });

                                }
                            });
                }
                Toast.makeText(citasespecialista.this, "Horarios habilitados exitosamente", Toast.LENGTH_LONG).show();
                startActivity(new Intent(citasespecialista.this, citasespecialista.class));
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_date:
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), ini_espe.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_perfil:
                    startActivity(new Intent(getApplicationContext(), perfil_espe1.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}