package com.example.jh.mydessert;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.mydessert.CustomAdapters.MainAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabLayout.setupWithViewPager(viewPager);
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }
}