package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectedActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etNote;
    Button btnUpdate, btnDelete, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etNote = findViewById(R.id.etNote);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intent = getIntent();
        int personId = intent.getIntExtra("personId", 0);
        PersonDataService dataService = new PersonDataService(SelectedActivity.this);
        dataService.getPersonById(personId, new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    etFullName.setText(jsonObject.getString("fullName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    etEmail.setText(jsonObject.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    etNote.setText(jsonObject.getString("note"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int result) {

            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "GetPersonById: "+error);
            }
        });

        btnUpdate.setOnClickListener(view -> {

            Person person = new Person();
            person.setPersonId(personId);
            person.setFullName(etFullName.getText().toString());
            person.setEmail(etEmail.getText().toString());
            person.setNote(etNote.getText().toString());

            dataService.putPerson(person, new DataServiceListener() {
                @Override
                public void onSuccess(JSONArray jsonArray) {

                }

                @Override
                public void onSuccess(JSONObject jsonObject) {

                }

                @Override
                public void onSuccess(int result) {
                    Toast.makeText(SelectedActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(SelectedActivity.this, MainActivity.class);
                    startActivity(goBack);
                    finish();
                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(SelectedActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(SelectedActivity.this, MainActivity.class);
                    startActivity(goBack);
                    finish();
                }
            });
        });

        btnDelete.setOnClickListener(view -> {
            dataService.deletePerson(personId, new DataServiceListener() {
                @Override
                public void onSuccess(JSONArray jsonArray) {
                    
                }

                @Override
                public void onSuccess(JSONObject jsonObject) {

                }

                @Override
                public void onSuccess(int result) {
                    if(result == 1){
                        Toast.makeText(SelectedActivity.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                        Intent goBack = new Intent(SelectedActivity.this, MainActivity.class);
                        startActivity(goBack);
                        finish();
                    }
                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(SelectedActivity.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                    Intent goBack = new Intent(SelectedActivity.this, MainActivity.class);
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