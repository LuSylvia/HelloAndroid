package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class question5 extends AppCompatActivity {
    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private  String q5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        q3=intent.getStringExtra("Q3");
        q4=intent.getStringExtra("Q4");
        //Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_LONG).show();
    }
    public  void test6(View view){
        LinearLayout mLayout=findViewById(R.id.checklist_Q5);

        int count= mLayout.getChildCount();
        StringBuilder check2=new StringBuilder();
        for(int i=0;i<count;i++){
            View child=mLayout.getChildAt(i);
            if(child instanceof CheckBox){
                CheckBox cb=(CheckBox)child;
                if(cb.isChecked()){
                    check2.append(cb.getText()+",");
                }
            }
        }
        q5=check2.toString();

        Intent intent6=new Intent(this,question6.class);
        intent6.putExtra("Wel",welcome);
        intent6.putExtra("Q1",q1);
        intent6.putExtra("Q2",q2);
        intent6.putExtra("Q3",q3);
        intent6.putExtra("Q4",q4);
        intent6.putExtra("Q5",q5);
        startActivity(intent6);
    }
}
