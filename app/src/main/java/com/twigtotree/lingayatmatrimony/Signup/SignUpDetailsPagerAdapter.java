package com.twigtotree.lingayatmatrimony.Signup;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sgoud1 on 7/9/16.
 */

public class SignUpDetailsPagerAdapter extends FragmentPagerAdapter {

    Context mApplicationContext;
    public SignUpDetailsPagerAdapter(FragmentManager fm, Context applicationContext) {
        super(fm);
        mApplicationContext = applicationContext;
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = null;
        switch(pos) {

            case 0:
                fragment = new BasicDetailsFragment();
               break;
            case 1:
                fragment = new ProfessionalDetailsFragment();
                break;
            case 2:
                fragment = new ReligiousDetailsFragment();
                break;
            case 3:
                fragment = new LocationDetailsFragment();
                break;
            case 4:
                fragment = new FamilyDetailsFragment();
                break;
            case 5 :
                fragment = new PhotoUploadFragment(mApplicationContext);

        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Basic Details";
            case 1:
                return "Professional Details";
            case 2:
                return "Religious Details";
            case 3:
                return "Location Details";
            case 4:
                return "Family Details";
        }
        return null;
    }
}