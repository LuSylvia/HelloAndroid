package com.sylvia.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
    int imgIDs[]=new int[32];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        StudentAdapter itemAdapter = new StudentAdapter(MainActivity.this, R.layout.list_item, list);
        ListView listView =findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                bundle.putString("info",info+chineNames[position]);
                bundle.putInt("imgId",R.drawable.ic_launcher_background);
             

                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, MoveList.class);
                startActivity(intent);

            }
        });


    }

    private void initList() {
        for (int i = 0; i < chineNames.length; i++) {
            Student student = new Student(chineNames[i],ids[i],
                    info+chineNames[i],
                    R.drawable.ic_launcher_background,
                    englishNames[i]);
            list.add(student);
        }
    }

}
