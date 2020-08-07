package com.hpsoft.truyentranh.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.objects.AnhTruyen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DocTruyenAdapter extends RecyclerView.Adapter<DocTruyenAdapter.ViewHolder> {
    private List<AnhTruyen> anhTruyens;

    public DocTruyenAdapter(List<AnhTruyen> anhTruyens) {
        this.anhTruyens = anhTruyens;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctruyen_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(anhTruyens.get(position).getUrlIMGTruyen()).fit().into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return anhTruyens.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgTruyen);
        }
    }
}
