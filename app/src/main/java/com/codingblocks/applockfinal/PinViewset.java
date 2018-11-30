package com.codingblocks.applockfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PinViewset extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, cross,tic;
    EditText edit;
    SharedPreferences sharedPreferences;
    String enteredPassword;
    boolean isEnteringFirstTime = true;
    boolean isEnteringSecondTime = false;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinview);

        three = (Button) findViewById(R.id.button);
        two = (Button) findViewById(R.id.button2);
        one = (Button) findViewById(R.id.button3);
        five = (Button) findViewById(R.id.button4);
        tic = (Button) findViewById(R.id.button7);
        zero = (Button) findViewById(R.id.button5);
        seven = (Button) findViewById(R.id.button9);
        four = (Button) findViewById(R.id.button11);
        cross = (Button) findViewById(R.id.button6);
        nine = (Button) findViewById(R.id.button8);
        eight = (Button) findViewById(R.id.button10);
        edit = (EditText) findViewById(R.id.editText);
        six = (Button) findViewById(R.id.button12);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "3");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "2");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "0");
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                int i = Integer.parseInt(s);
                i = i / 10;
                edit.setText("" + i);
            }
        });
        tic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String string = edit.getText().toString();
                if (isEnteringFirstTime) {


                    editor.putString(APPLockConstants.IS_PIN_, string);
                    editor.apply();
                    isEnteringFirstTime = false;
                    isEnteringSecondTime = true;
                    edit.setHint("Re-Draw Pattern");
                    edit.setText("");

                } else if (isEnteringSecondTime) {
                    if (string.matches(sharedPreferences.getString(APPLockConstants.IS_PIN_, "1111"))) {
                        tic.setEnabled(true);
                        editor.putBoolean(APPLockConstants.IS_PIN_ENTERED,true);
                        editor.apply();
                        Intent i=new Intent(PinViewset.this,LoadingActivity.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Both Pattern did not match - Try again", Toast.LENGTH_SHORT).show();
                        isEnteringFirstTime = true;
                        isEnteringSecondTime = false;

                    }
                }
            }

        });

    }
}

