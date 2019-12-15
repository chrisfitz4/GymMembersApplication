package com.example.gymmembersapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;

public class GymPlan {
    int id;
    String price;
    String name;

    public GymPlan(int id) {
        this.id = id;
        if(id==1){
            price = "$15.99";
            name = "Basic";
        }else if(id==2){
            price = "$23.99";
            name = "Premium";
        }else if(id==3){
            price = "39.99";
            name = "Gold Member";
        }else{
            price = "";
            name = "";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String toReturn = "";
        toReturn+=name+" Plan";
        toReturn+="\n";
        toReturn+=price+" per month";
        return toReturn;
    }
}
