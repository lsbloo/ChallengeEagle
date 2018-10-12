package com.example.osvaldoairon.challengeeeagle.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osvaldoairon.challengeeeagle.R;

public class FragmentShowRecipe extends Fragment {

    protected static View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_fragments_recipes_show,container,false);

        return view;
    }
}
