package com.example.admin.railwayticketreservationsystem.DataBase;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.admin.railwayticketreservationsystem.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class DBtestActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        // com.test.db 是程序的包名，请根据自己的程序调整
        // /data/data/com.test.db/
        // databases 目录是准备放 SQLite 数据库的地方，也是 Android 程序默认的数据库存储目录
        // 数据库名为 test.db
        String DB_PATH = "/data/data/com.example.railwayticketsreservationsystem/databases/";
        String DB_NAME = "test.db";

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

//        // 下面测试 /data/data/com.test.db/databases/ 下的数据库是否能正常工作
//        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
//        Cursor cursor = database.rawQuery("select * from test", null);
//
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            try {
//                // 解决中文乱码问题
//                byte test[] = cursor.getBlob(0);
//                String strtest = new String(test, "utf-8").trim();
//
//                // 看输出的信息是否正确
//                System.out.println(strtest);
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        cursor.close();
    }
}
