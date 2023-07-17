package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

import java.util.concurrent.TimeUnit;

public class White extends Lutemon {
    private static int attack = 5;
    private static int defence = 4;
    private static int maxHealth = 20;
    private static int imageSrc = R.drawable.white_lutemon;
    public White(String nimi) {
        super(nimi, Color.WHITE, attack, defence,0,maxHealth,maxHealth);
        setImageSrc(imageSrc);
    }
}
