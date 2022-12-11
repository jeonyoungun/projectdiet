package com.course.project;

import android.app.Application;

import java.util.ArrayList;

public class globalfood extends Application {
    private ArrayList<String> foodnames = new ArrayList<String>();
    private ArrayList<String> foodnumses = new ArrayList<String>();
    private ArrayList<String> foodfeels = new ArrayList<String>();
    private ArrayList<String> eattimes = new ArrayList<String>();
    private ArrayList<Integer> calolys = new ArrayList<Integer>();
    private ArrayList<Double> latitudes = new ArrayList<Double>();
    private ArrayList<Double> longitudes = new ArrayList<Double>();
    private ArrayList<byte[]> arrs = new ArrayList< byte[]>();
    private int a= 0;

    public int viewnum() {
        return a;
    }
    public void viewaddnum() {
        a++;
    }
    public String getfoodnames(int i) {
        return foodnames.get(i);
    }
    public void setfoodnames( String foodname ) {
        foodnames.add(foodname);
    }

    public String getfoodnumses(int i) {
        return foodnumses.get(i);
    }
    public void setfoodnumses( String foodnum ) {
        foodnumses.add(foodnum);
    }

    public String getfoodfeels(int i) {
        return foodfeels.get(i);
    }
    public void setfoodfeels( String foodfeel ) {
        foodfeels.add(foodfeel);
    }

    public String geteattimes(int i) {
        return eattimes.get(i);
    }
    public void seteattimes( String eattime ) {
        eattimes.add(eattime);
    }

    public int getcalolys(int i) {
        return calolys.get(i);
    }
    public void setcalolys(int caloly ) {
        calolys.add(caloly);
    }

    public Double getlatitudes(int i) {
        return latitudes.get(i);
    }
    public void setlatitudes( Double latitude ) {
        latitudes.add(latitude);
    }

    public Double getlongitudes(int i) {
        return longitudes.get(i);
    }
    public void setlongitudes( Double longitude) {
        longitudes.add(longitude);
    }

    public byte[] getarrs(int i) {
        return arrs.get(i);
    }
    public void setarrs( byte[] arr ) {
        arrs.add(arr);
    }
}

