package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class survey_report extends AppCompatActivity {

    Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_report);
        btn_save=findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save2SDCardRoot(getData());
            }
        });

    }

    private  String[] getData(){
        String ret[]=new String[12];
        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        ret[0] = "Q1: Buy new brand " + mySharedPreferences.getString("q_one", "");
        ret[1] = "Q2: Current phone cost " + mySharedPreferences.getString("q_two", "");
        ret[2] = "Q3: Phone cell network " + mySharedPreferences.getString("q_three", "");
        ret[3] = "Q4: Current function " + mySharedPreferences.getString("q_four", "");
        ret[4] = "Q5: Often function " + mySharedPreferences.getString("q_five", "");
        ret[5] = "Q6: Expected function " + mySharedPreferences.getString("q_six", "");
        ret[6] = "Q7: To buy new phone  " + mySharedPreferences.getString("q_seven", "");
        ret[7]= "Q8: Buy new brand " + mySharedPreferences.getString("q_eight", "");
        ret[8]= "Q9: Most important " + mySharedPreferences.getString("q_nine", "");
        ret[9]= "Q10: Age  " + mySharedPreferences.getString("q_ten", "");
        ret[10]= "Q11: Gender " + mySharedPreferences.getString("q_eleven", "");
        ret[11]= "Q12: Money earn per month " + mySharedPreferences.getString("q_twelve", "");

        return  ret;
    }


    public void getresult(View view) {


        TextView r1 = findViewById(R.id.rep_one);
        TextView r2 = findViewById(R.id.rep_two);
        TextView r3 = findViewById(R.id.rep_three);
        TextView r4 = findViewById(R.id.rep_four);
        TextView r5 = findViewById(R.id.rep_five);
        TextView r6 = findViewById(R.id.rep_six);
        TextView r7 = findViewById(R.id.rep_seven);
        TextView r8 = findViewById(R.id.rep_eight);
        TextView r9 = findViewById(R.id.rep_nine);
        TextView r10 = findViewById(R.id.rep_ten);
        TextView r11 = findViewById(R.id.rep_eleven);
        TextView r12 = findViewById(R.id.rep_twelve);


        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        String s1 = "Q1: Buy new brand " + mySharedPreferences.getString("q_one", "");
        String s2 = "Q2: Current phone cost " + mySharedPreferences.getString("q_two", "");
        String s3 = "Q3: Phone cell network " + mySharedPreferences.getString("q_three", "");
        String s4 = "Q4: Current function " + mySharedPreferences.getString("q_four", "");
        String s5 = "Q5: Often function " + mySharedPreferences.getString("q_five", "");
        String s6 = "Q6: Expected function " + mySharedPreferences.getString("q_six", "");
        String s7 = "Q7: To buy new phone  " + mySharedPreferences.getString("q_seven", "");
        String s8 = "Q8: Buy new brand " + mySharedPreferences.getString("q_eight", "");
        String s9 = "Q9: Most important " + mySharedPreferences.getString("q_nine", "");
        String s10 = "Q10: Age  " + mySharedPreferences.getString("q_ten", "");
        String s11 = "Q11: Gender " + mySharedPreferences.getString("q_eleven", "");
        String s12 = "Q12: Money earn per month " + mySharedPreferences.getString("q_twelve", "");

        r1.setText(s1);
        r2.setText(s2);
        r3.setText(s3);
        r4.setText(s4);
        r5.setText(s5);
        r6.setText(s6);
        r7.setText(s7);
        r8.setText(s8);
        r9.setText(s9);
        r10.setText(s10);
        r11.setText(s11);
        r12.setText(s12);

        final StringBuilder savedresult = new StringBuilder();

        String sBlock="{";
        String eBlock="}";
        String quote="\"";
        String qnum="question_number";
        String is=": ";
        String sep=", ";
        String content="content";


        String f1 = sBlock+quote+qnum+quote+is+quote+"1"+quote+sep+quote+content+quote+is+quote+s1+quote+eBlock;
        String f2 = sBlock+quote+qnum+quote+is+quote+"2"+quote+sep+quote+content+quote+is+quote+s2+quote+eBlock;
        String f3 = sBlock+quote+qnum+quote+is+quote+"3"+quote+sep+quote+content+quote+is+quote+s3+quote+eBlock;
        String f4 = sBlock+quote+qnum+quote+is+quote+"4"+quote+sep+quote+content+quote+is+quote+s4+quote+eBlock;
        String f5 = sBlock+quote+qnum+quote+is+quote+"5"+quote+sep+quote+content+quote+is+quote+s5+quote+eBlock;
        String f6 = sBlock+quote+qnum+quote+is+quote+"6"+quote+sep+quote+content+quote+is+quote+s6+quote+eBlock;
        String f7 = sBlock+quote+qnum+quote+is+quote+"7"+quote+sep+quote+content+quote+is+quote+s7+quote+eBlock;
        String f8 = sBlock+quote+qnum+quote+is+quote+"8"+quote+sep+quote+content+quote+is+quote+s8+quote+eBlock;
        String f9 = sBlock+quote+qnum+quote+is+quote+"9"+quote+sep+quote+content+quote+is+quote+s9+quote+eBlock;
        String f10 = sBlock+quote+qnum+quote+is+quote+"10"+quote+sep+quote+content+quote+is+quote+s10+quote+eBlock;
        String f11 = sBlock+quote+qnum+quote+is+quote+"11"+quote+sep+quote+content+quote+is+quote+s11+quote+eBlock;
        String f12 = sBlock+quote+qnum+quote+is+quote+"12"+quote+sep+quote+content+quote+is+quote+s12+quote+eBlock;



        savedresult.append(f1);
        savedresult.append(f2);
        savedresult.append(f3);
        savedresult.append(f4);
        savedresult.append(f5);
        savedresult.append(f6);
        savedresult.append(f7);
        savedresult.append(f8);
        savedresult.append(f9);
        savedresult.append(f10);
        savedresult.append(f11);
        savedresult.append(f12);
        //final String path = survey_report.this.getExternalFilesDir(null).getAbsolutePath();


        PermissionListener pm =new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(survey_report.this, "Permission granted!", Toast.LENGTH_SHORT).show();


                String fileLoc ="survey";
//                File directory = new File(path);
//                directory.mkdirs();
                //存储到/data/data/app包/app_survey包下
                File path=getDir("survey",MODE_PRIVATE);
                File saveData = new File(path,fileLoc+".json");

                JSONObject json=new JSONObject();

                while (saveData.exists()){
                    Integer subfix=1;
                    saveData= new File(path,fileLoc+subfix.toString()+".json");
                }
                    String msg[]=getData();
                    try {
                        for(int i=0;i<msg.length;i++){
                            json.put("Answer"+(i+1),msg[i]);
                        }


                        FileOutputStream fout = new FileOutputStream(saveData);
                        fout.write(json.toString().getBytes());
                        fout.flush();
                        fout.close();
                        Toast.makeText(survey_report.this, "JSON file saved!", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(survey_report.this, "Permission not given!", Toast.LENGTH_LONG).show();

            }
        };
        TedPermission.with(survey_report.this)
                .setPermissionListener(pm)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();




    }

    public void to_fin(View view) {
       startActivity(new Intent(survey_report.this, fin.class));
    }



    //保存到sdcard根目录
    private void save2SDCardRoot(String msg[]){

        JSONObject json=new JSONObject();
        try {

            for(int i=0;i<msg.length;i++){
                json.put("Answer"+(i+1),msg[i]);
            }

            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                String fileLoc ="survey";

                File path=Environment.getExternalStorageDirectory();
                File saveData = new File(path,fileLoc+".json");

                while (saveData.exists()){
                    Integer subfix=1;
                    saveData= new File(path,fileLoc+subfix.toString()+".json");
                }

                Log.e("location","Location2:"+saveData);
                try {
                    FileOutputStream fout = new FileOutputStream(saveData);
                    fout.write(json.toString().getBytes());
                    fout.flush();
                    fout.close();
                    Toast.makeText(survey_report.this, "JSON file saved!", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }



            }else{
                Toast.makeText(survey_report.this,"Save failed!",Toast.LENGTH_SHORT).show();
            }




        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
