package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonDataService dataService = new PersonDataService(MainActivity.this);

        dataService.getAllPersons(new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray response) {
                Log.d("MyData", "Data: " + response.toString());
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
            }

            @Override
            public void onSuccess(int result) {
            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "" + error);
            }
        });

        dataService.getPersonById(3, new DataServiceListener() {
            @Override
            public void onSuccess(JSONObject response) {
                Gson gson = new Gson();
                Person p = gson.fromJson(response.toString(), Person.class);

                Log.d("MyData", p.toString());
            }

            @Override
            public void onSuccess(int result) {
            }

            @Override
            public void onSuccess(JSONArray jsonArray) {
            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "" + error);
            }
        });


        JSONObject post = new JSONObject();
        try {
            post.put("fullName", "Lazzz");
            post.put("email", "yaskii@hotmail.com");
            post.put("note", "henlo");

        } catch (JSONException e){
            e.printStackTrace();
        }

        dataService.postPerson(post, new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) { }

            @Override
            public void onSuccess(JSONObject jsonObject) {
            }

            @Override
            public void onSuccess(int result) {
                Log.d("MyData", "postOnSuccess "+ result);
            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "postOnFailure " + error);
            }
        });

        dataService.deletePerson(22, new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onSuccess(JSONObject jsonObject) {

            }

            @Override
            public void onSuccess(int result) {
                Log.d("MyData", "DeleteOnSuccess "+ result);
            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "deleteOnFailure "+ error);
            }
        });

        Person person = new Person();
        try {
            person.setPersonId(5);
            person.setFullName("bazz");
            person.setEmail("yaskii@hotmail.com");
            person.setNote("henlo from the other side");

        } catch (Exception e){
            e.printStackTrace();
        }

        dataService.putPerson(person, new DataServiceListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onSuccess(JSONObject jsonObject) {

            }

            @Override
            public void onSuccess(int result) {
                Log.d("MyData", "putOnSuccess "+ result);
            }

            @Override
            public void onFailure(String error) {
                Log.d("MyData", "putOnFailure "+ error);
            }
        });

    }
}