package com.example.jh.mydessert.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jh.mydessert.R;
import com.example.jh.mydessert.RankMapActivity;
import com.example.jh.mydessert.RankRegionalActivity;

public class RankFragment extends Fragment {

    Button btnRankRegional;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        btnRankRegional = (Button) view.findViewById(R.id.btnRankRegional);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnRankRegional.setOnClickListener(btnRankRegionalListener);
    }

    public static RankFragment newInstance() {
        Bundle args = new Bundle();
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Button.OnClickListener btnRankRegionalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), RankMapActivity.class);
            startActivity(intent);
        }
    };
}
