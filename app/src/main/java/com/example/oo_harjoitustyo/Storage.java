package com.example.oo_harjoitustyo;

import android.content.Context;
import android.widget.Toast;

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

    //get lutemons by color
    public HashMap<Lutemon.Color,ArrayList<Lutemon>> getLutemonsByColor() {
        HashMap<Lutemon.Color,ArrayList<Lutemon>> lutemonByColor = new HashMap<>();

        //colors
        for(Lutemon.Color color : Lutemon.Color.values()) {
            ArrayList<Lutemon> tmp = new ArrayList<>();

            //fetch each by color
            lutemons.forEach( (key,lutemon) -> {
                if(lutemon.getColor() == color) {
                    tmp.add(lutemon);
                }
            });

            //append array list to hashmap
            lutemonByColor.put(color,tmp);
        }
        return lutemonByColor;
    }


    //get lutemons by state
    public HashMap<Lutemon.LutemonState,ArrayList<Lutemon>> getLutemonsByState() {
        HashMap<Lutemon.LutemonState,ArrayList<Lutemon>> lutemonByState = new HashMap<>();

        //states
        for(Lutemon.LutemonState state : Lutemon.LutemonState.values()) {
            ArrayList<Lutemon> tmp = new ArrayList<>();

            //fetch each by color
            lutemons.forEach( (key,lutemon) -> {
                if(lutemon.getLutemonState() == state) {
                    tmp.add(lutemon);
                }
            });

            //append array list to hashmap
            lutemonByState.put(state,tmp);
        }
        return lutemonByState;
    }


    //load data from file
    public void loadStorage(Context context) {
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (HashMap<String, Lutemon>) ois.readObject();
            ois.close();

            System.out.println("File read successful!");
            Toast.makeText(context, "Lutemonit ladattiin tiedostosta 'lutemons.data' onnistuneesti!", Toast.LENGTH_SHORT).show();
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
            oos.close();

            System.out.println("File write successful!");
            Toast.makeText(context, "Lutemonit tallennettiin tiedostoon 'lutemons.data' onnistuneesti!", Toast.LENGTH_SHORT).show();
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
