package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Que2Activity extends AppCompatActivity {

    Button que2Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two);

        que2Next=findViewById(R.id.btn_ques2_next);
        que2Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que2Activity.this,Que3Activity.class);
                startActivity(intent);
            }
        });
    }
}
