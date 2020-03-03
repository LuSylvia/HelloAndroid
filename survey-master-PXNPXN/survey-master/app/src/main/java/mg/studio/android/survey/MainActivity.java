package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.view.View;
import android.widget.*;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  mButton1;
    private  Bundle all =new Bundle();
    private  Bundle One=new Bundle();
    private  Bundle Two=new Bundle();
    private  Bundle Three=new Bundle();
    private  Bundle Four=new Bundle();
    private  Bundle Five=new Bundle();
    private  Bundle Six=new Bundle();
    private  Bundle Seven=new Bundle();
    private  Bundle Eight=new Bundle();
    private  Bundle Nine=new Bundle();
    private  Bundle Ten=new Bundle();
    private  Bundle Eleven=new Bundle();
    private  Bundle Twelve=new Bundle();
    private  RadioButton mRadioButton;
    private  CheckBox mCheckBox;
    private TextInputEditText mTextInputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mButton1 =(Button)findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button1: {
                setContentView(R.layout.question_one);
                mButton1 = (Button) findViewById(R.id.button2);
                mButton1.setOnClickListener(this);
            }
                break;
            case R.id.button2:
                SkipOneData();
                setContentView(R.layout.question_two);
                mButton1 = (Button) findViewById(R.id.button3);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button3:
                SkipTwoData();
                setContentView(R.layout.question_three);
                mButton1=(Button)findViewById(R.id.button4);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button4:
                SkipThreeData();
                setContentView(R.layout.question_four);
                mButton1=(Button)findViewById(R.id.button5);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button5:
                SkipFourData();
                setContentView(R.layout.question_five);
                mButton1=(Button)findViewById(R.id.button6);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button6:
                SkipFiveData();
                setContentView(R.layout.question_six);
                mButton1=(Button)findViewById(R.id.button7);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button7:
                SkipSixData();
                setContentView(R.layout.question_seven);
                mButton1=(Button)findViewById(R.id.button8);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button8:
                SkipSevenData();
                setContentView(R.layout.question_eight);
                mButton1=(Button)findViewById(R.id.button9);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button9:
                SkipEightData();
                setContentView(R.layout.question_nine);
                mButton1=(Button)findViewById(R.id.button10);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button10:
                SkipNineData();
                setContentView(R.layout.question_ten);
                mButton1=(Button)findViewById(R.id.button11);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button11:
                SkipTenData();
                setContentView(R.layout.question_eleven);
                mButton1=(Button)findViewById(R.id.button12);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button12:
                SkipElevenData();
                setContentView(R.layout.question_twelve);
                mButton1=(Button)findViewById(R.id.button13);
                mButton1.setOnClickListener(this);
                break;
            case R.id.button13:
                SkipTwelveData();
                setContentView(R.layout.finish_survey);
                //mButton1=(Button)findViewById(R.id.button14);
                //mButton1.setOnClickListener(this);
                break;
//            case R.id.button14:
//                setContentView(R.layout.welcome);
//                mButton1=(Button)findViewById(R.id.button1);
//                mButton1.setOnClickListener(this);
//                break;

        }
    }

    public void skip(View v){
        Intent intent=new Intent(MainActivity.this,Report.class);
        intent.putExtras(all);
        startActivity(intent);
    }

    public void SkipOneData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_1);
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_2);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_3);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_4);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_5);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_5);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_6);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_6);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_7);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_7);
        }
        if(mRadioButton.isChecked()){
            One.putString("one",mRadioButton.getText().toString());
            all.putBundle("Question1",One);
        }
        else{
            all.putBundle("Question1",One);
        }
    }

    public void SkipTwoData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_two_1);
        if(mRadioButton.isChecked()){
            Two.putString("two",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_2);
        }
        if(mRadioButton.isChecked()){
            Two.putString("two",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_3);
        }
        if(mRadioButton.isChecked()){
            Two.putString("two",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_4);
        }
        if(mRadioButton.isChecked()){
            Two.putString("two",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_5);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_two_5);
        }
        if(mRadioButton.isChecked()){
            Two.putString("two",mRadioButton.getText().toString());
            all.putBundle("Question2",Two);
        }
        else{
            all.putBundle("Question2",Two);
        }
    }


    public void SkipThreeData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_three_1);
        if(mRadioButton.isChecked()){
            Three.putString("three",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_2);
        }
        if(mRadioButton.isChecked()){
            Three.putString("three",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_3);
        }
        if(mRadioButton.isChecked()){
            Three.putString("three",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_three_4);
        }
        if(mRadioButton.isChecked()){
            Three.putString("three",mRadioButton.getText().toString());
            all.putBundle("Question3",Three);
        }
        else{
            all.putBundle("Question3",Three);
        }
    }

    public void SkipFourData(){
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_1);
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_2);

        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_2);

        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_3);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_3);
        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_4);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_4);
        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_5);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_5);
        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_6);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_6);
        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_7);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_four_7);
        }
        if(mCheckBox.isChecked()){
            Four.putString("four",mCheckBox.getText().toString());
            all.putBundle("Question4",Four);
        }
        else{
            all.putBundle("Question4",Four);
        }
    }

    public void SkipFiveData(){
        mCheckBox=(CheckBox)findViewById(R.id.cb_five_1);
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_2);

        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_2);

        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_3);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_3);
        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_4);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_4);
        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_5);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_5);
        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_6);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_6);
        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_7);
        }
        else{
            mCheckBox=(CheckBox)findViewById(R.id.cb_five_7);
        }
        if(mCheckBox.isChecked()){
            Five.putString("five",mCheckBox.getText().toString());
            all.putBundle("Question5",Five);
        }
        else{
            all.putBundle("Question5",Five);
        }
    }


    public void SkipSixData(){
        mTextInputEditText=(TextInputEditText)findViewById(R.id.et_six_1);
        if(mTextInputEditText.isClickable()){
            Six.putString("six",mTextInputEditText.getText().toString());
            all.putBundle("Question6",Six);
        }
        else{
            all.putBundle("Question6",Six);
        }
    }


    public void SkipSevenData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_seven_1);
        if(mRadioButton.isChecked()){
            Seven.putString("seven",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_2);
        }
        if(mRadioButton.isChecked()){
            Seven.putString("seven",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_3);
        }
        if(mRadioButton.isChecked()){
            Seven.putString("seven",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_4);
        }
        if(mRadioButton.isChecked()){
            Seven.putString("seven",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_5);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_seven_5);
        }
        if(mRadioButton.isChecked()){
            Seven.putString("seven",mRadioButton.getText().toString());
            all.putBundle("Question7",Seven);
        }
        else{
            all.putBundle("Question7",Seven);
        }
    }


    public void SkipEightData(){
           mRadioButton=(RadioButton)findViewById(R.id.rb_eight_1);
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_2);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_3);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_4);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_5);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_5);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_6);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_6);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_7);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eight_7);
        }
        if(mRadioButton.isChecked()){
            Eight.putString("eight",mRadioButton.getText().toString());
            all.putBundle("Question8",Eight);
        }
        else{
            all.putBundle("Question8",Eight);
        }
    }



    public void SkipNineData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_nine_1);
        if(mRadioButton.isChecked()){
            Nine.putString("nine",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_2);
        }
        if(mRadioButton.isChecked()){
            Nine.putString("nine",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_3);
        }
        if(mRadioButton.isChecked()){
Nine.putString("nine",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_nine_4);
        }
        if(mRadioButton.isChecked()){
            Nine.putString("nine",mRadioButton.getText().toString());
            all.putBundle("Question9",Nine);
        }
        else{
            all.putBundle("Question9",Nine);
        }
    }


    public void SkipTenData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_ten_1);
        if(mRadioButton.isChecked()){
            Ten.putString("ten",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_2);
        }
        if(mRadioButton.isChecked()){
            Ten.putString("ten",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_3);
        }
        if(mRadioButton.isChecked()){
            Ten.putString("ten",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_ten_4);
        }
        if(mRadioButton.isChecked()){
            Ten.putString("ten",mRadioButton.getText().toString());
            all.putBundle("Question10",Ten);
        }
        else{
            all.putBundle("Question10",Ten);
        }
    }


    public void SkipElevenData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_eleven_1);
        if(mRadioButton.isChecked()){
            Eleven.putString("eleven",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_eleven_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_eleven_2);
        }
        if(mRadioButton.isChecked()){
            Eleven.putString("eleven",mRadioButton.getText().toString());
            all.putBundle("Question11",Eleven);
        }
        else{
            all.putBundle("Question11",Eleven);
        }
    }


    public void SkipTwelveData(){
        mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_1);
        if(mRadioButton.isChecked()){
            Twelve.putString("twelve",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_2);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_2);
        }
        if(mRadioButton.isChecked()){
            Twelve.putString("twelve",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_3);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_3);
        }
        if(mRadioButton.isChecked()){
            Twelve.putString("twelve",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_4);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_4);
        }
        if(mRadioButton.isChecked()){
            Twelve.putString("twelve",mRadioButton.getText().toString());
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_5);
        }
        else{
            mRadioButton=(RadioButton)findViewById(R.id.rb_twelve_5);
        }
        if(mRadioButton.isChecked()){
            Twelve.putString("twelve",mRadioButton.getText().toString());
            all.putBundle("Question12",Twelve);
        }
        else{
            all.putBundle("Question12",Twelve);
        }
    }


}
