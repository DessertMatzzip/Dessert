package com.example.jh.mydessert.CustomAdapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.jh.mydessert.Fragments.HomeFragment;
import com.example.jh.mydessert.Fragments.MyPageFragment;
import com.example.jh.mydessert.Fragments.NewsFragment;
import com.example.jh.mydessert.Fragments.RankFragment;

/**
 * Created by dbswn on 2018-07-31.
 */

public class MainAdapter extends FragmentPagerAdapter {
    public FragmentManager mFragmentManager;
    private int nowPosition;
    int max_count = 4;
    String arr_menus[] = {"홈", "랭킹", "소식", "마이페이지"};
    Fragment arr_fragment_menus [] ={ HomeFragment.newInstance(), RankFragment.newInstance(), NewsFragment.newInstance(), MyPageFragment.newInstance()};

    public MainAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        nowPosition = position;
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
                return arr_fragment_menus[position];

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return max_count;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
                return arr_menus[position];
            default:
                return null;
        }

//        return super.getPageTitle(position);
    }
}
