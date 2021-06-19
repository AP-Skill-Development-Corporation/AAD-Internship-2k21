package com.example.exambledatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exambledatabase.RDB.RTable;
import com.example.exambledatabase.RDB.RViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText roll,name,num;
    RecyclerView rv;
    static RViewModel rViewModel;
    List<RTable> list;
    String l="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = findViewById(R.id.roll);
        name = findViewById(R.id.name);
        num = findViewById(R.id.phone);
        rv = findViewById(R.id.rv);
        rViewModel = new ViewModelProvider(this).get(RViewModel.class);
        rViewModel.readData().observe(this, new Observer<List<RTable>>() {
            @Override
            public void onChanged(List<RTable> rTables) {
                list = rTables;
                MyAdapter adapter = new MyAdapter(MainActivity.this,rTables);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });
    }

    public void save(View view) {
        /*In this Method we implemented Insert operation on RDB*/
        for (int i =0;i<list.size();i++){
            l = list.get(i).getSroll();
        }
        RTable rTable = new RTable();
        String r = roll.getText().toString();
        String n = name.getText().toString();
        String nu = num.getText().toString();
        if (r.isEmpty() | n.isEmpty() | nu.isEmpty()) {
            Toast.makeText(this, "Please fill all the details",
                    Toast.LENGTH_SHORT).show();
        } else if(l.equals(r)) {
            Toast.makeText(this, "Duplicate Data", Toast.LENGTH_SHORT).show();
        }else{
            rTable.setSroll(r);
            rTable.setSname(n);
            rTable.setSnumber(nu);
            rViewModel.insert(rTable);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
    }
}