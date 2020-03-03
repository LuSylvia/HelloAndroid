package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import java.lang.*;
import android.content.SharedPreferences;

public class q_one extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one);
    }

    public  void to_q_two(View view){
        RadioGroup r1= findViewById(R.id.r1);
        if (r1.getCheckedRadioButtonId()!=-1){
            RadioButton sel_one= findViewById(r1.getCheckedRadioButtonId());
            String msgone = sel_one.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_one",msgone);
            editor.apply();

            startActivity(new Intent(q_one.this,q_two.class));


        }
        else{
            Toast.makeText(q_one.this,"Need to select item!",Toast.LENGTH_LONG).show();

        }

    }


}
