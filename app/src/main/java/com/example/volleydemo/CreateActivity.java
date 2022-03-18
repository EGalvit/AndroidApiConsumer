package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etNote;
    Button btnCreate, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etNote = findViewById(R.id.etNote);

        btnCreate = findViewById(R.id.btnCreate);
        btnReturn = findViewById(R.id.btnReturn);

        PersonDataService dataService = new PersonDataService(CreateActivity.this);
        btnCreate.setOnClickListener(view -> {
            JSONObject post = new JSONObject();
            try {
                post.put("fullName", etFullName.getText().toString());
                post.put("email", etEmail.getText().toString());
                post.put("note", etNote.getText().toString());

            } catch (JSONException e){
                e.printStackTrace();
            }

            dataService.postPerson(post, new DataServiceListener() {
                @Override
                public void onSuccess(JSONArray jsonArray) {

                }

                @Override
                public void onSuccess(JSONObject jsonObject) {

                }

                @Override
                public void onSuccess(int result) {
                    Toast.makeText(CreateActivity.this, "Created successfully", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(CreateActivity.this, MainActivity.class);
                    startActivity(goBack);
                    finish();

                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(CreateActivity.this, "Created successfully", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(CreateActivity.this, MainActivity.class);
                    startActivity(goBack);
                    finish();

                }
            });

        });

        btnReturn.setOnClickListener(view -> {
            Intent goBack = new Intent(this, MainActivity.class);
            startActivity(goBack);
            finish();
        });
    }
}