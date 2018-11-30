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

import com.andrognito.patternlockview.utils.PatternLockUtils;

public class pinview extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, cross,tic;
    EditText edit;
    SharedPreferences sharedPreferences;
    String enteredPassword;


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
                String str=edit.getText().toString();
                if (sharedPreferences.getString(APPLockConstants.IS_PIN_, "").equals(str)) {
                    Toast.makeText(getApplicationContext(), "Success : Password Match", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(pinview.this, LoadingActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Pattern Try Again", Toast.LENGTH_SHORT).show();
                }



            }

        });

    }
}


