package com.example.myproject;
/*
auxdata:
wiki =url to image
copyright = arthur
license = license

*/

import android.os.Parcel;
import android.os.Parcelable;

public class Auxdata implements Parcelable {

    private String wiki;
    private String copyright;
    private String license;




    public Auxdata(String wiki, String copyright) {
        this.wiki = wiki;
        this.copyright = copyright;
        this.license = license;
    }


    protected Auxdata(Parcel in) {
        wiki = in.readString();
        copyright = in.readString();
        license = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wiki);
        dest.writeString(copyright);
        dest.writeString(license);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Auxdata> CREATOR = new Creator<Auxdata>() {
        @Override
        public Auxdata createFromParcel(Parcel in) {
            return new Auxdata(in);
        }

        @Override
        public Auxdata[] newArray(int size) {
            return new Auxdata[size];
        }
    };

    public String getCopyright() {
        return copyright;
    }

    public String getLicense() {
        return license;
    }

    public String getWiki() {
        return wiki;
    }


}

