package com.yesandroid.makeup.second;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yesandroid.makeup.R;
import com.yesandroid.makeup.api.MakeItem;

public class MainActivity2 extends AppCompatActivity {


    public  TextView wordItemView;
    public  TextView desc;
    public  ImageView makeupImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        wordItemView = (TextView) findViewById(R.id.word);
        desc =  (TextView) findViewById(R.id.desc);
        makeupImageView=(ImageView)findViewById(R.id.image);



        SingleClick singleClick=SingleClick.getInstance();
        MakeItem makeItem=SingleClick.getMakeItem();



        wordItemView.setText(makeItem.getDesc());
        desc.setText(makeItem.getName());
        Picasso.get().load(makeItem.getUrl()).into(makeupImageView);


        Log.e("test--",makeItem.getName());

    }
}