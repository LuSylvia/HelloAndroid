package com.example.admin.railwayticketreservationsystem.Main_User;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.railwayticketreservationsystem.R;
import com.example.admin.railwayticketreservationsystem.select_buy_Tickets.SelectTicketsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link homeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button ed_src;
    private Button ed_dest;

    private Button btn_switch;
    private Button btn_calender;
    private Button btn_search;
    private TextView tv_week;

    String src;
    String dest;

    Bundle bundle;
    CallBackValues callBackValues;

    //假如用户没有选择日期，设定默认日期
    String date="2019-06-17";


    private OnFragmentInteractionListener mListener;

    //回调接口
    public interface  CallBackValues{
        public void sendMessages(String location,String stop,String anotherStop,boolean home_to_SelStop);
    }




    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString("args1", param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            bundle=getArguments();
        }




//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home,container,false);



        findViewByid(view);
        //设置点击事件
        btn_switch.setOnClickListener(this);
        btn_calender.setOnClickListener(this);
        btn_search.setOnClickListener(this);

        ed_src.setOnClickListener(this);
        ed_dest.setOnClickListener(this);


        //接受SelStopFragment传来的参数，赋值


            if(bundle!=null){
                if(bundle.getString("location")!=null){
                    //
                    if(bundle.getString("location").equals("startcity")){

                        Log.e("Laowangtest","Home   location is "+bundle.getString("location")+",stop is "+bundle.getString("stop")
                                +",another is "+bundle.getString("anotherstop"));

                        ed_src.setText(bundle.getString("stop"));
                        ed_dest.setText(bundle.getString("anotherstop"));
                    }else if(bundle.getString("location").equals("arrivecity")){
                        ed_dest.setText(bundle.getString("stop"));
                        ed_src.setText(bundle.getString("anotherstop"));
                    }
                }
            }



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//绑定控件
    void findViewByid(View view){
        ed_src=view.findViewById(R.id.btn_startCity);
        ed_dest=view.findViewById(R.id.btn_arriveCIty);

        tv_week=view.findViewById(R.id.tv_week);

        btn_switch=view.findViewById(R.id.btn_switch);
        btn_calender=view.findViewById(R.id.btn_calendar);
        btn_search=view.findViewById(R.id.btn_search);
    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        //得到activity中回调接口的实例化对象
        callBackValues =(CallBackValues) getActivity();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_startCity:
                //切换到选择车站界面
                String location="startcity";
                //存储起点站信息
                String stop=ed_src.getText().toString();
                //存储终点站信息
                String another=ed_dest.getText().toString();
                //Button对象的getText方法获取到的字符串默认是空字符串，即""
                if(stop.equals(""))stop=null;
                if(another.equals(""))another=null;
                callBackValues.sendMessages(location,stop,another,true);



                break;

            case R.id.btn_arriveCIty:
                String location2="arrivecity";
                //存储终点站信息
                String stop2=ed_dest.getText().toString();
                //存储起点站信息
                String another2=ed_src.getText().toString();
                if(stop2.equals(""))stop2=null;
                if(another2.equals(""))another2=null;
                callBackValues.sendMessages(location2,stop2,another2,true);



                break;


            case R.id.btn_switch:
                //Toast.makeText(MainActivity_user.this,"交换按钮已经被点击！",Toast.LENGTH_SHORT).show();
                //交换目的地与出发地
                 src=ed_src.getText().toString();
                 dest=ed_dest.getText().toString();

                ed_src.setText(dest);
                ed_dest.setText(src);

                break;
            case R.id.btn_calendar:


                DatePickerDialog dp=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        btn_calender.setText((datePicker.getMonth()+1)+"月"+datePicker.getDayOfMonth()+"日");

                        int y=datePicker.getYear();
                        int m=datePicker.getMonth()+1;
                        int d=datePicker.getDayOfMonth();
                         date=y+"-"+m+"-"+d;
                        tv_week.setText(dateToWeek(date));
                        //tv_week.setText(getDayofWeek(y+"-"+m+"-"+d));

                //设定默认时间为6月17日
                    }
                },2019,5,17);
                //tv_week.setText(getWeek());


                dp.show();

                break;

            case R.id.btn_search:
                src=ed_src.getText().toString();
                dest=ed_dest.getText().toString();
                Log.e("Laowangtest","user date = "+date);




                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                Date d=null;
                try {
                     d=f.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                

                //查询车票
                Intent intent1=new Intent( getContext(), SelectTicketsActivity.class);
                intent1.putExtra("startCity",src);
                intent1.putExtra("arriveCity",dest);
                intent1.putExtra("date",f.format(d));

                startActivity(intent1);
                break;

            default:

                break;

        }
    }

    //计算用户选中日期是星期几
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
