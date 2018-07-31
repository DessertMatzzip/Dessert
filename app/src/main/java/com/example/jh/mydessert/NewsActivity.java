package com.example.jh.mydessert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jh.mydessert.CustomAdapters.NoticeAdapter;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ArrayList<NoticeAdapter.Notice> arrayList_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        arrayList_notice = new ArrayList<NoticeAdapter.Notice>();
        Button btnAnnouncement = findViewById(R.id.btnAnnouncement);
        Button btnNotice = findViewById(R.id.btnNotice);

        NoticeAdapter adapter = new NoticeAdapter(
                getApplicationContext(),
                R.layout.notice_listview,
                arrayList_notice);

        ListView listView_newsList = findViewById(R.id.newsList);
        listView_newsList.setAdapter(adapter);

        listView_newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "아이템 클릭, position : " + position + ", id: " + id);
            }
        });
    }
}

