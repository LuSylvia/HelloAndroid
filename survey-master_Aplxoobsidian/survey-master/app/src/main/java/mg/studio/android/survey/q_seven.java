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

public class q_seven extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_seven);
    }

    public void to_q_eight(View view) {
        RadioGroup r7= findViewById(R.id.r7);
        if (r7.getCheckedRadioButtonId()!=-1){

            RadioButton sel_seven= findViewById(r7.getCheckedRadioButtonId());
            String msgseven = sel_seven.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_seven",msgseven);
            editor.apply();





            startActivity(new Intent(q_seven.this,q_eight.class));}
        else{
            Toast.makeText(q_seven.this,"Need to select item!",Toast.LENGTH_LONG).show();

        }
    }
}
