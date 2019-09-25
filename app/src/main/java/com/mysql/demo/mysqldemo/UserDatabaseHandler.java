package com.mysql.demo.mysqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to hold all the operations related to SQLite
 */
public class UserDatabaseHandler extends SQLiteOpenHelper {

    /** Version number to keep track of database version.
     * If this version number is changed then SQLite engine will
     * assume that the database has underwent changes and tries
     * update the necessary tables.
     */
    private static final int DATABASE_VERSION = 1;

    // Name to given to the database
    private static final String DATABASE_NAME = "user_db";

    public UserDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // To initialize with necessary tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(name String, address String);");
    }

    // This method is triggered when the table's schema is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void insertUser(User user){
        // Creating and opening a writable SQLiteDatabase object
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating the content which maps the table's attributes with the values
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("address", user.getAddress());

        // Inserting into the database
        db.insert("user", null, contentValues);

        // Closing the connection
        db.close();
    }

    public List<User> getAllUsers(){
        // Creating and opening a Readable SQLiteDatabase object
        SQLiteDatabase db = this.getReadableDatabase();

        // List to store all the users
        List<User> usersList = new ArrayList<User>();

        // Querying the databse
        Cursor cursor = db.rawQuery("SELECT * FROM user;", null);

        // Checking if the cursor's data is null
        if(cursor!=null){
            cursor.moveToFirst();
        }

        // Looping through the records, creating user object and storing in the userslist
        do{
            User user = new User();
            user.name = cursor.getString(0);
            user.address = cursor.getString(1);
            usersList.add(user);
        }while(cursor.moveToNext());

        // Returning the users list
        return usersList;
    }


}
