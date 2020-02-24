package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que10Activity extends AppCompatActivity {
    Button que10Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_ten);

        que10Next=findViewById(R.id.btn_ques10_next);
        que10Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que10Activity.this, Que11Activity.class);
                startActivity(intent);
            }
        });
    }
}
