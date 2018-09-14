package com.mysql.demo.mysqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
    }

    public void save(View view){
        Log.d("Name", name.getText().toString());
        Log.d("Address", address.getText().toString());
        User user = new User(name.getText().toString(), address.getText().toString());
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(this);
        userDatabaseHandler.insertUser(user);
        List<User> userList = new ArrayList<User>();
        userList = userDatabaseHandler.getAllUsers();
        for(int i =0; i<userList.size(); i++) {
            Log.d("Users", "" + userList.get(i).getName()+"<-->"+userList.get(i).getAddress());
        }
    }
}
