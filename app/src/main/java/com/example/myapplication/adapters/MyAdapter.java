package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Employee;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Employee> {

    Context context;


    public MyAdapter(Context context, ArrayList<Employee> employees) {
        super(context, 0, employees);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.row, null);
        Employee currentEmployee =  (Employee) getItem(position);

//                ImageView images = row.findViewById(R.id.image);
//                TextView empid = row.findViewById(R.id.empid);
        TextView name = view.findViewById(R.id.firstname);
        TextView surname = view.findViewById(R.id.lastname);
//                TextView privatenumber = row.findViewById(R.id.privatenumber);
//                TextView positions = row.findViewById(R.id.positions);
//                TextView department = row.findViewById(R.id.department);

//                images.setImageResource(R.drawable.user1);
//                empid.setText(currentEmployee.empID.toString());
        name.setText(currentEmployee.name);
        surname.setText(currentEmployee.surname);
//                privatenumber.setText(currentEmployee.privateNumber);
//                positions.setText(currentEmployee.position);
//                department.setText(currentEmployee.department);


        return view;

    }
}
