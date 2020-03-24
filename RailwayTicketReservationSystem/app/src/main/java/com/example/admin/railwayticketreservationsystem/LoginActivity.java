package com.example.admin.railwayticketreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.railwayticketreservationsystem.DataBase.LoginHelp;
import com.example.admin.railwayticketreservationsystem.DataBase.MySQLiteHelper;
import com.example.admin.railwayticketreservationsystem.Main_User.MainActivity_user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_welcome;
    private EditText ed_username,ed_pwd;
    private Button btn_Login,btn_register;
    String phonenumber,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findByID();

        //设置点击事件
        btn_Login.setOnClickListener(LoginActivity.this);
        btn_register.setOnClickListener(LoginActivity.this);
        //拷贝数据库


        String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
        String DB_NAME = "RailwayReservation.db";

        // 检查 SQLite 数据库文件是否存在
        if ((new File(DB_PATH + DB_NAME)).exists() == false) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DB_PATH);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }

            try {
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                // 输出流
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                // 关闭文件流
                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //假如拷贝失败，创建数据库
//        MySQLiteHelper sqLiteHelper=new MySQLiteHelper(this,"user_data");
//        sqLiteHelper=new MySQLiteHelper(this,"Orders");
//        sqLiteHelper=new MySQLiteHelper(this,"train_main");
//        sqLiteHelper=new MySQLiteHelper(this,"train_tickets");
//        sqLiteHelper=new MySQLiteHelper(this,"train_type");




    }


    void findByID(){
        //绑定控件
        tv_welcome=findViewById(R.id.tv_welcome);
        ed_username=findViewById(R.id.ed_username);
        ed_pwd=findViewById(R.id.ed_pwd);

        btn_Login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);

    }

    @Override
    public void onClick(View arg0){
        switch (arg0.getId()){
            //登录
            case R.id.btn_login:
                //获取用户名和密码
                phonenumber=ed_username.getText().toString();
                pwd=ed_pwd.getText().toString();

                MySQLiteHelper mySQLiteHelper=new MySQLiteHelper(this,"user_data");


                int id= LoginHelp.findfromSql(phonenumber,pwd,mySQLiteHelper);
                if(id>=0){
                    //跳转到用户界面
                    Intent intent_login=new Intent(LoginActivity.this, MainActivity_user.class);
                    startActivity(intent_login);
                    break;
                }else if(id==-1){
                    Toast.makeText(LoginActivity.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                    break;
                }else if(id==-2){
                    Toast.makeText(LoginActivity.this,"不存在该用户！",Toast.LENGTH_SHORT).show();
                    break;
                }
                break;

            case R.id.btn_register:
                //创建用户
                Intent intent_register=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent_register);
                finish();
                break;




        }

    }
}
