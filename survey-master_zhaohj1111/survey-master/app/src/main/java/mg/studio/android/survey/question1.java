package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class question1 extends AppCompatActivity {

    private  String welcome;
    private  String q1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        RadioGroup r1=findViewById(R.id.RG_Q1);
        final RadioButton rb_q1_1=findViewById(R.id.RB_Q1_1);
        final RadioButton rb_q1_2=findViewById(R.id.RB_Q1_2);
        final RadioButton rb_q1_3=findViewById(R.id.RB_Q1_3);
        final RadioButton rb_q1_4=findViewById(R.id.RB_Q1_4);
        final RadioButton rb_q1_5=findViewById(R.id.RB_Q1_5);
        final RadioButton rb_q1_6=findViewById(R.id.RB_Q1_6);
        final RadioButton rb_q1_7=findViewById(R.id.RB_Q1_7);

        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.RB_Q1_1:
                        q1=rb_q1_1.getText().toString();
                        break;
                    case R.id.RB_Q1_2:
                        q1=rb_q1_2.getText().toString();
                        break;
                    case R.id.RB_Q1_3:
                        q1=rb_q1_3.getText().toString();
                        break;
                    case R.id.RB_Q1_4:
                        q1=rb_q1_4.getText().toString();
                        break;
                    case R.id.RB_Q1_5:
                        q1=rb_q1_5.getText().toString();
                        break;
                    case R.id.RB_Q1_6:
                        q1=rb_q1_6.getText().toString();
                        break;
                    case R.id.RB_Q1_7:
                        q1=rb_q1_7.getText().toString();
                        break;
                }
            }
        });

        Intent intent = getIntent();
        welcome=intent.getStringExtra("Wel");
        //Toast.makeText(getApplicationContext(),welcome,Toast.LENGTH_LONG).show();


    }

   /* public void initView(){
        RadioGroup rg1=findViewById(R.id.RG_Q1);
        RadioButton rb_q1_1=findViewById(R.id.RB_Q1_1);
        RadioButton rb_q1_2=findViewById(R.id.RB_Q1_2);
        RadioButton rb_q1_3=findViewById(R.id.RB_Q1_3);
        RadioButton rb_q1_4=findViewById(R.id.RB_Q1_4);
        RadioButton rb_q1_5=findViewById(R.id.RB_Q1_5);
        RadioButton rb_q1_6=findViewById(R.id.RB_Q1_6);
        RadioButton rb_q1_7=findViewById(R.id.RB_Q1_7);
        Button btn1=findViewById(R.id.BTN_Q1_1);
        btn1=setOnCheckedChangeListener(this);

    }
    */

    /*public void onCheckedChanged(RadioGroup radioGroup, int checkedId){
        switch (checkedId){
            case R.id.RB_Q1_1:
                q1=
                break;
            case R.id.RB_Q1_2:
                break;
            case R.id.RB_Q1_3:
                break;
            case R.id.RB_Q1_4:
                break;
            case R.id.RB_Q1_5:
                break;
            case R.id.RB_Q1_6:
                break;
            case R.id.RB_Q1_7:
                break;
        }
    }
*/
    public  void test2(View view){
        Intent intent2=new Intent(this,question2.class);
        intent2.putExtra("Wel",welcome);
        intent2.putExtra("Q1",q1);
        startActivity(intent2);
        //Toast.makeText(getApplicationContext(),q1,Toast.LENGTH_SHORT).show();
    }
}
