package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(220,220,220)));


        ImageView img = findViewById(R.id.image);
        String personalNumber = getIntent().getStringExtra("personal_id");
        String positions = getIntent().getStringExtra("positions");
        String departments = getIntent().getStringExtra("department");
        String Firstname = getIntent().getStringExtra("name");
        String Lastname = getIntent().getStringExtra("surname");
        Integer Id =getIntent().getIntExtra("id", 0);
//        Log.i("x", personalNumber);
        String url ="https://services.tbsg.ge/_test_timesheet/images/" + personalNumber + ".bmp";
        Picasso.with(this).load(url).into(img);



        TextView personalnumber = (TextView)findViewById(R.id.privatenumber);
        TextView position = (TextView)findViewById(R.id.positions);
        TextView department = (TextView)findViewById(R.id.department);
        TextView name = (TextView)findViewById(R.id.firstname);
        TextView surname = (TextView)findViewById(R.id.lastname);
        TextView id =(TextView) findViewById(R.id.empid);
        personalnumber.setText(personalNumber);
        position.setText(positions);
        department.setText(departments);
        name.setText(Firstname);
        surname.setText(Lastname);
        id.setText(Id.toString());

    }
}