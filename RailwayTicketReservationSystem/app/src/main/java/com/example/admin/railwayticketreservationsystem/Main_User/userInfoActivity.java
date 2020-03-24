package com.example.admin.railwayticketreservationsystem.Main_User;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.railwayticketreservationsystem.DataBase.UserinfoHelp;
import com.example.admin.railwayticketreservationsystem.R;

public class userInfoActivity extends AppCompatActivity {
    private TextView tv_phone;
    private TextView tv_pwd;
    private TextView tv_name;
    private TextView tv_idCard;
    private  TextView tv_sex;
    private TextView  tv_email;

    String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
    String DB_NAME = "RailwayReservation.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViewByID();
        init();


    }

    void init(){
        String user_datas[]= UserinfoHelp.find_userinfo();
        tv_phone.setText(user_datas[0]);
        tv_pwd.setText(user_datas[1]);
        tv_name.setText(user_datas[2]);
        tv_idCard.setText(user_datas[3]);
        tv_sex.setText(user_datas[4]);
        tv_email.setText(user_datas[5]);
    }


    void findViewByID(){
        tv_phone=findViewById(R.id.tv_infro_phone);
        tv_pwd=findViewById(R.id.tv_infro_pwd);
        tv_name=findViewById(R.id.tv_infro_name);
        tv_sex=findViewById(R.id.tv_info_sex);
        tv_email=findViewById(R.id.tv_info_email);
        tv_idCard=findViewById(R.id.tv_infro_idCard);
    }



}
