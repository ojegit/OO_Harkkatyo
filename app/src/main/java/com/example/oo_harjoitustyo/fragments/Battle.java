package com.example.oo_harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;
import com.example.oo_harjoitustyo.Storage;

import java.util.ArrayList;


public class Battle extends Fragment {

    //
    private ArrayList<Lutemon> lutemons = new ArrayList<Lutemon>();
    //
    private RadioGroup rg;

    private Lutemon.LutemonState lutemonState = Lutemon.LutemonState.BATTLE;


    public Battle() {
        // Required empty public constructor
    }

    public void getAllLutemons(Lutemon.LutemonState lutemonState) {
        int n = Storage.getInstance().getLutemons().size();
        if(n > 0) {
            Storage.getInstance().getLutemons().forEach((key,lutemon)->{
                //add only those with the wanted LutemonState
                if(lutemon.getLutemonState() == lutemonState) {
                    lutemons.add(lutemon);
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //first time Home is accessed every available Lutemon should go here
        if (getArguments() != null) {
            lutemons = (ArrayList<Lutemon>) getArguments()
                    .getSerializable(String.valueOf(serialVersionUID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            rg = view.findViewById(R.id.rgCBBattle);
            rg.setOrientation(RadioGroup.HORIZONTAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                rg.addView(cb);
            }
        }

        // Inflate the layout for this fragment
        return view;
    }

    public ArrayList<Lutemon> getLutemons() {return lutemons;}
    public RadioGroup getRG(){return rg;}

    private static final long serialVersionUID = 123456789L;

    // Accept serialization from other fragments
    public static Battle newInstance(ArrayList<Lutemon> move) {
        Battle fragment = new Battle();
        Bundle args = new Bundle();
        args.putSerializable(String.valueOf(serialVersionUID), move);
        fragment.setArguments(args);
        return fragment;
    }
}