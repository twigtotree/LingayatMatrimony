package com.twigtotree.lingayatmatrimony.Signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twigtotree.lingayatmatrimony.R;


/**
 * Created by sgoud1 on 7/9/16.
 */
public class LocationDetailsFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup_location_details, container, false);

        return rootView;
    }
}
