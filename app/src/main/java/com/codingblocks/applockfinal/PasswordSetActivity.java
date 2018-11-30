package com.codingblocks.applockfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
public class PasswordSetActivity extends AppCompatActivity {
    PatternLockView lock9View;
    Button confirmButton, retryButton;
    TextView textView;
    boolean isEnteringFirstTime = true;
    boolean isEnteringSecondTime = false;
    String enteredPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        lock9View = (PatternLockView) findViewById(R.id.lock_9_view);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        retryButton = (Button) findViewById(R.id.retryButton);
        textView = (TextView) findViewById(R.id.textView);
        confirmButton.setEnabled(false);
        retryButton.setEnabled(false);
        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(APPLockConstants.PASSWORD, enteredPassword);
                editor.apply();


                Intent i = new Intent(PasswordSetActivity.this, PasswordRecoverSetActivity.class);
                startActivity(i);
                finish();
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnteringFirstTime = true;
                isEnteringSecondTime = false;
                textView.setText("Draw Pattern");
                confirmButton.setEnabled(false);
                retryButton.setEnabled(false);
             //   AppLockLogEvents.logEvents(AppLockConstants.FIRST_TIME_PASSWORD_SET_SCREEN, "Retry Password", "retry_password", "");
            }
        });

        PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                retryButton.setEnabled(true);
                if (isEnteringFirstTime) {
                    enteredPassword = PatternLockUtils.patternToString(lock9View,pattern);
                    isEnteringFirstTime = false;
                    isEnteringSecondTime = true;
                    textView.setText("Re-Draw Pattern");
                } else if (isEnteringSecondTime) {
                    if (enteredPassword.matches(PatternLockUtils.patternToString(lock9View,pattern))) {
                        editor.putBoolean(APPLockConstants.Is_PIN_ENABLED,false);
                        editor.apply();
                        confirmButton.setEnabled(true);

                    } else {
                        Toast.makeText(getApplicationContext(), "Both Pattern did not match - Try again", Toast.LENGTH_SHORT).show();
                        isEnteringFirstTime = true;
                        isEnteringSecondTime = false;
                        textView.setText("Draw Pattern");
                        retryButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void onCleared() {

            }

        };
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
