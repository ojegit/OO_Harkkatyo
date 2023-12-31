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

public class ListAdapter extends RecyclerView.Adapter<ListHolder>   { //implements Serializable

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
        //holder.tvExperience.setText("Kokemus: " +String.valueOf(lutemons.get(position).getExperience()));
        holder.tvExperience.setText("Kokemus: "
                +String.valueOf(lutemons.get(position).getAmountOfExperienceTrained() + lutemons.get(position).getAmountOfExperienceFought()));
        holder.tvRevived.setText("Elvytetty: "+String.valueOf(lutemons.get(position).getNoTimesRevived()));
        holder.tvWins.setText("Voitot: "+String.valueOf(lutemons.get(position).getNoWins()));
        holder.tvLosses.setText("Tappiot: "+String.valueOf(lutemons.get(position).getNoLosses()));

        //date format
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm");

        //convert to long, to milliseconds, to Date and finally apply format
        String strDate  = DateFormat.format( new Date(1000 * Long.parseLong(lutemons.get(position).getId())));
        holder.tvTimeCreated.setText("Luotu: " +strDate);



        //
        //buttons' functionality
        //
        int pos = holder.getAdapterPosition();

        //Delete
        holder.ivDelete.setOnClickListener(view -> {
            String id = String.valueOf(lutemons.get(pos).getId());
            String colorName = lutemons.get(pos).getName()+
                    "("+lutemons.get(pos).getColor()+")";
            System.out.println("Deleted Lutemon " +colorName);

            //Ask permission for the deletion
            //https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
            new AlertDialog.Builder(((Activity)context))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Poista")
                    .setMessage("Haluatko varmasti poistaa '"+colorName+"'?")
                    .setPositiveButton("Kyllä", (dialog, which) -> {
                        Storage.getInstance().removeById(id);
                        lutemons.remove(pos);
                        notifyItemRemoved(pos);
                        System.out.println("Poistettiin '"+colorName+"' Lutemon!");
                    })
                    .setNegativeButton("Ei", (dialog, which) -> {
                        //do nothing
                        System.out.println("Did not delete anything!");
                        })
                    .show();

            //

        });

        //Revive
        if(lutemons.get(pos).getHealth() <= 0) {
            //make visible if perished and add listener
            holder.ivRevive.setVisibility(View.VISIBLE);

            holder.ivRevive.setOnClickListener(view -> {
                String id = String.valueOf(lutemons.get(pos).getId());
                String colorName = lutemons.get(pos).getName() +
                        "(" + lutemons.get(pos).getColor() + ")";
                System.out.println("Restored Lutemon " + colorName);

                new AlertDialog.Builder(((Activity)context))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Herätä henkiin")
                        .setMessage("Haluatko varmasti herättää henkiin '"+colorName+"'?")
                        .setPositiveButton("Kyllä", (dialog, which) -> {
                            lutemons.get(pos).resetHealth();
                            lutemons.get(pos).setLutemonState(Lutemon.LutemonState.HOME);
                            lutemons.get(pos).setNoTimesRevived(lutemons.get(pos).getNoTimesRevived()+1);

                            System.out.println("Restored '"+colorName+"' Lutemon!");

                            //hide button
                            holder.ivRevive.setVisibility(View.GONE);

                            notifyItemChanged(pos);
                        })
                        .setNegativeButton("Ei", (dialog, which) -> {
                            //do nothing
                            System.out.println("Did not delete anything!");
                        })
                        .show();

            });
        }

    }


    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
