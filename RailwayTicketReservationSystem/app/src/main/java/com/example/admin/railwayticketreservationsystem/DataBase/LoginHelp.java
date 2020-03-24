package com.example.admin.railwayticketreservationsystem.DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginHelp {

    static String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
    static String DB_NAME = "RailwayReservation.db";


    //从数据库中获取手机号码与密码
    //如果找不到手机号，返回-2
    //如果找到手机号，则判断密码是否正确，不正确时返回-1
    //如果手机号和密码都正确，返回0
   public static int findfromSql(String phonenumber, String pwd, MySQLiteHelper sqLiteHelper){

       SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

       //默认该用户不存在
       int result=-2;

       //连接数据库
       Cursor cursor=db.rawQuery("select * from user_data   where phonenumber=?",new String[]{phonenumber});

       if(cursor.moveToFirst()==false){
           //游标为空，说明不存在该用户
           result=-2;
       }else{
           //String TruePwd="123456";
           int index_pwd=cursor.getColumnIndex("pwd");
           String TruePwd=cursor.getString(index_pwd);
           if(TruePwd.equals(pwd)){
               //密码正确

               //修改登录状态
               String sql_update="update user_data set State= 'login' where phonenumber=?";
               db.execSQL(sql_update,new String[]{  phonenumber });
               //取得用户ID
               String sql_selid="select id from user_data where phonenumber = ?";
               Cursor cursor1=db.rawQuery(sql_selid,new String[]{phonenumber});
               if(cursor1.moveToFirst()){
                   int index=cursor1.getColumnIndex("id");
                   //返回用户Id，后续操作使用
                   result=cursor1.getInt(index);
               }
               //如果返回失败,设为0
               result=0;


           }else{
               //密码错误
               result=-1;
           }
       }
       //关闭游标
       cursor.close();;
       //关闭数据库
       db.close();

       //返回结果
       return result;

    }

    //查找数据库中是否存在相同的手机号
    public static boolean phoneIsRegistered(String phonenumber,MySQLiteHelper sqLiteHelper){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

        //创建游标
        Cursor cursor=db.rawQuery("select * from user_data where phonenumber = ?",new String[]{phonenumber});

        //游标列表为空，代表该电话未被注册，说明该电话可以使用
        if(cursor.moveToFirst()==false){
            //关闭游标
            cursor.close();
            return false;
        }
        //代表该电话已经被注册
        //关闭游标
        cursor.close();
        return true;
    }


    //使用正则表达式校验手机号
     public static boolean isMobileNO(  String mobiles) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
            Matcher m = regex.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;

    }

    //使用正则表达式校验邮箱
    public static boolean checkEmail(  String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean emailaddressisUsed(String email,MySQLiteHelper sqLiteHelper){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

        //创建游标
        Cursor cursor=db.rawQuery("select * from user_data where emailaddress = ?",new String[]{email});

        //游标列表为空，代表该邮箱未被注册，说明该邮箱可以使用
        if(cursor.moveToFirst()==false){
            //关闭游标
            cursor.close();
            return false;
        }
        //代表该邮箱已经被注册
        //关闭游标
        cursor.close();
        return true;
    }




    //判断身份证是否已被注册
    public static  boolean IDCardisUserd(String idCard,MySQLiteHelper sqLiteHelper){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
         //创建游标
         Cursor cursor=db.rawQuery("select * from user_data where idCard = ?",new String[]{idCard});

         //游标列表为空，代表该身份证未被注册，说明该身份证可以使用
         if(cursor.moveToFirst()==false){
             //关闭游标
             cursor.close();
             return false;
         }
         //代表该身份证已经被注册
         //关闭游标
         cursor.close();
         return true;
    }





    //使用正则表达式校验身份证
    public static boolean isIDCard(String idCard) {
        String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    //创建用户
    public static boolean createuser(String[] user_datas, MySQLiteHelper sqLiteHelper){
       boolean flag=false;

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

       Cursor cursor=db.rawQuery("select * from user_data",null);

       String id=String.valueOf(cursor.getCount()+1);
        String sql_insert_data="insert into user_data(id,pwd,realname,idCard,   " +
                "phonenumber,sex,emailaddress,State) values(?,?,?,?,    ?,?,?,?)";
        db.execSQL(sql_insert_data,new String[]{id,user_datas[1],user_datas[2],user_datas[3],user_datas[0],
        user_datas[4],user_datas[5],"logout"});



        cursor=db.rawQuery("select * from user_data where phonenumber = ?",new String[]{user_datas[0]});
        if(cursor.moveToFirst())flag=true;
        //关闭游标
        cursor.close();
        //关闭数据库
        db.close();

        return flag;

    }







}
