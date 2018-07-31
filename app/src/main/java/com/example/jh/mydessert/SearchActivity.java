package com.example.jh.mydessert;

import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    Button btn_cancel;
    EditText edit_search;
    LinearLayout layout_searchContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        edit_search = (EditText) findViewById(R.id.edit_search);
        layout_searchContent = (LinearLayout) findViewById(R.id.layout_searchContent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_cancel.setOnClickListener(btn_search_listener);
        edit_search.setOnEditorActionListener(edit_search_listener);
        edit_search.setOnKeyListener(edit_search_key_listener);
    }

    Button.OnClickListener btn_search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    View.OnKeyListener edit_search_key_listener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_ENTER) {
                doSearch();

            }
            return false;
        }
    };
    TextView.OnEditorActionListener edit_search_listener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                doSearch();

            }
            return false;
        }
    };

    private void doSearch() {
        if (layout_searchContent.getVisibility() == View.INVISIBLE) {
            layout_searchContent.setVisibility(View.VISIBLE);
        }
    }

}