package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class WebViewActivity extends AppCompatActivity {

    private Button close;
    private WebView author;
    private WebView copyright;

    private String data;
    private String ldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        close = findViewById(R.id.close_web);

        author = findViewById(R.id.photo);
        copyright = findViewById(R.id.license);
        data ="";
        ldata ="";


        transfer();
        web();
        webPages();
    }

    public void transfer(){
        Intent myintent =getIntent();
        Auxdata copyinfo = (Auxdata) myintent.getParcelableExtra("keys");
        data = copyinfo.getCopyright();
        ldata = copyinfo.getLicense();

    }


    private void webPages(){
        author.setWebViewClient(new WebViewClient());
        author.loadUrl(data);

        copyright.setWebViewClient(new WebViewClient());
        copyright.loadUrl(ldata);

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