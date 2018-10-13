package com.example.osvaldoairon.challengeeeagle.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.ADAPTER.RecipesAdapter;
import com.example.osvaldoairon.challengeeeagle.EditRecipes;
import com.example.osvaldoairon.challengeeeagle.R;
import com.example.osvaldoairon.challengeeeagle.Recipes;
import com.example.osvaldoairon.challengeeeagle.SQLITE.RecipesRepository;
import com.example.osvaldoairon.challengeeeagle.models.Recipe;

public class FragmentShowRecipe extends Fragment {

    protected static View view;
    protected static RecipesRepository recipesRepository;
    private RecipesAdapter adapter;
    private ListView list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        recipesRepository = new RecipesRepository(getActivity());
        list = new ListView(getActivity());

        Log.d("Reciper_user",Recipes.get_email);

        recipesRepository.recoverRecipes(Recipes.get_email);


        if(recipesRepository.returnList()!=null){
            Toast.makeText(getActivity(), "OKS!", Toast.LENGTH_SHORT).show();
            adapter = new RecipesAdapter(getActivity(),recipesRepository.returnList());



            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    final int posicao = position;
                    Log.d("POSITION", String.valueOf(posicao));
                    new AlertDialog.Builder(getContext()).setTitle("Operations Recipes")
                            .setMessage("Do you want to edit or delete a recipe?").setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent at = new Intent(getActivity(), EditRecipes.class);
                            Recipe edit = recipesRepository.returnObjectRecipeToLister(posicao);
                            at.putExtra("nameRecipes",edit.getName_dish());
                            at.putExtra("descriptionRecipes",edit.getDescription());
                            at.putExtra("makeRecipes",edit.getHow_to_make());
                            startActivity(at);
                            getActivity().finish();

                        }
                    }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "Delete!", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
            });
            return list;

        }
        view = inflater.inflate(R.layout.layout_null_recipes,container,false);
        return view;
    }



}
