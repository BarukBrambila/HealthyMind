package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class perfil_paciente extends AppCompatActivity {
    FirebaseFirestore mFirestore;
    TextView nom, email1;
    Button cerrarsesion, editar;
    CircleImageView img;
    StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_paciente);
        nom=(TextView)findViewById(R.id.txtnom);
        email1 =(TextView)findViewById(R.id.txtemailpaciente);
        cerrarsesion =(Button)findViewById(R.id.cerrarsesionbtn);
        editar=(Button)findViewById(R.id.botton1);
        img = (CircleImageView)findViewById(R.id.imgperfil1);
        mStorage = FirebaseStorage.getInstance().getReference();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottom_date);
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
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //String id = document.getId();
                                    String nombre = document.getString("nombres");
                                    String ape = document.getString("apellidos");
                                    nom.setText("" + nombre + " " + ape);
                                    String correo = document.getString("email");
                                    email1.setText("" + correo);
                                    String url = document.getString("foto");

                                    Glide.with(perfil_paciente.this).load(url).into(img);


                                }
                            } else {
                                //Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(perfil_paciente.this, bienvenidan.class));
            }
        });


        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_perfil:
                    return true;
                case R.id.bottom_date:
                    startActivity(new Intent(getApplicationContext(), listapsicologos_cita.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), ini_paciente.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
        Button button = findViewById(R.id.TyC);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(perfil_paciente.this,pdfActivity.class);
                startActivity(intent);



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                           @Override
                                                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                               Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                                               while (!uriTask.isSuccessful()) ;
                                                               if (uriTask.isSuccessful()) {
                                                                   uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                       @Override
                                                                       public void onSuccess(Uri uri) {
                                                                           String url = uri.toString();
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
                                                                                               if (task.isSuccessful()) {
                                                                                                   for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                                       String id = document.getId();
                                                                                                       DocumentReference fotoreference = db.collection("users-paciente").document(id);
                                                                                                       fotoreference.update("foto", url).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                                                   });

                                                               }
                                                           }
                                                       });



                   // Toast.makeText(perfil_paciente.this, "Archivo subido correctamente", Toast.LENGTH_LONG).show();
            Glide.with(this).load(uri).into(img);
        }
    }
}