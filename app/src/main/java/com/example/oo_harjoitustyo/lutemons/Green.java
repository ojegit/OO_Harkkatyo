package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

public class Green extends Lutemon {
    private static int attack = 6;
    private static int defence = 3;
    private static int maxHealth = 19;
    private static int imageSrc = R.drawable.green_lutemon;
    public Green(String nimi, int id){
        super(nimi, Color.GREEN, attack, defence,0,maxHealth,maxHealth, id);
        setImageSrc(imageSrc);
    }
}
