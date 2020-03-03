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

public class q_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two);
    }

    public void to_q_three(View view) {
        RadioGroup r2 = findViewById(R.id.r2);
        if (r2.getCheckedRadioButtonId() != -1) {
            RadioButton sel_two= findViewById(r2.getCheckedRadioButtonId());
            String msgtwo = sel_two.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_two",msgtwo);
            editor.apply();




            startActivity(new Intent(q_two.this, q_three.class));
        } else {
            Toast.makeText(q_two.this, "Need to select item!", Toast.LENGTH_LONG).show();

        }
    }
}