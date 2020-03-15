package com.example.surver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
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
import java.util.ArrayList;

public class Report2Activity extends AppCompatActivity {
    LinearLayout linear_report2;
    Button btn_saveResult;
    Button btn_upload;

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        linear_report2=findViewById(R.id.linear_report2);
        btn_saveResult =findViewById(R.id.btn_saveResult2);

        ArrayList<String> answerlist= getIntent().getStringArrayListExtra("result");
        final String answers[]=new String[answerlist.size()];
        for(int i=0;i<answerlist.size();i++){
            answers[i]=answerlist.get(i);
        }

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

        //动态申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }


        btn_saveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储到JSON文件
                save2SDCardRoot(answers);
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
}
