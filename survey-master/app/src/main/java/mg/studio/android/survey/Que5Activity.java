package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Que5Activity extends AppCompatActivity {
    Button que5Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five);

        que5Next=findViewById(R.id.btn_ques5_next);
        que5Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que5Activity.this,Que6Activity.class);
                startActivity(intent);
            }
        });
    }
}
