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

public class q_nine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_nine);

    }


    public  void to_q_ten(View view){
        RadioGroup r9= findViewById(R.id.r9);
        if (r9.getCheckedRadioButtonId()!=-1){
            RadioButton sel_nine= findViewById(r9.getCheckedRadioButtonId());
            String msgnine = sel_nine.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_nine",msgnine);
            editor.apply();

            startActivity(new Intent(q_nine.this,q_ten.class));}
        else{
            Toast.makeText(q_nine.this,"Need to select item!",Toast.LENGTH_LONG).show();
        }

    }
}
