package com.example.surver2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;


public class MainActivity extends AppCompatActivity implements PermissionCallbacks {


    private static final int REQUEST_CODE_PERMISSION = 1;
    TextView tv_single;
    TextView tv_multiselect;
    TextView tv_edit;
    TextView tv_main;

    Button btn_single_next;
    Button btn_multiselect_next;
    Button btn_edit_next;
    Button btn_loadjson;
    Button btn_scanQR;//扫描二维码


    EditText ed_edit;
    RadioGroup rg_single;
    RadioGroup rg_multiselect;
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();


    int numTotal = 0;//用于记录问题总数
    int currQues = 0;//用于记录当前是第几个问题

    private static int PERMISSION_GRANTED = 1;
    private ArrayList<String> optionsList = new ArrayList<>();//存储单个问题的所有选项
    ArrayList<String> answers = new ArrayList<>();//存储用户选择的答案
    String type;//类型
    String question;//题目


    //json对象
    String json = "{\"survey\":{\"id\":\"12344134\",\"len\":\"2\",\"questions\":[{\"type\":\"single\",\"question\":\"How well do the professors teach at this university?\",\"options\":[{\"1\":\"Extremely well\"},{\"2\":\"Very well\"}]},{\"type\":\"single\",\"question\":\"How effective is the teaching outside yur major at the univesrity?\",\"options\":[{\"1\":\"Extremetly effective\"},{\"2\":\"Very effective\"},{\"3\":\"Somewhat effective\"},{\"4\":\"Not so effective\"},{\"5\":\"Not at all effective\"}]}]}}";
    String json_QRcode = "";
    JSONObject jsonObject = null;
    JSONArray jsonQuestions = null;
    JSONObject jsonQuestion = null;
    JSONArray jsonOptions = null;


    //GPS
    LocationManager locationManager;
    LocationListener locationListener;
    OnNmeaMessageListener nmeaListener;
    GpsStatus.NmeaListener nmeaListenerOld;


    private TextView positionTextView;
    /**
     * Android 10及以上申请权限
     */
    private String[] permissionsQ = new String[]{
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            // 编译版本小于29，不能使用Manifest.permission.ACCESS_BACKGROUND_LOCATION
            "android.permission.ACCESS_BACKGROUND_LOCATION",
    };

    /**
     * Android10以下申请权限
     */
    private String[] permissionsO = new String[]{
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        btn_loadjson = findViewById(R.id.btn_loadjson);
        btn_scanQR = findViewById(R.id.btn_scanQR);

        positionTextView = findViewById(R.id.position_text_view);
        tv_main = findViewById(R.id.tv_main);


        //获取GPS定位
        String[] permissions = permissionsO;
        // 编译版本小于29，不能使用Build.VERSION_CODES.Q
        if (Build.VERSION.SDK_INT >= 29) {
            permissions = permissionsQ;
        }
        if (EasyPermissions.hasPermissions(this, permissions)) {
            onPermissionsGranted(REQUEST_CODE_PERMISSION, null);
        } else {
            EasyPermissions.requestPermissions(this, "请允许软件获取位置及读写权限，否则将无法正常运行！", REQUEST_CODE_PERMISSION, permissions);
        }


        //获取系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        positionTextView.setText(simpleDateFormat.format(date));


    }


    //定位用
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        requestLocation();
    }

    //获取定位信息
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void requestLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                    String string = "纬度为：" + df.format(location.getLatitude()) + ",经度为：" + df.format(location.getLongitude());
                    Log.i("logTag", "string" + string);
                    tv_main.setText(string);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        // 注册位置服务，获取系统位置
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //10s变更一次地理位置
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        // 监听NMEA
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (nmeaListener == null) {
                nmeaListener = new OnNmeaMessageListener() {
                    @Override
                    public void onNmeaMessage(String nmea, long l) {
                        receivedNmea(nmea);
                    }
                };
            }
            locationManager.addNmeaListener(nmeaListener);
        }
//         else {
//            if (nmeaListenerOld == null) {
//                nmeaListenerOld = new GpsStatus.NmeaListener() {
//                    @Override
//                    public void onNmeaReceived(long l, String nmea) {
//                        receivedNmea(nmea);
//                    }
//                };
//            }
//            locationManager.addNmeaListener(nmeaListenerOld);
//        }
    }

    private void receivedNmea(String nmea) {
        //tv_main.setText(nmea);

    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "没有获取到权限，软件将退出！", Toast.LENGTH_LONG).show();
        finish();
    }


    /**
     * Android6.0申请权限的回调方法
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //GPS权限
        if (EasyPermissions.hasPermissions(this, permissions)) {
            onPermissionsGranted(REQUEST_CODE_PERMISSION, null);
        } else {
            onPermissionsDenied(requestCode, null);
        }


        //扫描二维码权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "你已经拒绝过一次了！", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }


    }


    //绑定控件
    private void myFindViewByID_single() {
        tv_single = findViewById(R.id.tv_single);
        btn_single_next = findViewById(R.id.btn_single_next);
        rg_single = findViewById(R.id.rg_single);
    }

    private void myFindViewByID_multiselect() {
        tv_multiselect = findViewById(R.id.tv_multiselect);
        btn_multiselect_next = findViewById(R.id.btn_multiselect_next);
        rg_multiselect = findViewById(R.id.rg_multiselect);
    }

    private void myFindViewByID_edit() {
        tv_edit = findViewById(R.id.tv_edit);
        ed_edit = findViewById(R.id.ed_edit);
        btn_edit_next = findViewById(R.id.btn_edit_next);

    }


    //读取json对象中的数据，并将其一一填充
    private void loadJsonData() throws JSONException {
        //获取问题对象，包括类型，题目，选项
        jsonQuestion = jsonQuestions.getJSONObject(currQues);
        //获取问题的类型
        type = jsonQuestion.optString("type");
        System.out.println("type:" + type);
        //获取问题的题目
        question = jsonQuestion.optString("question");
        System.out.println("question:" + question);
    }


    public void myClick(View view) {
        switch (view.getId()) {
            //读取assets文件下的sample.json文件
            case R.id.btn_loadjson:
                try {
                    //用通过扫描二维码读取到的json_QRcode构造jsonObject
                    assert json_QRcode != null;
                    jsonObject = new JSONObject(String.valueOf(new JSONObject(json_QRcode).getJSONObject("survey")));
                    assert jsonObject != null;
                    //获取问题数量，确定要构造的页面总数
                    numTotal = this.jsonObject.optInt("len");
                    System.out.println("length=" + numTotal);
                    //获取所有问题对象
                    jsonQuestions = this.jsonObject.optJSONArray("questions");
                    //获取currQues处的问题
                    loadJsonData();

                    if (type.equals("single")) {
                        showSingleQuestion();
                    } else if (type.equals("multiple")) {
                        showMultiselectQuestion();
                    } else if (type.equals("edittext")) {
                        showEditQuestion();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            //单选
            case R.id.btn_single_next:
                //获取用户勾选选项
                int id = rg_single.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast.makeText(MainActivity.this, "You must select 1 choice first!", Toast.LENGTH_SHORT).show();
                    break;
                }
                //若存在被勾选的选项，则记录该选项的文字信息
                RadioButton radioButton = findViewById(id);
                answers.add("question" + (currQues + 1) + ":" + radioButton.getText().toString());

                currQues++;

                if (currQues == numTotal) {
                    //已经是最后一个问题了，该跳转到finish界面
                    jumpFinish();
                    break;
                } else {
                    //否则，跳转到下一个问题
                    try {
                        loadJsonData();
                        if (type.equals("single")) {
                            showSingleQuestion();
                        } else if (type.equals("multiple")) {
                            showMultiselectQuestion();
                        } else if (type.equals("edittext")) {
                            showEditQuestion();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("survey2", " 单选跳转出错!");
                    }

                }

                break;

            //多选
            case R.id.btn_multiselect_next:
                //获取用户勾选选项
                StringBuffer sb = new StringBuffer();
                for (CheckBox checkbox : checkBoxes) {
                    if (checkbox.isChecked()) {
                        sb.append(checkbox.getText().toString() + "--");
                    }
                }
                if (sb != null && "".equals(sb.toString())) {
                    Toast.makeText(getApplicationContext(), "You should at least select 1 choice!", Toast.LENGTH_LONG).show();
                    break;
                } else {
                    //Toast.makeText(getApplicationContext(), "你选择的是:" + sb.toString(), Toast.LENGTH_LONG).show();
                    answers.add(("question" + (currQues + 1) + ":" + sb.toString()));

                }

                currQues++;
                if (currQues == numTotal) {
                    //已经是最后一个问题了，该跳转到finish界面

                    Toast.makeText(this, "Last question!", Toast.LENGTH_SHORT).show();
                    jumpFinish();
                    break;
                } else {
                    //否则，跳转到下一个问题
                    try {
                        loadJsonData();
                        if (type.equals("single")) {
                            showSingleQuestion();
                        } else if (type.equals("multiple")) {
                            showMultiselectQuestion();
                        } else if (type.equals("edittext")) {
                            showEditQuestion();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("survey2", " 多选跳转出错!");
                    }

                }

                break;
            case R.id.btn_edit_next:
                String answer = ed_edit.getText().toString();
                if (answer != null && !answer.equals("")) {
                    answers.add("question" + (currQues + 1) + ":" + answer);

                    currQues++;

                    if (currQues == numTotal) {
                        //已经是最后一个问题了，该跳转到finish界面
                        Toast.makeText(this, "Last question!", Toast.LENGTH_SHORT).show();
                        jumpFinish();
                        break;
                    } else {
                        //否则，跳转到下一个问题
                        try {
                            loadJsonData();
                            if (type.equals("single")) {
                                showSingleQuestion();
                            } else if (type.equals("multiple")) {
                                showMultiselectQuestion();
                            } else if (type.equals("edittext")) {
                                showEditQuestion();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("survey2", " 编辑跳转出错!");
                        }

                    }

                } else {
                    Toast.makeText(this, "You should write somethins first!", Toast.LENGTH_SHORT).show();
                    break;
                }


                break;


            //扫描二维码
            case R.id.btn_scanQR:
                //创建IntentIntegrator对象
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                //开始扫描
                intentIntegrator.initiateScan();
                break;

            default:
                break;
        }


    }

    private void jumpFinish() {
        System.out.println(answers);


    }


    //重写onActivityResult方法，获取扫描结果

    /**
     * 库中还有8个配置项
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this,"扫描内容:"+ result.getContents(),Toast.LENGTH_LONG).show();
                //从二维码中扫描出Json字符串，并将其填充进json_QRcode
                json_QRcode = result.getContents();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //显示单选页面
    private void showSingleQuestion() throws JSONException {
        setContentView(R.layout.question_single);
        myFindViewByID_single();
        //获取所有选项
        jsonOptions = new JSONArray(String.valueOf(jsonQuestion.get("options")));
        //填充问题
        tv_single.setText((currQues + 1) + "." + question);
        //移除原有的选项
        rg_single.removeAllViews();
        optionsList.clear();
        //获取每个选项的内容，存放进选项列表里

        for (int i = 0; i < jsonOptions.length(); i++) {
            optionsList.add(jsonOptions.getString(i));
            //System.out.println("options:"+optionsList.get(i));
        }


        for (int i = 0; i < optionsList.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rg_single.addView(rb);
            rb.setTextSize(18);
            rb.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            rb.setPadding(50, 10, 50, 10);
            rb.setText(optionsList.get(i));
        }
    }

    //显示多选页面
    private void showMultiselectQuestion() throws JSONException {
        setContentView(R.layout.question_multiselect);
        myFindViewByID_multiselect();
        //移除原有选项
        rg_multiselect.removeAllViewsInLayout();
        optionsList.clear();

        jsonOptions = new JSONArray(String.valueOf(jsonQuestion.get("options")));

        //填充问题
        tv_multiselect.setText((currQues + 1) + "." + question);
        //获取每个选项的内容，存放进选项列表里
        for (int i = 0; i < jsonOptions.length(); i++) {
            optionsList.add(jsonOptions.getString(i));
            System.out.println("options:" + optionsList.get(i));
        }
        //移除checkboxes原有的checkbox
        checkBoxes.clear();
        //填充选项
        for (int i = 0; i < optionsList.size(); i++) {
            CheckBox cb = new CheckBox(this);
            rg_multiselect.addView(cb);
            cb.setText(optionsList.get(i));
            checkBoxes.add(cb);
        }

    }

    //显示编辑页面
    private void showEditQuestion() {
        setContentView(R.layout.question_edit);
        myFindViewByID_edit();
        ed_edit.setText(null);
        //填充问题
        tv_edit.setText((currQues + 1) + "." + question);


    }


}
