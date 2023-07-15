package com.example.oo_harjoitustyo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Storage {

    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    //private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private static Storage single_instance = null;

    public static synchronized Storage getInstance() {
        //singleton
        if(single_instance == null) {single_instance = new Storage();}
        return single_instance;
    }

    public Lutemon getLutemon(int id) {return lutemons.get(id);}
    public void addLutemon(Lutemon lutemon) {
        //note: id/key of the HashMap VS id of Lutemon!
        lutemons.put(lutemon.getId(),lutemon);
    }
    public void listLutemons(){
        lutemons.forEach((key,obj) ->
        {
                System.out.println("key: "+key+ ", " +
                        "Lutemon: {" +
                        "Id: "+obj.getId()+" "+
                        "Color: "+obj.getColor()+" "+
                        "Experience: "+obj.getExperience()+" "+
                        "Health: "+obj.getHealth()+
                        "}");
            });
    }

    public HashMap<Integer,Lutemon> getLutemons() {return lutemons;}
    public ArrayList<Lutemon> getLutemonsList() {
        Collection<Lutemon> values = lutemons.values();
        return (
                new ArrayList<Lutemon>(values)
        );
    }

}
