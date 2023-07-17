package com.example.oo_harjoitustyo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Lutemon implements Serializable {
    //
    private static final long serialVersionUID = 123456789L;
    //

    public enum LutemonState {
        HOME,
        TRAIN,
        BATTLE,
        PERISHED
    }

    //Available colors
    public static enum Color {
        BLACK,
        GREEN,
        ORANGE,
        PINK,
        WHITE
    }

    protected String name;
    protected Color color;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected String id;
    protected LutemonState lutemonState;

    protected int imageSrc;

    //no instances
    private static int idCounter;

    //bookkeeping
    public int noWins; //number of winds (health > 0 after the battle concludes)
    public int noLosses; //number of losses (health <= after the battle concludes)
    public int noTrained; //number of times trained
    public int amountOfExperienceTrained; //experience accrued from training
    public String createdTimestamp; //timestamp when instance was created
    public String removedTimestamp; //timestamp when instance was deleted (if kept alive then may never be deleted)
    public int noAttacks;
    public int noDefences;
    public int totalDamageDone;
    public int noTimesReset;

    public int noLastOpponentsSaved;
    public ArrayList<Lutemon> lastOpponents;
    //

    public Lutemon(){}
    public Lutemon(String name, Color color, int attack, int defence, int experience, int health, int maxHealth) {
        idCounter++;
        this.lutemonState = LutemonState.HOME;
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;

        //use time (in seconds) as unique id
        this.id = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));;
    }

    //Methods
    public void defence(Lutemon lutemon) {
        //Sets health based on
        //-attacker's "attack" stat
        //-defender's "health" amount
        //-defender's "defence" stat
        //If net health is positive then survives, else perishes.
        int netHealth = this.health - (lutemon.attack - this.defence);
        this.health = netHealth;
    }

    public LutemonState getLutemonState() {return lutemonState;}
    public void setLutemonState(LutemonState lutemonState) {this.lutemonState = lutemonState;}

    public void resetHealth() {
        this.health = new Integer(maxHealth); //copy the value
    }

    public int attack(){return -1;}
    public int getNumberOfCreatedLutemons(){return -1;}

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getExperience() {
        return experience;
    }


    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getId() {
        return id;
    }


    public static int getIdCounter() {return idCounter;}




    @Override
    public String toString(){
        return id+": "+color+"("+name+") att: " +attack+"; def: "+defence+ "; exp: "+experience+"; health: "+health+ "/"+maxHealth;
    }
}
