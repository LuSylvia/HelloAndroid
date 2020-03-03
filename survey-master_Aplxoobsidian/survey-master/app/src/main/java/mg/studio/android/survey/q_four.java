package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.lang.StringBuilder;

public class q_four extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_four);

    }

    public void to_q_five(View view) {
        CheckBox b1 = findViewById(R.id.cb1);
        CheckBox b2 = findViewById(R.id.cb2);
        CheckBox b3 = findViewById(R.id.cb3);
        CheckBox b4 = findViewById(R.id.cb4);
        CheckBox b5 = findViewById(R.id.cb5);
        CheckBox b6 = findViewById(R.id.cb6);
        CheckBox b7 = findViewById(R.id.cb7);

        if ((b1.isChecked()||b2.isChecked()||b3.isChecked()||b4.isChecked()||b5.isChecked()||b6.isChecked()||b7.isChecked())){
            StringBuilder total = new StringBuilder(100);
            if(b1.isChecked()){total.append(b1.getText().toString());total.append("; ");}
            if(b2.isChecked()){total.append(b2.getText().toString());total.append("; ");}
            if(b3.isChecked()){total.append(b3.getText().toString());total.append("; ");}
            if(b4.isChecked()){total.append(b4.getText().toString());total.append("; ");}
            if(b5.isChecked()){total.append(b5.getText().toString());total.append("; ");}
            if(b6.isChecked()){total.append(b6.getText().toString());total.append("; ");}
            if(b7.isChecked()){total.append(b7.getText().toString());total.append("; ");}


            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_four",total.toString());
            editor.apply();





            startActivity(new Intent(q_four.this, q_five.class));
        }
        else{
            Toast.makeText(q_four.this, "Need to select item!", Toast.LENGTH_LONG).show();
        }

    }
}
