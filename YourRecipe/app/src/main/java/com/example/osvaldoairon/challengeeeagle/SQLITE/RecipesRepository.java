package com.example.osvaldoairon.challengeeeagle.SQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.models.Recipe;

import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecipesRepository {


    private RecipesDB recipesDB;
    protected static  List<Recipe> list_recipes = new ArrayList<Recipe>();
    private Context ctx;
    protected static boolean check;
    protected static boolean check_delete;


    public RecipesRepository(Context ctx){
        this.ctx=ctx;
        recipesDB = new RecipesDB(ctx,"1",null,1);

    }


    public boolean checkRecipe(Recipe recipe){
        if(list_recipes!=null && list_recipes.size()>1){
            for(Recipe r : list_recipes){
                if(r.getName_dish().equals(recipe.getName_dish()) && r.getDescription().equals(recipe.getDescription())
                        && r.getHow_to_make().equals(recipe.getHow_to_make())){
                    return true;

                }
            }
        }
        return false;
    }

    public long insert(Recipe recipe){

        if(checkRecipe(recipe)){
            Toast.makeText(ctx, "\n" +
                    "revenue already inserted! ", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase db = recipesDB.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(recipesDB.NAME_DISH,recipe.getName_dish());
            cv.put(recipesDB.DESCRIPTION_DISH,recipe.getDescription());
            cv.put(recipesDB.MAKE_DISH,recipe.getHow_to_make());
            cv.put(recipesDB.NAME_IMG,transformBitMapToByte(recipe.getBitmap_img()));
            cv.put(recipesDB.MASK_USER,recipe.getUser_mask());


            Long id = db.insert(recipesDB.NAME_TABLE,null,cv);

            Toast.makeText(ctx, "Sucess ! ", Toast.LENGTH_SHORT).show();
            if(id != -1){
                recipe.setId(id);
            }else{
                db.close();
                return id;
            }
        }

        return 0;
    }

    public Recipe returnObjectRecipeToLister(int position){
        if(position == 0){
            Log.d("TESTE_POSITION", list_recipes.get(0).getName_dish());
            return list_recipes.get(0);
        }
        if(list_recipes!=null && list_recipes.size()>1){
            return list_recipes.get(position);
        }
        return null;
    }



    public boolean deleteRecipes(int id, String name, String description){
        /**
         * Similar MethodEdit;
         *
         */
        String ret_name = "\'"+name+"\'";
        String ret_description = "\'"+description+"\'";
        SQLiteDatabase db = recipesDB.getWritableDatabase();
        int cont =0;
        String ret_id="";
        String whereClause = "_id=";
        String sql = "SELECT * FROM "+recipesDB.NAME_TABLE+" WHERE "+recipesDB.NAME_DISH+" =" +ret_name+ " AND "+recipesDB.DESCRIPTION_DISH+ " ="+ret_description;

        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            int indexColumnId = cursor.getColumnIndex(recipesDB.ID_COLUMN);

            ret_id="\'"+cursor.getLong(indexColumnId)+"\'";
            cont++;
            Log.d("CONT_DELETE?",ret_id);
            if(cont>=1) check_delete = true; break;
        }
        if(check_delete){
            Log.d("CHECK_DELETE?","CHECK_DELETE?");
            db.delete(recipesDB.NAME_TABLE,whereClause+ret_id,null);
            return true;
        }
        return false;
    }
    public boolean editRecipes(String nameOld,String descriptionOld,String makeNew,String nameNew,String descriptionNew){
        /**
         *
         This simple algorithm,
         aims to perform the data search for the recipes,
         with this data returned is using the update command of sqlitedatabse that changes
         this data according to its ID, using the contentvalues ​​class
         to change the attributes of the fields;
         */
        String ret_name = "\'"+nameOld+"\'";
        String ret_description = "\'"+descriptionOld+"\'";

        SQLiteDatabase db = recipesDB.getWritableDatabase();
        check = false;
        int cont =0;
        long id = 0;
        String ret_id="";

        String sql = "SELECT * FROM "+recipesDB.NAME_TABLE+ " WHERE " +recipesDB.NAME_DISH+"="+ret_name+ " AND "
                +recipesDB.DESCRIPTION_DISH+"="+ret_description;

        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            int indexColumnId = cursor.getColumnIndex(recipesDB.ID_COLUMN);

            id = cursor.getLong(indexColumnId);
            Log.d("EDIT_id", String.valueOf(id));
            ret_id = "\'"+id+"\'";
            cont++;
            Log.d("CONT?", String.valueOf(cont));
            if(cont>=1) check = true; break;
        }
        if(check){
            Log.d("CHECK?","CHECK?");
            ContentValues cv = new ContentValues();
            cv.put(recipesDB.NAME_DISH,nameNew);
            cv.put(recipesDB.DESCRIPTION_DISH,descriptionNew);
            cv.put(recipesDB.MAKE_DISH,makeNew);
            db.update(recipesDB.NAME_TABLE,cv,recipesDB.ID_COLUMN+"="+ret_id,null);
            return true;
        }

        //String sql = "UPDATE "+recipesDB.NAME_TABLE + "SET "+recipesDB.NAME_DISH+"="+ret_name+" WHERE "+recipesDB.ID_COLUMN+"="+ret_id;
        return false;


    }


    public void recoverRecipes(String passe){
        String ret = "\'"+passe+"\'";
        clean();
        SQLiteDatabase db = recipesDB.getWritableDatabase();
        String sql = "SELECT * FROM "+recipesDB.NAME_TABLE + " WHERE " +recipesDB.MASK_USER+ "="+ret;
        Cursor cursor = db.rawQuery(sql,null);

        //cursor.moveToFirst();

        try{
            while(cursor.moveToNext()) {


                int indexColumnNameDish = cursor.getColumnIndex(recipesDB.NAME_DISH);
                int indexColumnDescriptionDish = cursor.getColumnIndex(recipesDB.DESCRIPTION_DISH);
                int indexColumnMake = cursor.getColumnIndex(recipesDB.MAKE_DISH);
                int indexColumnImg = cursor.getColumnIndex(recipesDB.NAME_IMG);
                int indexColumnMask = cursor.getColumnIndex(recipesDB.MASK_USER);


                String name = cursor.getString(indexColumnNameDish);
                String description = cursor.getString(indexColumnDescriptionDish);
                String make = cursor.getString(indexColumnMake);
                byte[] bytes_img = cursor.getBlob(indexColumnImg);
                String mask = cursor.getString(indexColumnMask);

                Recipe r = new Recipe();


                r.setName_dish(name);
                r.setDescription(description);
                r.setHow_to_make(make);
                r.setByte_img(bytes_img);
                r.setBitmap_img(transformByteToBitmap(bytes_img));
                r.setUser_mask(mask);

                Log.d("RECIPE_USER", r.getUser_mask());
                list_recipes.add(r);
            }
        }catch (Exception e){
            Log.d("RET", ret);
            Log.d("USUARIO cursor" , "usuario ainda nao colocou dados receitos no cursor");
            e.printStackTrace();


        }

    }



    public void clean(){
        list_recipes.clear();
    }
    public List<Recipe> returnList(){
        if(list_recipes!=null && list_recipes.size()>0){
            return list_recipes;
        }
        return null;
    }



    public Bitmap transformByteToBitmap(byte[] b){
        /**
         * Convert to Array of bytes[] in BitMap
         * Class: BitmapFactory;
         */
            Bitmap img = BitmapFactory.decodeByteArray(b,0,b.length);
            return img;

    }

    public byte[] transformBitMapToByte(Bitmap bitmap){
        /**
         * Convert to ImagemBitmap for Array bytes[]
         * TYPE BLOB SQLITE, is compatible;
         *
         */

        ByteArrayOutputStream exit = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,exit);

        byte[] img = exit.toByteArray();

        return img;
    }







}
