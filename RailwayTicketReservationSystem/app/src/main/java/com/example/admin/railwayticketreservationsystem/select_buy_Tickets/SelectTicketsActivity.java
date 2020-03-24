package com.example.admin.railwayticketreservationsystem.select_buy_Tickets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.DataBase.MySQLiteHelper;
import com.example.admin.railwayticketreservationsystem.DataBase.TicketHelp;
import com.example.admin.railwayticketreservationsystem.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectTicketsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Map<String,Object>> mDatas;
    private SelectTicketsActivityAdapter mAdapter;

    String startcity,arrivecity,date;
    String train_id;
    final String ACTIVITY_TAG="MyAndroid";

    static String DB_PATH = "/data/data/com.example.admin.railwayticketreservationsystem/databases/";
    static String DB_NAME = "RailwayReservation.db";


    String starttime;
    String startStop;
    String arrivetime;
    String arriveStop;
    String train_name;
    String firstClass;
    String secondClass;
    String totaltime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tickets);

        Intent i=getIntent();
        //初始化
        startcity=i.getStringExtra("startCity");
        arrivecity=i.getStringExtra("arriveCity");
        date=i.getStringExtra("date");
        initData(startcity,arrivecity,date);




       mRecyclerView=findViewById(R.id.recyclerview);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        mRecyclerView.setAdapter(mAdapter=new SelectTicketsActivityAdapter());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
               this, DividerItemDecoration.VERTICAL));


    }


    //注意，这个版本的countTime不支持到达时间已经是第2天的情况，需要额外的参数才行
    public static String countTime(String time1,String time2){
        char arr1[]=time1.toCharArray();
        char arr2[]=time2.toCharArray();


        int timecount1=(arr1[0]*10+arr1[1])*60+(arr1[3]*10+arr1[4]);
        int timecount2=(arr2[0]*10+arr2[1])*60+(arr2[3]*10+arr2[4]);



        //时间
        int hour=(timecount2-timecount1)/60;
        //分钟
        int min=(timecount2-timecount1-60*hour);

        return "\n"+hour+"小时"+min+"分";
    }


    //从数据库中读取数据，并填充到每一个item中
    protected void initData(String startCity,String arriveCity,String start_date){
        mDatas = new ArrayList<Map<String, Object>>();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);

        //取得游标
        Cursor cursor_train=null,cursor_time=null;
        int index=0;
        cursor_train=TicketHelp.findTrain(startCity,arriveCity,db,cursor_train);
        //注意，这个游标仅仅用来判断到底应该乘坐哪一部火车，但具体有没有票则不负责
        while(cursor_train.moveToNext()){
            Map<String,Object> map=new HashMap<String,Object>();
            //取得火车id
            index=cursor_train.getColumnIndex("train_id");
            train_id=String.valueOf(cursor_train.getInt(index));

            //存储出发站点名字
            index=cursor_train.getColumnIndex("startStop_name");
            map.put("start",cursor_train.getString(index));

            //存储目标站点名字
            index=cursor_train.getColumnIndex("arriveStop_name");
            map.put("arrive",cursor_train.getString(index));

            //存储列车名
            index=cursor_train.getColumnIndex("train_name");
            map.put("trainName",cursor_train.getString(index));


            //存储出发时间
            index=cursor_train.getColumnIndex("start_time");
           map.put("startTime",cursor_train.getString(index));
           //存储到达时间
            index=cursor_train.getColumnIndex("arrive_time");
            map.put("arriveTime",cursor_train.getString(index));

            //存储总路程，用于计算票价
            index=cursor_train.getColumnIndex("total_KM");
            map.put("totalKM",cursor_train.getInt(index));

            //临时存储列车号
            String the_train_id;
            index=cursor_train.getColumnIndex("train_id");
            the_train_id=String.valueOf(cursor_train.getInt(index));

            Log.e("Laowangtest","train id is  "+the_train_id);

            //打开新的游标，读取票数
            cursor_time=TicketHelp.findTicketCounts(the_train_id,start_date,db,cursor_time);
            if(cursor_time.moveToFirst()){
                //一等座
                index=cursor_time.getColumnIndex("firstClass");
                map.put("一等座",cursor_time.getInt(index));
                //二等座
                index=cursor_time.getColumnIndex("secondClass");
                map.put("二等座",cursor_time.getInt(index));
            }



            //加入List
            mDatas.add(map);

        }
        if(!cursor_train.moveToFirst()){
            Log.e("Laowangtest","sql is null   !!!");
        }

        //关闭游标
        if(cursor_time!=null){
            cursor_time.close();
        }
       if(cursor_train!=null){
           cursor_train.close();
       }


        //全部读取完成，关闭数据库
        db.close();

    }



    class SelectTicketsActivityAdapter extends RecyclerView.Adapter<SelectTicketsActivityAdapter.MyViewHolder>{



        @Override
        public MyViewHolder onCreateViewHolder( ViewGroup parent, int i) {
            MyViewHolder holder = new MyViewHolder(  LayoutInflater.from(SelectTicketsActivity.this).inflate(R.layout.item, parent,false ) );
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
            //取出
              startStop= (String) mDatas.get(position).get("start");
              arriveStop=(String )mDatas.get(position).get("arrive");

              starttime=(String)mDatas.get(position).get("startTime");
              arrivetime=(String)mDatas.get(position).get("arriveTime");

              train_name=(String )mDatas.get(position).get("trainName");

              firstClass=String.valueOf(mDatas.get(position).get("一等座"));
              secondClass=String.valueOf(mDatas.get(position).get("二等座"));


              totaltime=countTime(starttime,arrivetime);

            //填充数据
            myViewHolder.tv_startTime.setText(starttime);
            myViewHolder.tv_startCity.setText(startStop);
            myViewHolder.tv_arriveTime.setText(arrivetime);
            myViewHolder.tv_arriveCity.setText(arrivecity);
            myViewHolder.tv_firstClassSeat.setText(firstClass);
            myViewHolder.tv_secondClassSeat.setText(secondClass);
            myViewHolder.tv_trainname.setText(train_name);

            myViewHolder.tv_totalTime.setText(totaltime);




            String first="一等座:"+mDatas.get(position).get("一等座")+"张";
            String second="二等座:"+mDatas.get(position).get("二等座")+"张";

            myViewHolder.tv_firstClassSeat.setText(first);
            myViewHolder.tv_secondClassSeat.setText(second);


            myViewHolder.rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //如果没有票，不会跳转到购买界面
                    if(     (int)(  mDatas.get(position).get("一等座")  )==0&&(int)(    mDatas.get(position).get("二等座")  )==0){
                        Toast.makeText(v.getContext(),"该日车票已经卖完！",Toast.LENGTH_LONG).show();
                        return;
                    }


                    //如果有票,取出路程,并计算票价
                    int KM=(int)mDatas.get(position).get("totalKM");
                    Log.e(ACTIVITY_TAG,"KM = "+KM);

                    //计算



                    double firstprice = new BigDecimal(KM*0.63).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    double secondprice = new BigDecimal(KM*0.43).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();

                    //将数据填充到datas数组中，用于界面传值
                    String datas[]=new String[]{ starttime,startStop,arrivetime,arriveStop, train_name,
                            firstClass,String.valueOf(firstprice),secondClass,String.valueOf(secondprice),totaltime,
                            date,train_id };

                    //跳转到购票界面
                    Intent intent=new Intent(SelectTicketsActivity.this,BuyTicketActivity.class);
                    intent.putExtra("datas",datas);
                    startActivity(intent);



                }
            });



        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }




        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_trainname;
            TextView tv_startCity;
            TextView tv_arriveCity;
            TextView tv_startTime;
            TextView tv_arriveTime;
            TextView tv_firstClassSeat;
            TextView tv_secondClassSeat;
            TextView tv_totalTime;
            LinearLayout rootview;


            //绑定控件
            void FindViewByID(View v){
                tv_trainname=v.findViewById(R.id.tv_trainName);
                tv_startCity=v.findViewById(R.id.tv_startCity);
                tv_arriveCity=v.findViewById(R.id.tv_arriveCity);

                tv_startTime=v.findViewById(R.id.tv_startTime);
                tv_arriveTime=v.findViewById(R.id.tv_arrivetime);
                tv_firstClassSeat=v.findViewById(R.id.tv_firstClassSeat);
                tv_secondClassSeat=v.findViewById(R.id.tv_secondClassSeat);
                tv_totalTime=v.findViewById(R.id.tv_totaltime_sel);

                rootview=v.findViewById(R.id.rootview);
            }



            public MyViewHolder( View v) {
                super(v);
                FindViewByID(v);
            }
        }





    }








}
