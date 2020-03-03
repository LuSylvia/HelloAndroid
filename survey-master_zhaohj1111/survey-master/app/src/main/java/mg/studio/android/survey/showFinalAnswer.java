package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class showFinalAnswer extends AppCompatActivity {
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_answer);
        /*
        Question6=findViewById(R.id.Q6_GetBack);
        Intent intent = getIntent();
        String Q6=intent.getStringExtra("Q6");
        Question6.setText("Q6:"+Q6);
        */
        TextView Question1 = findViewById(R.id.Q1_GetBack);
        TextView Question2 = findViewById(R.id.Q2_GetBack);
        TextView Question3 = findViewById(R.id.Q3_GetBack);
        TextView Question4 = findViewById(R.id.Q4_GetBack);
        TextView Question5 = findViewById(R.id.Q5_GetBack);
        TextView Question6 = findViewById(R.id.Q6_GetBack);
        Intent intent = getIntent();
        q1 = intent.getStringExtra("Q1");
        q2 = intent.getStringExtra("Q2");
        q3 = intent.getStringExtra("Q3");
        q4 = intent.getStringExtra("Q4");
        q5 = intent.getStringExtra("Q5");
        q6 = intent.getStringExtra("Q6");
        Question1.setText("Q1:" + q1);
        Question2.setText("Q2:" + q2);
        Question3.setText("Q3:" + q3);
        Question4.setText("Q4:" + q4);
        Question5.setText("Q5:" + q5);
        Question6.setText("Q6:" + q6);

        Button store = findViewById(R.id.storeToFile);
        //checkPermission();

        //动态申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }


        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text1 = q1;
                String text2 = q2;
                String text3 = q3;
                String text4 = q4;
                String text5 = q5;
                String text6 = q6;

                if ((text1.length() == 0) && (text2.length() == 0) && (text3.length() == 0) && (text4.length() == 0) && (text5.length() == 0) && (text6.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "Please for sure you are choose questuons Q1-Q6", Toast.LENGTH_LONG).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Q1", text1);
                        jsonObject.put("Q2", text2);
                        jsonObject.put("Q3", text3);
                        jsonObject.put("Q4", text4);
                        jsonObject.put("Q5", text5);
                        jsonObject.put("Q6", text6);

//                        String json = jsonObject.toString();
//                        save2Internal("file1.json", json);
//                        save2External("file2.json", json);
//                        saveToFile(json);
                        newSave2Internal(jsonObject);
                        newSave2External(jsonObject);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //saveToFile(text);

                }
            }
        });


    }


    private void newSave2Internal(JSONObject json){
        File sdFile= getDir("test",MODE_PRIVATE);
        File saveData=new File(sdFile,"file1.json");

        Log.e("location","Location2:"+saveData);

        BufferedWriter out = null;
        try {

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveData, true)));
            out.newLine();//换行

            out.write(json.toString());

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
    }


    private void newSave2External(JSONObject json){
        //将json字符串写入到json文件中
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdFile= Environment.getExternalStorageDirectory();
            File saveData=new File(sdFile,"file2.json");

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
            Toast.makeText(showFinalAnswer.this,"Save failed!",Toast.LENGTH_SHORT).show();
        }
    }


    private void save2Internal(String fileName, String fileContents) {
        try (FileOutputStream fos = getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND)) {
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Success: save to internal done.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: cannot save to internal", Toast.LENGTH_SHORT).show();
        }
    }

    private void save2External(String fileName, String fileContents) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(this.getExternalCacheDir(), fileName);
            try (FileOutputStream fos = new FileOutputStream(file, true)) {
                fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
                Toast.makeText(this, "Success: save to external done.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: cannot save to external.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: no external storage!", Toast.LENGTH_SHORT).show();
        }
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上

            int permission = ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.RECEIVE_SMS);
            int permission1 = ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED && permission1 != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(showFinalAnswer.this, "get premission.", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.RECEIVE_SMS) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    Toast.makeText(showFinalAnswer.this, "attention：", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Toast.makeText(showFinalAnswer.this, "no permission", Toast.LENGTH_SHORT).show();
                    Toast.makeText(showFinalAnswer.this, "no premission，please go to 'setting' to open you store permission", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(showFinalAnswer.this, "you get the permission", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(showFinalAnswer.this, "you get the permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(showFinalAnswer.this, "you get the permission", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(showFinalAnswer.this, "you don`t get the permission", Toast.LENGTH_SHORT).show();
//                Toast.makeText(showFinalAnswer.this, "you don`t get the permission，please go to 'setting' to open you store permission", Toast.LENGTH_SHORT).show();
//            }
//        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }


    //
    public void saveToFile(String content) {
        BufferedWriter out = null;

        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "check sdcard", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = Environment.getExternalStorageDirectory();
        try {
            Toast.makeText(this, "======SDcard address：" + file.getCanonicalPath(), Toast.LENGTH_SHORT).show();
            if (file.exists()) {
                Toast.makeText(this, "file.getCanonicalPath() == " + file.getCanonicalPath(), Toast.LENGTH_SHORT).show();
            }


            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getCanonicalPath() + "/test.json", true)));
            out.newLine();
            out.write(content);
            Toast.makeText(this, "store successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
