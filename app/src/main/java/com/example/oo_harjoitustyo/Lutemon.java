package com.example.oo_harjoitustyo;

public class Lutemon {
    protected String name;
    protected String color;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;

    protected int imageSrc;
    //private int idCounter;

    public Lutemon(String name, String color, int attack, int defence, int experience, int health, int maxHealth, int id) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = id;
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

    public String getColor() {
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

    public int getId() {
        return id;
    }


}
