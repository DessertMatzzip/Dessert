package com.example.jh.mydessert.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jh.mydessert.R;
import com.example.jh.mydessert.SettingActivity;

public class MyPageFragment extends Fragment {

    Button btnSearch;
    Context context;

    public MyPageFragment() { super();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        btnSearch=(Button) view.findViewById(R.id.btnSearch);
        context=view.getContext();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        btnSearch.setOnClickListener(btnSearchListener);
    }

    public static MyPageFragment newInstance() {
        Bundle args = new Bundle();
        MyPageFragment fragment = new MyPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Button.OnClickListener btnSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        }
    };
}

