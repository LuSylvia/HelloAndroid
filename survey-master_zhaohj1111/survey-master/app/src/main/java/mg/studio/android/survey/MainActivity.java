package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        a1=findViewById(R.id.CB1);
        b1=findViewById(R.id.RB_Q1_1);
        b2=findViewById(R.id.RB_Q1_2);
        b3=findViewById(R.id.RB_Q1_3);
        b4=findViewById(R.id.RB_Q1_4);
        b5=findViewById(R.id.RB_Q1_5);
        b6=findViewById(R.id.RB_Q1_6);
        b7=findViewById(R.id.RB_Q1_7);
    }
    private CheckBox a1;

    private RadioButton b1,b2,b3,b4,b5,b6,b7;


    public void test1(View view){
        String choose1="You don`t accepted these question.Plesae check your checkbox.";
        if(a1.isChecked()){
            TextView welcome=findViewById(R.id.text1);
            Intent intent1=new Intent(this,question1.class);
            intent1.putExtra("Wel",welcome.getText().toString().trim());
            startActivity(intent1);
        }
        else{
            Toast.makeText(getApplicationContext(),choose1,Toast.LENGTH_LONG).show();
        }
        //setContentView(R.layout.question_one);
    }


    /*public void test2(View view){
        setContentView(R.layout.question_two);
    }
    public void test3(View view){
        setContentView(R.layout.question_three);
    }
    public void test4(View view){
        setContentView(R.layout.question_four);
    }
    public void test5(View view){
        setContentView(R.layout.question_five);
    }
    public void test6(View view){
        setContentView(R.layout.question_six);
    }
    public void test7(View view){
        setContentView(R.layout.question_seven);
    }
    public void test8(View view){
        setContentView(R.layout.question_eight);
    }
    public void test9(View view){
        setContentView(R.layout.question_nine);
    }
    public void test10(View view){
        setContentView(R.layout.question_ten);
    }
    public void test11(View view){
        setContentView(R.layout.question_eleven);
    }
    public void test12(View view){
        setContentView(R.layout.question_twelve);
    }
    public void test13(View view){
        setContentView(R.layout.finish_survey);
    }
    private  EditText question6;
    public void finish(View view){
        //closeContextMenu();
        EditView question6=findViewById(R.id.ET_Q6);
        Intent intent=new Intent(this,showFinalAnswer.class);
        intent.putExtra("Q6",question6.getText().toString().trim());
        startActivity(intent);


    }
    */
}
