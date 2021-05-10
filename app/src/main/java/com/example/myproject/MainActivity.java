package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WebView WebView;
    ArrayList<Building> buildingArrayList=new ArrayList();
    private Building[] buildings;
    private ArrayAdapter<Building> adapter;
    private ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        adapter=new ArrayAdapter<>(this, R.layout.text_view);
        listView = findViewById(R.id.list_View);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {



            Snackbar.make(findViewById(android.R.id.content),buildingArrayList.get(position).info(), Snackbar.LENGTH_LONG).show();

            showImageView(position);
        });

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=b19karhj");



    }


    public void showImageView(int index){
        ImageView imageView = findViewById(R.id.url_img);
        Picasso.get().load(buildings[index].getAuxdata().getWiki()).into(imageView);
    }





    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            Log.d("AsyncTask ==>",json);
            Gson gson = new Gson();
            buildings = gson.fromJson(json,Building[].class);
            adapter = new ArrayAdapter<>(MainActivity.this,R.layout.text_view,buildings);
            listView.setAdapter(adapter);
            for (int i = 0; i < buildings.length; i++) {
                Log.d("MainActivity ==>", "Find buildings: " + buildings[i]);


                buildingArrayList.add(buildings[i]);
            }
        }
    }
}
