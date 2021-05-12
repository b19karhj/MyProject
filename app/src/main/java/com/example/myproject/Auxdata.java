package com.example.myproject;
/*
auxdata:
wiki =url to image
copyright = copyright

*/

public class Auxdata {

    private String wiki;
    private String copyright;

    public Auxdata(String wiki, String copyright) {
        this.wiki = wiki;
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "Copyright"+copyright;
    }

    public String getWiki() {
        return wiki;
    }

    public String getCopyright() {
        return copyright;
    }
}

