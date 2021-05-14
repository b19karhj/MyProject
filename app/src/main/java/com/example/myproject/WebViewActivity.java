package com.example.myproject;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class WebViewActivity extends AppCompatActivity {

    private Button close;
    private WebView athour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        close = findViewById(R.id.close_web);

        athour = findViewById(R.id.)


        web();
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