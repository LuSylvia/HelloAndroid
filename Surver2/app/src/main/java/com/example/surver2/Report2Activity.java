package com.example.surver2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class Report2Activity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    LinearLayout linear_report2;
    Button btn_saveResult;
    Button btn_upload;

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    int survey_id=0;//survey_id
    String gpslocation=null;//位置
    String time=null;//时间
    String emei=null;//EMEI
    JSONObject jsonObject=null;

    //GPS
    LocationManager locationManager;
    LocationListener locationListener;
    OnNmeaMessageListener nmeaListener;
    GpsStatus.NmeaListener nmeaListenerOld;
    private static final int REQUEST_CODE_PERMISSION = 1;

    //创建数据库操作类
    DatabaseOperator db;


    /**
     * Android 10及以上申请权限
     */
    private String[] permissionsQ = new String[]{
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            // 编译版本小于29，不能使用Manifest.permission.ACCESS_BACKGROUND_LOCATION
            "android.permission.ACCESS_BACKGROUND_LOCATION",
    };

    /**
     * Android10以下申请权限
     */
    private String[] permissionsO = new String[]{
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        linear_report2=findViewById(R.id.linear_report2);
        btn_saveResult =findViewById(R.id.btn_saveResult2);
        btn_upload=findViewById(R.id.btn_upload);

        //得到result
        ArrayList<String> answerlist= getIntent().getStringArrayListExtra("result");
        //得到survey_id
        final String survey_id=getIntent().getStringExtra("survey_id");
        //将result存放进answers数组
        final String answers[]=new String[answerlist.size()];
        for(int i=0;i<answerlist.size();i++){
            answers[i]=answerlist.get(i);
        }

//        try {
//            for(int i=0;i<answers.length;i++){
//                //得到json对象
//                jsonObject.put("Answer"+(i+1),answers[i]);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }

        //初始化数据库操作类
        db=new DatabaseOperator(this);


        //构造页面布局
        linear_report2.removeAllViewsInLayout();

        assert answers!=null;
        for(int i=0;i<answers.length;i++){
            TextView textView=new TextView(this);
            linear_report2.addView(textView);
            textView.setText(answers[i]);
            textView.setTextSize(20);
            textView.setTextColor(getResources().getColor(R.color.colorBlack));
            textView.setPadding(30,10,30,10);
        }



        //申请存储权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }


        btn_saveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储到JSON文件和数据库
                save2SDCardRoot(answers);
                save2Database(answers,survey_id);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });


    }




    //定位用
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        requestLocation();
    }

    //获取定位信息
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void requestLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                    gpslocation = "纬度为：" + df.format(location.getLatitude()) + ",经度为：" + df.format(location.getLongitude());
                    Log.i("logTag", "string" + gpslocation);

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        // 注册位置服务，获取系统位置
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //10s变更一次地理位置
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        // 监听NMEA
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (nmeaListener == null) {
                nmeaListener = new OnNmeaMessageListener() {
                    @Override
                    public void onNmeaMessage(String nmea, long l) {
                        receivedNmea(nmea);
                    }
                };
            }
            locationManager.addNmeaListener(nmeaListener);
        }
//         else {
//            if (nmeaListenerOld == null) {
//                nmeaListenerOld = new GpsStatus.NmeaListener() {
//                    @Override
//                    public void onNmeaReceived(long l, String nmea) {
//                        receivedNmea(nmea);
//                    }
//                };
//            }
//            locationManager.addNmeaListener(nmeaListenerOld);
//        }
    }

    private void receivedNmea(String nmea) {
        //tv_main.setText(nmea);

    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "没有获取到权限，软件将退出！", Toast.LENGTH_LONG).show();
        finish();
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //GPS权限
        if (EasyPermissions.hasPermissions(this, permissions)) {
            onPermissionsGranted(REQUEST_CODE_PERMISSION, null);
        } else {
            onPermissionsDenied(requestCode, null);
        }


        //存储权限
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("权限", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }



    //保存到sdcard根目录
    private void save2SDCardRoot(String msg[]){
        JSONObject json=new JSONObject();
        try {
            for(int i=0;i<msg.length;i++){
                json.put("Answer"+(i+1),msg[i]);
            }
            //将json字符串写入到json文件中
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File sdFile= Environment.getExternalStorageDirectory();
                File saveData=new File(sdFile,"result.json");

                Log.e("location","Location2:"+saveData);
                //在json文件中追加内容
                BufferedWriter out = null;
                try {

                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveData, true)));
                    out.newLine();//换行
                    out.write(    json.toString()   );


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }else{
                Toast.makeText(Report2Activity.this,"Save failed!",Toast.LENGTH_SHORT).show();
            }
            System.out.println(json.toString());



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //保存到数据库
    private void save2Database(String msg[],String survey_id){
        //获取系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        time=(simpleDateFormat.format(date));

        assert survey_id!=null;
        System.out.println(survey_id);

            //survey_id=" '4545'  ";

//        if(db.isSurveyIdAlreadyExisted(survey_id)){
//            //survey_id已经存在
//            Toast.makeText(this,"The survey_id is existed!",Toast.LENGTH_SHORT).show();
//            return;
//        }else {
//
//        }

        db.add(survey_id,msg.toString(),gpslocation,time,null);
        Toast.makeText(this,"Save to database succeed!",Toast.LENGTH_SHORT).show();


    }

    //上传数据
    private void upload(){

    }





}
