package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Que3Activity extends AppCompatActivity {
    Button que3Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three);

        que3Next=findViewById(R.id.btn_ques3_next);
        que3Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que3Activity.this,Que4Activity.class);
                startActivity(intent);
            }
        });
    }
}
