package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class detalles_cita extends AppCompatActivity {
    DatePicker calendario;
    TextView nom , especialidad;
    CircleImageView img;
    String fecha, fecha1;
    String hora1, hora2, hora3, hora4, hora5, hora6, hora7;
    CheckBox hr1, hr2, hr3, hr4, hr5, hr6, hr7;

    Button confirmar, agendar, button_back1;
    final Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cita);
        calendario =(DatePicker) findViewById(R.id.calendario);
        nom= (TextView)findViewById(R.id.text_title2);
        button_back1 = (Button)findViewById(R.id.button_back1);
        especialidad=(TextView)findViewById(R.id.txtespe);
        img =(CircleImageView)findViewById(R.id.image_perfil);
        confirmar=(Button)findViewById(R.id.confirmarbtn);
        agendar=(Button)findViewById(R.id.agendar);
        hr1=(CheckBox)findViewById(R.id.hr1);
        hr2=(CheckBox)findViewById(R.id.hr2);
        hr3=(CheckBox)findViewById(R.id.hr3);
        hr4=(CheckBox)findViewById(R.id.hr4);
        hr5=(CheckBox)findViewById(R.id.hr5);
        hr6=(CheckBox)findViewById(R.id.hr6);
        hr7=(CheckBox)findViewById(R.id.hr7);
        calendario.setMinDate(System.currentTimeMillis() - 1000);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            db.collection("users-especialista")
                    .whereEqualTo("rfc",id)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String nombre = document.getString("nombres");
                                    String ape = document.getString("apellido");
                                    String curp = document.getId();
                                    nom.setText("" + nombre + " " + ape);
                                    String url = document.getString("foto");
                                    String espe = document.getString("especialidad");
                                    especialidad.setText(""+espe);
                                    Glide.with(detalles_cita.this).load(url).into(img);

                                    confirmar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String dayOfMonth = String.valueOf(calendario.getDayOfMonth());
                                            String mes = String.valueOf(calendario.getMonth() + 1);
                                            String año = String.valueOf(calendario.getYear());
                                            fecha = dayOfMonth + mes + año;
                                            fecha1 = dayOfMonth +"/"+ mes + "/" + año;

                                            button_back1.setOnClickListener(new View.OnClickListener() {  ////////////////////
                                                @Override
                                                public void onClick(View v) {
                                                    Intent button_back0 = new Intent(detalles_cita.this, perfilespecialista.class);
                                                    startActivity(button_back0);
                                                }
                                            });


                                            db.collection("users-especialista/" + curp + "/agenda").document(fecha).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    //PETICION A COLECCION
                                                    if (task.isSuccessful()){
                                                        //VALIDAR SI EXISTE LO QUE SE CONSULTÓ
                                                        if(task.getResult().exists()){
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!= ""){
                                                                        hr1.setVisibility(View.VISIBLE);
                                                                        hora1= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("2").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado") != ""){
                                                                        hr2.setVisibility(View.VISIBLE);
                                                                        hora2= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("3").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr3.setVisibility(View.VISIBLE);
                                                                        hora3= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("4").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr4.setVisibility(View.VISIBLE);
                                                                        hora4= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("5").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr5.setVisibility(View.VISIBLE);
                                                                        hora5= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("6").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr6.setVisibility(View.VISIBLE);
                                                                        hora6= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }

                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("7").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr7.setVisibility(View.VISIBLE);
                                                                        hora7= documentSnapshot.getString("hora_inicial");
                                                                    }

                                                                }
                                                            });


                                                        }else{
                                                            Toast.makeText(detalles_cita.this, "FECHA NO DISPONIBLE", Toast.LENGTH_LONG).show();

                                                        }
                                                    }else{
                                                        Log.e("Error->Firebase", String.valueOf(task.getException()));
                                                    }
                                                    hr1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr2.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr2.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr2.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr2.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr2.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr7.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    hr7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                            if (isChecked){
                                                                hr1.setEnabled(false);
                                                                hr2.setEnabled(false);
                                                                hr3.setEnabled(false);
                                                                hr4.setEnabled(false);
                                                                hr5.setEnabled(false);
                                                                hr6.setEnabled(false);
                                                            }
                                                        }
                                                    });
                                                    agendar.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {


                                                            if (hr1.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora1);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr2.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora2);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr3.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora3);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr4.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora4);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr5.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora5);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr6.isChecked()){
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora6);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                            if (hr7.isChecked()){
                                                                Map<String, Object> map = new HashMap<>();
                                                                map.put("estado","");
                                                                Intent intent = new Intent(detalles_cita.this, infocita.class);
                                                                intent.putExtra("nombres",nombre);
                                                                intent.putExtra("apellido",ape);
                                                                intent.putExtra("fecha",fecha1);
                                                                intent.putExtra("hora",hora7);
                                                                intent.putExtra("id",id);
                                                                intent.putExtra("curp_espe",curp);
                                                                intent.putExtra("date",fecha);
                                                                startActivity(intent);
                                                            }
                                                        }
                                                    });

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.e("Error->Firebase", String.valueOf(e));

                                                }
                                            });
                                        }


                                    });

                                }
                            }

                        }
                    });
        }


    }

}