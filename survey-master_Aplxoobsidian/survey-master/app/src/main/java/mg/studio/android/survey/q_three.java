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

public class q_three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three);

    }

    public void to_q_four(View view) {
        RadioGroup r3 = findViewById(R.id.r3);
        if (r3.getCheckedRadioButtonId() != -1) {
            RadioButton sel_three= findViewById(r3.getCheckedRadioButtonId());
            String msgthree = sel_three.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_three",msgthree);
            editor.apply();



            startActivity(new Intent(q_three.this, q_four.class));
        } else {
            Toast.makeText(q_three.this, "Need to select item!", Toast.LENGTH_LONG).show();

        }
    }

}
