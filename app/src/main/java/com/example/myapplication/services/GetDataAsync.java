package com.example.myapplication.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Employee;
import com.example.myapplication.HttpClient;
import com.example.myapplication.PopupActivity;
import com.example.myapplication.adapters.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDataAsync extends AsyncTask<String, String, ArrayList<Employee>> {
    protected boolean loading = false;
    Context context;
    ListView listView;
    private ProgressDialog progressDialog;
    public int pageNum = 1;
    Button next, previous;
    public GetDataAsync(Context context, ListView listView, int pageNum) {
        this.context = context;
        this.listView = listView;
        this.pageNum = pageNum;
    }

    public ArrayList<Employee> setEmployeeData() {
        HttpClient httpClient = new HttpClient();
        String result = httpClient.httpGet("http://45.9.47.42:8735/api/Employees/paging?ItemsPerPage=10&CurrentPage=" + this.pageNum, null);

        ArrayList<Employee> employees = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(result);

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
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("იტვირთება");
        progressDialog.show();
    }

    @Override
    protected ArrayList<Employee> doInBackground(String... strings) {
         return setEmployeeData();
    }

    @Override
    protected void onPostExecute(final ArrayList<Employee> employees) {
        super.onPostExecute(employees);

        final MyAdapter adapter = new MyAdapter(context, employees);
        listView.setAdapter(adapter);

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(MainActivity.this, EmloyeeDetailActivity.class);
//                    String positions = employees.get(position).position;
//                    String department = employees.get(position).department;
//                    String personalNumber = employees.get(position).privateNumber;
//                    String Firstname = employees.get(position).firstName;
//                    String lastname = employees.get(position).lastName;
//                    int empid = employees.get(position).empID;
//
//                    intent.putExtra("empid",  empid);
//                    intent.putExtra("personalNumber", personalNumber);
//                    intent.putExtra("positions", positions);
//                    intent.putExtra("department", department);
//                    intent.putExtra("Firstname", Firstname);
//                    intent.putExtra("lastname", lastname);
////                  Log.i("x", selectedItem);
//                    startActivity(intent);
//
//                }
//            });
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
        loading = false;
        progressDialog.dismiss();

    }
}