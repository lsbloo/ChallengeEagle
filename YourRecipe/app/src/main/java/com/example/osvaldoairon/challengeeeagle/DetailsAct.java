package com.example.osvaldoairon.challengeeeagle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        String string = getIntent().getStringExtra("text");

        TextView txt = (TextView)findViewById(R.id.txt_detalhe);

        txt.setText(string);
    }
}
