package com.example.admin.railwayticketreservationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.DataBase.UserinfoHelp;

public class UpdateActivity extends AppCompatActivity {
    private EditText update_phone;
    private EditText update_oldpwd;
    private EditText update_newpwd;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_phone=findViewById(R.id.update_phone);
        update_oldpwd=findViewById(R.id.update_oldpwd);
        update_newpwd=findViewById(R.id.update_newpwd);
        btn_update=findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=update_phone.getText().toString();
                String oldpwd=update_oldpwd.getText().toString();
                String newpwd=update_newpwd.getText().toString();
                if(UserinfoHelp.updateinfo(phone,oldpwd,newpwd)){
                    //Toast.makeText(this,"修改密码还在开发中！",Toast.LENGTH_SHORT).show();

                    Toast.makeText(UpdateActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(UpdateActivity.this,"修改失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
