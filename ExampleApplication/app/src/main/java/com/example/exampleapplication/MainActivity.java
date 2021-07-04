package com.example.exampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<MyModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Call<String> response = retrofit.create(MyInterface.class).value();
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String res = response.body();
                try {
                    JSONObject root = new JSONObject(res);
                    JSONArray results = root.getJSONArray("results");
                    for (int i=0;i<results.length();i++){
                        JSONObject object = results.getJSONObject(i);
                        String trackname = object.getString("trackName");
                        MyModel myModel = new MyModel(trackname);
                        list.add(myModel);
                        MyAdapter adapter = new MyAdapter(MainActivity.this,list);
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}