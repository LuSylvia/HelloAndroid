package com.example.admin.railwayticketreservationsystem.Main_User;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelStopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelStopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//用于搜索车站站点
public class SelStopFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Button btn_chengdu;
    private Button btn_chongqing;
    private Button btn_guiyang;
    private Button btn_guilin;
    private Button btn_zhaoqing;
    private Button btn_guangzhou;
    private Button btn_zhuhai;
    private Button btn_hangzhou;
    private Button btn_tianmen;
    private Button btn_jingzhou;
    private Button btn_nanjing;
    private Button btn_changsha;
    private Button btn_jinhua;
    private Button btn_nanchang;
    private Button btn_shanghai;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    static Bundle bundle;
    CallBackValues_2 callBackValues;

    public SelStopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelStopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelStopFragment newInstance(String param1, String param2) {
        SelStopFragment fragment = new SelStopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public interface  CallBackValues_2{
        public void sendMessages2(String location,String stop,String anotherStop,boolean home_to_SelStop);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);

            //得到activity传过来的bundle对象
             bundle = getArguments();


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sel_stop,container,false);
        //绑定控件
        findViewbyid(view);
        //设置点击事件
        btn_chengdu.setOnClickListener(this);
        btn_chongqing.setOnClickListener(this);
        btn_guiyang.setOnClickListener(this);
        btn_guilin.setOnClickListener(this);
        btn_zhaoqing.setOnClickListener(this);
        btn_guangzhou.setOnClickListener(this);
        btn_zhuhai.setOnClickListener(this);
        btn_hangzhou.setOnClickListener(this);
        btn_tianmen.setOnClickListener(this);
        btn_jingzhou.setOnClickListener(this);
        btn_nanjing.setOnClickListener(this);
        btn_changsha.setOnClickListener(this);
        btn_jinhua.setOnClickListener(this);
        btn_nanchang.setOnClickListener(this);
        btn_shanghai.setOnClickListener(this);


        return view;
    }
    //绑定控件
    void findViewbyid(View view){
        btn_chengdu=view.findViewById(R.id.btn_成都);
        btn_chongqing=view.findViewById(R.id.btn_重庆);
        btn_guiyang=view.findViewById(R.id.btn_贵阳);
        btn_guilin=view.findViewById(R.id.btn_桂林);
        btn_zhaoqing=view.findViewById(R.id.btn_肇庆);
        btn_guangzhou=view.findViewById(R.id.btn_广州);
        btn_zhuhai=view.findViewById(R.id.btn_珠海);
        btn_hangzhou=view.findViewById(R.id.btn_杭州);
        btn_tianmen=view.findViewById(R.id.btn_天门);
        btn_jingzhou=view.findViewById(R.id.btn_荆州);
        btn_nanjing=view.findViewById(R.id.btn_南京);
        btn_changsha=view.findViewById(R.id.btn_长沙);
        btn_jinhua=view.findViewById(R.id.btn_金华);
        btn_nanchang=view.findViewById(R.id.btn_南昌);
        btn_shanghai=view.findViewById(R.id.btn_上海);

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValues= (CallBackValues_2) getActivity();
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //点击事件，用于将站点名字正确传送给homeFragment中触发事件的Button
    @Override
    public void onClick(View v) {
        String location,anotherStop,stop;
        location=bundle.getString("location");
        anotherStop=bundle.getString("anotherstop");
        switch (v.getId()){
            case R.id.btn_重庆:
                stop="重庆";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                //Log.e("Laowangtest","Sel   location is "+location+",stop is "+stop+",another is "+anotherStop+"home to sel is ");
                break;

            case R.id.btn_广州:
                 stop="广州";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;

            case R.id.btn_成都:
                stop="成都";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;

            case R.id.btn_贵阳:
                stop="贵阳";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_桂林:

                stop="桂林";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;

            case R.id.btn_肇庆:
                stop="肇庆";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_珠海:
                stop="珠海";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_杭州:
                stop="杭州";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_天门:
                stop="天门";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_荆州:
                stop="荆州";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;

            case R.id.btn_南京:
                stop="南京";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_长沙:
                stop="长沙";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_金华:
                stop="金华";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_南昌:
                stop="南昌";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;
            case R.id.btn_上海:
                stop="上海";
                callBackValues.sendMessages2(location,stop,anotherStop,false);
                break;

                default:
                    break;

        }



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
