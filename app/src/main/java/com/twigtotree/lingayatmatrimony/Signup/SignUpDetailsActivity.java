package com.twigtotree.lingayatmatrimony.Signup;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.twigtotree.lingayatmatrimony.R;


public class SignUpDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    private SignUpDetailsPagerAdapter signUpDetailsPagerAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpDetailsPagerAdapter = new SignUpDetailsPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(12);
        mViewPager.setOffscreenPageLimit(3);


        mViewPager.setAdapter(signUpDetailsPagerAdapter);


        mViewPager.setCurrentItem(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = "Details";
                switch (position) {
                    case 0:
                        title = "Basic Details";
                        break;
                    case 1:
                        title = "Professional Details";
                        break;
                    case 2:
                        title = "Religious Details";
                        break;
                    case 3:
                        title = "Location Details";
                        break;
                    case 4:
                        title = "Family Details";
                        break;
                }
//                toolbar = (Toolbar) findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);
//
//                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
