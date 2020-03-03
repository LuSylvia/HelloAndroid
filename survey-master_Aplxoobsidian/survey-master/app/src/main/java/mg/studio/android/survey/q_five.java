package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class q_five extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five);

    }

    public void to_q_six(View view) {
        CheckBox b12 = findViewById(R.id.cb12);
        CheckBox b22 = findViewById(R.id.cb22);
        CheckBox b32 = findViewById(R.id.cb32);
        CheckBox b42 = findViewById(R.id.cb42);
        CheckBox b52 = findViewById(R.id.cb52);
        CheckBox b62 = findViewById(R.id.cb62);
        CheckBox b72 = findViewById(R.id.cb72);

        if ((b12.isChecked()||b22.isChecked()||b32.isChecked()||b42.isChecked()||b52.isChecked()||b62.isChecked()||b72.isChecked())){

            StringBuilder total = new StringBuilder(100);
            if(b12.isChecked()){total.append(b12.getText().toString());total.append("; ");}
            if(b22.isChecked()){total.append(b22.getText().toString());total.append("; ");}
            if(b32.isChecked()){total.append(b32.getText().toString());total.append("; ");}
            if(b42.isChecked()){total.append(b42.getText().toString());total.append("; ");}
            if(b52.isChecked()){total.append(b52.getText().toString());total.append("; ");}
            if(b62.isChecked()){total.append(b62.getText().toString());total.append("; ");}
            if(b72.isChecked()){total.append(b72.getText().toString());total.append("; ");}


            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_five",total.toString());
            editor.apply();






            startActivity(new Intent(q_five.this, q_six.class));
        }
        else{
            Toast.makeText(q_five.this, "Need to select item!", Toast.LENGTH_LONG).show();
        }

    }

}
