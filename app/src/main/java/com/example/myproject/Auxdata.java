package com.example.myproject;
/*
auxdata:
wiki =url to image
copyright = arthur
license = license

*/

public class Auxdata {

    private String wiki;
    private String copyright;
    private String license;




    public Auxdata(String wiki, String copyright) {
        this.wiki = wiki;
        this.copyright = copyright;
        this.license = license;
    }


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

