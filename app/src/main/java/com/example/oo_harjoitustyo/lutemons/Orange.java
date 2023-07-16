package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

public class Orange extends Lutemon {
    private static int attack = 8;
    private static int defence = 1;
    private static int maxHealth = 17;
    private static int imageSrc = R.drawable.orange_lutemon;
    public Orange(String nimi, int id) {
        super(nimi, Color.ORANGE, attack, defence,0,maxHealth,maxHealth, id);
        setImageSrc(imageSrc);
    }
}
