package com.example.jh.mydessert;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.jh.mydessert.Fragments.AnnoucementFragment;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(AnnoucementFragment.newInstance(createLowerCase()));
        fragmentList.add(AnnoucementFragment.newInstance(createUpperCase()));

        NewsAdapter adapter=new NewsAdapter(getSupportFragmentManager(),
                fragmentList,
                new String[] {"공지", "알림"});
        viewPager.setAdapter(adapter);


        tabLayout.setupWithViewPager(viewPager);
    }

    private List<String> createLowerCase() {
        List<String> list=new ArrayList<>();
        char ch='a';
        for (char i=ch; i<='z'; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private List<String> createUpperCase() {
        List<String> list=new ArrayList<>();
        char ch='A';
        for (char i=ch; i<='Z'; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private static class NewsAdapter extends FragmentPagerAdapter {
        private List<Fragment> mmFragmentList;
        private String [] mmPageTitles;

        public NewsAdapter(android.support.v4.app.FragmentManager fm,
                           List<Fragment> fragmentList,
                           String[] pageTitles) {
            super(fm);
            mmFragmentList=fragmentList;
            mmPageTitles=pageTitles;
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return mmFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mmPageTitles[position];
        }
    }
}


