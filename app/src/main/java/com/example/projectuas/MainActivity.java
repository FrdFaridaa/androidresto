package com.example.projectuas;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectuas.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences prefs;
    public SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        isLogin();
    }

    @OnClick(R.id.logout)
    void keluar(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Html.fromHtml("<font color='#25c5da'><b>Yakin ingin Keluar ?</b></font>"))
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        isLogOut();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        }).show();
    }

    @OnClick(R.id.input_meja)
    void inputnomeja(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.input_nomer_meja);
        dialog.show();
    }

    public void isLogin(){
        // Session manager
        session = new SessionManager(this);

        //Session Login
        if(session.isLoggedIn()){
            String emails       = prefs.getString("email", "");
            String passwords = prefs.getString("password", "");
//            email.setText(emails);
//            password.setText(passwords);


        }else{
            Intent a = new Intent(getApplicationContext(), login.class);
            startActivity(a);
            finish();
        }
    }

    public void isLogOut(){
        session.setLogin(false);
        session.setSkip(false);
        session.setSessid(0);
        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish();
    }
}