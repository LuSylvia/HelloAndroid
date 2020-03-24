package com.example.admin.railwayticketreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.DataBase.LoginHelp;
import com.example.admin.railwayticketreservationsystem.DataBase.MySQLiteHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_phone;
    private EditText ed_pwd_1;
    private EditText ed_pwd_2;


    private EditText ed_realname;
    private EditText ed_idnumber;
    private Spinner spinner;
    private EditText ed_emailaddress;
    private EditText ed_inviteCode;


    private Button btn_reg;


    //数据库
    MySQLiteHelper sql=new MySQLiteHelper(this,"user_data");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewbyID();
        btn_reg.setOnClickListener(RegisterActivity.this);

    }



    void findViewbyID(){

        ed_phone=findViewById(R.id.ed_phone);
        ed_pwd_1=findViewById(R.id.ed_pwd_reg_1);
        ed_pwd_2=findViewById(R.id.ed_pwd_reg_2);


        ed_realname=findViewById(R.id.ed_realname_reg);
        ed_idnumber=findViewById(R.id.ed_idnumber);
        spinner=findViewById(R.id.spinner);
        ed_emailaddress=findViewById(R.id.ed_email);


        btn_reg=findViewById(R.id.btn_reg);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg:
                //连接数据库，查询电话是否已经被注册
                String phonenumber=ed_phone.getText().toString();
                Log.e("register test","phone number is "+phonenumber);
                if(LoginHelp.phoneIsRegistered(phonenumber,sql)==true){
                    Toast.makeText(RegisterActivity.this,"该电话已经被注册！",Toast.LENGTH_SHORT).show();
                    break;
                }

                //如果不存在，继续判断前后2次输入的密码是否相同
                String pwd_1=ed_pwd_1.getText().toString();
                String pwd_2=ed_pwd_2.getText().toString();
                if(!pwd_1.equals(pwd_2)){
                    Toast.makeText(RegisterActivity.this,"前后2次输入的密码不正确！",Toast.LENGTH_SHORT).show();
                    break;
                }
                Log.e("register test","pwd number is "+pwd_1);

                //连接数据库
//                MySQLiteHelper sqLiteHelper2=new MySQLiteHelper(this,"user_data");
//                SQLiteDatabase db2=sqLiteHelper2.getWritableDatabase();

                String realname=ed_realname.getText().toString();//获取姓名
                Log.e("register test","name is "+realname);
                String idCard=ed_idnumber.getText().toString();//获取身份证

                //检验身份证有效性
                if(!LoginHelp.isIDCard(idCard)) {
                    Toast.makeText(RegisterActivity.this,"身份证格式有误！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //判断身份证是否已被注册
                if(LoginHelp.IDCardisUserd(idCard,sql)){
                    Toast.makeText(RegisterActivity.this,"身份证已被注册",Toast.LENGTH_SHORT).show();
                    break;
                }

                Log.e("register test","idCARD number is "+idCard);
                String sex=spinner.getSelectedItem().toString();//获取性别
                Log.e("register test","sex  is "+sex);
                String email=ed_emailaddress.getText().toString();//获取邮箱
                //检验邮箱有效性
                if(!LoginHelp.checkEmail(email)){
                    Toast.makeText(RegisterActivity.this,"邮箱格式有误！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //判断邮箱是否已经被注册
                if(LoginHelp.emailaddressisUsed(email,sql)){
                    Toast.makeText(RegisterActivity.this,"邮箱已被注册！",Toast.LENGTH_SHORT).show();
                    break;
                }
                Log.e("register test","email  is "+email);


                //赋值
                String user_datas[]=new String[6];
                user_datas[0]=phonenumber;
                user_datas[1]=pwd_2;
                user_datas[2]=realname;
                user_datas[3]=idCard;
                user_datas[4]=sex;
                user_datas[5]=email;


                //创建用户
                if(!LoginHelp.createuser(user_datas,sql)){
                    Toast.makeText(RegisterActivity.this,"创建失败！",Toast.LENGTH_SHORT).show();
                    break;
                }

                Toast.makeText(RegisterActivity.this,"创建成功！",Toast.LENGTH_SHORT).show();

                //创建成功后，返回登录页面
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

                break;

                default:
                    break;



        }

    }
}
