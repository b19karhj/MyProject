package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {

    private Button close;
    private TextView test;
    private Bundle test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        test = findViewById(R.id.text_id);
        close = findViewById(R.id.close_web);

        test1 = getIntent().getExtras();

        ifs();
        closeWeb();
    }

    private void closeWeb() {

        close.setOnClickListener(v -> {
            finish();
        });

    }

    private void ifs(){
        if (test1 != null){
            String name = test1.getString("name");
            test.setText(name);
        }
    }

}