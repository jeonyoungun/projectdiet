package com.course.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class monthfoodlist extends AppCompatActivity {
    ArrayList<Foods.Food> foodItemArrayList, filteredList;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int monthcalss = 0;
        Intent intent = getIntent();
        String yearmonth = intent.getStringExtra("음식 먹은 달");
        String[] datearray = yearmonth.split("\\.");
        int year = Integer.parseInt(datearray[0]);
        int month = Integer.parseInt(datearray[1]);
        filteredList=new ArrayList<>();
        foodItemArrayList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthfoodlist);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        ArrayList<Foods.Food> foodsInfo = new ArrayList<>();
        Myadapter myAdapter = new Myadapter(foodsInfo);
        int b = ((globalfood)this.getApplication()).viewnum();
        for(int i=0;i<b;i++) {
            String[] datearray1 = ((globalfood)this.getApplication()).geteattimes(i).split("\\.");
            int year1 = Integer.parseInt(datearray1[0]);
            int month1 = Integer.parseInt(datearray1[1]);
            if(year==year1 & month ==month1) {
                int foodnum = Integer.parseInt(((globalfood) this.getApplication()).getfoodnumses(i));
                foodsInfo.add(new Foods.Food(
                        ((globalfood) this.getApplication()).getarrs(i),
                        ((globalfood) this.getApplication()).getfoodnames(i),
                        foodnum,
                        ((globalfood) this.getApplication()).getcalolys(i),
                        ((globalfood) this.getApplication()).getfoodfeels(i),
                        ((globalfood) this.getApplication()).geteattimes(i),
                        ((globalfood) this.getApplication()).getlatitudes(i),
                        ((globalfood) this.getApplication()).getlongitudes(i)
                ));
                monthcalss+=((globalfood) this.getApplication()).getcalolys(i)*foodnum;
            }
        }
        myRecyclerView.setAdapter(myAdapter);
        TextView totalcals;
        totalcals = findViewById(R.id.monthcal);
        totalcals.setText(String.valueOf(monthcalss));

    }
}