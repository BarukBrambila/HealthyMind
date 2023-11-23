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

public class PostActivity extends AppCompatActivity {
    TextView titulo, contenido;
    CircleImageView img;

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        titulo = (TextView)findViewById(R.id.tituloTxt);
        contenido = (TextView)findViewById(R.id.cuerpoTxt);
        img=(CircleImageView) findViewById(R.id.image_perfil);
        back = (Button) findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher();
            }
        });

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(PostActivity.this, ""+id, Toast.LENGTH_LONG).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            db.collection("publicaciones")
                    .whereEqualTo("id_post",id)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    String title = document.getString("titulo");
                                    String cuerpo = document.getString("texto");
                                    titulo.setText("" + title);
                                    contenido.setText("" + cuerpo);
                                    String url = document.getString("imagen");
                                    Glide.with(PostActivity.this).load(url).into(img);

                                }
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