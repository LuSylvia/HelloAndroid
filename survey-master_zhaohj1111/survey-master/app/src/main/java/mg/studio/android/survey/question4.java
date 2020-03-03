package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class question4 extends AppCompatActivity {
    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        q3=intent.getStringExtra("Q3");
        //Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_LONG).show();
    }
    public  void test5(View view){
        LinearLayout mLayout=findViewById(R.id.checklist_Q4);

        int count= mLayout.getChildCount();
        StringBuilder check1=new StringBuilder();
        for(int i=0;i<count;i++){
            View child=mLayout.getChildAt(i);
            if(child instanceof CheckBox){
                CheckBox cb=(CheckBox)child;
                if(cb.isChecked()){
                    check1.append(cb.getText()+",");
                }
            }
        }
        q4=check1.toString();


        Intent intent5=new Intent(this,question5.class);
        intent5.putExtra("Wel",welcome);
        intent5.putExtra("Q1",q1);
        intent5.putExtra("Q2",q2);
        intent5.putExtra("Q3",q3);
        intent5.putExtra("Q4",q4);
        //Toast.makeText(getApplicationContext(),q4,Toast.LENGTH_LONG).show();
        startActivity(intent5);
    }
}

