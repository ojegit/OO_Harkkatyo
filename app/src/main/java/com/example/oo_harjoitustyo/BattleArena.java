package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BattleArena extends AppCompatActivity {

    private ArrayList<Lutemon> lutemons;
    private ArrayList<BattleMsgContainer> battleMsgs;
    private RadioGroup rg;
    private Lutemon.LutemonState lutemonState;
    private Context context;
    private RecyclerView recyclerView;
    private BattleInfoAdapter battleInfoAdapter;
    private int numberOfCheckboxesChecked;

    private ArrayList<Lutemon> isCheckedList;

    TextView tvBattleMsg;

    public BattleArena() {
        //container for those lutemon whose LutemonState is BATTLE
        this.lutemons = new ArrayList<Lutemon>();

        //message container (that is eventually shipped to RecycleView)
        this.battleMsgs = new ArrayList<BattleMsgContainer>();

        this.lutemonState = Lutemon.LutemonState.BATTLE;
        this.numberOfCheckboxesChecked = 0;
        this.isCheckedList = new ArrayList<>();
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

                //add listener to checkbox
                //https://stackoverflow.com/questions/16220156/how-to-limit-number-of-checkboxes-that-can-be-checked
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked && numberOfCheckboxesChecked >= 2) {
                            cb.setChecked(false);
                        } else {
                            if (isChecked) {
                                numberOfCheckboxesChecked++;

                                //check if checkbox already added to list (HashMap cannot be used because the order is important!)
                                int contains = 0;
                                for(Lutemon lm : isCheckedList) {
                                    if(lm.getId().equals(cb.getTag())) {
                                        contains++;
                                        break;
                                    }
                                }
                                if(contains == 0) { isCheckedList.add(lm);}

                                Toast.makeText(context, "Valittu "+cb.getText()+ ", "+numberOfCheckboxesChecked+"/2", Toast.LENGTH_SHORT).show();
                            } else {
                                numberOfCheckboxesChecked--;
                                //remove unchecked from the list
                                ListIterator<Lutemon> it = isCheckedList.listIterator();
                                int i = 0;
                                while(it.hasNext()) {
                                    Lutemon tmp = it.next();
                                    if(tmp.getId().equals(cb.getTag())) {
                                        it.remove();
                                        break;
                                    }
                                }
                                Toast.makeText(context, "Poistettu valinta, "+numberOfCheckboxesChecked+"/2", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                });


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_arena);
        this.context = this;

        System.out.println("isCheckedList: " + isCheckedList.toString());

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

                //add listener to checkbox
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked && numberOfCheckboxesChecked >= 2) {
                            cb.setChecked(false);
                        } else {
                            if (isChecked) {
                                numberOfCheckboxesChecked++;

                                //check if checkbox already added to list (HashMap cannot be used because the order is important!)
                                int contains = 0;
                                for(Lutemon lm : isCheckedList) {
                                    if(lm.getId().equals(cb.getTag())) {
                                        contains++;
                                        break;
                                    }
                                }
                                if(contains == 0) { isCheckedList.add(lm);}

                                Toast.makeText(context, "Valittu "+cb.getText()+ ", "+numberOfCheckboxesChecked+"/2", Toast.LENGTH_SHORT).show();
                            } else {
                                numberOfCheckboxesChecked--;
                                //remove unchecked from the list
                                ListIterator<Lutemon> it = isCheckedList.listIterator();
                                int i = 0;
                                while(it.hasNext()) {
                                    Lutemon tmp = it.next();
                                    if(tmp.getId().equals(cb.getTag())) {
                                        it.remove();
                                        break;
                                    }
                                }
                                Toast.makeText(context, "Poistettiin valinta, "+numberOfCheckboxesChecked+"/2", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


            }
        }

        //tvBattleMsg = findViewById(R.id.tvBattleMsg);


        findViewById(R.id.btnBattleEngage).setOnClickListener(view -> {

            //TBA: add a number in which order the checkboxes have been checked!


            if(isCheckedList.size() == 2) {
                //clear old messages
                if(battleMsgs.size()>0) {
                    battleMsgs.clear();
                    battleInfoAdapter.notifyDataSetChanged();
                }


            //check if at least two fighters have been selected

                //engage fight
                fight(isCheckedList.get(0), isCheckedList.get(1));

                //clear selected and perished
                for(int i=0; i<rg.getChildCount(); i++) {
                    CheckBox cb = (CheckBox)rg.getChildAt(i);
                    if(cb.isChecked()){ cb.toggle(); }
                }

                //notify RecycleView of the new data
                battleInfoAdapter.notifyDataSetChanged();

            } else {
                Toast.makeText(context, "Valitse 2 Lutemonia ennen taistelun aloittamista!", Toast.LENGTH_SHORT).show();
            }


        });

    }




    public void fight(Lutemon lutemonA, Lutemon lutemonB) {
        int noRounds = 0;
        double avgDamage[] = {0,0};

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
                msg = "\nERÄ " +noRounds+ "\n----------------\n";
            } else {
                msg = "ERÄ " +noRounds+ "\n----------------\n";
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
            msg = lutemonA.color+"("+lutemonA.name+") hyökkää "+lutemonB.color+ "("+lutemonB.name+")";
            //tvBattleMsg.append(msg+"\n");
            battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
            System.out.println(msg);

            lutemonB.defence(lutemonA);
            avgDamage[0]+=lutemonA.getAttack();

            lutemonA.setNoAttacks(lutemonA.getNoAttacks() + 1);
            lutemonB.setNoDefences(lutemonB.getNoDefences() + 1);
            //

            //check B's health
            if(lutemonB.health > 0) {
                //
                msg = lutemonB.color+"("+lutemonB.name+") selvisi hengissä.";
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
                msg = lutemonB.color+"("+lutemonB.name+") hyökkää "+lutemonA.color+ "("+lutemonA.name+")";
                //tvBattleMsg.append(msg+"\n");
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
                System.out.println(msg);

                lutemonA.defence(lutemonB);

                avgDamage[1]+=lutemonB.getAttack();

                lutemonB.setNoAttacks(lutemonB.getNoAttacks() + 1);
                lutemonA.setNoDefences(lutemonA.getNoDefences() + 1);
                //

                //check A's health
                if(lutemonA.health > 0) {
                    msg = lutemonA.color+"("+lutemonA.name+") selvisi hengissä.";
                    //tvBattleMsg.append(msg+"\n");
                    battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
                    System.out.println(msg);
                } else {
                    //A is dead, exit
                    //set A to DEAD status

                    //B gains experience for killing A
                    lutemonB.setAmountOfExperienceFought(lutemonB.getAmountOfExperienceFought() + 1);
                    lutemonB.setNoWins(lutemonB.getNoWins() + 1);
                    lutemonA.setNoLosses(lutemonA.getNoLosses() + 1);

                    msg = lutemonA.color+"("+lutemonA.name+") MENEHTYI, "+lutemonB.color+"("+lutemonB.name+")  VOITTI!";
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
                lutemonA.setAmountOfExperienceFought(lutemonA.getAmountOfExperienceFought() + 1);
                lutemonA.setNoWins(lutemonA.getNoWins() + 1);
                lutemonB.setNoLosses(lutemonB.getNoLosses() + 1);

                msg = lutemonB.color+"("+lutemonB.name+") MENEHTYI, "+lutemonA.color+"("+lutemonA.name+")  VOITTI!";
                //tvBattleMsg.append(msg+"\n");

                lutemonB.setLutemonState(Lutemon.LutemonState.PERISHED);
                battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, lutemonB));
                System.out.println(msg);

                //break;
            }

        } while(lutemonA.health > 0 && lutemonB.health > 0);


        msg = "Taisteltiin " +noRounds+" erää ja tehtiin vahinkoa per taistelija "
                +lutemonA.color+"("+lutemonA.name+"): " +avgDamage[0]+ " ja "
                +lutemonB.color+"("+lutemonB.name+"): "+avgDamage[1];

        //tvBattleMsg.append(msg+"\n");
        battleMsgs.add(new BattleMsgContainer(msg, BattleMsgContainer.MessageType.TEXT, null));
        System.out.println(msg);

        //tally up the results
        lutemonA.setTotalDamageDone(lutemonA.getTotalDamageDone() + (int)avgDamage[0]);
        lutemonB.setTotalDamageDone(lutemonB.getTotalDamageDone() + (int)avgDamage[1]);
        lutemonA.setNoRoundsFought(lutemonA.getNoRoundsFought() + noRounds);
        lutemonB.setNoRoundsFought(lutemonB.getNoRoundsFought() + noRounds);
        //

        //check level up eligibility
        int noLvlUpsA = lutemonA.checkLevelUp(2);
        int noLvlUpsB = lutemonA.checkLevelUp(2);
        if(noLvlUpsA > 0) {
            //announce if lutemon got a level up
            Toast.makeText(context,
                    lutemonA.getName()+"("+lutemonA.getColor()+") sai " +noLvlUpsA+ " level uppia!",
                    Toast.LENGTH_SHORT).show();
        }
        if(noLvlUpsB > 0) {
            //announce if lutemon got a level up
            Toast.makeText(context,
                    lutemonB.getName()+"("+lutemonB.getColor()+") sai " +noLvlUpsB+ " level uppia!",
                    Toast.LENGTH_SHORT).show();
        }


        //clean checklist
        for(Lutemon lm : lutemons) {
            for(int j=0; j<rg.getChildCount(); j++) {
                CheckBox cb = (CheckBox)rg.getChildAt(j);
                if(cb.getTag().equals(lm.getId()) && lm.getHealth() <= 0) {
                    //uncheck
                    if(cb.isChecked()){cb.toggle();}
                    //remove
                    rg.removeViewAt(j);
                }
            }
        }

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