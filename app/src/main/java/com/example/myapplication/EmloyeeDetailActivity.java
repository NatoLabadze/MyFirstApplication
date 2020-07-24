package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EmloyeeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emloyee_detail);

        ImageView img = findViewById(R.id.image);
        String personalNumber = getIntent().getStringExtra("personalNumber");
        String positions = getIntent().getStringExtra("positions");
        String departments = getIntent().getStringExtra("department");
        String Firstname = getIntent().getStringExtra("Firstname");
        String Lastname = getIntent().getStringExtra("lastname");
        Integer Empid =getIntent().getIntExtra("empid", 0);
//        Log.i("x", personalNumber);
        String url ="https://services.tbsg.ge/_test_timesheet/images/" + personalNumber + ".bmp";
        Picasso.with(this).load(url).into(img);



        TextView personalnumber = (TextView)findViewById(R.id.privatenumber);
        TextView position = (TextView)findViewById(R.id.positions);
        TextView department = (TextView)findViewById(R.id.department);
        TextView fisrtname = (TextView)findViewById(R.id.firstname);
        TextView lastname = (TextView)findViewById(R.id.lastname);
        TextView empid =(TextView) findViewById(R.id.empid);
        personalnumber.setText(personalNumber);
        position.setText(positions);
        department.setText(departments);
        fisrtname.setText(Firstname);
        lastname.setText(Lastname);
        empid.setText(Empid.toString());




    }
}