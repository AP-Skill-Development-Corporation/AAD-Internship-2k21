package com.example.firedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HoldView> {
    Context ct;
    ArrayList<MyModel> list;

    public MyAdapter(Context ct, ArrayList<MyModel> list) {
        this.ct = ct;
        this.list = list;
    }

    @Override
    public HoldView onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new HoldView(LayoutInflater.from(ct).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.HoldView holder, int position) {
        Glide.with(ct).load(list.get(position).getImg())
                .placeholder(R.drawable.ic_launcher_background).into(holder.iv);
        holder.name.setText(list.get(position).getName());
        holder.mail.setText(list.get(position).getMail());
        holder.number.setText(list.get(position).getNumber());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UserData");
                reference.child(list.get(position).getNumber()).removeValue();
                Toast.makeText(ct, "Data Deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoldView extends RecyclerView.ViewHolder {
        ImageButton edit,del;
        TextView name,mail,number;
        ImageView iv;
        public HoldView(@NonNull View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.edit);
            del = itemView.findViewById(R.id.del);
            name = itemView.findViewById(R.id.name);
            mail = itemView.findViewById(R.id.mail);
            number = itemView.findViewById(R.id.number);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
