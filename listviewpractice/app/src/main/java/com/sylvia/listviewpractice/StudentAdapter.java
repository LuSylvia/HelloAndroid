package com.sylvia.listviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private int layoutId;

    public StudentAdapter(Context context, int layoutId, List<Student> students) {
        super(context, layoutId, students);
        this.layoutId = layoutId;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Student student = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView =  view.findViewById(R.id.item_img);
            viewHolder.textView =  view.findViewById(R.id.item_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(student.getImgId());
        viewHolder.textView.setText(student.getId()+student.getChineseName()+student.getEnglishName());

        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }




}
