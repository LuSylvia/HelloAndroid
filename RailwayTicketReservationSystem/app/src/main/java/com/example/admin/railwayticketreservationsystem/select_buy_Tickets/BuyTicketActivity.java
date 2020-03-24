package com.example.admin.railwayticketreservationsystem.select_buy_Tickets;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.admin.railwayticketreservationsystem.DataBase.MySQLiteHelper;
import com.example.admin.railwayticketreservationsystem.DataBase.TicketHelp;
import com.example.admin.railwayticketreservationsystem.Main_User.MainActivity_user;
import com.example.admin.railwayticketreservationsystem.R;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class BuyTicketActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_starttime_buy;
    private TextView tv_startcity_buy;
    private TextView tv_arrivetime_buy;
    private TextView tv_arrivetcity_buy;
    private TextView tv_trainname_buy;
    private TextView tv_totaltime_buy;

    private EditText ed_passenger_name;
    private EditText ed_passenger_idCard;
    private EditText ed_passenger_phone;
    private EditText ed_checksum;

    private Button btn_first;
    private Button btn_second;

    private Button btn_count;
    private Button btn_commit;

    TimeCount timeCount;
    String checkSum;
    String datas[];
    //用于标识用户到底是选择了一等座还是二等座，ttue为一等座，false为二等座
    Boolean firstisseleceted=true;
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    static String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
    static String DB_NAME = "RailwayReservation.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        FindViewbyid();
        //获取查票Acitvity传过来的信息
        Intent intent=getIntent();
        datas=intent.getStringArrayExtra("datas");
        initData(datas);

        //设置点击事件
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);

        btn_count.setOnClickListener(this);
        btn_commit.setOnClickListener(this);

        //初始化定时器
        timeCount=new TimeCount(60000,1000,btn_count);


    }

    //绑定控件
    void FindViewbyid(){
        tv_starttime_buy=findViewById(R.id.tv_startTime_buy);
        tv_startcity_buy=findViewById(R.id.tv_startCity_buy);

        tv_arrivetime_buy=findViewById(R.id.tv_arrivetime_buy);
        tv_arrivetcity_buy=findViewById(R.id.tv_arriveCity_buy);

        tv_trainname_buy=findViewById(R.id.tv_trainName_buy);
        tv_totaltime_buy=findViewById(R.id.tv_totaltime_buy);

        btn_first=findViewById(R.id.btn_firstClass);
        btn_second=findViewById(R.id.btn_secondClass);

        ed_passenger_name=findViewById(R.id.ed_realname_buy);
        ed_passenger_idCard=findViewById(R.id.ed_idCard_buy);
        ed_passenger_phone=findViewById(R.id.ed_phone_buy);
        ed_checksum=findViewById(R.id.ed_VerificationCode);

        btn_count=findViewById(R.id.btn_getVerificationCode);
        btn_commit=findViewById(R.id.btn_commit);



    }

    //初始化数组
    void initData(String buffers[]){
        tv_starttime_buy.setText(buffers[0]);
        tv_startcity_buy.setText(buffers[1]);

        tv_arrivetime_buy.setText(buffers[2]);
        tv_arrivetcity_buy.setText(buffers[3]);

        tv_trainname_buy.setText(buffers[4]);

        btn_first.setText("一等座\n "+buffers[5]+"张\n ￥"+buffers[6]);
        btn_second.setText("二等座\n"+buffers[7]+"张\n ￥"+buffers[8]);


        if(Integer.parseInt(buffers[5])==0){
            //如果一等座车票已经卖完，设置其为不可点击
            btn_first.setEnabled(false);
        }
        if(Integer.parseInt(buffers[7])==0){
            //如果二等座车票已经卖完，设置其为不可点击
            btn_second.setEnabled(false);
        }



        tv_totaltime_buy.setText(buffers[9]);

        Log.e("Laowangtest","laowang test!");
    }

    //生成随机验证码
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

    public AlertDialog.Builder simpleDialog(final Context context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setIcon(R.mipmap.ic_user3)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null);
        return builder;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_firstClass:
                //代表用户选中了一等座
                if(!firstisseleceted){
                    //修改一等座按钮背景颜色
                    btn_first.setBackgroundResource(R.color.colorBlue);
                    btn_first.setTextColor(Color.WHITE);
                    //修改二等座按钮背景颜色
                    btn_second.setBackgroundResource(R.color.colorWhite);
                    btn_second.setTextColor(Color.BLACK);

                    firstisseleceted=true;
                }

                break;


            case  R.id.btn_secondClass:
                if(firstisseleceted){
                    //修改二等座按钮背景颜色
                    btn_second.setBackgroundResource(R.color.colorBlue);
                    btn_second.setTextColor(Color.WHITE);
                    //修改一等座按钮背景颜色
                    btn_first.setBackgroundResource(R.color.colorWhite);
                    btn_first.setTextColor(Color.BLACK);

                    firstisseleceted=false;

                }

                break;



            case R.id.btn_getVerificationCode:
                //获取验证码

                timeCount.start();

                //随机生成验证码
                checkSum=generateVerifyCode(4);
                Toast.makeText(this,"checksum is "+checkSum,Toast.LENGTH_SHORT).show();

                //弹出小型对话框
                AlertDialog.Builder builder=simpleDialog(this,"验证码",checkSum);
                builder.show();

                break;

            case R.id.btn_commit:
                //提交订单
                String name=ed_passenger_name.getText().toString();
                if(name==null){
                    Toast.makeText(this,"姓名不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                String idCard=ed_passenger_idCard.getText().toString();

                if(idCard==null){
                    Toast.makeText(this,"身份证不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    if(TicketHelp.IDCardisUsed(idCard)){
                        Toast.makeText(this,"该身份证已经被使用！",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                String phone=ed_passenger_phone.getText().toString();

                if(phone==null){
                    Toast.makeText(this,"电话不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }



                String checksumOfuser=ed_checksum.getText().toString();
                if(!checksumOfuser.equals(checkSum)){
                    Toast.makeText(this,"检验码错误！",Toast.LENGTH_SHORT).show();
                    break;
                }
                Log.e("Laowangtest","btn_commit is open!");

                //连接数据库
//                MySQLiteHelper sqLiteHelper_orders=new MySQLiteHelper(this,"Orders");
//                MySQLiteHelper sqLiteHelper_tickets=new MySQLiteHelper(this,"train_tickets");
//                SQLiteDatabase db_orders=sqLiteHelper_orders.getWritableDatabase();
//                SQLiteDatabase db_tickets=sqLiteHelper_tickets.getWritableDatabase();

                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

                //获取当前时间（X年X月X日）
                Calendar cal=Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DATE);
                String nowDate=year+"-"+month+"-"+day;

                Log.e("Laowangtest",nowDate);


                //补充data数组中没有的信息到另一个数组中
                String dataExtra[]=new String[10];
                dataExtra[0]=ed_passenger_name.getText().toString();
                dataExtra[1]=ed_passenger_idCard.getText().toString();
                dataExtra[2]=ed_passenger_phone.getText().toString();
                dataExtra[3]=nowDate;
                if(firstisseleceted){
                    //一等座
                    //存储座位类型
                    dataExtra[4]=datas[5];
                    //存储座位价格
                    dataExtra[5]=datas[6];
                }else{
                    //二等座
                    dataExtra[4]=datas[7];
                    dataExtra[5]=datas[8];
                }



                //调用购买车票函数，往订单表中添加乘客信息，同时tickets表中票数-1
                TicketHelp.buyTicket(db,db,datas,dataExtra);


                //关闭数据库
                db.close();


                Toast.makeText(this,"购买成功！",Toast.LENGTH_LONG).show();
                //返回主界面
                Intent intent=new Intent(BuyTicketActivity.this, MainActivity_user.class);
                startActivity(intent);


                break;

                default:
                    Log.e("Laowangtest","default is open!");
                    break;




        }
    }
}
