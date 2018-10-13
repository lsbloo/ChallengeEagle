package com.example.osvaldoairon.challengeeeagle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.R;
import com.example.osvaldoairon.challengeeeagle.SQLITE.RecipesRepository;
import com.example.osvaldoairon.challengeeeagle.models.validations;

public class EditRecipes extends AppCompatActivity implements validations {

    protected static RecipesRepository recipesRepository;
    private EditText name;
    private EditText description;
    private EditText make;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipes);

        recipesRepository = new RecipesRepository(this);
        final String name_intent = (String) getIntent().getSerializableExtra("nameRecipes");
        final String description_intent = (String) getIntent().getSerializableExtra("descriptionRecipes");
        String make_intent = (String) getIntent().getSerializableExtra("makeRecipes");


        name=(EditText)findViewById(R.id.edit_name);
        description=(EditText)findViewById(R.id.edit_description);
        make =(EditText)findViewById(R.id.edit_how_make);
        edit=(Button)findViewById(R.id.btn_edit);


        name.setText(name_intent);
        description.setText(description_intent);
        make.setText(make_intent);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validationDataFields(name,description,make,null)){

                    boolean check =recipesRepository.editRecipes(name_intent,description_intent,make.getText().toString(),name.getText().toString(),description.getText().toString());

                    if(check){
                        Toast.makeText(EditRecipes.this, "Edit sucess!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };
                
            }
        });


    }



    @Override
    public boolean validationDataFields(EditText name, EditText login, EditText email, EditText password) {
        if(name.getText().toString().isEmpty() && login.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()){
            Toast.makeText(this, "fill in the fields correctly!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        
    }
}
