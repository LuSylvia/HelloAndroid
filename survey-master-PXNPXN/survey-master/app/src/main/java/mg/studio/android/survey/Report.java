package mg.studio.android.survey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Report extends AppCompatActivity {
    private TextView mTextView;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private RadioButton mRadioButton6;
    private RadioButton mRadioButton7;
    private RadioButton mRadioButton8;
    private RadioButton mRadioButton9;
    private RadioButton mRadioButton10;
    private RadioButton mRadioButton11;
    private RadioButton mRadioButton12;
    private Bundle report;

    private Button btn_save;
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;



    Report reportActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        //动态申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }



        GetData();
        init();
        SetData();

        btn_save=findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputArr []=new String[12];
                inputArr=getInputArr();
                //保存到sd卡根目录
                save2JSON(inputArr);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }



    public void GetData() {
        Intent i = getIntent();
        report = i.getExtras();
    }



    public void init() {
        mRadioButton1 = (RadioButton) findViewById(R.id.rb_report_one);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb_report_two);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb_report_three);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb_report_four);
        mRadioButton5 = (RadioButton) findViewById(R.id.rb_report_five);
        mRadioButton6 = (RadioButton) findViewById(R.id.rb_report_six);
        mRadioButton7 = (RadioButton) findViewById(R.id.rb_report_seven);
        mRadioButton8 = (RadioButton) findViewById(R.id.rb_report_eight);
        mRadioButton9 = (RadioButton) findViewById(R.id.rb_report_nine);
        mRadioButton10 = (RadioButton) findViewById(R.id.rb_report_ten);
        mRadioButton11 = (RadioButton) findViewById(R.id.rb_report_eleven);
        mRadioButton12 = (RadioButton) findViewById(R.id.rb_report_twelve);
    }


    private String[] getInputArr(){
        String[] inputArr = new String[12];
        mRadioButton1.setText(report.getBundle("Question1").getString("one"));
        inputArr[0] = report.getBundle("Question1").getString("one");
        mRadioButton2.setText(report.getBundle("Question2").getString("two"));
        inputArr[1] = report.getBundle("Question2").getString("two");
        mRadioButton3.setText(report.getBundle("Question3").getString("three"));
        inputArr[2] = report.getBundle("Question3").getString("three");
        mRadioButton4.setText(report.getBundle("Question4").getString("four"));
        inputArr[3] = report.getBundle("Question4").getString("four");
        mRadioButton5.setText(report.getBundle("Question5").getString("five"));
        inputArr[4] = report.getBundle("Question5").getString("five");
        mRadioButton6.setText(report.getBundle("Question6").getString("six"));
        inputArr[5] = report.getBundle("Question6").getString("six");
        mRadioButton7.setText(report.getBundle("Question7").getString("seven"));
        inputArr[6] = report.getBundle("Question7").getString("seven");
        mRadioButton8.setText(report.getBundle("Question8").getString("eight"));
        inputArr[7] = report.getBundle("Question8").getString("eight");
        mRadioButton9.setText(report.getBundle("Question9").getString("nine"));
        inputArr[8] = report.getBundle("Question9").getString("nine");
        mRadioButton10.setText(report.getBundle("Question10").getString("ten"));
        inputArr[9] = report.getBundle("Question10").getString("ten");
        mRadioButton11.setText(report.getBundle("Question11").getString("eleven"));
        inputArr[10] = report.getBundle("Question11").getString("eleven");
        mRadioButton12.setText(report.getBundle("Question12").getString("twelve"));
        inputArr[11] = report.getBundle("Question12").getString("twelve");
        return  inputArr;

    }

    public void SetData() {
        String[] inputArr = new String[12];
//        mRadioButton1.setText(report.getBundle("Question1").getString("one"));
//        inputArr[0] = report.getBundle("Question1").getString("one");
//        mRadioButton2.setText(report.getBundle("Question2").getString("two"));
//        inputArr[1] = report.getBundle("Question2").getString("two");
//        mRadioButton3.setText(report.getBundle("Question3").getString("three"));
//        inputArr[2] = report.getBundle("Question3").getString("three");
//        mRadioButton4.setText(report.getBundle("Question4").getString("four"));
//        inputArr[3] = report.getBundle("Question4").getString("four");
//        mRadioButton5.setText(report.getBundle("Question5").getString("five"));
//        inputArr[4] = report.getBundle("Question5").getString("five");
//        mRadioButton6.setText(report.getBundle("Question6").getString("six"));
//        inputArr[5] = report.getBundle("Question6").getString("six");
//        mRadioButton7.setText(report.getBundle("Question7").getString("seven"));
//        inputArr[6] = report.getBundle("Question7").getString("seven");
//        mRadioButton8.setText(report.getBundle("Question8").getString("eight"));
//        inputArr[7] = report.getBundle("Question8").getString("eight");
//        mRadioButton9.setText(report.getBundle("Question9").getString("nine"));
//        inputArr[8] = report.getBundle("Question9").getString("nine");
//        mRadioButton10.setText(report.getBundle("Question10").getString("ten"));
//        inputArr[9] = report.getBundle("Question10").getString("ten");
//        mRadioButton11.setText(report.getBundle("Question11").getString("eleven"));
//        inputArr[10] = report.getBundle("Question11").getString("eleven");
//        mRadioButton12.setText(report.getBundle("Question12").getString("twelve"));
//        inputArr[11] = report.getBundle("Question12").getString("twelve");
        inputArr=getInputArr();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String filename = year + "-" + month + "-" + day + "-" + hour + ":" + minute + ":" + second + ".json";
            /***********************External storage********************/
            //File sdfile = getExternalFilesDir(null);
            File sdfile;
            sdfile=getDir("test",MODE_PRIVATE);


            File savedata = new File(sdfile, filename);
            FileOutputStream fout = null;
            /*********************************************************/
            Log.e("location", "Location1: " + sdfile);
            Log.e("location", "Location1.5: " + savedata);

            /**********************Internal storage********************/
            FileOutputStream outputStream;
            /**********************************************************/
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                fout = new FileOutputStream(savedata);
                for (int i = 0; i < 12; i++) {
                    int temp = i + 1;
                    String input = "{Question:" + temp + ",Answer:'" + inputArr[i] + "'}\n";
                    fout.write(input.getBytes());
                    outputStream.write(input.getBytes());
                }
                fout.flush();
                fout.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }








        }
    }


    //保存到JSON文件
    private void save2JSON(String msg[]){
        JSONObject json=new JSONObject();

        try {
            for(int i=0;i<msg.length;i++){
                json.put("Answer"+(i+1),msg[i]);
            }
            //将json字符串写入到json文件中
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String filename = year + "-" + month + "-" + day + "-" + hour + ":" + minute + ":" + second + ".json";

                File sdFile= Environment.getExternalStorageDirectory();
                File saveData=new File(sdFile,filename);

                Log.e("location","location2:"+saveData);
                //在json文件中追加内容
                BufferedWriter out = null;
                FileOutputStream outputStream;
                FileOutputStream fout = null;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    fout = new FileOutputStream(saveData);
                    for (int i = 0; i < 12; i++) {
                        int temp = i + 1;
                        String input = "{Question:" + temp + ",Answer:'" + msg[i] + "'}\n";
                        fout.write(input.getBytes());
                        outputStream.write(input.getBytes());
                    }
                    fout.flush();
                    fout.close();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(Report.this,"Save failed!",Toast.LENGTH_SHORT).show();
            }
            System.out.println(json.toString());



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
