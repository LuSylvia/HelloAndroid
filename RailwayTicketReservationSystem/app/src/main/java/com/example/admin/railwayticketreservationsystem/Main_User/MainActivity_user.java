package com.example.admin.railwayticketreservationsystem.Main_User;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.admin.railwayticketreservationsystem.DataBase.UserinfoHelp;
import com.example.admin.railwayticketreservationsystem.R;

public class MainActivity_user extends AppCompatActivity implements View.OnClickListener,BottomNavigationBar.OnTabSelectedListener,

        homeFragment.CallBackValues, SelStopFragment.CallBackValues_2 {


    private BottomNavigationBar mbottomNavigationBar;
    private homeFragment fragement_home;
    private reservationFragment fragment_reservation;
    private userFragment fragment_user;
    int lastSelectedPosition = 0;

    private static boolean isExit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        FindViewbyid();

        //btn_switch.setOnClickListener(MainActivity_user.this);
        //btn_search.setOnClickListener(MainActivity_user.this);
        //btn_calender.setOnClickListener(MainActivity_user.this);


        mbottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp,"主页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp,"订单"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user1,"我的"))
                .setFirstSelectedPosition(lastSelectedPosition).
                initialise();




        mbottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorBlue)
                .setInActiveColor(R.color.colorPrimary)
                .setBarBackgroundColor(R.color.colorMistyRose);

        setDefaultFragment();

    }

    //设置默认导航栏
    private void setDefaultFragment(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        fragement_home=homeFragment.newInstance("首页");
        transaction.replace(R.id.tb,fragement_home);
        transaction.commit();
    }


    @Override
      protected void onResume() {
        int id = getIntent().getIntExtra("id", 0);
        if (id == 2) {
            Fragment fragmen = new homeFragment();
            FragmentManager fmanger = getSupportFragmentManager();
            FragmentTransaction transaction = fmanger.beginTransaction();
            transaction.replace(R.id.rootview, fragmen);
            transaction.commit();

            //帮助跳转到指定子fragment
            Intent i=new Intent();
            i.setClass(MainActivity_user.this,homeFragment.class);
            i.putExtra("id",2);
        }

        super.onResume();
    }



    //主线程处理视图，isExit默认为false，就是点击第一次时，弹出"再按一次退出程序"
    //点击第二次时关闭应用
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击两次退出程序
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            UserinfoHelp.logout();
            finish();
            //参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
            System.exit(0);
        }
    }








    void FindViewbyid(){
        //绑定控件

        mbottomNavigationBar=findViewById(R.id.navigation_bottom_bar);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(int position) {
        //Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
       FragmentManager fm=getSupportFragmentManager();

       //开启事务
       FragmentTransaction transaction=fm.beginTransaction();
        switch (position){
            case 0:
                if(fragement_home==null){
                    fragement_home=homeFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb,fragement_home);
                break;
            case 1:
                if(fragment_reservation==null){
                    fragment_reservation=reservationFragment.newInstance("订单");
                }
                transaction.replace(R.id.tb,fragment_reservation);
                break;
            case 2:
                if(fragment_user==null){
                    fragment_user=userFragment.newInstance("用户");
                }
                transaction.replace(R.id.tb,fragment_user);
                break;
            default:
                break;

        }
        //事务提交
        transaction.commit();


    }

    //设置未选择Fragment事务
    @Override
    public void onTabUnselected(int position) {

    }

    //设置释放Fragment事务
    @Override
    public void onTabReselected(int position) {

    }


    //回调函数，用于回传参数
    @Override
    public void sendMessages(String location, String stop, String anotherStop,boolean home_to_SelStop) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();


            //home向selstop发送数据
            SelStopFragment selStopFragment = new SelStopFragment();
            Bundle bundle = new Bundle();
            bundle.putString("location", location);
            bundle.putString("stop", stop);
            bundle.putString("anotherstop", anotherStop);

            //Log.e("Laowangtest", "Main,location is " + location + ",stop is " + stop + ",another is " + anotherStop + ",hometo selstop is " + home_to_SelStop);
            selStopFragment.setArguments(bundle);
            //将sel 替换布局
            transaction.replace(R.id.tb, selStopFragment);
            transaction.commit();//提交事务






    }

    @Override
    public void sendMessages2(String location, String stop, String anotherStop, boolean home_to_SelStop) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        homeFragment homefr=new homeFragment();

        Bundle bundle=new Bundle();
        bundle.putString("location",location);
        bundle.putString("stop",stop);
        bundle.putString("anotherstop",anotherStop);

        //Log.e("Laowangtest","Main2,   location is "+location+",stop is "+stop+",another is "+anotherStop+",home to sel is "+home_to_SelStop);

        homefr.setArguments(bundle);
        //将homefr替换布局
        transaction.replace(R.id.tb,homefr);
        transaction.commit();//提交事务
    }
}
