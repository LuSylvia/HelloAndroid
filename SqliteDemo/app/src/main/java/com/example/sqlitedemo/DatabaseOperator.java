package com.example.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseOperator {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseOperator(Context context) {
        dbHelper = new DatabaseHelper(context, "SQLiteData", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    // 检验手机号是否已存在
    public boolean checkPhoneIsAlreadyInDBorNot(String value) {
        String Query = "Select * from SQLiteData where phone =?";
        Cursor cursor = db.rawQuery(Query, new String[] { value });
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }



    //读取数据库中所有信息
    public ArrayList<User> read(){
        ArrayList<User> users=new ArrayList<User>();
        String Query = "Select * from SQLiteData ";
        Cursor cursor = db.rawQuery(Query, null);
       while (cursor.moveToNext()){
           User user=new User();
           user.setPhone(cursor.getString(0));
           user.setName(cursor.getString(1));
           user.setEmail(cursor.getString(2));

           users.add(user);

       }
        cursor.close();
        return  users;
    }



    // 添加姓名,邮箱，手机号
    public void add(User user) {
        db.execSQL("insert into SQLiteData values(?,?,?)",
                new Object[] {user.getPhone(),user.getName(),user.getEmail() });

    }

    // 修改姓名，邮箱
    public void update(User user) {
        db.execSQL("update SQLiteData set name=?,email=? where phone=?",
                new Object[] { user.getName(), user.getEmail(), user.getPhone()});
    }

    // 删除数据库中所有信息
    public void delete() {
        db.execSQL("delete from SQLiteData" );
    }

}
