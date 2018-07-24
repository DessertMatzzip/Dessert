package com.example.jh.mydessert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnSignUP = findViewById(R.id.btnSignUp);
        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });

        Button btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(myIntent);
            }
        });
    }


        private void setDialog()
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.activity_join, null);
            builder.setView(view);
            final Button submit = view.findViewById(R.id.btn_Submit);
            final EditText email = view.findViewById(R.id.edittextEmailAddress);
            final EditText password = view.findViewById(R.id.edittextPassword);

            final AlertDialog dialog=builder.create();
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strEmail=email.getText().toString();
                    String strPassword=password.getText().toString();
                    Toast.makeText(getApplicationContext(), strEmail+"/"+strPassword, Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            });

            AlertDialog msgDialog = builder.create();
            msgDialog.show();
        }
    }

