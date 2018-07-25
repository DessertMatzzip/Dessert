package com.example.jh.mydessert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jh.mydessert.CustomAdapters.*;

import java.util.ArrayList;

public class RankRegionalActivity extends AppCompatActivity {

    ArrayList<MatzzipAdapter.Matzzip> arrayList_matzzip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_regional);
        arrayList_matzzip = new ArrayList<MatzzipAdapter.Matzzip>();
        Button btnRankRegional = findViewById(R.id.btnRankRegional);
        Button btnRankMark = findViewById(R.id.btnRankMark);

        MatzzipAdapter.Matzzip matzzip = new MatzzipAdapter.Matzzip(R.drawable.back, "맛집", "평점", "평가하기");
        arrayList_matzzip.add(matzzip);

        MatzzipAdapter adapter = new MatzzipAdapter(
                getApplicationContext(),
                R.layout.matzzip_listview,
                arrayList_matzzip);

        ListView listView_matzzip = findViewById(R.id.MatzzipList);
        listView_matzzip.setAdapter(adapter);

        listView_matzzip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "아이템 클릭, position : " + position + ", id: " + id);
            }
        });


    }
}

