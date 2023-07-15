package com.example.oo_harjoitustyo.lutemons;

import com.example.oo_harjoitustyo.Lutemon;

public class Green extends Lutemon {
    private static int attack = 6;
    private static int defence = 3;
    private static int maxHealth = 19;
    private static String color = "Green";
    public Green(String nimi, int id){
        super(nimi, color, attack, defence,0,maxHealth,maxHealth, id);
    }
}
