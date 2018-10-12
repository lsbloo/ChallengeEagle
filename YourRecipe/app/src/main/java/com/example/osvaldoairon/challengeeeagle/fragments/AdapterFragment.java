package com.example.osvaldoairon.challengeeeagle.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.osvaldoairon.challengeeeagle.fragments.FragmentShowRecipe;
import com.example.osvaldoairon.challengeeeagle.fragments.FragmentRecipesAliments;


public class AdapterFragment extends FragmentStatePagerAdapter{

    private String[] titulos;

    public AdapterFragment(FragmentManager fm , String[] titulos) {
        super(fm);
        this.titulos=titulos;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
                return null;
            case 0:
                return new FragmentShowRecipe();
            case 1:
                return new FragmentRecipesAliments();



        }
    }

    @Override
    public int getCount() {
        return this.titulos.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return this.titulos[position];

    }

}
