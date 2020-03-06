package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ed_name;
    EditText ed_email;
    EditText ed_phone;
    Button btn_write;
    Button btn_read;
    Button btn_update;
    Button btn_remove;
    TextView tv_records;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void myFindViewByID(){
        ed_email=findViewById(R.id.ed_email);
        ed_name=findViewById(R.id.ed_name);
        ed_phone=findViewById(R.id.ed_phone);
        btn_write=findViewById(R.id.btn_write);
        btn_read=findViewById(R.id.btn_read);
        btn_remove=findViewById(R.id.btn_remove);
        btn_update=findViewById(R.id.btn_update);
        tv_records=findViewById(R.id.tv_records);

    }


    public void myClick(View view) {
        switch (view.getId()){
            case R.id.btn_write:

                break;
            case R.id.btn_read:

                break;
            case R.id.btn_update:

                break;
            case R.id.btn_remove:

                break;

            default:
                break;

        }


    }
}
