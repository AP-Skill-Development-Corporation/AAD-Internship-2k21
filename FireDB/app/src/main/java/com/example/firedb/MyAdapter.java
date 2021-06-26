package com.example.firedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HoldView> {
    Context ct;
    ArrayList<MyModel> list;
    @Override
    public HoldView onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new HoldView(LayoutInflater.from(ct).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.HoldView holder, int position) {

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
