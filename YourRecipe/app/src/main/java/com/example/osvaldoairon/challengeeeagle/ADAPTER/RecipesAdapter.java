package com.example.osvaldoairon.challengeeeagle.ADAPTER;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osvaldoairon.challengeeeagle.R;
import com.example.osvaldoairon.challengeeeagle.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends BaseAdapter {

    private List<Recipe> list;
    private Context ctx;


    public RecipesAdapter(Context ctx, List<Recipe> list_){
        this.ctx=ctx;
        this.list=list_;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Recipe a = list.get(position);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_fragments_recipes_show,parent,false);

        TextView nameDish = (TextView)view.findViewById(R.id.text_name);
        TextView descriptionDish = (TextView)view.findViewById(R.id.text_description);
        TextView makeDish = (TextView)view.findViewById(R.id.text_make);
        ImageView img  = (ImageView)view.findViewById(R.id.img_dish);


        nameDish.setText("Name: " +a.getName_dish());
        descriptionDish.setText("Description:" + a.getDescription());
        makeDish.setText("How to Make? :"+ a.getHow_to_make());
        if(a.getBitmap_img()!=null){
            Log.d("MEC","meczada");
        }
        img.setImageBitmap(a.getBitmap_img());


        return view;
    }
}
