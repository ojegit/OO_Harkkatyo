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

    private int lastLevelUp; //exp when last level up happened
    private int noLevelUps; //no of level ups so far

    //bookkeeping
    private int noWins; //number of wins
    private int noLosses; //number of losses
    private int noTrained; //number of times trained
    private int amountOfExperienceTrained; //experience accrued from training
    private int amountOfExperienceFought; //experience accrued from fighting
    private int noAttacks;
    private int noDefences;
    private int totalDamageDone;
    private int noTimesRevived;
    private int noRoundsFought;
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


    public int checkLevelUp(int expGap) {
        int totalExp = getAmountOfExperienceFought() + getAmountOfExperienceTrained();

        //check exp gap aka if level up is possible and make sure
        //they're only given once for the same exp
        if(this.lastLevelUp != totalExp) {
            //no level ups to be applied since last update
            int noLvls = (int)Math.floor((totalExp - this.lastLevelUp) / expGap);
            //
            for(int i = 0; i < noLvls; i++) {
                //level up effects
                this.attack += 2;
                this.defence += 1;
                //

                //accumulate no level ups
                this.noLevelUps++;
            }

            //update last level up
            this.lastLevelUp = totalExp;
            return noLvls;
        } else {
            //no level ups necessary this time
            return 0;
        }

    }
    public LutemonState getLutemonState() {return lutemonState;}
    public void setLutemonState(LutemonState lutemonState) {this.lutemonState = lutemonState;}

    public void resetHealth() {
        this.health = new Integer(maxHealth); //copy the value
    }

    public int attack(){return -1;}

    //
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
    //

    //stats

    public int getNoRoundsFought() {return noRoundsFought;}
    public void setNoRoundsFought(int noRoundsFought) {this.noRoundsFought = noRoundsFought;}
    public int getAmountOfExperienceFought() {    return amountOfExperienceFought;}
    public void setAmountOfExperienceFought(int amountOfExperienceFought) {this.amountOfExperienceFought = amountOfExperienceFought;}
    public int getNoWins() {return noWins;}
    public void setNoWins(int noWins) {this.noWins = noWins;}
    public int getNoLosses() {return noLosses;}
    public void setNoLosses(int noLosses) {this.noLosses = noLosses;}
    public int getNoTrained() {return noTrained;}
    public void setNoTrained(int noTrained) {this.noTrained = noTrained;}
    public int getAmountOfExperienceTrained() {return amountOfExperienceTrained;}
    public void setAmountOfExperienceTrained(int amountOfExperienceTrained) {this.amountOfExperienceTrained = amountOfExperienceTrained;}
    public int getNoAttacks() {return noAttacks;}
    public void setNoAttacks(int noAttacks) {this.noAttacks = noAttacks;}
    public int getNoDefences() {return noDefences;}
    public void setNoDefences(int noDefences) {this.noDefences = noDefences;}
    public int getTotalDamageDone() {return totalDamageDone;}
    public void setTotalDamageDone(int totalDamageDone) {this.totalDamageDone = totalDamageDone;}
    public int getNoTimesRevived() {return noTimesRevived;}
    public void setNoTimesRevived(int noTimesRevived) {this.noTimesRevived = noTimesRevived;}
    //

    @Override
    public String toString(){
        return id+": "+color+"("+name+") att: " +attack+"; def: "+defence+ "; exp: "+experience+"; health: "+health+ "/"+maxHealth;
    }
}
