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
 wiki = img

 */


import android.os.Parcel;
import android.os.Parcelable;

public class Building implements Parcelable {

    private final Object Auxdata;
    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;
    private Auxdata auxdata;


    protected Building(Parcel in) {
        ID = in.readString();
        name = in.readString();
        type = in.readString();
        company = in.readString();
        location = in.readString();
        category = in.readString();
        size = in.readInt();
        cost = in.readInt();
        Auxdata = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(company);
        dest.writeString(location);
        dest.writeString(category);
        dest.writeInt(size);
        dest.writeInt(cost);
        dest.writeFloat((Float) Auxdata);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Building> CREATOR = new Creator<Building>() {
        @Override
        public Building createFromParcel(Parcel in) {
            return new Building(in);
        }

        @Override
        public Building[] newArray(int size) {
            return new Building[size];
        }
    };

    public Auxdata getAuxdata() {
        return auxdata;
    }

    public String info(){
        String tmp= name+" Is located in "+location+" "+company+" and is "+size+" meters tall and have "+cost+" floors"; //Placerar in datan i en sträng.
        return tmp;
    }


    @Override
    public String toString() {
        return name;
    }



}
