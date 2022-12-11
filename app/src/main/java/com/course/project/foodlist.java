package com.course.project;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class foodlist extends AppCompatActivity {

    ArrayList<Foods.Food> foodItemArrayList, filteredList;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        InputStream is = getResources().openRawResource(R.raw.fooddb);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        int totalcalss = 0;
        String line;
        try{
            reader.readLine();
            int b = ((globalfood)this.getApplication()).viewnum();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[1].equals(((globalfood)this.getApplication()).getfoodnames(b-1))){
                    ((globalfood)this.getApplication()).setcalolys(parseInt(tokens[3]));
                }
            }

        } catch (IOException e) {
            Log.d("Activity", "Error");
            e.printStackTrace();
        }
        filteredList=new ArrayList<>();
        foodItemArrayList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodlist);

        Button main_btn = (Button) findViewById(R.id.main_btn);
        main_btn.setOnClickListener(view -> {
            Intent intents = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intents);
        });
        Button monthfood = (Button) findViewById(R.id.monthfood);
        monthfood.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), monthfoodlist.class);
            String month;
            month = ((EditText) findViewById(R.id.searchMonth)).getText().toString();
            intent.putExtra("음식 먹은 달",  month);
            startActivity(intent);
        });

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        ArrayList<Foods.Food> foodsInfo = new ArrayList<>();
        Myadapter myAdapter = new Myadapter(foodsInfo);

        int b = ((globalfood)this.getApplication()).viewnum();
        for(int i=0;i<b;i++) {
            int foodnum = Integer.parseInt(((globalfood)this.getApplication()).getfoodnumses(i));
            foodsInfo.add(new Foods.Food(
                    ((globalfood)this.getApplication()).getarrs(i),
                    ((globalfood)this.getApplication()).getfoodnames(i),
                    foodnum,
                    ((globalfood)this.getApplication()).getcalolys(i),
                    ((globalfood)this.getApplication()).getfoodfeels(i),
                    ((globalfood)this.getApplication()).geteattimes(i),
                    ((globalfood)this.getApplication()).getlatitudes(i),
                    ((globalfood)this.getApplication()).getlongitudes(i)
            ));
            totalcalss+=((globalfood)this.getApplication()).getcalolys(i)*foodnum;
        }
        myRecyclerView.setAdapter(myAdapter);
        TextView totalcals;
        totalcals = findViewById(R.id.totalcal);
        totalcals.setText( String.valueOf(totalcalss));

    }
}
