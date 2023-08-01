package com.example.oo_harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oo_harjoitustyo.fragments.Battle;
import com.example.oo_harjoitustyo.fragments.Home;
import com.example.oo_harjoitustyo.fragments.Perished;
import com.example.oo_harjoitustyo.fragments.Train;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoveLutemons extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);

        //initialize TabLayout and ViewPager2
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        rg = findViewById(R.id.rgDestinations);


        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);

        /*
        set tab id's so that they can be referred globally!
         */
        viewPager2.setAdapter(tabPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    //Go back to main view
    public void switchToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public Fragment getVisibleFragment() {
        //https://stackoverflow.com/questions/9294603/how-do-i-get-the-currently-displayed-fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    //move selected Lutemons from visited fragment to targeted fragment

    public void moveSelected(View view) {

        /*
        Problem with this approach is that the destination fragment needs to have its
        onCreate called before sending. This is not done until it's clicked!
         */

        //Get currently active fragment
        Fragment onFragment = getVisibleFragment();
        ArrayList<Lutemon> lutemons = null;
        Lutemon.LutemonState lutemonCurr = null;
        RadioGroup rgOnFragment = null;
        if (onFragment instanceof Home) {
            lutemons = ((Home)onFragment).getLutemons();
            rgOnFragment = ((Home)onFragment).getRG();
            lutemonCurr = Lutemon.LutemonState.HOME;
        } else if(onFragment instanceof Train) {
            lutemons = ((Train)onFragment).getLutemons();
            rgOnFragment = ((Train)onFragment).getRG();
            lutemonCurr = Lutemon.LutemonState.TRAIN;
        } else if(onFragment instanceof Battle) {
            lutemons = ((Battle)onFragment).getLutemons();
            rgOnFragment = ((Battle)onFragment).getRG();
            lutemonCurr = Lutemon.LutemonState.BATTLE;
        } else if(onFragment instanceof Perished) {
            lutemons = ((Perished)onFragment).getLutemons();
            rgOnFragment = ((Perished)onFragment).getRG();
            lutemonCurr = Lutemon.LutemonState.PERISHED;
        }

        //Get checkboxes and remove those being shipped from this fragment
        if (rgOnFragment.getChildCount() > 0) {
            //Get destination fragment
            Fragment fragment = null;
            Lutemon.LutemonState lutemonDest = null;
            switch (rg.getCheckedRadioButtonId()) {
                case R.id.rbHome:
                    //fragment = new Home();
                    //fragment = getSupportFragmentManager().findFragmentByTag("f0");
                    lutemonDest = Lutemon.LutemonState.HOME;
                    break;
                case R.id.rbTrain:
                    //fragment = new Train();
                    //fragment = getSupportFragmentManager().findFragmentByTag("f1");
                    lutemonDest = Lutemon.LutemonState.TRAIN;
                    break;
                case R.id.rbBattle:
                    //fragment = new Battle();
                    //fragment = getSupportFragmentManager().findFragmentByTag("f2");
                    lutemonDest = Lutemon.LutemonState.BATTLE;
                    break;
                    /*
                    //Don't allow moves to Perished! (Also disabled the button)
                case R.id.rbPerished:
                    //fragment = new Perished();
                    //fragment = getSupportFragmentManager().findFragmentByTag("f3");
                    lutemonDest = Lutemon.LutemonState.PERISHED;
                    break;
                     */
            }

            //check which checkboxes are checked
            List <String> isChecked = new ArrayList<>(); //keep track of selected lutemons' ids
            for (int i = 0; i < rgOnFragment.getChildCount(); i++) {
                CheckBox cb = (CheckBox)rgOnFragment.getChildAt(i);
                if (cb.isChecked() &&
                    lutemonDest != lutemonCurr //don't allow moves to itself
                ) {


                    //get id
                    Lutemon lutemonInFragment = lutemons.get(i);
                    String id = lutemonInFragment.getId();
                    //note: cb tag and id should match!
                    assert(id.equals(String.valueOf(cb.getTag())));
                    //
                    isChecked.add(id);

                    //change the state
                    Lutemon lutemonInStorage = Storage.getInstance().getLutemon(id);
                    lutemonInStorage.setLutemonState(lutemonDest);

                    //TBA: remove those from the list
                    System.out.println("Lutemon "
                            +id+" state is "
                            +Storage.getInstance().getLutemon(id).getLutemonState());
                }
            }

            //clear
            if(isChecked.size() > 0) {
                rgOnFragment.clearCheck();
                int id = -1;
                //remove designated radio buttons
                for (int i = 0; i < isChecked.size(); i++) {
                    for(int j = 0; j < rgOnFragment.getChildCount(); j++) {
                        CheckBox cb = (CheckBox)rgOnFragment.getChildAt(j);
                        if(cb.getTag().equals(isChecked.get(i))) {
                            id = j;
                            break;
                        }
                    }
                    rgOnFragment.removeViewAt(id);
                    //lutemons.remove(isChecked.get(i));
                    //rgOnFragment.removeViewAt(isChecked.get(i)); //for multiple this doesn't work because the location changes
                    //lutemons.remove(isChecked.get(i));
                }

                //announce transfer
                Toast.makeText(view.getContext(),
                        "Moved " +isChecked.size()+
                                " lutemons from "
                                +lutemonCurr+" to "
                                +lutemonDest, Toast.LENGTH_SHORT).show();
            }

        } else {
            System.out.println("No data to be sent from MoveLutemons");
        }

        //reset/clear the button
        rg.clearCheck();
    }


    private static final long serialVersionUID = 123456789L;


    //return complete list of fragments
    public List<Fragment> getFragments() {
        return getSupportFragmentManager().getFragments();
    }
}