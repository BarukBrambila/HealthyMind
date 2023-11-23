package com.example.healthymind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.healthymind.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class citasAdapter extends ArrayAdapter<citasModel> {

    public citasAdapter(@NonNull Context context, ArrayList<citasModel> dataModalArrayList){
        super(context, 0, dataModalArrayList);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.lista_citasprogramadas, parent, false);
        }

        citasModel dataModal = getItem(position);

        TextView nomTV = listitemView.findViewById(R.id.nomtxt);
        TextView fechaTV = listitemView.findViewById(R.id.fechatxt);
        TextView horaTV = listitemView.findViewById(R.id.horatxt);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users-paciente")
                .document(dataModal.getId_paciente())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                               if(task.isSuccessful())
                                               {
                                                   DocumentSnapshot document = task.getResult();
                                                   String paciente = document.getString("nombres") + " " +document.getString("apellidos");
                                                   nomTV.setText(paciente);
                                               }
                                           }
                                       });
        fechaTV.setText("Fecha: " + dataModal.getFecha_cita());
        horaTV.setText("Hora: "+dataModal.getHr_cita());
        return listitemView;
    }
}
