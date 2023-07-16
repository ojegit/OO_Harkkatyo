package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Storage storage;
    StatisticsStorage statisticsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize Lutemon storage
        storage = Storage.getInstance();

        //initialize LutemonStatistics storage
        statisticsStorage = StatisticsStorage.getInstance();
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

    public void switchToBattleArena(View view) {
        Intent intent = new Intent(this, BattleArena.class);
        startActivity(intent);
    }

}