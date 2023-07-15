package com.example.oo_harjoitustyo;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    //private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private static Storage single_instance = null;

    public static synchronized Storage getInstance() {
        //singleton
        if(single_instance == null)
            single_instance = new Storage();
        return single_instance;
    }

    public Lutemon getLutemon(int id) {return lutemons.get(id);}
    public void addLutemon(Lutemon lutemon) {lutemons.add(lutemon);}
    public Lutemon getLutemons(){return lutemons;}


}
