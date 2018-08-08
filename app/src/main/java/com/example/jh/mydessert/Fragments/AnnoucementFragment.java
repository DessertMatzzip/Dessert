package com.example.jh.mydessert.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jh.mydessert.R;

import java.io.Serializable;
import java.util.List;

public class AnnoucementFragment extends Fragment {  // Fragement는 그냥 Layout 덩어리

    private List <String> mData;

    public static AnnoucementFragment newInstance(List<String> data) {
        AnnoucementFragment fragment=new AnnoucementFragment();

        Bundle bundle=new Bundle();
        bundle.putSerializable("data", (Serializable) data);

        fragment.setArguments(bundle);
        return  fragment;
    }


    // Activity의 onCreate와 같은부분 : 레이아웃 완성
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_annoucement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); // Fragment 생명주기에는 없음. 위와 아래 있는 것 사이

        ListView listViewAnnouncemnet = (ListView) view.findViewById(R.id.listViewAnnouncement);

        Bundle bundle=getArguments();
        mData=(List<String>)bundle.getSerializable("data");

        NewsAdapter adapter=new NewsAdapter(mData);

        listViewAnnouncemnet.setAdapter(adapter);
    }

    private static class NewsAdapter extends BaseAdapter {

        private final List<String> mmData;

        public NewsAdapter(List<String> data) {
            mmData = data;
        }

        @Override
        public int getCount() {
            return mmData.size();
        }

        @Override
        public Object getItem(int position) {
            return mmData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);

                viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);

                convertView.setTag(viewHolder);
            } else {
                viewHolder=(ViewHolder) convertView.getTag();
            }

            String data=mmData.get(position);

            viewHolder.textView.setText(data);

            return convertView;
        }
    }

    private static class ViewHolder {
        TextView textView;
    }


}
