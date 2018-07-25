package com.example.jh.mydessert.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.mydessert.R;

import java.util.ArrayList;

public class MatzzipAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<MatzzipAdapter.Matzzip> ml;
    LayoutInflater inf;

    public MatzzipAdapter(Context context, int layout, ArrayList<MatzzipAdapter.Matzzip> ml) {
        this.context = context;
        this.layout = layout;
        this.ml = ml;
        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ml.size();
    }

    @Override
    public Object getItem(int position) {
        return ml.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inf.inflate(layout, null);

        ImageView imageMatzzip = convertView.findViewById(R.id.imageMatzzip);
        TextView textShopName = convertView.findViewById(R.id.textShopName);
        TextView textAverageMark = convertView.findViewById(R.id.textAverageMark);
        Button btnRankMark = convertView.findViewById(R.id.btnRankMark);

        MatzzipAdapter.Matzzip mz = ml.get(position);
        imageMatzzip.setImageResource(mz.img);
        textShopName.setText(mz.name);
        textAverageMark.setText(mz.rank);
        btnRankMark.setText(mz.btn);

        return convertView;
    }

    public static class Matzzip {
        int img;
        String name;
        String rank;
        String btn;

        public Matzzip(int img, String name, String rank,String btn) {
            this.img = img;
            this.name = name;
            this.rank = rank;
            this.btn = btn;
        }

        public Matzzip() {
        }
    }
}
