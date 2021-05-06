package com.example.myproject;

/*
List for the different type of data

 ID = ID
 name = name
 type = login
 company = country
 location = city
 category = category
 size = height
 cost = floors

 auxdata:
 wiki
 img
 */


public class Building {

    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;
    private Auxdata auxdata;



    public Auxdata getAuxdata() {
        return auxdata;
    }

    @Override
    public String toString() {
        return name;
    }



}
