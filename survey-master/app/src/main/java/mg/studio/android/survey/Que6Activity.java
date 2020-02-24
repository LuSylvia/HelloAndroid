package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que6Activity extends AppCompatActivity {

    Button que6Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_six);

        que6Next =findViewById(R.id.btn_ques6_next);
        que6Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que6Activity.this, Que7Activity.class);
                startActivity(intent);
            }
        });
    }
}
