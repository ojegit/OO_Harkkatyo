package com.example.oo_harjoitustyo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
        holder.tvName.setText(
                String.valueOf(lutemons.get(position).getName())+
                        " @ "+String.valueOf(lutemons.get(position).getLutemonState())
        );
        holder.tvAttack.setText("Hyökkäys: " +String.valueOf(lutemons.get(position).getAttack()));
        holder.tvDefense.setText("Puolustus: " +String.valueOf(lutemons.get(position).getDefence()));
        holder.tvHealth.setText("Elämä: " +String.valueOf(lutemons.get(position).getHealth())+" / " +
                                           String.valueOf(lutemons.get(position).getMaxHealth()));
        holder.tvExperience.setText("Kokemus: " +String.valueOf(lutemons.get(position).getExperience()));

        //date format
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm");

        //convert to long, to milliseconds, to Date and finally apply format
        String strDate  = DateFormat.format( new Date(1000 * Long.parseLong(lutemons.get(position).getId())));
        holder.tvTimeCreated.setText("Luotu: " +strDate);





        //buttons' functionality
        holder.ivDelete.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            String id = String.valueOf(lutemons.get(pos).getId());
            String colorName = lutemons.get(pos).getName()+
                    "("+lutemons.get(pos).getColor()+")";
            System.out.println("Deleted Lutemon " +colorName);

            //Ask permission for the deletion
            //https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
            new AlertDialog.Builder(((Activity)context))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete '"+colorName+"'?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Storage.getInstance().removeById(id);
                        lutemons.remove(pos);
                        notifyItemRemoved(pos);
                        System.out.println("Deleted '"+colorName+"' Lutemon!");
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        //do nothing
                        System.out.println("Did not delete anything!");
                        })
                    .show();

            //

        });
    }


    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
