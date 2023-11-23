package com.example.healthymind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.ViewHolder> {

    private ArrayList<Posts> postsArrayList;
    private Context context;

    public PostsListAdapter(ArrayList<Posts> postsArrayList, Context context) {
        this.postsArrayList = postsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsListAdapter.ViewHolder holder, int position) {
        Posts Posts = postsArrayList.get(position);
        holder.titulo.setText(Posts.getTitulo());
        holder.texto.setText(Posts.getTexto());
        Glide.with(context).load(Posts.getImagen()).into(holder.imagen);

        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {

        //        Intent intent = new Intent(context, PostActivity.class);
        //        intent.putExtra("id",Posts.getId());
        //        context.startActivity(intent);

        //    }
        //});



    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView titulo;
        private final TextView texto;
        public String rfc;
        private final ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloTxt);
            texto = itemView.findViewById(R.id.contenidoTxt);
            imagen = itemView.findViewById(R.id.imageView);
        }
    }
}
