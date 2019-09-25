package com.mysql.demo.mysqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name, address;

    // Method is triggered when the this activity is invoked
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associating the EditText or TextBoxes present in UI
        // with this class variables
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
    }

    // Triggered when the save button in the UI is pressed.
    public void save(View view){

        // Printing what are we going to save in this method
        Log.d("Name", name.getText().toString());
        Log.d("Address", address.getText().toString());

        // Creating a USer Object
        User user = new User(name.getText().toString(), address.getText().toString());

        // Creating an instance of database handler or SQLite open Helper
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(this);

        // Trying to insert the user
        userDatabaseHandler.insertUser(user);

        // Fetching all the users from the list
        List<User> userList = userDatabaseHandler.getAllUsers();

        // Iterating through each user in the list and printing the details of user
        for(int i =0; i<userList.size(); i++) {
            Log.d("Users", "" + userList.get(i).getName()+"<-->"+userList.get(i).getAddress());
        }
    }
}
