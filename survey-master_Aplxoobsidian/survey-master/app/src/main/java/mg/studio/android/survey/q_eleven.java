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

public class q_eleven extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_eleven);

    }
    public  void to_q_twelve(View view){
        RadioGroup r11= findViewById(R.id.r11);
        if (r11.getCheckedRadioButtonId()!=-1){


            RadioButton sel_eleven= findViewById(r11.getCheckedRadioButtonId());
            String msgeleven = sel_eleven.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_eleven",msgeleven);
            editor.apply();



            startActivity(new Intent(q_eleven.this,q_twelve.class));}
        else{
            Toast.makeText(q_eleven.this,"Need to select item!",Toast.LENGTH_LONG).show();
        }

    }
}
