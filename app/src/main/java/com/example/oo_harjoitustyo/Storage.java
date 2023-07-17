package com.example.oo_harjoitustyo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Storage {


    private HashMap<String, Lutemon> lutemons = new HashMap<>();
    //private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private static Storage single_instance = null;

    public static synchronized Storage getInstance() {
        //singleton
        if(single_instance == null) {single_instance = new Storage();}
        return single_instance;
    }

    public Lutemon getLutemon(String id) {return lutemons.get(id);}

    public void addLutemon(Lutemon lutemon) {
        //Since id of a Lutemon is its time of creation it's
        //almost surely unique and can be used as a key
        lutemons.put(lutemon.getId(),lutemon);
    }

    public void removeById(String id) {
        lutemons.remove(id);
    }

    public void removeByName(String name) {
        //need to remove carefully since the structure of the HashMap is changed after each removal
        System.out.println("TBD!");
    }

    public Lutemon removeReturn(int id) {
        Lutemon lutemon = lutemons.get(id);
        lutemons.remove(id);
        return lutemon;
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

    public HashMap<String,Lutemon> getLutemons() {return lutemons;}
    public ArrayList<Lutemon> getLutemonsList() {
        Collection<Lutemon> values = lutemons.values();
        return (
                new ArrayList<Lutemon>(values)
        );
    }


    //load data from file
    public void loadStorage(Context context) {
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (HashMap<String, Lutemon>) ois.readObject();
            ois.close();
            System.out.println("File read successful!");
        } catch (FileNotFoundException e) {
            System.out.println("File read failed!: "+ e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File read failed!: " +e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("File read failed!: " +e);
            e.printStackTrace();
        }
    }


    //save data to file
    public void saveStorage(Context context) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            oos.writeObject(lutemons);
        } catch (FileNotFoundException e) {
            System.out.println("Saving users failed!: "+ e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Saving users failed!: " +e);
        } finally {
            //
        }
    }

}
