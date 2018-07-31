package com.example.jh.mydessert.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jh.mydessert.R;
import com.example.jh.mydessert.SearchActivity;

public class HomeFragment extends Fragment {

    Button btn_search;
    LinearLayout layout_searchContent;

    public HomeFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        layout_searchContent = (LinearLayout) view.findViewById(R.id.layout_searchContent);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btn_search.setOnClickListener(btn_search_listener);
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Button.OnClickListener btn_search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);


        }
    };
}
