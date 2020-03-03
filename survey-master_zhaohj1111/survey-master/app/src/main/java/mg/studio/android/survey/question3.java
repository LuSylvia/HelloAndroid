package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class question3 extends AppCompatActivity {
    private  String welcome;
    private String q1;
    private String q2;
    private String q3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        RadioGroup r1=findViewById(R.id.RG_Q3);
        final RadioButton rb_q1_1=findViewById(R.id.RB_Q3_1);
        final RadioButton rb_q1_2=findViewById(R.id.RB_Q3_2);
        final RadioButton rb_q1_3=findViewById(R.id.RB_Q3_3);
        final RadioButton rb_q1_4=findViewById(R.id.RB_Q3_4);


        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.RB_Q3_1:
                        q3=rb_q1_1.getText().toString();
                        //Toast.makeText(getApplicationContext(),q3,Toast.LENGTH_LONG).show();
                        break;
                    case R.id.RB_Q3_2:
                        q3=rb_q1_2.getText().toString();
                        break;
                    case R.id.RB_Q3_3:
                        q3=rb_q1_3.getText().toString();
                        break;
                    case R.id.RB_Q3_4:
                        q3=rb_q1_4.getText().toString();
                        break;

                }
            }
        });

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        //Toast.makeText(getApplicationContext(),q2,Toast.LENGTH_LONG).show();
    }
    public  void test4(View view){
        Intent intent4=new Intent(this,question4.class);
        intent4.putExtra("Wel",welcome);
        intent4.putExtra("Q1",q1);
        intent4.putExtra("Q2",q2);
        intent4.putExtra("Q3",q3);
        startActivity(intent4);
    }
}
