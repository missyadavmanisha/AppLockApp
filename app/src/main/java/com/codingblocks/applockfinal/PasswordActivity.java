package com.codingblocks.applockfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.takwolf.android.lock9.Lock9View;

import java.util.List;

/**
 * Created by amitshekhar on 30/04/15.
 */
public class PasswordActivity extends AppCompatActivity {
    PatternLockView lock9View;
    SharedPreferences sharedPreferences;
    Context context;
    Button forgetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_password);

        forgetPassword = (Button) findViewById(R.id.forgetPassword);
        lock9View = (PatternLockView) findViewById(R.id.lock_9_view);
        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
         PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if (sharedPreferences.getString(APPLockConstants.PASSWORD, "").equals(PatternLockUtils.patternToString(lock9View,pattern))) {
                    Toast.makeText(getApplicationContext(), "Success : Password Match", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PasswordActivity.this, LoadingActivity.class);
                    startActivity(i);

                    //  AppLockLogEvents.logEvents(AppLockConstants.PASSWORD_CHECK_SCREEN, "Correct Password", "correct_password", "");
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Pattern Try Again", Toast.LENGTH_SHORT).show();
                    //  AppLockLogEvents.logEvents(AppLockConstants.PASSWORD_CHECK_SCREEN, "Wrong Password", "wrong_password", "");
                }
            }

            @Override
            public void onCleared() {

            }
        };

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PasswordActivity.this, PasswordRecoveryActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        super.onStop();
    }
}
