package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter  extends BaseAdapter {


    Context context;
    ArrayList<StudentModel> arrayList;

    public MyAdapter(Context context, ArrayList<StudentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mycustomlistview, null);

            TextView txt_id = view.findViewById(R.id.id_txt);
            TextView txt_name = view.findViewById(R.id.name_txt);
            TextView txt_age = view.findViewById(R.id.age_txt);

            StudentModel studentModel = arrayList.get(i);
            txt_age.setText(String.valueOf(studentModel.getAge()));
            txt_id.setText(String.valueOf(studentModel.getId()));
            txt_name.setText(studentModel.getName());

        }
        return view;
    }


}
