package com.example.graphsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {
    PieChart pc;
    ArrayList<PieEntry> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        pc = findViewById(R.id.pc);
        list = new ArrayList<>();

        list.add(new PieEntry(5,"Alpha"));
        list.add(new PieEntry(10,"Beta"));
        list.add(new PieEntry(15,"CupCake"));
        list.add(new PieEntry(20,"Donut"));
        list.add(new PieEntry(50,"Kitkat"));

        PieDataSet set = new PieDataSet(list,"Android Versions");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setValueTextColor(Color.RED);
        set.setValueTextSize(15);

        PieData data = new PieData(set);
        pc.setData(data);
        pc.setCenterText("Android Versions");
        pc.animate();
    }
}