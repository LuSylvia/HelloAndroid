package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class question6 extends AppCompatActivity {
    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private  String q5;
    private String q6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        q3=intent.getStringExtra("Q3");
        q4=intent.getStringExtra("Q4");
        q5=intent.getStringExtra("Q5");
        //Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_LONG).show();
    }
    public  void test7(View view){
        EditText question6 =findViewById(R.id.ET_Q6);
        q6=question6.getText().toString();

        Intent intent7=new Intent(this,question7.class);
        intent7.putExtra("Wel",welcome);
        intent7.putExtra("Q1",q1);
        intent7.putExtra("Q2",q2);
        intent7.putExtra("Q3",q3);
        intent7.putExtra("Q4",q4);
        intent7.putExtra("Q5",q5);
        intent7.putExtra("Q6",q6);
        startActivity(intent7);
        //Toast.makeText(getApplicationContext(),q6,Toast.LENGTH_LONG).show();
    }
}
