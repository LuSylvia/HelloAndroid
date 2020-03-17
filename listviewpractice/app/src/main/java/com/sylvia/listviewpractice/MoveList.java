package com.sylvia.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MoveList extends AppCompatActivity {
    ImageView iv_student_img;
    TextView tv_student_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_list);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int imgId=bundle.getInt("imgId");
        String  info=bundle.getString("info");

        iv_student_img=findViewById(R.id.iv_student_img);
        tv_student_info=findViewById(R.id.tv_student_info);

        iv_student_img.setImageResource(imgId);
        tv_student_info.setText(info);

    }
}
