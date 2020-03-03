package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class finaltext extends AppCompatActivity {

    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private  String q6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaltext);

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        q3=intent.getStringExtra("Q3");
        q4=intent.getStringExtra("Q4");
        q5=intent.getStringExtra("Q5");
        q6=intent.getStringExtra("Q6");
        //Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_LONG).show();
    }
    public  void finish(View view){
        Intent intent14=new Intent(this,showFinalAnswer.class);
        intent14.putExtra("Wel",welcome);
        intent14.putExtra("Q1",q1);
        intent14.putExtra("Q2",q2);
        intent14.putExtra("Q3",q3);
        intent14.putExtra("Q4",q4);
        intent14.putExtra("Q5",q5);
        intent14.putExtra("Q6",q6);
        //Toast.makeText(getApplicationContext(),q1,Toast.LENGTH_LONG).show();
        startActivity(intent14);
    }
}
