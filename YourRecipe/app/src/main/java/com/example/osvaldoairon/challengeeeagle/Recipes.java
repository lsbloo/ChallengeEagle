package com.example.osvaldoairon.challengeeeagle;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.example.osvaldoairon.challengeeeagle.fragments.AdapterFragment;

public class Recipes extends AppCompatActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;
    public static  String get_email;
    public static String get_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

         get_name = (String) getIntent().getSerializableExtra("name_user");
         get_email = (String) getIntent().getSerializableExtra("email_user");

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tableLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager(), getResources().getStringArray(R.array.sections)));

        tableLayout.setupWithViewPager(viewPager);
    }
}
