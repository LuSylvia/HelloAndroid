package com.sylvia.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MoveList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_list);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int imgId=bundle.getInt("imgId");
        String  info=bundle.getString("info");


    }
}
