package com.example.myproject;

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
    private WebSettings wsettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        close = findViewById(R.id.close_web);

        athour = findViewById(R.id.photo);
        copyright = findViewById(R.id.license);




        web();
    }

    private void webpages(){
        athour.setWebViewClient(new WebViewClient());
        copyright.setWebViewClient(new WebViewClient());


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