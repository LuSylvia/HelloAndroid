package com.example.sqlite;

public class DatabaseOperator {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public LianxirenOperator(Context context) {
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



    // 添加联系人
    public void add(LianxirenBean lxr) {
        db.execSQL("insert into SQLiteData values(?,?,?)",
                new Object[] { lxr.getName(), lxr.getNumber(), lxr.getIntroduce() });

    }

    // 修改联系人
    public void update(LianxirenBean lxr) {
        db.execSQL("update lxrData set number=?,introduce=? where name=?",
                new Object[] { lxr.getNumber(), lxr.getIntroduce(), lxr.getName() });
    }

    // 删除联系人
    public void delete(String name) {
        db.execSQL("delete from lxrData where name=?", new String[] { name });
    }

    // 查询联系人
    public LianxirenBean queryOne(String name) {
        LianxirenBean lxr = new LianxirenBean();
        Cursor c = db.rawQuery("select * from lxrData where name= ?", new String[] { name });
        while (c.moveToNext()) {
            lxr.setName(c.getString(0));
            lxr.setNumber(c.getString(1));
            lxr.setIntroduce(c.getString(2));
        }
        c.close();
        return lxr;
    }








}
