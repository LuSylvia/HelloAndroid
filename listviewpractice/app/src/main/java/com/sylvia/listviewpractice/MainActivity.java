package com.sylvia.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_main;

    private List<Student> list = new ArrayList<>();
    String chineNames[]=new String[32];


    int ids[]={20161707, 20161713, 20171591, 20171592, 20171616,
        20171627, 20171641, 20171649, 20171650, 20171653,
        20171654, 20171655, 20171656, 20171659, 20171664,
        20171666, 20171667, 20171668, 20171669, 20171670,
        20171679, 20171688, 20171697, 20171705, 20171707,
        20171714, 20171717, 20171731, 20171742, 20175064,
        20175980, 20175990

};
    String englishNames[]=new String[32];
    String info="hello, I am ";
    int imgIDs[]={R.drawable.emoji_kids_01, R.drawable.emoji_kids_02,R.drawable.emoji_kids_03,R.drawable.emoji_kids_04,
            R.drawable.emoji_kids_05,R.drawable.emoji_kids_06,R.drawable.emoji_kids_07,R.drawable.emoji_kids_08,
            R.drawable.emoji_kids_09,R.drawable.emoji_kids_10,R.drawable.emoji_kids_11,R.drawable.emoji_kids_12,
            R.drawable.emoji_kids_13,R.drawable.emoji_kids_14,R.drawable.emoji_kids_15,R.drawable.emoji_kids_16,
            R.drawable.emoji_kids_17,R.drawable.emoji_kids_18,R.drawable.emoji_kids_19,R.drawable.emoji_kids_20,
            R.drawable.emoji_kids_21,R.drawable.emoji_kids_22,R.drawable.emoji_kids_23,R.drawable.emoji_kids_24,
            R.drawable.emoji_kids_25,R.drawable.emoji_kids_26,R.drawable.emoji_kids_27,R.drawable.emoji_kids_28,
            R.drawable.emoji_kids_29,R.drawable.emoji_kids_30,R.drawable.emoji_kids_31,R.drawable.emoji_kids_32
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_main=findViewById(R.id.tv_main);
        tv_main.setText("The number of students:"+ids.length);


        initList();

        StudentAdapter itemAdapter = new StudentAdapter(MainActivity.this, R.layout.list_item, list);
        ListView listView =findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                bundle.putString("info",info+ids[position]);
                bundle.putInt("imgId",imgIDs[position]);


                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, MoveList.class);
                startActivity(intent);

            }
        });


    }

    private void initList() {
        for (int i = 0; i < chineNames.length; i++) {
            Student student = new Student("这是测试",ids[i],
                    info+"这是测试",
                    imgIDs[i],
                    "Demo");

            //Student student=new Student(chineNames[i],ids[i],info+chineNames[i],R.drawable.ic_launcher_background,englishNames[i]);
            list.add(student);
        }
    }

}
