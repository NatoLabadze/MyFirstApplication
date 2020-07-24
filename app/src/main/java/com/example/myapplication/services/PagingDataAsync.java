package com.example.myapplication.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Employee;
import com.example.myapplication.HttpClient;
import com.example.myapplication.PopupActivity;
import com.example.myapplication.adapters.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PagingDataAsync  extends AsyncTask<String, String, ArrayList<Employee>> {
    Context context;
    ListView listView;
    private MyAdapter adapterCallCenter;
    private ProgressDialog progressDialog;


    int ExecutStatus;
    int CurrentPage = 1;
    int _TotalItems = 0, _TotalPages = 0;
    int totalItemCount = 0;

    protected boolean loading = false;


    public PagingDataAsync(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;

        adapterCallCenter = new MyAdapter(context, new ArrayList<Employee>());
        listView.setAdapter(adapterCallCenter);
    }
    public PagingDataAsync(Context context, ListView listView, int CurrentPage, int totalItemCount, MyAdapter adapterCallCenter) {
        this.context = context;
        this.listView = listView;

        this.adapterCallCenter = adapterCallCenter;
        this.CurrentPage = CurrentPage + 1;
        this.totalItemCount = totalItemCount;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("იტვირთება");
        progressDialog.show();
    }


    @Override
    protected ArrayList<Employee> doInBackground(String... strings) {

        HttpClient httpClient = new HttpClient();
        String result = httpClient.httpGet("http://45.9.47.42:8735/api/Employees/paging?ItemsPerPage=20&CurrentPage=" + this.CurrentPage, null);

        ArrayList<Employee> employees = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(result);

            _TotalItems = 1000;
            _TotalPages = 50
            ;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);

                Employee employee = new Employee();
                employee.id = jObject.getInt("id");
                employee.name = jObject.getString("name");
                employee.surname = jObject.getString("surname");
                employee.personal_id = jObject.getString("personal_id");
                employee.position = jObject.getString("position");
                employee.departmentId = jObject.getInt("departmentId");
                employee.department = jObject.getString("department");


                employees.add(employee);
            }
        } catch (JSONException ex) {
            Toast.makeText(context, "წამოღება დასრულდა", Toast.LENGTH_SHORT).show();
        }
        return employees;
    }

    @Override
    protected void onPostExecute(final ArrayList<Employee> employees) {
        super.onPostExecute(employees);

        for (Employee cInfo : employees) {
            adapterCallCenter.add(cInfo);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  startActivity( new Intent( MainActivity.this, PopupActivity.class));
                Intent intent = new Intent(context, PopupActivity.class);
                String positions = employees.get(position).position;
                String department = employees.get(position).department;
                String personalNumber = employees.get(position).personal_id;
                String Firstname = employees.get(position).name;
                String lastname = employees.get(position).surname;
                int empid = employees.get(position).id;

                intent.putExtra("id", empid);
                intent.putExtra("personal_id", personalNumber);
                intent.putExtra("positions", positions);
                intent.putExtra("department", department);
                intent.putExtra("name", Firstname);
                intent.putExtra("surname", lastname);
//              Log.i("x", selectedItem);
                context.startActivity(intent);
            }
        });


        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (load(firstVisibleItem, visibleItemCount, totalItemCount)) {
                    new PagingDataAsync(context, listView, CurrentPage, totalItemCount, adapterCallCenter).execute();
                    Toast.makeText(context, (CurrentPage + 1) + " / " + _TotalPages, Toast.LENGTH_SHORT).show();
                }
            }
        });

        loading = false;
        progressDialog.dismiss();
    }

    protected boolean load(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        boolean lastItem = firstVisibleItem + visibleItemCount == totalItemCount && listView.getChildAt(visibleItemCount - 1) != null && listView.getChildAt(visibleItemCount - 1).getBottom() <= listView.getHeight();
        boolean moreRows = totalItemCount < _TotalItems;
        return moreRows && lastItem && !loading;
    }


}
