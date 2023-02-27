package com.yesandroid.makeup;
/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yesandroid.makeup.api.MakeItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final LinkedList<MakeItem> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private MakeupListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new MakeupListAdapter(this, mWordList);






        Get_Interface apiService= Get_Retrofit_Client.getClient().create(Get_Interface.class);
        Call<JsonArray> call = apiService.getsocial();
        Log.d("pass 2" ,"passed form 2");
        call.enqueue(new Callback<JsonArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                Log.d("finalres",response.body().toString());
                Log.d("finalres",response.body().get(0).toString());


                for(int i=0;i<response.body().size();i++)
                {
                    try {
                        JSONObject jsonObject =new JSONObject(response.body().get(i).toString());
                      //  Log.d("finalres",jsonObject.getString("name"));

                        MakeItem makeItem=new MakeItem();
                        makeItem.setName(jsonObject.getString("name"));
                        makeItem.setUrl(jsonObject.getString("image_link"));
                        makeItem.setType(jsonObject.getString("product_type"));
                        makeItem.setDesc(jsonObject.getString("description"));
                     //   mWordList.addLast(jsonObject.getString("name"));
                        mWordList.add(makeItem);
                        mRecyclerView.setAdapter(mAdapter);
                        // Give the recycler view a default layout manager.
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }




            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

                Log.d("failed","failed");
                Log.d("reason",t.getMessage());

            }
        });



    }



}
