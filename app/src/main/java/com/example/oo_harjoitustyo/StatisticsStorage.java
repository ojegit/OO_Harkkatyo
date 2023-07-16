package com.example.oo_harjoitustyo;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class StatisticsStorage {

    private HashMap<Lutemon.Color,LutemonStatistic> lutemonStatistics = new HashMap<>();

    private static StatisticsStorage single_instance = null;

    public static synchronized StatisticsStorage getInstance() {
        //singleton
        if(single_instance == null) {single_instance = new StatisticsStorage();}
        return single_instance;
    }

    //fetch container
    public LutemonStatistic getLutemonStatistic(Lutemon.Color color) {
        if(lutemonStatistics.containsKey(color)){
            return lutemonStatistics.get(color);
        } else {
            System.out.println(color +" doesn't exist!");
            return null;
        }
    }

    //add new container
    public void addLutemonStatistic(Lutemon.Color color) {
        if(!lutemonStatistics.containsKey(color)){
            lutemonStatistics.put(color, new LutemonStatistic(color));
        } else {
            System.out.println(color +" already exists!");
        }
    }

    //load data from file
    public void loadStatisticsStorage(Context context) {
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("statistics.data"));

            //how t
            lutemonStatistics = (HashMap<Lutemon.Color, LutemonStatistic>) ois.readObject();

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
    public void saveStatisticsStorage(Context context) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("statistics.data", Context.MODE_PRIVATE));
            oos.writeObject(lutemonStatistics);
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
