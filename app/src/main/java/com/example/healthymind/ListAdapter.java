package com.example.healthymind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<listseleccionesp> especialistaArrayList;
    private Context context;

    public ListAdapter(ArrayList<listseleccionesp> especialistaArrayList, Context context) {
        this.especialistaArrayList = especialistaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_psicologo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        listseleccionesp listseleccionesp = especialistaArrayList.get(position);
        holder.nombre.setText(listseleccionesp.getNombre());
        holder.especialidad.setText(listseleccionesp.getEspecialidad());
        Glide.with(context).load(listseleccionesp.getFoto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, perfilespecialista.class);
                intent.putExtra("id",listseleccionesp.getRfc());
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return especialistaArrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nombre;
        private final TextView especialidad;
        public String rfc;
        private final CircleImageView foto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombretxt);
            especialidad = itemView.findViewById(R.id.especialidadtxt);
            foto=itemView.findViewById(R.id.imageView);
        }
    }
}
