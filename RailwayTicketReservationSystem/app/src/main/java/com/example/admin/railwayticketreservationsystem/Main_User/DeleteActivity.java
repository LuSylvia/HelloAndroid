package com.example.admin.railwayticketreservationsystem.Main_User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.DataBase.TicketHelp;
import com.example.admin.railwayticketreservationsystem.R;

public class DeleteActivity extends AppCompatActivity {
    private EditText ed_delname;
    private EditText ed_delidCard;
    private EditText ed_del_Startstop;
    private EditText ed_del_Arrivestop;
    private Button btn_del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ed_delname=findViewById(R.id.tv_del_name);
        ed_delidCard=findViewById(R.id.tv_del_idCard);
        ed_del_Startstop=findViewById(R.id.tv_del_startstop);
        ed_del_Arrivestop=findViewById(R.id.tv_del_arrivestop);

        btn_del=findViewById(R.id.btn_del);


        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idCard=ed_delidCard.getText().toString();
                String name=ed_delname.getText().toString();
                String startstop=ed_del_Startstop.getText().toString();
                String arrivestop=ed_del_Arrivestop.getText().toString();

                if(  TicketHelp.deleteOrders(name,idCard,startstop,arrivestop) ){
                    //退票成功
                    Toast.makeText(DeleteActivity.this,"退票成功！",Toast.LENGTH_SHORT).show();
                    return;

                }else {
                    //退票失败
                    Toast.makeText(DeleteActivity.this,"退票失败！",Toast.LENGTH_SHORT).show();
                    return;

                }

            }
        });



    }
}
