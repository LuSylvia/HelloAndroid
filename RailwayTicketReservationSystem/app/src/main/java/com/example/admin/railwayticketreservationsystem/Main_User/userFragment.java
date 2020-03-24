package com.example.admin.railwayticketreservationsystem.Main_User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.railwayticketreservationsystem.R;
import com.example.admin.railwayticketreservationsystem.UpdateActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link userFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link userFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_userinfo;
    private Button btn_chageinfo;
    private Button btn_about;

    private OnFragmentInteractionListener mListener;

    public userFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment userFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userFragment newInstance(String param1) {
        userFragment fragment = new userFragment();
        Bundle args = new Bundle();
        args.putString("args1", param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_user,container,false);
        Bundle bundle=getArguments();
        String args1=bundle.getString("args1");

        btn_userinfo=view.findViewById(R.id.btn_userinfo);
        btn_chageinfo=view.findViewById(R.id.btn_changeUserdata);
        btn_about=view.findViewById(R.id.btn_about);

        //设置点击事件
        btn_userinfo.setOnClickListener(this);
        btn_chageinfo.setOnClickListener(this);
        btn_about.setOnClickListener(this);


        return view;
       // return inflater.inflate(R.layout.fragment_user, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

       switch (v.getId()){
           case R.id.btn_about:

               //Log.e("Laowangtest","this is about test!");
               Intent intent=new Intent(getContext(),AboutActivity.class);
               startActivity(intent);

               break;

           case R.id.btn_changeUserdata:
               Intent intent2=new Intent(getContext(), UpdateActivity.class);
               startActivity(intent2);

//               Toast.makeText(getContext(),"修改密码还在开发中！",Toast.LENGTH_SHORT).show();
               break;

           case R.id.btn_userinfo:
               Intent intent1=new Intent(getContext(),userInfoActivity.class);
               startActivity(intent1);


               //Toast.makeText(getContext(),"个人信息还在开发中！",Toast.LENGTH_SHORT).show();
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
