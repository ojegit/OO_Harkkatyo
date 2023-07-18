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
    private RadioGroup rg;
    private View view;
    private Lutemon.LutemonState lutemonState = Lutemon.LutemonState.PERISHED;
    //

    public Perished() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            //clear old contents
            rg = view.findViewById(R.id.rgCBPerished);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.VERTICAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                cb.setTag(lm.getId()); //for unique identification
                rg.addView(cb);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perished,
                container, false);

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            rg = view.findViewById(R.id.rgCBPerished);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.VERTICAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                cb.setTag(lm.getId()); //for unique identification
                rg.addView(cb);
            }
        }

        return view;
    }

    public ArrayList<Lutemon> getLutemons() {return lutemons;}
    public RadioGroup getRG(){return rg;}
    public void getAllLutemons(Lutemon.LutemonState lutemonState) {
        //clear old entries
        lutemons.clear();

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
}