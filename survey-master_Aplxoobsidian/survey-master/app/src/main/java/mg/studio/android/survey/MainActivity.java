package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void to_q_one(View view){
        CheckBox welbox = findViewById(R.id.acceptreq);
        if (welbox.isChecked()){
        startActivity(new Intent(MainActivity.this,q_one.class));}
        else{
            Toast.makeText(MainActivity.this,"Need to agree terms first!",Toast.LENGTH_LONG).show();

        }
    }
}
