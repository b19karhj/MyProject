package com.example.myproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewFragment extends AppCompatActivity {

    private Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewfragment);

        close = findViewById(R.id.close_web);
        closeWeb();
    }

    private void closeWeb() {

        close.setOnClickListener(v -> {
            finish();
        });

    }


}