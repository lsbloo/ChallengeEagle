package com.example.osvaldoairon.challengeeeagle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.SQLITE.UserRepository;
import com.example.osvaldoairon.challengeeeagle.models.User;
import com.example.osvaldoairon.challengeeeagle.models.validations;

public class MainActivity extends AppCompatActivity implements validations {

    private EditText edt_login;
    private EditText edt_pass;
    private TextView sign_in;
    private TextView register;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt_login = (EditText)findViewById(R.id.edt_login);
        edt_pass = (EditText)findViewById(R.id.edt_password);
        sign_in =(TextView)findViewById(R.id.btn_enter);
        register = (TextView)findViewById(R.id.edt_cad);

        userRepository = new UserRepository(MainActivity.this);






        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                Create operation of login in app, its necessary get datas of database;
                if user not exist, method of autenticate, return only message;
                 */
                if(validationDataFields(null,edt_login,null,edt_pass)){
                        userRepository.recoverUserRepository();
                        User u = userRepository.autenthicateUser(edt_login.getText().toString(),edt_pass.getText().toString());


                        if(u!=null){
                            Intent at = new Intent(MainActivity.this,MySplashNice.class);
                            at.putExtra("nameuser",u.getName());
                            at.putExtra("emailuser",u.getEmail());
                            at.putExtra("enabled",u.isEnabled());
                            startActivity(at);
                        }else{
                            Toast.makeText(MainActivity.this, "Error: Login or Password \n" +
                                    "incorrect!", Toast.LENGTH_SHORT).show();
                        }
                };

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent at = new Intent(MainActivity.this,RegisterUser.class);

                startActivity(at);

            }
        });



    }


    @Override
    public boolean validationDataFields(EditText name, EditText login, EditText email, EditText password) {
        if(login.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(this, "\n" +
                    "Fill in the fields correctly", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
