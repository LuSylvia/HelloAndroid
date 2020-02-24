package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Que1Activity extends AppCompatActivity {
    Button que1Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one);

        que1Next=findViewById(R.id.btn_ques1_next);
        que1Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que1Activity.this,Que2Activity.class);
                startActivity(intent);
            }
        });
    }

}
