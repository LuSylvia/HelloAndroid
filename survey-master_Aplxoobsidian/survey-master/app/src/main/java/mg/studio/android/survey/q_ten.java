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

public class q_ten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ten);

    }

    public  void to_q_eleven(View view){
        RadioGroup r10= findViewById(R.id.r10);
        if (r10.getCheckedRadioButtonId()!=-1){
            RadioButton sel_ten= findViewById(r10.getCheckedRadioButtonId());
            String msgten = sel_ten.getText().toString();

            SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("q_ten",msgten);
            editor.apply();
            startActivity(new Intent(q_ten.this,q_eleven.class));}
        else{
            Toast.makeText(q_ten.this,"Need to select item!",Toast.LENGTH_LONG).show();
        }

    }

}
