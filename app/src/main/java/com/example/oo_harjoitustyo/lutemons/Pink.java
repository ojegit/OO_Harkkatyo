package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;

public class Pink extends Lutemon {
    private static int attack = 7;
    private static int defence = 2;
    private static int maxHealth = 18;
    private static String color = "Pink";
    public Pink(String nimi, int id) {
        super(nimi, color, attack, defence,0,maxHealth,maxHealth, id);
    }
}
