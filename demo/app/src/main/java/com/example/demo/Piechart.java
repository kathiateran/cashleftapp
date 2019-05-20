package com.example.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Piechart extends AppCompatActivity {

        float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
        String[] xData = {"Mitch", "Jessica", "Moh", "Kelsy", "Robert", "Ashley"};

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_front);

            setupPieChart();
        }

        private void setupPieChart() {
            List<PieEntry> pieEntries = new ArrayList<>();
            for(int i = 0; i < yData.length; i++)
            {
                pieEntries.add(new PieEntry(yData[i],xData[i]));
            }

            PieDataSet dataSet = new PieDataSet(pieEntries,"Categories");
            PieData data =  new PieData(dataSet);

            com.github.mikephil.charting.charts.PieChart chart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.chart1);
            chart.setData(data);
            chart.invalidate();
        }
    }


