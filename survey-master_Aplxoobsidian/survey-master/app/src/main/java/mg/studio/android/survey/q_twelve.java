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

public class q_twelve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_twelve);

    }
    public  void to_fin(View view){
        RadioGroup r12= findViewById(R.id.r12);
        if (r12.getCheckedRadioButtonId()!=-1){

            RadioButton sel_twelve= findViewById(r12.getCheckedRadioButtonId());
            String msgtwelve = sel_twelve.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_twelve",msgtwelve);
            editor.apply();




            startActivity(new Intent(q_twelve.this,survey_report.class));}
        else{
            Toast.makeText(q_twelve.this,"Need to select item!",Toast.LENGTH_LONG).show();
        }

    }
}
