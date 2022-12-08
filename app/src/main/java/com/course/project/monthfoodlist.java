package com.course.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class monthfoodlist extends AppCompatActivity {
    ArrayList<monthfoods.food> monthfoodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthfoodlist);
        Intent intent = getIntent();
        String database = intent.getStringExtra("데이터베이스");
        String month = intent.getStringExtra("음식 먹은 달");
        String[] datearray = database.split("\\.");
        String[] montharr = month.split("\\.");
        monthfoodList = new ArrayList<monthfoods.food>();
        int selectyear = Integer.parseInt(montharr[0]);
        int selectmonth = Integer.parseInt(montharr[1]);
        for (int i = 0; i < datearray.length / 6; i++) {
            if (Integer.parseInt(datearray[6 * i + 3]) == selectyear & Integer.parseInt(datearray[6 * i + 4]) == selectmonth) {
                monthfoodList.add(new monthfoods.food(datearray[6 * i], Integer.parseInt(datearray[6 * i + 1])
                        , Integer.parseInt(datearray[6 * i + 2]), Integer.parseInt(datearray[6 * i + 3]),
                        Integer.parseInt(datearray[6 * i + 4]), Integer.parseInt(datearray[6 * i + 5])));
            }
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        final monthmyadapter myAdapter = new monthmyadapter(this, monthfoodList);

        listView.setAdapter(myAdapter);
    }
}