package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Que7Activity extends AppCompatActivity {

    Button que7Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_seven);

        que7Next=findViewById(R.id.btn_ques7_next);
        que7Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que7Activity.this, Que8Activity.class);
                startActivity(intent);
            }
        });
    }
}
