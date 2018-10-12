package com.example.osvaldoairon.challengeeeagle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.SQLITE.UserRepository;
import com.example.osvaldoairon.challengeeeagle.models.User;
import com.example.osvaldoairon.challengeeeagle.models.validations;

public class RegisterUser extends AppCompatActivity implements validations {

    private EditText name;
    private EditText login;
    private EditText email;
    private EditText password;
    private TextView register;




    private UserRepository userRepository;
    private boolean validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        /**
         * Create only instance of myRepository of Users
         * Methods: Insert,Recovery datas;
         */
        userRepository = new UserRepository(this);
        validate = false;

        name=(EditText)findViewById(R.id.registerusername);
        login=(EditText)findViewById(R.id.registeruserlogin);
        email=(EditText)findViewById(R.id.registeruseremail);
        password=(EditText)findViewById(R.id.registeruserpassword);
        register=(TextView)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(validationDataFields(name,login,email,password)){
                    validate=true;
                }

                if(validate){
                    String l = login.getText().toString();
                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = password.getText().toString();

                    User u = new User();

                    u.setLogin(l);
                    u.setEnabled(true);
                    u.setName(n);
                    u.setEmail(e);
                    u.setPassword(p);

                    userRepository.recoverUserRepository();
                    userRepository.insertUser(u);
                    finish();
                }
            }
        });


    }

    @Override
    public boolean validationDataFields(EditText name,EditText login , EditText email
    ,EditText password){

        if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||login.getText().toString().isEmpty()
                || password.getText().toString().isEmpty() || password.getText().length() > 10){
            Toast.makeText(this, "Please, \n" +
                    "Enter your data correctly, the password field must be 10 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }
}
