package com.example.oo_harjoitustyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListHolder extends RecyclerView.ViewHolder {

    ImageView ivPortrait;
    TextView tvName, tvAttack, tvDefense, tvHealth;
    public ListHolder(@NonNull View itemView) {
        super(itemView);
        ivPortrait = itemView.findViewById(R.id.ivPortrait);
        tvName = itemView.findViewById(R.id.tvName);
        tvAttack = itemView.findViewById(R.id.tvAttack);
        tvDefense = itemView.findViewById(R.id.tvDefense);
        tvHealth = itemView.findViewById(R.id.tvHealth);
    }
}
