package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Storage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize Lutemon storage
        storage = Storage.getInstance();

    }

    public void onClickLoadData(View view) {

        new AlertDialog.Builder(((Activity)this))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Lataa tiedot")
                .setMessage("Oletko varma että haluat ladata tiedot?")
                .setPositiveButton("Kyllä", (dialog, which) -> {
                    Storage.getInstance().loadStorage(this);
                })
                .setNegativeButton("Ei", (dialog, which) -> {
                    //do nothing
                    System.out.println("Did not load anything!");
                })
                .show();
    }


    public void onClickSaveData(View view) {

        new AlertDialog.Builder(((Activity)this))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Tallenna tiedot")
                .setMessage("Oletko varma että haluat tallentaa tiedot?")
                .setPositiveButton("Kyllä", (dialog, which) -> {
                    Storage.getInstance().saveStorage(this);
                })
                .setNegativeButton("Ei", (dialog, which) -> {
                    //do nothing
                    System.out.println("Did not save anything!");
                })
                .show();
    }

    public void switchToAddLutemon(View view) {
        Intent intent = new Intent(this, AddLutemon.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view) {
        Intent intent = new Intent(this, ListLutemons.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view) {
        Intent intent = new Intent(this, MoveLutemons.class);
        startActivity(intent);
    }

    public void switchStatistics(View view) {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void switchToBattleArena(View view) {
        Intent intent = new Intent(this, BattleArena.class);
        startActivity(intent);
    }

}