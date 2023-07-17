package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

public class Pink extends Lutemon {
    private static int attack = 7;
    private static int defence = 2;
    private static int maxHealth = 18;
    private static int imageSrc = R.drawable.pink_lutemon;
    public Pink(String nimi) {
        super(nimi, Color.PINK, attack, defence,0,maxHealth,maxHealth);
        setImageSrc(imageSrc);
    }
}
