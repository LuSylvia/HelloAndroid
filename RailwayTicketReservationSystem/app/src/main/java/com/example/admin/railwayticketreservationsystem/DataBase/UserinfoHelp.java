package com.example.admin.railwayticketreservationsystem.DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserinfoHelp {
    static String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
    static String DB_NAME = "RailwayReservation.db";

//查找用户个人信息
    public  static String[] find_userinfo(){
        String user_datas[]=new String[6];
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        String sql_seluser="select * from user_data where State = 'login'";
        Cursor cursor=db.rawQuery(sql_seluser,null);
        int index;
        if(cursor.moveToFirst()){
            index=cursor.getColumnIndex("phonenumber");
            user_datas[0]=cursor.getString(index);



            index=cursor.getColumnIndex("pwd");
            user_datas[1]=cursor.getString(index);

            index=cursor.getColumnIndex("realname");
            user_datas[2]=cursor.getString(index);

            index=cursor.getColumnIndex("idCard");
            user_datas[3]=cursor.getString(index);

            index=cursor.getColumnIndex("sex");
            user_datas[4]=cursor.getString(index);

            index=cursor.getColumnIndex("emailaddress");
            user_datas[5]=cursor.getString(index);


        }
        //关闭游标
        cursor.close();
        //关闭数据库
        db.close();

        Log.e("user datas test!",user_datas[0]+","+user_datas[1]);

        return user_datas;
    }

    //退出
    //由于服务器部署在本地，所以不会出现多人同时访问的情况
    public  static void logout(){
        String sql_seluser_login="update   user_data  set State = 'logout' where State = 'login'";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        db.execSQL(sql_seluser_login);
        db.close();
    }


    public static boolean updateinfo(String phone,String oldpwd,String newpwd){
        boolean flag=false;
        String sql_seluser="select phonenumber,pwd from user_data where phonenumber=?";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor=db.rawQuery(sql_seluser,new String[]{phone});
        if(cursor.moveToFirst()){
            int index=cursor.getColumnIndex("pwd");
            String truepwd=cursor.getString(index);

            if(truepwd.equals(oldpwd)){
                String sql_update="update user_data set pwd = ? where phonenumber =?";
                db.execSQL(sql_update,new String []{newpwd,phone});

                flag=true;
            }

        }


        //关闭游标
        cursor.close();
        //关闭数据库
        db.close();
        return flag;
    }


}
