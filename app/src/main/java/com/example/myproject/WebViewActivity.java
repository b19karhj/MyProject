package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class WebViewActivity extends AppCompatActivity {

    private Button close;
    private WebView athour;
    private WebView copyright;
    private WebSettings photosettings;
    private WebSettings copysettings;
    private String data;
    private String ldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        close = findViewById(R.id.close_web);

        athour = findViewById(R.id.photo);
        copyright = findViewById(R.id.license);
        data ="";
        ldata ="";


        transfer();
        web();


    }

    public void transfer(){
        Intent myintent =getIntent();
        Auxdata copyright = (Auxdata) myintent.getParcelableExtra("keys");
        data = copyright.getCopyright();
        ldata = copyright.getLicense();

    }
    public void showExternalWebPage(int index){


    }

    private void webPages(){
        athour.setWebViewClient(new WebViewClient());
        /*photosettings.setJavaScriptEnabled(true);*/
        photosettings = athour.getSettings();

        copyright.setWebViewClient(new WebViewClient());
        /*copysettings.setJavaScriptEnabled(true);*/
        copysettings = athour.getSettings();


    }

    private void web(){
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}