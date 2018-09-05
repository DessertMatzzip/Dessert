package com.example.jh.mydessert;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;

    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        keywordScreen=(PreferenceScreen) findPreference("keywordScreen");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (prefs.getBoolean("keyword", false)) {
            keywordScreen.setSummary("사용");
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("keyword")) {
                if (prefs.getBoolean("keyword", false)) {
                    keywordScreen.setSummary("사용");
                } else {
                    keywordScreen.setSummary("사용안함");
                }

                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }
        }


    };

}
