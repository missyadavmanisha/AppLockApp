package com.codingblocks.applockfinal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {



    private static int SPLASH_TIME_OUT = 1000;
    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        /****************************** too much important don't miss it *****************************/
        startService(new Intent(SplashActivity.this, AppCheckServices.class));

        try {
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 999, alarmIntent, 0);
            int interval = (86400 * 1000) / 4;
            if (manager != null) {
                manager.cancel(pendingIntent);
            }
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***************************************************************************************/

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.primary_dark));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(layoutParams);

        TextView textView = new TextView(this);
        textView.setText(getResources().getString(R.string.app_name));
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(32);
        // textView.setGravity(Gravity.CENTE);
        textView.setPadding(70, 100, 60, 20);
        linearLayout.addView(textView);

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.applockicon));
        linearLayout.addView(imageView);

        setContentView(linearLayout);
        sharedPreferences = getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
        boolean ispinviewenabled = sharedPreferences.getBoolean(APPLockConstants.Is_PIN_ENABLED, false);
        if (ispinviewenabled != true) {
            final boolean isPasswordSet = sharedPreferences.getBoolean(APPLockConstants.IS_PASSWORD_SET, false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isPasswordSet) {
                        Intent i = new Intent(SplashActivity.this, PasswordActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashActivity.this, PasswordSetActivity.class);
                        startActivity(i);
                    }
                    finish();
                }
            }, SPLASH_TIME_OUT);


        } else {
final boolean ispinset=sharedPreferences.getBoolean(APPLockConstants.IS_PIN_ENTERED,false);
if(ispinset)
{
    Intent i=new Intent(SplashActivity.this,);
    startActivity(i);
}
else
{
    Intent i=new Intent(SplashActivity.this,PinViewset.class);
    startActivity(i);
}
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        super.onStop();
    }
}

