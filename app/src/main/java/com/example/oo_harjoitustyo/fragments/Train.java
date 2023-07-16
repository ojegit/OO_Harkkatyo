package com.example.oo_harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

import java.util.ArrayList;


public class Train extends Fragment {

    //
    ArrayList<Lutemon> train = new ArrayList<Lutemon>();
    //

    public Train() {
        // Required empty public constructor
    }

    public static Train newInstance(String param1, String param2) {
        Train fragment = new Train();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_train, container, false);
    }
}