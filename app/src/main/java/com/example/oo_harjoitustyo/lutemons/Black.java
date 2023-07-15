package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;

public class Black extends Lutemon {
    private static int attack = 9;
    private static int defence = 0;
    private static int maxHealth = 16;
    private static String color = "Black";
    public Black(String nimi, int id) {
        super(nimi, color, attack, defence,0,maxHealth,maxHealth, id);
    }

}
