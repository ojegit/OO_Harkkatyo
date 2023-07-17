package com.example.oo_harjoitustyo.fragments;

import android.content.Context;
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


public class Home extends Fragment {


    //
    private ArrayList<Lutemon> lutemons = new ArrayList<Lutemon>();
    private RadioGroup rg;

    private View view;

    private Lutemon.LutemonState lutemonState = Lutemon.LutemonState.HOME;
    //

    public Home() {
        // Required empty public constructor
    }

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

    @Override
    public void onResume() {
        super.onResume();

        System.out.println("onResume Home: "+getArguments()+ ", tag: "+this.getTag());

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            //clear old contents
            rg = view.findViewById(R.id.rgCBHome);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.HORIZONTAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                rg.addView(cb);
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //where should this be? messages can come and go at any time, but this
        //refers to only those being read when the intance is created!
        if(getArguments() != null) {
            System.out.println("Fragment arguments at Home: "+getArguments());
            lutemons = (ArrayList<Lutemon>) getArguments()
                    .getSerializable(String.valueOf(serialVersionUID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("OnCreateView Home");
        if(getArguments() != null) {
            System.out.println("Fragment arguments at Home: "+getArguments());
            lutemons = (ArrayList<Lutemon>) getArguments()
                    .getSerializable(String.valueOf(serialVersionUID));
        }

        view = inflater.inflate(R.layout.fragment_home, container, false);

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            //clear old contents

            rg = view.findViewById(R.id.rgCBHome);
            rg.removeAllViews();

            rg.setOrientation(RadioGroup.HORIZONTAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                rg.addView(cb);
            }
        }

        return view;
    }

    /*
    public void onClickMove() {
        if (rg.getChildCount() > 0) {
            //Get checkboxes and remove those being shipped from this fragment
            ArrayList<Lutemon> move = new ArrayList<Lutemon>();
            for (int i = 0; i < rg.getChildCount(); i++) {
                if (rg.getChildAt(i).isPressed()) {
                    Lutemon lutemon = lutemons.remove(i);
                    move.add(lutemon);
                }
            }

            //Get destination fragment
            RadioGroup rg = container.findViewById(R.id.rgDestinations);
            Fragment fragment = null;
            switch (rg.getCheckedRadioButtonId()) {
                case R.id.rbHome:
                    fragment = new Home();
                    break;
                case R.id.rbTrain:
                    fragment = new Train();
                    break;
                case R.id.rbBattle:
                    fragment = new Battle();
                    break;
                case R.id.rbPerished:
                    fragment = new Perished();
                    break;
            }

            if (fragment != null) {
                Bundle args = new Bundle();
                args.putSerializable(String.valueOf(serialVersionUID), move);
                fragment.setArguments(args);
            }
        }
    }
     */
    public ArrayList<Lutemon> getLutemons() {return lutemons;}
    public RadioGroup getRG(){return rg;}

    private static final long serialVersionUID = 123456789L;

    // Accept serialization from other fragments
    public static Home newInstance(ArrayList<Lutemon> move) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putSerializable(String.valueOf(serialVersionUID), move);
        fragment.setArguments(args);
        return fragment;
    }

}