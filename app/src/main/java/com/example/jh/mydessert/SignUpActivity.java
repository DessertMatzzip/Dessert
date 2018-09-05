package com.example.jh.mydessert;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jh.mydessert.Connections.PostConnection;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp;
    Button btnCancel;

    private String a;
    private String b;

    EditText editTextEmailId;
    EditText editTextEmailPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText editTextSex = (EditText) findViewById(R.id.editTextSex);
        EditText editTextRegion = (EditText) findViewById(R.id.editTextRegion);
        EditText editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        EditText editTextAge = (EditText) findViewById(R.id.editTextAge);


    }

    @Override
    protected void onStart() {
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        super.onStart();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmailId = (EditText) findViewById(R.id.editTextEmailId);
                editTextEmailPassword = (EditText) findViewById(R.id.editTextEmailPassword);
                a = editTextEmailId.getText().toString();
                b = editTextEmailPassword.getText().toString();

                try{
                    PostConnection postConnection = new PostConnection(getResources().getString(R.string.dessert_server_addr)+"join/itself");
                    postConnection.addParam("userMail",a);
                    postConnection.addParam("userPwd",b);

                    postConnection.execute().get();
                    if(postConnection.resultObj.getString("result").contentEquals("signup")){

                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(),"회원가입에 성공",Toast.LENGTH_SHORT);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT);
                    }
                }catch (Exception exc){
                    exc.printStackTrace();
                }

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent2);


            }
        });
    }
}
