package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListLutemons extends AppCompatActivity {

    RecyclerView recyclerView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);

        recyclerView = findViewById(R.id.rvListLutemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //HashMap to ArrayList

        listAdapter = new ListAdapter(this, Storage.getInstance().getLutemonsList());
        recyclerView.setAdapter(listAdapter);

    }
}