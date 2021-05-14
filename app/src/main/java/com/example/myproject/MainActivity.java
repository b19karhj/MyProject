package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Building> buildingArrayList=new ArrayList();
    private Building[] buildings;
    private ArrayAdapter<Building> adapter;
    private ListView listView;
    private ImageView myImgView;
    private Button aboutButton;
    private Button webButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter=new ArrayAdapter<>(this, R.layout.text_view);
        listView = findViewById(R.id.list_View);
        myImgView = findViewById(R.id.url_img);
        aboutButton = findViewById(R.id.open);
        webButton = findViewById(R.id.open_web);





       createMethods();
        openWindow();



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

            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            intent.putExtra("testitem",buildingArrayList.get(position));
            startActivity(intent);

        });


        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=b19karhj");
    }

    public void showImageView(int index){


        Picasso.get().load(buildings[index].getAuxdata().getWiki()).into(myImgView);


    }

    public void buttons(){
        aboutButton.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"Myfragment");
        });

    }

    private void openWindow(){
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent( MainActivity.this, WebViewActivity.class);
              /*HOw to pass array infromation with intent*/
                  /*startActivity(intent);*/


            }
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
