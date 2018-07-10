package com.bonusteam.favtrack.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deryan Cruz on 10/07/2018.
 */

public class SectionsAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> TitleList=new ArrayList<>();
    private final int rotation=0;
    private final boolean validateScreen=true;

    //GETTERS
    public int getRotation() {
        return rotation;
    }

    public boolean isValidateScreen() {
        return validateScreen;
    }

    public SectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        TitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TitleList.get(position);
    }


    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
