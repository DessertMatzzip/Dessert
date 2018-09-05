package com.example.jh.mydessert;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class StorePageActivity extends AppCompatActivity {
    private Button btnAlteration;
    private Button btnCancel;
    private PopupWindow pwindo;
    final Point p=new Point();
    private int mWidthPixels, mHeightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        btnAlteration=(Button) findViewById(R.id.btnAlteration);
        btnAlteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p != null) {
                    showpopupwindows(StorePageActivity.this, p);
                }
            }
        });
            }

            public void onWindowsFoucusChanged(boolean hasFocus)
            {
                int location[]=new int [2];

                btnAlteration.getLocationOnScreen(location);

                p.x=location[0];
                p.y=location[1];
        }

        private void showpopupwindows(final Activity context, Point p)
        {
            int popupwidth=800;
            int popupheight=450;

            LinearLayout viewgroup=(LinearLayout) context.findViewById(R.id.popup_element);
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            View layout=inflater.inflate(R.layout.store_info_alter_pop_up_window,viewgroup);

           final PopupWindow window=new PopupWindow(context);
           window.setContentView(layout);
           window.setWidth(popupwidth);
           window.setHeight(popupheight);
           window.setFocusable(true);

           int offset_x=150;
           int offset_y=550;

           window.showAtLocation(layout,Gravity.NO_GRAVITY,p.x+offset_x,p.y+offset_y);

           Button btnCancel=(Button) layout.findViewById(R.id.btnCancel);
           btnCancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   window.dismiss();
               }
           });

        }

}
