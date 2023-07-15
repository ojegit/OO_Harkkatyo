package com.example.oo_harjoitustyo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.oo_harjoitustyo.fragments.Battle;
import com.example.oo_harjoitustyo.fragments.Home;
import com.example.oo_harjoitustyo.fragments.Perished;
import com.example.oo_harjoitustyo.fragments.Train;

public class TabPagerAdapter extends FragmentStateAdapter {


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Train();
            case 2:
                return new Battle();
            default:
                return new Perished();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
