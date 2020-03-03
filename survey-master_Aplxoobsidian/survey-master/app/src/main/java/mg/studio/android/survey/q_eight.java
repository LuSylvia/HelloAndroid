package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class q_eight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_eight);

    }

    public  void to_q_nine(View view){
        RadioGroup r8= findViewById(R.id.r8);
        if (r8.getCheckedRadioButtonId()!=-1){

            RadioButton sel_eight= findViewById(r8.getCheckedRadioButtonId());
            String msgeight = sel_eight.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_eight",msgeight);
            editor.apply();


            startActivity(new Intent(q_eight.this,q_nine.class));}
        else{
            Toast.makeText(q_eight.this,"Need to select item!",Toast.LENGTH_LONG).show();

        }

    }


}
