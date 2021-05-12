package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.Resources;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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


    ArrayList<Building> buildingArrayList=new ArrayList();
    private Building[] buildings;
    private ArrayAdapter<Building> adapter;
    private ListView listView;
    private ImageView myImgView;
    private Button aboutButton;
    private TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter=new ArrayAdapter<>(this, R.layout.text_view);
        listView = findViewById(R.id.list_View);


        myImgView = findViewById(R.id.url_img);

        aboutButton = findViewById(R.id.open);


        myText = findViewById(R.id.copyRightText);

       createMethods();
    }

    public void createMethods(){
        list();
        buttons();

    }

    public void list(){
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Toast.makeText(getApplicationContext(), buildingArrayList.get(position).info(),Toast.LENGTH_LONG).show();
            showImageView(position);

        });


        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=b19karhj");
    }

    public void showImageView(int index){


        Picasso.get().load(buildings[index].getAuxdata().getWiki()).into(myImgView);
        myText.setText(buildings[index].getAuxdata().getCopyright());

    }

    public void buttons(){
        aboutButton.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"Myfragment");
        });
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
