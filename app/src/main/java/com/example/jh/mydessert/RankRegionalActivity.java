package com.example.jh.mydessert;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RankRegionalActivity extends AppCompatActivity {

    ArrayList<MatzzipAdapter.Matzzip> ml = new ArrayList<MatzzipAdapter.Matzzip>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_regional);

        Button btnRankRegional = findViewById(R.id.btnRankRegional);
        Button btnRankMark = findViewById(R.id.btnRankMark);

        MatzzipAdapter.Matzzip ml1 = new MatzzipAdapter.Matzzip();
        ml1.img = R.drawable.back;
        ml1.name = "맛집";
        ml1.rank = "평점";
        ml1.btn = "평가하기";


        MatzzipAdapter adapter = new MatzzipAdapter(
                getApplicationContext(),
                R.layout.matzzip_listview,
                ml);

        ListView MatzzipList = findViewById(R.id.MatzzipList);
        MatzzipList.setAdapter(adapter);

        MatzzipList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "아이템 클릭, position : " + position + ", id: " + id);
            }
        });


    }
}

class MatzzipAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Matzzip> ml;
    LayoutInflater inf;

    public MatzzipAdapter(Context context, int layout, ArrayList<Matzzip> ml) {
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

        Matzzip mz = ml.get(position);
        imageMatzzip.setImageResource(mz.img);
        textShopName.setText(mz.name);
        textAverageMark.setText(mz.rank);
        btnRankMark.setText(mz.btn);

        return convertView;
    }

    static class Matzzip {
        int img;
        String name;
        String rank;
        String btn;

        public Matzzip(int img, String name, String rank) {
            this.img = img;
            this.name = name;
            this.rank = rank;
            this.btn = btn;
        }

        public Matzzip() {
        }
    }
}
