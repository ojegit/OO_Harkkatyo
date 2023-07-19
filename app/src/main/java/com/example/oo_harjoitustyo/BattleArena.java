package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleArena extends AppCompatActivity {

    private ArrayList<Lutemon> lutemons;
    private ArrayList<BattleMsgContainer> battleMsgs;
    private RadioGroup rg;
    private Lutemon.LutemonState lutemonState;
    private Context context;
    private RecyclerView recyclerView;
    private BattleInfoAdapter battleInfoAdapter;

    TextView tvBattleMsg;

    public BattleArena() {
        //container for those lutemon whose LutemonState is BATTLE
        this.lutemons = new ArrayList<Lutemon>();

        //message container (that is eventually shipped to RecycleView)
        this.battleMsgs = new ArrayList<BattleMsgContainer>();

        this.lutemonState = Lutemon.LutemonState.BATTLE;
    }

    @Override
    public void onResume() {
        super.onResume();

        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            //clear old contents
            rg = findViewById(R.id.rgCBBattleArena);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.VERTICAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(context);
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                cb.setTag(lm.getId()); //for unique identification
                rg.addView(cb);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_arena);
        this.context = this;

        recyclerView = findViewById(R.id.rvBattle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        battleInfoAdapter = new BattleInfoAdapter(this, battleMsgs);
        recyclerView.setAdapter(battleInfoAdapter);



        //update lutemons list
        getAllLutemons(lutemonState);

        //create checkboxes
        if (lutemons.size() > 0) {
            //clear old contents
            rg = findViewById(R.id.rgCBBattleArena);
            rg.removeAllViews();
            rg.setOrientation(RadioGroup.VERTICAL);
            for (Lutemon lm : lutemons) {
                CheckBox cb = new CheckBox(context);
                cb.setText(lm.getName() + "(" + lm.getColor() + ")");
                cb.setTag(lm.getId()); //for unique identification
                rg.addView(cb);
            }
        }

        //tvBattleMsg = findViewById(R.id.tvBattleMsg);


        findViewById(R.id.btnBattleEngage).setOnClickListener(view -> {

            //TBA: add a number in which order the checkboxes have been checked!

            List<Integer> isChecked = new ArrayList<>(); //keep track of selected lutemons' ids
            //List<String> isChecked = new ArrayList<>(); //keep track of selected lutemons' ids
            //
            for (int i = 0; i < rg.getChildCount(); i++) {
                CheckBox cb = (CheckBox)rg.getChildAt(i);
                if (cb.isChecked()) {

                    //get id
                    //Lutemon lutemonInFragment = lutemons.get(i);
                    //String id = lutemonInFragment.getId();
                    //note: cb tag and id should match!
                    //assert(id.equals(String.valueOf(cb.getTag())));
                    //
                    isChecked.add(i);

                    //uncheck
                    cb.toggle();
                }
                if(isChecked.size() == 2) {
                    break;
                }
            }

            //clear selected
            rg.clearCheck();

            //check if at least two fighters have been selected
            if(isChecked.size() == 2) {
                //engage fight
                fight(lutemons.get(0), lutemons.get(1));

                //notify RecycleView of the new data
                battleInfoAdapter.notifyDataSetChanged();
            }


        });

    }




    public void fight(Lutemon lutemonA, Lutemon lutemonB) {
        int noRounds = 0;
        double avgDamage[] = {0,0};
        int expGained[] = {0,0};

        //TBA: choose between turn based and until death:
        //turn based combat and give the opponent the chance to give up/escape (then apply consequences and benefits for doing so)!

        //reset text
        //tvBattleMsg.setText("");
        String msg = "";

        //Do combat until one of them perishes aka health <= 0
        do {
            //
            noRounds++;
            if(noRounds>1) {
                msg = "\nROUND " +noRounds+ "\n----------------\n";
            } else {
                msg = "ROUND " +noRounds+ "\n----------------\n";
            }
            battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
            //tvBattleMsg.append(msg+"\n");
            System.out.println(msg);
            //

            //Print stats
            msg = lutemonA.toString();
            //tvBattleMsg.append(msg+"\n");
            battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.LEFT, lutemonA));
            System.out.println(msg);

            msg = lutemonB.toString();
            //tvBattleMsg.append(msg+"\n");
            battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.RIGHT, lutemonB));
            System.out.println(msg);
            //

            //A attacks B
            msg = lutemonA.color+"("+lutemonA.name+") attacks "+lutemonB.color+ "("+lutemonB.name+")";
            //tvBattleMsg.append(msg+"\n");
            battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
            System.out.println(msg);

            lutemonB.defence(lutemonA);
            avgDamage[0]+=lutemonA.getAttack();
            //

            //check B's health
            if(lutemonB.health > 0) {
                //
                msg = lutemonB.color+"("+lutemonB.name+") manages to escape death.";
                //tvBattleMsg.append(msg+"\n");
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
                System.out.println(msg);
                //

                //Print stats
                msg = lutemonB.toString();
                //tvBattleMsg.append(msg+"\n");
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.RIGHT, lutemonB));
                System.out.println(msg);

                msg = lutemonA.toString();
                //tvBattleMsg.append(msg+"\n");
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.LEFT, lutemonA));
                System.out.println(msg);
                //

                //B attacks A
                msg = lutemonB.color+"("+lutemonB.name+") attacks "+lutemonA.color+ "("+lutemonA.name+")";
                //tvBattleMsg.append(msg+"\n");
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
                System.out.println(msg);

                lutemonA.defence(lutemonB);
                //

                //check A's health
                if(lutemonA.health > 0) {
                    msg = lutemonA.color+"("+lutemonA.name+") manages to escape death.";
                    //tvBattleMsg.append(msg+"\n");
                    battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
                    System.out.println(msg);
                } else {
                    //A is dead, exit
                    //set A to DEAD status

                    //B gains experience for killing A
                    expGained[1]++;

                    msg = "A IS DEAD, B WON!";
                    //tvBattleMsg.append(msg+"\n");
                    lutemonA.setLutemonState(Lutemon.LutemonState.PERISHED);
                    battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, lutemonA));
                    System.out.println(msg);

                    //change status to perished
                    //POSSIBLE BUG: why doesn't this state show up in the listing (but it works with the move states)
                    //TBA: add revive/reset option to the list IFF the state is perished
                    //TBA add turn based combat option  here where one can escape if necessary on the expense of some
                    //incurred penalty etc


                    //break;
                }
            } else {
                //B is dead, exit
                //set B to DEAD status

                //A gains experience for killing B
                expGained[0]++;

                msg = "B IS DEAD, A WON";
                //tvBattleMsg.append(msg+"\n");

                lutemonB.setLutemonState(Lutemon.LutemonState.PERISHED);
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, lutemonB));
                System.out.println(msg);

                //break;
            }

        } while(lutemonA.health > 0 && lutemonB.health > 0);

        //calculate average damage
        for(int i = 0; i<2; i++) {
            avgDamage[i] = avgDamage[i]/(1.0*noRounds);
        }

        msg = "Combat results:\nNo rounds: " +noRounds+"\nAverage damage done per combatant: " +avgDamage;
        //tvBattleMsg.append(msg+"\n");
        battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
        System.out.println(msg);
    }

    public ArrayList<Lutemon> getLutemons() {return lutemons;}
    public ArrayList<BattleMsgContainer> getBattleMsgs() {return battleMsgs;}
    public RadioGroup getRG(){return rg;}

    public void getAllLutemons(Lutemon.LutemonState lutemonState) {
        //clear old entries
        lutemons.clear();

        int n = Storage.getInstance().getLutemons().size();
        if(n > 0) {
            Storage.getInstance().getLutemons().forEach((key,lutemon)->{
                //add only those with the wanted LutemonState
                if(lutemon.getLutemonState() == lutemonState) {
                    lutemons.add(lutemon);
                }
            });
        }
    }


}