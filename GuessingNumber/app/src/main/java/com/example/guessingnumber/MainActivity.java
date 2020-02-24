package com.example.guessingnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.guessingnumber.BuildConfig.DEBUG;

public class MainActivity extends AppCompatActivity {
    int numToGuess=0;
    Button  btn_check;
    ImageView im_up;
    ImageView im_low;
    TextView tv_1;
    EditText ed_1;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化待猜的数字
        numToGuess=(int) ((Math.random() * 9 + 1) * (1000));
        Log.e("what the hell", String.valueOf(numToGuess));

        btn_check=findViewById(R.id.btn_check);
        im_up=findViewById(R.id.image_high);
        im_low=findViewById(R.id.image_low);
        tv_1=findViewById(R.id.tv_1);
        ed_1=findViewById(R.id.ed_1);

        //编辑框添加监听事件
        ed_1.addTextChangedListener(new EditChangedListener());


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=Integer.parseInt(ed_1.getText().toString());

                //小于
                if(num<numToGuess){
                    Log.e("what the small","small test");
                    im_up.setVisibility(View.VISIBLE);
                    im_up.setImageResource(R.drawable.up_wrong);
                    im_low.setVisibility(View.VISIBLE);
                    im_low.setImageResource(R.drawable.low_right);
                    Toast.makeText(MainActivity.this,"Too small!",Toast.LENGTH_SHORT).show();
                    tv_1.setText("Lorem ipsum");
                    //
                    btn_check.setText("");
                     btn_check.setBackgroundResource(R.drawable.wrong);
                }//大于
                else if (num>numToGuess){
                    Log.e("what the big","big test");
                    im_up.setVisibility(View.VISIBLE);
                    im_up.setImageResource(R.drawable.up_right);
                    im_low.setVisibility(View.VISIBLE);
                    im_low.setImageResource(R.drawable.low_wrong);
                    Toast.makeText(MainActivity.this,"Too big!",Toast.LENGTH_SHORT).show();
                    tv_1.setText("Lorem ipsum");
                    btn_check.setText("");

                    btn_check.setBackgroundResource(R.drawable.wrong);
                }//等于
                else if(num==numToGuess){
                    im_up.setVisibility(View.INVISIBLE);
                    im_low.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this,"Right answer!",Toast.LENGTH_SHORT).show();
                    btn_check.setText("");
                    btn_check.setBackgroundResource(R.drawable.right);
                    tv_1.setText("You got it");
                    ed_1.setEnabled(false);
                }


            }
        });

    }

    class EditChangedListener implements TextWatcher {
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 10;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.e("before","edit before");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if (!TextUtils.isEmpty(etLoginname.getText().toString()) && !TextUtils.isEmpty(etLoginpwd.getText().toString())) {
//                btLogin.setBackgroundResource(R.color.white);
//                btLogin.setEnabled(true);
//            } else {
//                btLogin.setBackgroundResource(R.color.gray);
//
//                btLogin.setEnabled(false);
//            }
            Log.e("onTextChanged","edit is doing");

            //如果编辑框内容不为空且者数字不为0，check按钮可视化
            if (!TextUtils.isEmpty((ed_1.getText().toString())) && (Integer.parseInt(ed_1.getText().toString()) != 0)) {

                btn_check.setVisibility(View.VISIBLE);

            }



        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e("after","edit finished");
            btn_check.setText("check");
            btn_check.setBackgroundResource(R.drawable.circle);

            tv_1.setText("The computer only considers a number \n                     from 1 to 9999");
            im_up.setVisibility(View.INVISIBLE);
            im_low.setVisibility(View.INVISIBLE);
        }
    };

}
