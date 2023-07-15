package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BattleArena extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_arena);
    }


    public fight(Lutemon lutemonA, Lutemon LutemonB) {

        while(1==1) {
            //tba create toString or a function that prints the stats!
            Storage.getInstance().listLutemons();

        }



        /*
        What should this print?
        ------------------------

        1: Pink(Pinky) att: 7; def: 2; exp:0; health: 18/18
2: Black(Black Knight) att: 9; def: 0; exp:0; health: 16/16
Pink(Pinky) attacks Black(Black Knight)
Black(Black Knight) manages to escape death.
2: Black(Black Knight) att: 9; def: 0; exp:0; health: 9/16
1: Pink(Pinky) att: 7; def: 2; exp:0; health: 18/18
Black(Black Knight) attacks Pink(Pinky)
Pink(Pinky) manages to escape death.
1: Pink(Pinky) att: 7; def: 2; exp:0; health: 11/18
2: Black(Black Knight) att: 9; def: 0; exp:0; health: 9/16
Pink(Pinky) attacks Black(Black Knight)
Black(Black Knight) manages to escape death.
2: Black(Black Knight) att: 9; def: 0; exp:0; health: 2/16
1: Pink(Pinky) att: 7; def: 2; exp:0; health: 11/18
Black(Black Knight) attacks Pink(Pinky)
Pink(Pinky) manages to escape death.
1: Pink(Pinky) att: 7; def: 2; exp:0; health: 4/18
2: Black(Black Knight) att: 9; def: 0; exp:0; health: 2/16
Pink(Pinky) attacks Black(Black Knight)
Black(Black Knight) gets killed.
The battle is over.
         */

    }


}