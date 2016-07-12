package com.twigtotree.lingayatmatrimony.Mainflow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.twigtotree.lingayatmatrimony.Signup.BasicDetailsFragment;
import com.twigtotree.lingayatmatrimony.Signup.FamilyDetailsFragment;
import com.twigtotree.lingayatmatrimony.Signup.LocationDetailsFragment;
import com.twigtotree.lingayatmatrimony.Signup.ProfessionalDetailsFragment;
import com.twigtotree.lingayatmatrimony.Signup.ReligiousDetailsFragment;


/**
 * Created by sgoud1 on 7/9/16.
 */
public class MainFlowPagerAdapter extends FragmentPagerAdapter {

    public MainFlowPagerAdapter(FragmentManager fm) {
        super(fm);
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

        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }


    @Override
    public int getCount() {
        return 4;
    }

}