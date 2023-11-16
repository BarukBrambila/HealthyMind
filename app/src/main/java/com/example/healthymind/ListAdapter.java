package com.example.healthymind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<listseleccionesp> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<listseleccionesp> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_psicologo,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<listseleccionesp> items){ mData = items;}

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView icono;
        TextView nombre, especialidad, costo;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            icono = itemView.findViewById(R.id.icono);
            nombre = itemView.findViewById(R.id.nombretxt);
            especialidad = itemView.findViewById(R.id.especialidadtxt);
            costo = itemView.findViewById(R.id.costotxt);
        }

        void bindData(final listseleccionesp item){
            //icono.setImageResource(); para la foto creo, como poner la imagen¿¿¿
            nombre.setText(item.getNombre());
            especialidad.setText(item.getEspecialidad());
            costo.setText(item.getPreio());
        }
    }
}
