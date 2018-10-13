package com.example.osvaldoairon.challengeeeagle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MySplashNice extends AppCompatActivity {

    private ProgressBar progressBar;
    protected final int TIME = 4000;
    private static String get_name;
    private static String get_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_splash_nice);


        get_name = (String) getIntent().getSerializableExtra("nameuser");
        get_email = (String) getIntent().getSerializableExtra("emailuser");
        boolean get_enabled = (Boolean) getIntent().getSerializableExtra("enabled");
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


        Toast.makeText(this, "Welcome: "+get_name, Toast.LENGTH_SHORT).show();


        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                /**
                 * PROGRESS BAR ENABLED;
                 * start activie , Recipes;

                 */
                Intent at = new Intent(MySplashNice.this,Recipes.class);
                at.putExtra("name_user",get_name);
                at.putExtra("email_user",get_email);
                startActivity(at);
                finish();


            }
        }, TIME);
    }
}
