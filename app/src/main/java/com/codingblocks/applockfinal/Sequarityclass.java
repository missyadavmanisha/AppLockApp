package com.codingblocks.applockfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Sequarityclass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        if(i.hasExtra("unblocksetting") )
        {

            Intent intent=new Intent(Sequarityclass.this,unblocksetting.class);
            startActivity(intent);

        }
        if(i.hasExtra("sequaritysetting"))
        {
            Intent intent=new Intent(Sequarityclass.this,PasswordRecoverSetActivity.class);
            startActivity(intent) ;
        }



    }
}
