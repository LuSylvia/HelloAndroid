package com.example.admin.railwayticketreservationsystem.Main_User;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.railwayticketreservationsystem.DataBase.TicketHelp;
import com.example.admin.railwayticketreservationsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MyticketsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Map<String,Object>> mTDatas;
    private MyticketAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytickets);


        mRecyclerView =  findViewById(R.id.recyclerview3);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MyticketAdapter());
        mTDatas = new ArrayList<Map<String, Object>>();
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        //添加删除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        TicketHelp.findOrders(mTDatas);

    }




    //注意，这个版本的countTime不支持到达时间已经是第2天的情况，需要额外的参数才行
    public  String countTime(String time1,String time2){
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



    class MyticketAdapter extends RecyclerView.Adapter<MyticketAdapter.MyViewHolder>{









        @Override
        public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MyticketsActivity.this).inflate(R.layout.item_unpaid, viewGroup,
                    false));
            return holder;

        }

        @Override
        public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {
            //取得数据
            String realname= (String) mTDatas.get(position).get("realname");
            String idCard= (String) mTDatas.get(position).get("idCard");
            String seat_type= (String) mTDatas.get(position).get("seat_type");
            String price= (String) mTDatas.get(position).get("price");
            String start_stop= (String) mTDatas.get(position).get("start_stop_name");
            String start_time= (String) mTDatas.get(position).get("start_time");
            String arrive_stop= (String) mTDatas.get(position).get("end_stop_name");
            String arrive_time= (String) mTDatas.get(position).get("arrive_time");
            String godate= (String) mTDatas.get(position).get("Date");
            String train_name= (String) mTDatas.get(position).get("train_name");


            //计算总时长
            String totaltime=countTime(start_time,arrive_time);




            //对身份证进行加密
            char idCards[]=idCard.toCharArray();
            for(int i=10;i<=13;i++){
                idCards[i]='*';
            }
            idCard=String.valueOf(idCards);

            //填充数据
            myViewHolder.tv_ticket_name.setText(realname);
            myViewHolder.tv_ticket_idCard.setText(idCard);
            myViewHolder.tv_trainname.setText(train_name);
            myViewHolder.tv_ticket_seattype.setText(seat_type);
            myViewHolder.tv_price.setText("￥"+price+"元");
            myViewHolder.tv_startCity.setText(start_stop);
            myViewHolder.tv_startTime.setText(start_time);
            myViewHolder.tv_arriveCity.setText(arrive_stop);
            myViewHolder.tv_arriveTime.setText(arrive_time);
            myViewHolder.tv_godate.setText(godate);

            myViewHolder.tv_totalTime.setText(totaltime);







        }

        @Override
        public int getItemCount() {
            return mTDatas.size();
        }

        class  MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv_trainname;
            TextView tv_startCity;
            TextView tv_arriveCity;
            TextView tv_startTime;
            TextView tv_arriveTime;
            TextView tv_totalTime;
            TextView tv_ticket_seattype;
            TextView tv_ticket_idCard;
            TextView tv_ticket_name;
            TextView tv_godate;
            TextView tv_price;
            //绑定控件
            void FindViewByID(View v) {
                tv_trainname = v.findViewById(R.id.tv_ticket_trainName);

                tv_startCity = v.findViewById(R.id.tv_ticket_startCity);
                tv_arriveCity = v.findViewById(R.id.tv_ticket_arriveCity);

                tv_startTime = v.findViewById(R.id.tv_ticket_startTime);
                tv_arriveTime = v.findViewById(R.id.tv_ticket_arrivetime);
                tv_totalTime = v.findViewById(R.id.tv_ticket_totaltime);



                tv_ticket_seattype=v.findViewById(R.id.tv_ticket_seattype);
                tv_ticket_idCard=v.findViewById(R.id.tv_ticket_idCard);
                tv_ticket_name=v.findViewById(R.id.tv_ticket_name);
                tv_godate=v.findViewById(R.id.tv_ticket_godate);
                tv_price=v.findViewById(R.id.tv_ticket_price);

            }




            public MyViewHolder( View itemView) {
                super(itemView);
                FindViewByID(itemView);



            }
        }
    }






}
