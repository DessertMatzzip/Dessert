package com.example.jh.mydessert;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {


    ArrayList<item> settingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        Button btnBack=(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        settingItem = new ArrayList<item>();
        item i;
        i = new item("내 프로필");
        settingItem.add(i);
        i = new item("취향 설정");
        settingItem.add(i);

        i = new item("알림");
        settingItem.add(i);
        i = new item("로그아웃");
        settingItem.add(i);
        i = new item("탈퇴하기");
        settingItem.add(i);

        MyListAdapter myAdapter=new MyListAdapter(this, R.layout.setting_listview, settingItem);

        ListView myList;
        myList=(ListView) findViewById(R.id.listViewSetting);
        myList.setAdapter(myAdapter);

    }
}

class item {
    String text;


    item(String text) {
        this.text = text;
    }
}

class MyListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<item> Src;
    int layout;

    public MyListAdapter(Context acontext, int alayout, ArrayList<item> aSrc) {
        context =acontext;
        Src = aSrc;
        layout = alayout;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Src.size();
    }

    @Override
    public Object getItem(int position) {
        return Src.get(position).text;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        TextView txt = (TextView) convertView.findViewById(R.id.textSetting);
        txt.setText(Src.get(position).text);

        Button btnSetting=(Button) convertView.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "버튼이 눌려짐", Toast.LENGTH_LONG).show();
            }
        });



        return convertView;
    }
}
