package com.example.oo_harjoitustyo.lutemons;

import android.net.Uri;

import com.example.oo_harjoitustyo.Lutemon;
import com.example.oo_harjoitustyo.R;

public class Black extends Lutemon {
    private static int attack = 9;
    private static int defence = 0;
    private static int maxHealth = 16;
    private static int imageSrc = R.drawable.black_lutemon;
    public Black(String nimi, int id) {
        super(nimi, Color.BLACK, attack, defence,0,maxHealth, maxHealth, id);
        setImageSrc(imageSrc);
    }

}
