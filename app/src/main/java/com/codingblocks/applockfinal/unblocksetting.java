package com.codingblocks.applockfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class unblocksetting extends AppCompatActivity {
    TextView pattern,password;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unblocksetting);
        pattern =findViewById(R.id.unblocktext);
        password=findViewById(R.id.unblocktext1);
        pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(unblocksetting.this,PasswordSetActivity.class);
                startActivity(i);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(unblocksetting.this,PinViewset.class);
            }
        });



    }
}
