package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class question10 extends AppCompatActivity {

    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private  String q5;
    private  String q6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question10);

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
    public  void test11(View view){
        Intent intent11=new Intent(this,question11.class);
        intent11.putExtra("Wel",welcome);
        intent11.putExtra("Q1",q1);
        intent11.putExtra("Q2",q2);
        intent11.putExtra("Q3",q3);
        intent11.putExtra("Q4",q4);
        intent11.putExtra("Q5",q5);
        intent11.putExtra("Q6",q6);
        startActivity(intent11);
    }
}
