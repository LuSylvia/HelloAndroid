package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que12Activity extends AppCompatActivity {

    Button que12Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_twelve);

        que12Next=findViewById(R.id.btn_ques12_next);
        que12Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que12Activity.this, FinishActivity.class);
                startActivity(intent);
            }
        });
    }
}
