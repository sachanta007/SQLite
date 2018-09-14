package com.mysql.demo.mysqldemo;

public class User {
    String name;
    String address;

    public User(){}

    public User(String name, String address){
        this.name=name;
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }
}
