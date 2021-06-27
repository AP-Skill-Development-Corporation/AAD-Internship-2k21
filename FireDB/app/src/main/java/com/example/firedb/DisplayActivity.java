package com.example.firedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference reference;
    ArrayList<MyModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("UserData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MyModel myModel = dataSnapshot.getValue(MyModel.class);
                    list.add(myModel);
                }
                MyAdapter adapter = new MyAdapter(DisplayActivity.this,list);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(DisplayActivity.this, ""+error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}