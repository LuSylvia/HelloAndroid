package com.example.admin.railwayticketreservationsystem.DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//负责创建数据库与数据的初始化
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static Integer version =1;

    public MySQLiteHelper( Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    public MySQLiteHelper(Context context,String name,int version){
        this(context,name,null,version);
    }

    public MySQLiteHelper(Context context,String name){
        this(context,name,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


//        //创建数据库
//        String sql_user_data="create table user_data(id integer primary key autoincrement," +
//                "pwd varchar(16), " +
//                "realname varchar(5)," +
//                " idCard char(18) unique , " +
//                "phonenumber varchar(12)  unique ," +
//                "sex char(2), " +
//                "emailaddress varchar(20)unique ," +
//                "jurisdiction varchar(3)not null    )  ";
//        db.execSQL(sql_user_data);
//
//        String sql_train_type="create table train_type(train_id int(11) primary key," +
//                "train_name varchar(10)," +
//                "train_type varchar(10) not null)";
//        db.execSQL(sql_train_type);
//
//        String sql_trainmain="create table train_main(train_id int(11)," +
//                "stop_id int(11)," +
//                "city_name varchar(10)," +
//                "stop_name varchar(10)," +
//                "arrive_time varchar(15)," +
//                "start_time varchar(15)," +
//                "KM integer," +
//                "primary key(train_id,stop_id)  )";
//        db.execSQL(sql_trainmain);
//
//        String sql_train_seat="CREATE TABLE train_seat (" +
//                "    train_id      INT (11)    NOT NULL," +
//                "    seat_id       INT (11)    NOT NULL," +
//                "    seat_type     VARCHAR (3)," +
//                "    seat_location VARCHAR (5)," +
//                "    seatIsSelled  CHAR (1)    NOT NULL," +
//                "    PRIMARY KEY (train_id,seat_id )  )";
//        db.execSQL(sql_train_seat);
//
//
//        String sql_traintickets="create table train_tickets(train_id int(11) ,start_date varchar(15)," +
//                "notSelled integer,firstClass integer,secondClass integer,total integer," +
//                "primary key(train_id,start_date) )";
//        db.execSQL(sql_traintickets);
//
//
//
//        String sql_orders="CREATE TABLE Orders (" +
//                "    order_id        INTEGER      PRIMARY KEY ," +
//                "    realname        VARCHAR (5)," +
//                "    idCard          CHAR (18)," +
//                "    phonenumber     VARCHAR (12)," +
//                "    train_name      VARCHAR (10)," +
//                "    seat_type       VARCHAR (3)," +
//                "    price           INTEGER," +
//                "    start_stop_name VARCHAR (10)," +
//                "    start_time      VARCHAR (15)," +
//                "    end_stop_name   VARCHAR (10)," +
//                "    arrive_time     VARCHAR (15)," +
//                "    CreateDate      VARCHAR (15)," +
//                "    Date            VARCHAR (15)," +
//                "    Status          VARCHAR (10)  );";
//        db.execSQL(sql_orders);
//
//        //往用户信息表中插入信息
//        insert_userdata(db);
//        //往traintype表中插入信息
//        insert_traintype(db);
//        //往trainmain表中插入信息
//        insert_trainmain(db);
//        //往火车票数表中插入信息
//        insert_traintickets(db);
//
//
//        //往火车座位表中插入信息
//        //insert_trainseat(db);


    }

    void insert_userdata(SQLiteDatabase db){
        String sql_insert_data_1="insert into user_data(id,pwd,realname,idCard,phonenumber,sex,emailaddress,jurisdiction) " +
                "values(1,'123','王腾','522227199905067230','13637981781','男','985380526@qq.com','用户')";
        db.execSQL(sql_insert_data_1);
        String sql_insert_data_2="insert into user_data(id,pwd,realname,idCard,phonenumber,sex,emailaddress,jurisdiction) " +
                "values(2,'456','龙曦','522227202507108475','15844687932','女','wt985380526@163.com','管理员')";
        db.execSQL(sql_insert_data_2);
    }


    void insert_trainmain(SQLiteDatabase db){
        String sql_insert_trainmain="INSERT INTO train_main (train_id,stop_id,city_name,stop_name,arrive_time,start_time,KM)" +
                "VALUES ( 1,0,'广州','广州南','始发站','07:28',0)," +
                "( 1,1,'肇庆','肇庆东','08:05','08:07',100)," +
                "( 1,2,'重庆','重庆西','15:07','15:11',1320),"+
                "( 1,3,'成都','成都东','17:23','终点站',1581),"+

                "( 2,0,'杭州','杭州东','始发站','07:42',0),"+
                "( 2,1,'天门','天门南','13:27','13:36',850),"+
                "( 2,2,'荆州','荆州','14:21','14:23',930),"+
                "( 2,3,'重庆','重庆北','19：45','终点站',1600),"+

                "( 3,0,'重庆','重庆西','始发站','07:26',0),"+
                "( 3,1,'贵阳','贵阳东','09:40','09:44',350),"+
                "( 3,2,'广州','广州南','14:55','终点站',1320),"+

                "( 4,0,'重庆','重庆北','始发站','07:26',0),"+
                "( 4,1,'宜昌','宜昌东','11:35','11:42',600),"+
                "( 4,2,'荆州','荆州','12:22','12:24',650),"+
                "( 4,3,'杭州','杭州东','19:10','终点站',1250),"+

                "( 5,0,'广州','广州南','始发站','08:00',0),"+
                "( 5,1,'长沙','长沙南','10:21','10:24',670),"+
                "( 5,2,'杭州','杭州东','14:00','14:02',1250),"+
                "( 5,3,'上海','上海虹桥','14:50','终点站',1500)";
        db.execSQL(sql_insert_trainmain);

    }

    void insert_traintype(SQLiteDatabase db){
        String sql_traintype="insert into train_type(train_id,train_name,train_type)" +
                "values(1,'G1','高铁'),"+
                "(2,'G2','高铁'),"+
                "(3,'G3','高铁'),"+
                "(4,'D1','动车'),"+
                "(5,'D2','动车')";
        db.execSQL(sql_traintype);
    }

    void insert_trainseat(SQLiteDatabase db){
        String sql_trainseat="insert into train_seat(train_id,seat_id,seat_type,seat_location,seatIsSelled)"+
                "values(1,1,'一等座','01A','是'),"+
                "(1,2,'一等座','01B','否'),"+
                "(1,3,'二等座','02A','否'),"+
                "(1,4,'二等座','02B','是'),"+
                "(2,1,'一等座','01A','是'),"+
                "(2,2,'一等座','01B','是'),"+
                "(2,3,'二等座','02A','是'),"+
                "(2,3,'二等座','02B','否'),"+

                "(3,1,'一等座','01A','是'),"+
                "(3,2,'一等座','01B','否'),"+
                "(3,3,'一等座','01C','否'),"+
                "(3,4,'二等座','02A','是'),"+
                "(3,5,'二等座','02B','否'),"+
                "(3,6,'二等座','02C','否'),"+

                "(4,1,'一等座','01A','是'),"+
                "(4,2,'一等座','01B','是'),"+
                "(4,3,'二等座','02A','否'),"+
                "(4,4,'二等座','02B','是'),"+

                "(5,1,'一等座','01A','是'),"+
                "(5,2,'一等座','01B','否'),"+
                "(5,3,'二等座','02A','否'),"+
                "(5,4,'二等座','02B','否')";
        db.execSQL(sql_trainseat);


    }


    void insert_traintickets(SQLiteDatabase db){
        String sql_insert_traintickets="insert into train_tickets(train_id,start_date,notSelled,firstClass,secondClass,total)" +
                "values(1,'2019-06-17',56,32,24,100)," +
                "(1,'2019-06-18',23,1,22,100),"+
                "(1,'2019-06-19',2,1,1,100),"+

                "(2,'2019-06-17',20,5,15,100)," +
                "(2,'2019-06-18',25,6,19,100),"+
                "(2,'2019-06-19',41,21,20,100),"+


                "(3,'2019-06-17',32,10,22,120)," +
                "(3,'2019-06-18',22,12,10,120),"+
                "(3,'2019-06-19',84,8,76,120),"+


                "(4,'2019-06-17',5,0,5,130)," +
                "(4,'2019-06-18',5,2,3,130),"+
                "(4,'2019-06-19',7,3,4,130),"+

                "(5,'2019-06-17',0,0,0,150),"+
                "(5,'2019-06-18',18,9,9,150),"+
                "(5,'2019-06-19',8,3,5,150)";
        db.execSQL(sql_insert_traintickets);



    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库更新
//        db.execSQL("drop table if exists user_data");
//        onCreate(db);
    }
}
