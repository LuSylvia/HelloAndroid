package com.example.admin.railwayticketreservationsystem.DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.admin.railwayticketreservationsystem.DataBase.LoginHelp.DB_NAME;
import static com.example.admin.railwayticketreservationsystem.DataBase.LoginHelp.DB_PATH;

public class TicketHelp {

    //根据出发城市和目标城市查询火车
    public static Cursor findTrain(String start_city, String arrive_city, SQLiteDatabase db,Cursor cursor){
        String sql_selectTrain=
                "select distinct(a.train_id) as train_id,train_name,a.stop_name as startStop_name  , a.start_time as start_time,b.stop_name as arriveStop_name, b.arrive_time as arrive_time, b.KM-a.KM as total_KM " +
                        "from train_main a ,train_main b,train_type where a.city_name=? and b.city_name=? and a.stop_id<b.stop_id  and a.train_id=b.train_id and a.train_id=train_type.train_id";
        cursor=db.rawQuery(sql_selectTrain,new String []{start_city,arrive_city});
        return  cursor;
    }

    //查询剩余车票数量
    public static Cursor findTicketCounts(String train_id,String start_date,SQLiteDatabase db,Cursor cursor){

        String sql_selectTicketCounts="select * from train_tickets where train_id=? and start_date=?";
          final String ACTIVITY_TAG="MyAndroid";
        Log.w(ACTIVITY_TAG,"train_id = "+train_id+",start_date= "+start_date);
        cursor=db.rawQuery(sql_selectTicketCounts,new String[]{train_id,start_date});

        return cursor;

    }

    //添加订单到orders表，同时tickets表票数-1
    public static void buyTicket(SQLiteDatabase db_orders,SQLiteDatabase db_tickets,String datas[],String dataExtras[]){
        String sql_selectOrders="select count(order_id) as totalorders from Orders";
        //打开游标
        Cursor cursor= db_orders.rawQuery(sql_selectOrders,null);
        int index=0;
        if(cursor.moveToFirst()){
            index=cursor.getColumnIndex("totalorders");
        }
         //获取订单ID
        int new_order_id=cursor.getInt(index)+1;


        Log.e("Laowangtest","order_id is "+new_order_id);

        //往订单表中插入数据
        String sql_insertOrders="insert into Orders(order_id,id,realname,idCard,phonenumber," +
                "train_name,seat_type,price,start_stop_name,start_time," +
                "end_stop_name,arrive_time,CreateDate,Date )" +
                "values(?,?,?,?,?,   ?,?,?,?,?,    ?,?,?,?) ";
        db_orders.execSQL(sql_insertOrders,new String[]{String.valueOf(new_order_id),     dataExtras[0],dataExtras[1],dataExtras[2],datas[4],
                dataExtras[4],dataExtras[5], datas[1],datas[0],datas[3],
                datas[2],dataExtras[3],datas[10]
        });

        //调试用
        Log.e("Laowangtest","datas[10]="+datas[10]);
        Log.e("Laowangtest","datas[11]="+datas[11]);


        //查找一等座车票，二等座车票和未售出的车票
        String sql_findtickets="select * from train_tickets where start_date=? and train_id = ?";
        Cursor cursor1=db_tickets.rawQuery(sql_findtickets,new String[]{ datas[10], datas[11] });
        if(cursor1.moveToFirst()){
            //获取一等座车票总数
             index=cursor1.getColumnIndex("firstClass");
            int tickets_first=cursor1.getInt(index);
            //获取二等座车票总数
            index=cursor1.getColumnIndex("secondClass");
            int tickets_second=cursor1.getInt(index);
            //获取未售出车票总数
            index=cursor1.getColumnIndex("notSelled");
            int tickets_notSelled=cursor1.getInt(index);


            //更新未售出的票的总数
            tickets_notSelled--;
            String sql_updatetickets;

            if(dataExtras[4].equals("一等座")){
                //更新一等座所剩座位
                tickets_first--;

                sql_updatetickets="update train_tickets set firstClass = ? and notSelled = ? where train_id = ? and start_date=?";
                db_tickets.execSQL(sql_updatetickets,new String[]{ String.valueOf(tickets_first),String.valueOf(tickets_notSelled),datas[11],datas[10]      });
            }else if(dataExtras[4].equals("二等座")){
                //更新二等座所剩座位
                tickets_second--;

                sql_updatetickets="update train_tickets set secondClass = ? and notSelled= ? where train_id =? and start_date = ?";
                db_tickets.execSQL(sql_updatetickets,new String[]{ String.valueOf(tickets_second),String.valueOf(tickets_second),datas[11],datas[10]    });

            }

        }

        //关闭游标
        cursor1.close();
        cursor.close();
        //关闭数据库
        db_orders.close();
        db_tickets.close();






    }

    //判断该用户购买的车票中是否已经使用过该身份证
    public static  boolean IDCardisUsed(String idCard){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        //创建游标
        Cursor cursor=db.rawQuery("select * from Orders where idCard = ?",new String[]{idCard});

        //游标列表为空，代表该身份证未被用来购买车票，说明该身份证可以使用
        if(cursor.moveToFirst()==false){
            //关闭游标
            cursor.close();
            return false;
        }
        //代表该身份证已经被使用
        //关闭游标
        cursor.close();
        return true;
    }










    //根据用户id查找订单
    public   static List<Map<String,Object>>   findOrders( List<Map<String,Object>>  list){
        String sql_selectOrders="select * " +
                "from Orders where id=(select id from user_data where State='login') ";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor=db.rawQuery(sql_selectOrders,null);


        int index;

            while (cursor.moveToNext()){
                Map<String,Object> map=new HashMap<String,Object>();

                index=cursor.getColumnIndex("realname");
                map.put("realname",cursor.getString(index));

                //Log.e("Laowangtest","realname is "+cursor.getString(index));

                index=cursor.getColumnIndex("idCard");
                map.put("idCard",cursor.getString(index));

                index=cursor.getColumnIndex("train_name");
                map.put("train_name",cursor.getString(index));

                index=cursor.getColumnIndex("seat_type");
                map.put("seat_type",cursor.getString(index));

                index=cursor.getColumnIndex("price");
                map.put("price",String.valueOf(cursor.getInt(index))  );

                index=cursor.getColumnIndex("start_stop_name");
                map.put("start_stop_name",cursor.getString(index));

                index=cursor.getColumnIndex("start_time");
                map.put("start_time",cursor.getString(index));


                index=cursor.getColumnIndex("end_stop_name");
                map.put("end_stop_name",cursor.getString(index));

                index=cursor.getColumnIndex("arrive_time");
                map.put("arrive_time",cursor.getString(index));


                index=cursor.getColumnIndex("Date");
                map.put("Date",cursor.getString(index));

                list.add(map);
            }



        //关闭游标
        cursor.close();
        //关闭数据库
        db.close();


        return list;


    }



    //取消订单

    public static  boolean deleteOrders(String name,String idCard,String startstop,String arriveStop){
        boolean result=false;

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        String sql_findstart_stop="select * from Orders where realname=? and idCard=? and start_stop_name  =? and end_stop_name=?";
        Cursor cursor=db.rawQuery(sql_findstart_stop,new String[]{name,idCard,startstop,arriveStop});
        if(cursor.moveToFirst()){
            cursor.close();
            //说明存在该订单
            String sql_del="delete from Orders where idCard= ?";
            db.execSQL(sql_del,new String[]{idCard});

           Cursor cursor1=db.rawQuery("select * from Orders where idCard =?",new String[]{idCard});
            if(!cursor1.moveToFirst()){
                //删除成功
                result=true;
            }
            //关闭游标
            cursor1.close();

        }

        return result;

    }

}
