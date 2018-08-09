package com.example.jh.mydessert;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jh.mydessert.Connections.PostConnection;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton btnFacebook;
    SessionCallback callback;
    com.kakao.usermgmt.LoginButton btnKakaotalk;
    public void redirectLoginActivity(){
        String token = Session.getCurrentSession().getAccessToken();
        if(token.length() > 0){
            PostConnection postConnection = new PostConnection(getResources().getString(R.string.dessert_server_addr)+"login/kakao");
            postConnection.addParam("accessToken",token);
            try {
                postConnection.execute().get();
                if(postConnection.resultObj.getString("result").contentEquals("signup_req")){
                    Log.e("server logged in",postConnection.resultObj.toString());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }catch (Exception exc){
                exc.printStackTrace();
            }

        }else {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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
//                Intent myIntent = new Intent(getApplicationContext(), SearchActivity.class);
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        btnFacebook = (LoginButton) findViewById(R.id.btnFacebook);
        btnFacebook.setReadPermissions("email");
        btnKakaotalk = (com.kakao.usermgmt.LoginButton) findViewById(R.id.btnKakaotalk);
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                PostConnection postConnection = new PostConnection(getResources().getString(R.string.dessert_server_addr)+"login/facebook");
                postConnection.addParam("accessToken", AccessToken.getCurrentAccessToken().toString());
                try {
                    postConnection.execute().get();
                    Log.e("result",postConnection.resultObj.getString("result"));
                    if(postConnection.resultObj.getString("result").contentEquals("signup_req")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("로그인 실패");
                builder.setPositiveButton("확인", null);
                dialog = builder.create();
                dialog.show();

            }

            @Override
            public void onError(FacebookException error) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("로그인 에러");
                builder.setPositiveButton("확인", null);
                dialog = builder.create();
                dialog.show();

            }
        });
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
    }


    private void setDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_join, null);
        builder.setView(view);
        final Button submit = view.findViewById(R.id.btn_Submit);
        final EditText email = view.findViewById(R.id.edittextEmailAddress);
        final EditText password = view.findViewById(R.id.edittextPassword);

        final AlertDialog dialog = builder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();
                Toast.makeText(getApplicationContext(), strEmail + "/" + strPassword, Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });

        AlertDialog msgDialog = builder.create();
        msgDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {

            KakaorequestMe();

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Log.d("ErrorSession", exception.getMessage());
            }
        }
    }

    public void KakaorequestMe() {


        UserManagement.requestMe(new MeResponseCallback() {

            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.d("Error", "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("Error", "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.


                //이곳에서 로그인이 완료될시 실행시킬 동작을 추가시켜주시면 됩니다 ~

                Log.e("UserProfile", userProfile.toString());
                Log.e("TOKEN", Session.getCurrentSession().getAccessToken());

                redirectLoginActivity();

            }
        });
    }

}

