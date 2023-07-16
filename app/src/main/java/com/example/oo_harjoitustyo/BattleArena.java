package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;

public class BattleArena extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_arena);
    }


    public void fight(Lutemon lutemonA, Lutemon lutemonB) {
        int noRounds = 0;
        double avgDamage[] = {0,0};
        int expGained[] = {0,0};


        //Do combat until one of them perishes aka health <= 0
        do {
            noRounds++;

            System.out.println(lutemonA.toString());
            System.out.println(lutemonB.toString());

            //A attacks B
            System.out.println(lutemonA.color+"("+lutemonA.name+") attacks "+lutemonB.color+ "("+lutemonB.name+")");
            lutemonB.defence(lutemonA);

            avgDamage[0]+=lutemonA.getAttack();

            //check B's health
            if(lutemonB.health > 0) {
                System.out.println(lutemonA.color+"("+lutemonA.name+") manages to escape death.");

                System.out.println(lutemonB.toString());
                System.out.println(lutemonA.toString());

                //B attacks A
                System.out.println(lutemonB.color+"("+lutemonB.name+") attacks "+lutemonA.color+ "("+lutemonA.name+")");
                lutemonA.defence(lutemonB);

                //check A's health
                if(lutemonA.health > 0) {
                    System.out.println(lutemonB.color+"("+lutemonB.name+") manages to escape death.");
                } else {
                    //A is dead, exit
                    //set A to DEAD status

                    //B gains experience for killing A
                    expGained[1]++;

                    System.out.println("A IS DEAD, B WON!");
                    //break;
                }
            } else {
                //B is dead, exit
                //set B to DEAD status

                //A gains experience for killing B
                expGained[0]++;

                System.out.println("B IS DEAD, A WON");
                //break;
            }

        } while(lutemonA.health > 0 && lutemonB.health > 0);

        //calculate average damage
        for(int i = 0; i<2; i++) {
            avgDamage[i] = avgDamage[i]/(1.0*noRounds);
        }

        System.out.println("Combat results:");
        System.out.println("No rounds: " +noRounds);
        System.out.println("Average damage done per combatant: " +avgDamage);

    }


}