package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    LinearLayout llWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonDataService dataService = new PersonDataService(MainActivity.this);
        llWrapper = findViewById(R.id.llWrapper);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(this, CreateActivity.class);
            startActivity(intent);
        });

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        dataService.getAllPersons(new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                for (int i=0; i < jsonArray.length(); i++) {
                    Button btn = new Button(MainActivity.this);
                    try {
                        btn.setText(jsonArray.getJSONObject(i).getString("fullName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    btn.setLayoutParams(btnParams);
                    llWrapper.addView(btn, btnParams);
                    int finalI = i;
                    btn.setOnClickListener(view -> {
                        Intent intent = new Intent(MainActivity.this, SelectedActivity.class);
                        try {
                            intent.putExtra("personId", jsonArray.getJSONObject(finalI).getInt("personId"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    });
                }
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {

            }

            @Override
            public void onSuccess(int result) {

            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "GetAllPerson: "+error);
            }
        });
    }
}