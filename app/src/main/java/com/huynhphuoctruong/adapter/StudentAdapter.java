package com.huynhphuoctruong.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huynhphuoctruong.crud_student.MainActivity;
import com.huynhphuoctruong.crud_student.R;
import com.huynhphuoctruong.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Student> students;

    public StudentAdapter(Activity context, int item_layout, List<Student> students) {
        this.context = context;
        this.item_layout = item_layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);

            holder.txtId = view.findViewById(R.id.txtId);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtClass = view.findViewById(R.id.txtClass);

            view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }

        Student s = students.get(i);
        holder.txtId.setText(s.getStudentId());
        holder.txtName.setText(s.getStudentName());
        holder.txtClass.setText(s.getStudentClass());

        return view;
    }

    public static class ViewHolder{
        TextView txtId, txtName, txtClass;
    }
}
