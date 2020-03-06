package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ed_name;
    EditText ed_email;
    EditText ed_phone;
    Button btn_write;
    Button btn_read;
    Button btn_update;
    Button btn_remove;
    TextView tv_records;
    //创建数据库操作类
    DatabaseOperator db;

    Intent Serviceintent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFindViewByID();
        //初始化数据库操作类
        db=new DatabaseOperator(this);
        //设置记录过长时textview可滚动
        tv_records.setMovementMethod(ScrollingMovementMethod.getInstance());

        //播放背景音乐
        Serviceintent=new Intent();
        Serviceintent.setPackage(getPackageName());
        Serviceintent.setAction("com.angel.Android.MUSIC");


        startService(Serviceintent);

    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        stopService(Serviceintent);
        System.exit(0);
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
                //增
                //先判断是否已经输入全部信息，然后再判断手机号是否被注册，如果未被注册才可创建该用户
                tv_records.setText("no records");

                String phone=ed_phone.getText().toString();
                String name=ed_name.getText().toString();
                String email=ed_email.getText().toString();

                if(phone.isEmpty()||name.isEmpty()||email.isEmpty()){
                    Toast.makeText(this,"You must input all the information first!",Toast.LENGTH_SHORT).show();
                    break;
                }

                if(db.checkPhoneIsAlreadyInDBorNot(phone)){
                    //手机号已经被注册
                    Toast.makeText(this,"The phone has been registered!",Toast.LENGTH_SHORT).show();
                    break;
                }

                User user=new User();
                user.setName(name);user.setEmail(email);user.setPhone(phone);

                db.add(user);
                Toast.makeText(this,"Save successed!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_read:
                //查
                //首先读取数据库，如果得到的users长度不为0则显示所有用户信息，否则显示数据库为空

                ArrayList<User> users=db.read();
                if(users.size()==0){
                    Toast.makeText(this,"DB is empty!",Toast.LENGTH_SHORT).show();
                    tv_records.setText("no records");
                    break;
                }


                tv_records.setText("");
                for(int i=0;i<users.size();i++){

                    tv_records.append("name:"+users.get(i).getName()+"\n"
                                    +"email:"+users.get(i).getEmail()+"\n"
                                    +"phone:"+users.get(i).getPhone()+"\n"+"\n");
//                    tv_records.setText("email:"+users.get(i).getEmail()+"\r\n");
//                    tv_records.setText("phone:"+users.get(i).getPhone()+"\r\n");
                }

                break;
            case R.id.btn_update:
                //改
                //先判断用户是否输入全部信息，再读取数据库判断该手机号是否存在，然后再对该手机号所对应的用户进行更新
                tv_records.setText("no records");

                name=ed_name.getText().toString();
                email=ed_email.getText().toString();
                phone=ed_phone.getText().toString();
                if(phone.isEmpty()||name.isEmpty()||email.isEmpty()){
                    Toast.makeText(this,"You must input all the information first!",Toast.LENGTH_SHORT).show();
                    break;
                }

                if(db.checkPhoneIsAlreadyInDBorNot(phone)){
                    User user1=new User();
                    user1.setName(name);user1.setEmail(email);user1.setPhone(phone);

                    db.update(user1);

                    Toast.makeText(this,"Update successed!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Invalid phone number!",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_remove:
                //删
                tv_records.setText("no records");
                db.delete();
                Toast.makeText(this,"Delete successed!",Toast.LENGTH_SHORT).show();
                tv_records.setText("no records");

                break;

            default:
                break;

        }

    }
}
