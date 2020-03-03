package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class question2 extends AppCompatActivity {
    private  String welcome;
    private  String q1;
    private String q2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        RadioGroup r1=findViewById(R.id.RG_Q2);
        final RadioButton rb_q1_1=findViewById(R.id.RB_Q2_1);
        final RadioButton rb_q1_2=findViewById(R.id.RB_Q2_2);
        final RadioButton rb_q1_3=findViewById(R.id.RB_Q2_3);
        final RadioButton rb_q1_4=findViewById(R.id.RB_Q2_4);
        final RadioButton rb_q1_5=findViewById(R.id.RB_Q2_5);


        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.RB_Q2_1:
                        q2=rb_q1_1.getText().toString();
                        //Toast.makeText(getApplicationContext(),q2,Toast.LENGTH_LONG).show();
                        break;
                    case R.id.RB_Q2_2:
                        q2=rb_q1_2.getText().toString();
                        break;
                    case R.id.RB_Q2_3:
                        q2=rb_q1_3.getText().toString();
                        break;
                    case R.id.RB_Q2_4:
                        q2=rb_q1_4.getText().toString();
                        break;
                    case R.id.RB_Q2_5:
                        q2=rb_q1_5.getText().toString();
                        break;

                }
            }
        });

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        q1=intent.getStringExtra("Q1");
    }
    public  void test3(View view){
        Intent intent3=new Intent(this,question3.class);
        intent3.putExtra("Wel",welcome);
        intent3.putExtra("Q1",q1);
        intent3.putExtra("Q2",q2);
        startActivity(intent3);
    }
}
