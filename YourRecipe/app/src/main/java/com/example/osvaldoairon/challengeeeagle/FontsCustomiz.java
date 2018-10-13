package com.example.osvaldoairon.challengeeeagle;

import android.content.Context;
import android.graphics.Typeface;

public class FontsCustomiz {


    public static Typeface setArizonizaRegular(Context ctx){
        return Typeface.createFromAsset(ctx.getAssets(),"Arizonia-Regular.ttf");
    }
    public static Typeface setRobotoRegular(Context ctx){
        return Typeface.createFromAsset(ctx.getAssets(),"robotoslabregular.ttf");
    }
}
