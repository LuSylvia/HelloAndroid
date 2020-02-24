package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que9Activity extends AppCompatActivity {
    Button que9Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_nine);

        que9Next=findViewById(R.id.btn_ques9_next);
        que9Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que9Activity.this, Que10Activity.class);
                startActivity(intent);
            }
        });
    }
}
