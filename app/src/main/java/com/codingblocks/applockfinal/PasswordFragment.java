package com.codingblocks.applockfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.takwolf.android.lock9.Lock9View;

import java.util.List;

public class PasswordFragment extends Fragment {
    PatternLockView  lock9View;
    Button confirmButton, retryButton;
    TextView textView;
    boolean isEnteringFirstTime = true;
    boolean isEnteringSecondTime = false;
    String enteredPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static PasswordFragment newInstance() {
        PasswordFragment f = new PasswordFragment();
        return (f);
    }

    public PasswordFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_password_set, container, false);

        lock9View = (PatternLockView) v.findViewById(R.id.lock_9_view);
        confirmButton = (Button) v.findViewById(R.id.confirmButton);
        retryButton = (Button) v.findViewById(R.id.retryButton);
        textView = (TextView) v.findViewById(R.id.textView);
        confirmButton.setEnabled(false);
        retryButton.setEnabled(false);
        sharedPreferences = getActivity().getSharedPreferences(APPLockConstants.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(APPLockConstants.PASSWORD, enteredPassword);
                editor.apply();

                editor.putBoolean(APPLockConstants.IS_PASSWORD_SET, true);
                editor.apply();

                Intent i = new Intent(getActivity(),LoadingActivity.class);
                getActivity().startActivity(i);
                //getActivity().finish();
              //  AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Confirm Password", "confirm_password", "");

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
             //   AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Retry Password", "retry_password", "");
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
                    enteredPassword = PatternLockUtils.patternToString(lock9View, pattern);
                    isEnteringFirstTime = false;
                    isEnteringSecondTime = true;
                    textView.setText("Re-Draw Pattern");
                    confirmButton.setText("confirm");
                } else if (isEnteringSecondTime) {
                    if (enteredPassword.matches(PatternLockUtils.patternToString(lock9View, pattern))) {
                        confirmButton.setEnabled(true);
                    } else {
                        Toast.makeText(getActivity(), "Both Pattern did not match - Try again", Toast.LENGTH_SHORT).show();
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
    return v;

}
}
