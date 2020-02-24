package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que8Activity extends AppCompatActivity {
    Button que8Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_eight);

        que8Next=findViewById(R.id.btn_ques8_next);
        que8Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que8Activity.this, Que9Activity.class);
                startActivity(intent);
            }
        });
    }
}
