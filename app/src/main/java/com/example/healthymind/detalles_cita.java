package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
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

import org.w3c.dom.Text;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class detalles_cita extends AppCompatActivity {
    DatePicker calendario;
    TextView nom , especialidad;
    CircleImageView img;
    String fecha;
    CheckBox hr1, hr2, hr3, hr4, hr5, hr6, hr7;

    Button confirmar;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cita);
        calendario =(DatePicker) findViewById(R.id.calendario);
        nom= (TextView)findViewById(R.id.text_title2);
        especialidad=(TextView)findViewById(R.id.txtespe);
        img =(CircleImageView)findViewById(R.id.image_perfil);
        confirmar=(Button)findViewById(R.id.confirmarbtn);
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
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("2").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado") != ""){
                                                                        hr2.setVisibility(View.VISIBLE);
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("3").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr3.setVisibility(View.VISIBLE);
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("4").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr4.setVisibility(View.VISIBLE);
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("5").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr5.setVisibility(View.VISIBLE);
                                                                    }

                                                                }
                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("6").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr6.setVisibility(View.VISIBLE);
                                                                    }

                                                                }

                                                            });
                                                            db.collection("users-especialista/"+curp+"/agenda/"+fecha+"/horarios").document("7").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    if (documentSnapshot.getString("estado")!=""){
                                                                        hr7.setVisibility(View.VISIBLE);
                                                                    }

                                                                }
                                                            });
                                                            //AGENDAR




                                                        }else{
                                                            Toast.makeText(detalles_cita.this, "FECHA NO DISPONIBLE", Toast.LENGTH_LONG).show();

                                                        }
                                                    }else{
                                                        Log.e("Error->Firebase", String.valueOf(task.getException()));
                                                    }
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