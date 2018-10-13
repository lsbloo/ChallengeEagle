package com.example.osvaldoairon.challengeeeagle;


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.fragments.AdapterFragment;
import com.example.osvaldoairon.challengeeeagle.notifications.NotificationDelete;

public class Recipes extends AppCompatActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;
    public static  String get_email;
    public static String get_name;
    private MyReceiver receiver;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

         get_name = (String) getIntent().getSerializableExtra("name_user");
         get_email = (String) getIntent().getSerializableExtra("email_user");

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ctx = Recipes.this;

        tableLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager(), getResources().getStringArray(R.array.sections)));

        tableLayout.setupWithViewPager(viewPager);



        receiver = new MyReceiver();

        registerReceiver(receiver, new IntentFilter(NotificationDelete.ACTION_DELETE));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void createNotifications(Context ctx){
        NotificationDelete.createNotification(ctx,"Delete Reciper", 1);
    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context ctx , Intent at ){
            Toast.makeText(ctx, at.getAction(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
