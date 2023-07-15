package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;

public class Orange extends Lutemon {
    private static int attack = 8;
    private static int defence = 1;
    private static int maxHealth = 17;
    private static String color = "Orange";
    public Orange(String nimi, int id) {
        super(nimi, color, attack, defence,0,maxHealth,maxHealth, id);
    }
}
