package com.example.testemployeemvvm.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployeemvvm.R;
import com.example.testemployeemvvm.adapters.EmployeeAdapter;
import com.example.testemployeemvvm.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity  implements EmployeeListView{
    private RecyclerView recyclerViewEmployee;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployee=findViewById(R.id.recyclerViewEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));
        adapter=new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployee.setAdapter(adapter);
        presenter=new EmployeeListPresenter(this);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    public void loadData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
    }
}