package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Que4Activity extends AppCompatActivity {

    Button que4Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_four);

        que4Next=findViewById(R.id.btn_ques4_next);
        que4Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Que4Activity.this,Que5Activity.class);
                startActivity(intent);
            }
        });
    }
}
