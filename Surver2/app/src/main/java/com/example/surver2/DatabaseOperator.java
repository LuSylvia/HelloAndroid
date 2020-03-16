package com.example.surver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperator {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseOperator(Context context) {
        dbHelper = new DatabaseHelper(context, "Survey_Result", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    // 检验survey_id是否已存在于Survey_Result
    public boolean isSurveyIdAlreadyExisted(String value) {
        String Query = "Select * from Survey_Result where survey_id =?";
        Cursor cursor = db.rawQuery(Query, new String[] { value });
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }







    // 添加id,survey_id,survey_data,location,time,emei
    public void add(String survey_id,String survey_data,String location,String time,String emei) {
        db.execSQL("insert into Survey_Result(survey_id,survey_data,location,time,emei) values(?,?,?,?,?)",
                new Object[] {survey_id,survey_data,location,time,emei });

    }

}
