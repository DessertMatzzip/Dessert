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
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton btnFacebook;
    private SessionCallback callback;
    com.kakao.usermgmt.LoginButton btnKakaotalk;
    Map<String,String> kakao_properties;


    public void redirectLoginActivity() {
        String token = Session.getCurrentSession().getAccessToken();
        if (token.length() > 0) {
            PostConnection postConnection = new PostConnection(getResources().getString(R.string.dessert_server_addr) + "login/kakao");
            postConnection.addParam("accessToken", token);
            try {
                postConnection.execute().get();
                if (postConnection.resultObj.getString("result").contentEquals("signup_req")) {
                    Log.e("server logged in", postConnection.resultObj.toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }

        } else {
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
                PostConnection postConnection = new PostConnection(getResources().getString(R.string.dessert_server_addr) + "login/facebook");
                postConnection.addParam("accessToken", AccessToken.getCurrentAccessToken().toString());
                try {
                    postConnection.execute().get();
                    Log.e("result", postConnection.resultObj.getString("result"));
                    if (postConnection.resultObj.getString("result").contentEquals("signup_req")) {
                        KakaoToast.makeToast(LoginActivity.this, "회원가입이 필요합니다.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else if (postConnection.resultObj.getString("result").contentEquals("signin_req")) {
                        KakaoToast.makeToast(LoginActivity.this, UserManagement.getInstance().toString() +"로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog dialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("앱 서버와 연결할 수 없습니다.");
                        builder.setPositiveButton("확인", null);
                        dialog = builder.create();
                        dialog.show();
                    }
                } catch (Exception exc) {
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
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
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
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    KakaoToast.makeToast(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onSuccess(MeV2Response result) {

                    KakaoToast.makeToast(LoginActivity.this, result.getNickname()+"로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                    long number = result.getId();

                    redirectLoginActivity();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
                KakaoToast.makeToast(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);

    }
}

