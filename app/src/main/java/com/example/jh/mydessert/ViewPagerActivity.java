package com.example.jh.mydessert;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager=(ViewPager) findViewById(R.id.pager);

        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           switch (position) {
               case 0:
                   break;
               case 1:
                   break;
           }
           return null;
    }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
