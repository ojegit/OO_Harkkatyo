package com.example.oo_harjoitustyo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> implements Serializable  {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        //set data
        holder.ivPortrait.setImageResource(lutemons.get(position).getImageSrc());
        holder.tvName.setText(String.valueOf(lutemons.get(position).getName()));
        holder.tvAttack.setText("Hyökkäys: " +String.valueOf(lutemons.get(position).getAttack()));
        holder.tvDefense.setText("Puolustus: " +String.valueOf(lutemons.get(position).getDefence())); //typo
        holder.tvHealth.setText("Elämä: " +String.valueOf(lutemons.get(position).getHealth()));
        holder.tvExperience.setText("Kokemus: " +String.valueOf(lutemons.get(position).getExperience()));
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
