package com.example.osvaldoairon.challengeeeagle.SQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.osvaldoairon.challengeeeagle.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    private UserDB userDB;

    private static  List<User> list_users = new ArrayList<User>();

    private Context ctx;

    public UserRepository(Context ctx){
        this.ctx=ctx;
        userDB= new UserDB(ctx,"k",null,0);
    }

    public String verificEnables(boolean enabled){
        /**
         * THIS DATABASE SQLITE3 , DONT HAVE TYPE BOOLEAN IN YOUR DOCS DATATYPES
         * THIS METHOD, VERIFIC IF VALUE OF BOOLEAN IS TRUE OR FALSE
         * RETURN ONLY STRING 1 = TRUE , 0 = FALSE;
         */
        if(enabled){
            return "1";
        }
        return "0";
    }

    public long insertUser(User user){

        if(CheckUserExists(user)){
            Log.d("User Exist","USER:" +user.getName());
            Toast.makeText(ctx, "\n" +
                    "Sorry, there is already a user with this data", Toast.LENGTH_SHORT).show();
        }else{

            SQLiteDatabase db = userDB.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(userDB.USER_NAME,user.getName());
            cv.put(userDB.USER_EMAIL,user.getEmail());
            cv.put(userDB.USER_LOGIN,user.getLogin());
            cv.put(userDB.USER_PASSWORD,user.getPassword());
            cv.put(userDB.USER_ENABLED,verificEnables(user.isEnabled()));

            long id = db.insert(userDB.NAME_TABLE,null,cv);

            Log.d("insert_ok","insert_ok");
            Toast.makeText(ctx, "Sucess!", Toast.LENGTH_SHORT).show();
            if(id != -1){
                user.setId(id);
            }else {
                db.close();
                return id;
            }
        }


        return 0;
    }

    public boolean recoverUsersEnables(String string){

        if(string.equals("1")){
            return true;
        }
        return false;
    }

    public User autenthicateUser(String login, String password){
        if(list_users!=null && list_users.size()>1){
            for(User user : list_users){
                if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                    Log.d("MASK", user.getEmail());
                    return user;
                }
            }
        }
        return null;
    }

    public void recoverUserRepository(){
        SQLiteDatabase db = userDB.getWritableDatabase();
        String sql = "SELECT * FROM "+userDB.NAME_TABLE;
        Cursor cursor = db.rawQuery(sql,null);

        cursor.moveToFirst();

        while (cursor.moveToNext()){
            int indexColumnName = cursor.getColumnIndex(userDB.USER_NAME);
            int indexColumnLogin = cursor.getColumnIndex(userDB.USER_LOGIN);
            int indexColumnPassword = cursor.getColumnIndex(userDB.USER_PASSWORD);
            int indexColumnEmail= cursor.getColumnIndex(userDB.USER_EMAIL);
            int indexColumnEnabled = cursor.getColumnIndex(userDB.USER_ENABLED);


            String name = cursor.getString(indexColumnName);
            String login = cursor.getString(indexColumnLogin);
            String password = cursor.getString(indexColumnPassword);
            String email = cursor.getString(indexColumnEmail);
            boolean enabled = recoverUsersEnables(cursor.getColumnName(indexColumnEnabled));


            User u = new User();



            u.setName(name);
            u.setEnabled(enabled);
            u.setEmail(email);
            u.setLogin(login);
            u.setPassword(password);

            Log.d("USER",u.getEmail());

            list_users.add(u);


        }

        cursor.close();
        db.close();
    }

    public boolean CheckUserExists(User user){
        if(list_users != null && list_users.size()>1){
            for(User user1 : list_users){
                Log.d("User_check","CHECK: " +user1.getName());
                if(user.getLogin().equals(user1.getLogin()) && user.getPassword().equals(user1.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }
}
