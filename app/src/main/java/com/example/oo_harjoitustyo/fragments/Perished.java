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


public class Perished extends Fragment {


    //
    private ArrayList<Lutemon> lutemons = new ArrayList<Lutemon>();
    //
    private RadioGroup rg;

    private Lutemon.LutemonState lutemonState = Lutemon.LutemonState.PERISHED;

    public Perished() {
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

    private static final long serialVersionUID = 123456789L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            lutemons = (ArrayList<Lutemon>) getArguments()
                    .getSerializable(String.valueOf(serialVersionUID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perished, container, false);

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            rg = view.findViewById(R.id.rgCBPerished);
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
}