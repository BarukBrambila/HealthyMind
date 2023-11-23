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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class pago extends AppCompatActivity {
    EditText ntarjeta, vencimiento, cvv;
    FirebaseFirestore mFirestore;
    Button pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        ntarjeta = (EditText) findViewById(R.id.ntarjeta);
        vencimiento = (EditText) findViewById(R.id.vencimiento);
        cvv = (EditText) findViewById(R.id.cv);
        pago = (Button) findViewById(R.id.pagobtn);
        Intent intent = getIntent();
        String fech = intent.getStringExtra("fecha");
        String hr = intent.getStringExtra("hora");
        String id_espe = intent.getStringExtra("id");
        String curp_espe = intent.getStringExtra("curp_espe");
        String date = intent.getStringExtra("date");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String regex = "\\d{16}";

        pago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = ntarjeta.getText().toString();
                String expiracion = vencimiento.getText().toString();
                String cv = cvv.getText().toString();
               if (numero.isEmpty()&& expiracion.isEmpty() && cv.isEmpty()){
                   Toast.makeText(pago.this, "Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
               }else {
                   FirebaseFirestore db = FirebaseFirestore.getInstance();
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                   if (user != null) {
                       String email = user.getEmail();
                       db.collection("users-paciente")
                               .whereEqualTo("email", email)
                               .get()
                               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                       // Toast.makeText(pago.this, ""+email, Toast.LENGTH_LONG).show();

                                       if (task.isSuccessful()) {
                                           for (QueryDocumentSnapshot document : task.getResult()) {
                                               String curp = document.getId();
                                               Map<String, Object> map = new HashMap<>();
                                               map.put("rfc_especialista", id_espe);
                                               map.put("fecha_cita", fech);
                                               map.put("hr_cita", hr);
                                               map.put("id_paciente", curp);
                                               pagar(map, curp);
                                               Intent intent = new Intent(pago.this, pago_exitoso.class);
                                               startActivity(intent);
                                           }

                                       }

                                   }
                               });
                   }
                   FirebaseFirestore us = FirebaseFirestore.getInstance();
                   if (us != null) {
                       //String id = us.getId();
                       db.collection("users-especialista/"+curp_espe+"/agenda/"+date+"/horarios")
                               .whereEqualTo("hora_inicial", hr)
                               .get()
                               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                       if (task.isSuccessful()){
                                           for (QueryDocumentSnapshot document : task.getResult()){
                                               String id = document.getId();
                                              // Toast.makeText(pago.this, ""+id,Toast.LENGTH_LONG).show();
                                               Map<String, Object> map = new HashMap<>();
                                               map.put("estado","");
                                               db.collection("users-especialista/"+curp_espe+"/agenda/"+date+"/horarios").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void unused) {

                                                   }
                                               });
                                           }
                                       }

                                   }
                               });
                   }


                }
            }

            private void pagar(Map<String, Object> map, String curp) {
                db.collection("users-paciente/"+curp+"/pago").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(pago.this, "exitoso", Toast.LENGTH_LONG).show();

                    }
                });
                db.collection("transacciones").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(pago.this, "coleccion exitoso", Toast.LENGTH_LONG).show();/
                    }
                });
                db.collection("users-especialista/"+curp_espe+"/pago").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(pago.this, "exitoso", Toast.LENGTH_LONG).show();

                    }
                });
            }

        });
    }

}