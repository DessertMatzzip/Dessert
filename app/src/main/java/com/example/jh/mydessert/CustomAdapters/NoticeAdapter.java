package com.example.jh.mydessert.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jh.mydessert.R;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<NoticeAdapter.Notice> noticeList;
    LayoutInflater inflater;

    public NoticeAdapter(Context context, int layout, ArrayList<NoticeAdapter.Notice> noticeList) {
        this.context = context;
        this.layout = layout;
        this.noticeList = noticeList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(layout, null);

        TextView textAnnouncement = convertView.findViewById(R.id.textAnnouncement);

        NoticeAdapter.Notice notice = noticeList.get(position);

        return convertView;

    }

    public static class Notice {
        String text;

        public Notice(String text) {
            this.text = text;
        }
    }
}
