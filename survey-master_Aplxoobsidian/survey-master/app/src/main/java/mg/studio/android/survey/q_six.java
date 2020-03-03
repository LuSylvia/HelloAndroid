package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class q_six extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_six);

    }

    public void to_q_seven(View view) {
        EditText edit1 =findViewById(R.id.edit1);


        if (!edit1.getText().toString().matches("")){

            String msgsix = edit1.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_six",msgsix);
            editor.apply();




            startActivity(new Intent(q_six.this, q_seven.class));
        }
        else{
            Toast.makeText(q_six.this, "Need to input item!", Toast.LENGTH_LONG).show();
        }

    }
}
