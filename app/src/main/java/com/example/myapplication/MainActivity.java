package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.services.GetDataAsync;
import com.example.myapplication.services.PagingDataAsync;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView2;
    EditText searchView;
    int pageNum = 1;
    ArrayList<Employee> ad;
//  ArrayAdapter <Employee>;
    Button next, previous;
    EditText inputSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView2 = findViewById(R.id.listView);
//      next = findViewById(R.id.buttonnext);
//      previous=findViewById(R.id.buttonprevious);
        inputSearch = findViewById(R.id.searchView);

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pageNum++;
////              getEmployees.pageNum++;
//                GetDataAsync getEmployees = new GetDataAsync(MainActivity.this, listView2, pageNum);
//                getEmployees.execute();
//                Toast.makeText(getApplicationContext(), "Page number" + pageNum, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (pageNum > 1) {
//                    pageNum --;
//                }
//                GetDataAsync getEmployees = new GetDataAsync(MainActivity.this, listView2, pageNum );
//                getEmployees.execute();
//                Toast.makeText(getApplicationContext(),"Page number" + pageNum,Toast.LENGTH_SHORT).show();
//            }
//        });

        //new GetDataAsync(MainActivity.this, listView2, this.pageNum).execute();
        new PagingDataAsync(MainActivity.this, listView2).execute();
    }
}



