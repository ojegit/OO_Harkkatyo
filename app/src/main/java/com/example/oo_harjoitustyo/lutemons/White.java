package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;

public class White extends Lutemon {
    private static int attack = 5;
    private static int defence = 4;
    private static int maxHealth = 20;
    private static String color = "White";
    public White(String nimi, int id) {
        super(nimi, color, attack, defence,0,maxHealth,maxHealth, id);
    }
}
