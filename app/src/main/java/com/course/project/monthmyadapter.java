package com.course.project;

import static com.course.project.R.id.textView13;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class monthmyadapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<monthfoods.food> sample;

    public monthmyadapter(Context context, ArrayList<monthfoods.food> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public monthfoods.food getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.monthfoodlist, null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView foodname = (TextView)view.findViewById(R.id.textView12);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView foodnum = (TextView)view.findViewById(textView13);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView calory = (TextView)view.findViewById(R.id.textView14);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView eattime = (TextView)view.findViewById(R.id.textView15);


        foodname.setText(sample.get(position).getFoodName());
        foodnum.setText(sample.get(position).getFoodNum());
        calory.setText(sample.get(position).getCaloly());
        eattime.setText(sample.get(position).getEattime());
        return view;
    }
}
