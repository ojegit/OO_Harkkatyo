package com.example.oo_harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;
import com.example.oo_harjoitustyo.Storage;

import java.util.ArrayList;
import java.util.List;


public class Train extends Fragment {
    //
    private ArrayList<Lutemon> lutemons = new ArrayList<Lutemon>();
    private RadioGroup rg;
    private View view;
    private Lutemon.LutemonState lutemonState = Lutemon.LutemonState.TRAIN;
    //

    public Train() {
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
            rg = view.findViewById(R.id.rgCBTrain);
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

    public void train() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_train,
                container, false);

        //train button functionality
        view.findViewById(R.id.btnTrain).setOnClickListener(view->{
            //train only those that have been selected
            //accumulate statistics: noTrained and amountOfExperienceTrained
            if(lutemons.size() > 0) {
                for (int i = 0; i < rg.getChildCount(); i++) {
                    CheckBox cb = (CheckBox) rg.getChildAt(i);
                    if (cb.isChecked()) {

                        //get id
                        Lutemon lutemonInFragment = lutemons.get(i);
                        String id = lutemonInFragment.getId();
                        //note: cb tag and id should match!
                        assert (id.equals(String.valueOf(cb.getTag())));
                        //

                        //do the training
                        lutemonInFragment.setAmountOfExperienceTrained(
                                lutemonInFragment.getAmountOfExperienceTrained() + 2
                                //
                                //note: when adding more than one point at a time it is necessary to check f
                                //or level ups later because the current mechanism only checks for gaps!
                                //
                        );
                        lutemonInFragment.setNoTrained(lutemonInFragment.getNoTrained() + 1);


                        //check level up eligibility
                        int noLvlUps = lutemonInFragment.checkLevelUp(2);
                        if (noLvlUps > 0) {
                            //announce if lutemon got a level up
                            Toast.makeText(getContext(),
                                    lutemonInFragment.getName() + "(" + lutemonInFragment.getColor() + ") sail " + noLvlUps + " level uppia!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        //uncheck
                        if (cb.isChecked()) {
                            cb.toggle();
                        }
                    }
                }
            } else {
                System.out.println("No Lutemons to be trained!");
            }

        });

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            rg = view.findViewById(R.id.rgCBTrain);
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