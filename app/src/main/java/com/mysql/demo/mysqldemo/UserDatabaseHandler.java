package com.mysql.demo.mysqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME= "user_db";

    public UserDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(name String, address String);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("address", user.getAddress());
        db.insert("user", null, contentValues);
        db.close();
    }

    public List<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> usersList = new ArrayList<User>();
        Cursor cursor = db.rawQuery("SELECT * FROM user;", null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        do{
            User user = new User();
            user.name = cursor.getString(0);
            user.address = cursor.getString(1);
            usersList.add(user);
        }while(cursor.moveToNext());
        return usersList;
    }


}
