package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cita);
        calendario =(DatePicker) findViewById(R.id.calendario);
        nom= (TextView)findViewById(R.id.text_title2);
        especialidad=(TextView)findViewById(R.id.txtespe);
        img =(CircleImageView)findViewById(R.id.image_perfil);
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
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    String nombre = document.getString("nombres");
                                    String ape = document.getString("apellido");
                                    nom.setText(""+ nombre + " "+ape);
                                    String url = document.getString("foto");
                                    Glide.with(detalles_cita.this).load(url).into(img);

                                }
                            }
                        }
                    });

        }
    }
}