package com.example.oo_harjoitustyo;
import java.io.Serializable;

public class LutemonStatistic implements Serializable {
    //
    private static final long serialVersionUID = 123456789L;
    //

    private Lutemon.Color color;
    private int totalNoWins; //number of winds (health > 0 after the battle concludes)
    private int totalNoLosses; //number of losses (health <= after the battle concludes)
    private int totalNoTrained; //number of times trained
    private int totalAmountOfExperienceTrained; //experience accrued from training
    private int totalNoAttacks;
    private int totalNoDefences;
    private int totalDamageDone;
    private int totalNoTimesReset;

    public LutemonStatistic(Lutemon.Color _color) {color = _color;} //Note: color is mandatory!

    //
    public void incrementTotalNoTrained() {this.totalNoTrained++;}
    public void incrementTotalNoWins() {this.totalNoWins++;}
    public void incrementTotalNoLosses() {this.totalNoLosses++;}
    public void incrementTotalAmountOfExperience() {this.totalAmountOfExperienceTrained++;}
    public void incrementTotalNoAttacks() {this.totalNoAttacks++;}
    public void incrementTotalNoDefences() {this.totalNoDefences++;}
    public void incrementTotalDamageDone(int amount) {this.totalDamageDone+=amount;}
    public void incrementTotalNoTimesReset() {this.totalNoTimesReset++;}
    //
    public int getTotalNoWins() {return totalNoWins;}
    public int getTotalNoLosses() {return totalNoLosses;}
    public int getTotalNoTrained() {return totalNoTrained;}
    public int getTotalAmountOfExperienceTrained() {return totalAmountOfExperienceTrained;}
    public int getTotalNoAttacks() {return totalNoAttacks;}
    public int getTotalNoDefences() {return totalNoDefences;}
    public int getTotalDamageDone() {return totalDamageDone;}
    public int getTotalNoTimesReset() {return totalNoTimesReset;}
    public Lutemon.Color getColor() {return color;}

}
