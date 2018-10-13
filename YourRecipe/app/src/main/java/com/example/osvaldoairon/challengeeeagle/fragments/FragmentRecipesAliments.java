package com.example.osvaldoairon.challengeeeagle.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.R;
import com.example.osvaldoairon.challengeeeagle.Recipes;
import com.example.osvaldoairon.challengeeeagle.SQLITE.RecipesRepository;
import com.example.osvaldoairon.challengeeeagle.models.Recipe;
import com.example.osvaldoairon.challengeeeagle.models.validations;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class FragmentRecipesAliments extends Fragment implements validations {

    protected static RecipesRepository recipesRepository;
    protected static Bitmap bit;
    protected static View view;
    protected static final int CHOSE_IMG = 101;

    protected EditText name_dish;
    protected EditText description_dish;
    protected ImageView photo_dish;
    protected EditText make_dish;
    protected Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activity_fragments_recipes_aliments,container,false);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recipesRepository = new RecipesRepository(getActivity());

        photo_dish = (ImageView)getActivity().findViewById(R.id.img_dish);
        name_dish = (EditText)getActivity().findViewById(R.id.edt_name_dish);
        description_dish = (EditText)getActivity().findViewById(R.id.edt_description_dish);
        make_dish=(EditText)getActivity().findViewById(R.id.edt_how_to_make);
        save=(Button)getActivity().findViewById(R.id.btn_save);



        photo_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captchaPhoto();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(validationDataFields(name_dish,description_dish,make_dish,null) && photo_dish!=null){
                    /**
                     * Insert Recipes in recipesRepository;
                     */

                    Recipe r = new Recipe();
                    r.setName_dish(name_dish.getText().toString());
                    r.setDescription(description_dish.getText().toString());
                    r.setHow_to_make(description_dish.getText().toString());
                    if(Recipes.get_email.isEmpty()){
                        Toast.makeText(getActivity(), "fail mask", Toast.LENGTH_SHORT).show();
                    }else{
                        r.setUser_mask(Recipes.get_email);
                    }

                    try{
                        if(bit!=null){
                            Toast.makeText(getActivity(), "XD", Toast.LENGTH_SHORT).show();
                            r.setBitmap_img(bit);
                        }else{
                            Toast.makeText(getContext(), "fail img", Toast.LENGTH_SHORT).show();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }


                    recipesRepository.insert(r);
                    getActivity().finish();
                };


            }
        });



    }



    protected void captchaPhoto(){
        /**
         *
         Creates an intention that aims to open the images folder of the device and any subfolder, wait for a request code
         */
        Intent it = new Intent();

        it.setType("image/*");
        it.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(it,"image dish"),CHOSE_IMG);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         *
         This is the code of request and if it is ok, a Uri Type object is created, 
         where the uri of the selected image is taken, 
         after which it is creating a bitmap object to resize the image, 
         finally dynamically loads the image in the user's view
         */

        if(requestCode == CHOSE_IMG && resultCode == RESULT_OK && data.getData() !=null){
            Uri img = data.getData();


            try {
                bit = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),img);

                photo_dish.setImageBitmap(dimensImg(getContext(),bit,100,100));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    protected static Bitmap dimensImg(Context context, Bitmap bmpOriginal,
                                                      float newWidth, float newWeight) {
        Bitmap novoBmp;
        int w = bmpOriginal.getWidth();
        
        int h = bmpOriginal.getHeight();
        
        float densityFactor = context.getResources().getDisplayMetrics().density;
        
        float novoW = newWidth * densityFactor;
        float novoH = newWeight * densityFactor;
        float scalaW = novoW / w;
        float scalaH = novoH / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scalaW, scalaH);
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);
        return novoBmp;
    }


    @Override
    public boolean validationDataFields(EditText name, EditText login, EditText email, EditText password) {
        if(name.getText().toString().isEmpty() || login.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Fill in the fields correctly!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
