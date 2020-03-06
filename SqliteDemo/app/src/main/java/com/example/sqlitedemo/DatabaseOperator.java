package com.example.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperator {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseOperator(Context context) {
        dbHelper = new DatabaseHelper(context, "SQLiteData", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    // 检验手机号是否已存在
    public boolean checkPhoneISAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from SQLiteData where phone =?";
        Cursor cursor = db.rawQuery(Query, new String[] { value });
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }



    // 添加姓名,邮箱，手机号
    public void add(String name,String email,String phone) {
        db.execSQL("insert into SQLiteData values(?,?,?)",
                new Object[] {name,email,phone });

    }

    // 修改姓名，邮箱
    public void update(String name,String email,String phone) {
        db.execSQL("update SQLiteData set name=?,email=? where phone=?",
                new Object[] { name, email, phone});
    }

    // 删除数据库中所有信息
    public void delete() {
        db.execSQL("delete from SQLiteData" );
    }

}
