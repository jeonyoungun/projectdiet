package com.course.project;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import kotlin.text.UStringsKt;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
public class MainActivity extends AppCompatActivity  {
    Button btnCamera;
    ImageView imageView;
    Double latitude;
    Double longitude;
    Bitmap imageBitmap;
    Drawable drawable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button googlemap_btn = findViewById(R.id.googlemap_btn);
        googlemap_btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), googlesmap.class);
            startActivity(intent);
        });
        Button mapread_btn = findViewById(R.id.mapread_btn);
        mapread_btn.setOnClickListener(view -> {
            Intent intent = getIntent();
            latitude = intent.getDoubleExtra("위도",1);
            longitude = intent.getDoubleExtra("경도", 2);
            TextView textView2, textView3;
            textView2 = findViewById(R.id.textView2);
            textView3 = findViewById(R.id.textView3);
            textView2.setText("위도 "+latitude);
            textView3.setText("경도 "+longitude);
        });
        btnCamera = findViewById(R.id.foodpicture_btn);
        imageView = findViewById(R.id.imageView);
        btnCamera.setOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.foodpicture_btn:
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 0);
                    break;
        }});
        Button foodinfo_btn = findViewById(R.id.foodinfo_btn);
        foodinfo_btn.setOnClickListener(view -> {
            String Foodname = ((EditText) findViewById(R.id.editText1)).getText().toString();
            String Foodnum = ((EditText) findViewById(R.id.editText2)).getText().toString();
            String Foodfeel = ((EditText) findViewById(R.id.editText3)).getText().toString();
            String Eattime = ((EditText) findViewById(R.id.editText4)).getText().toString();
            Double latitude1 = latitude; // 위도
            Double longitude1 = longitude; // 경도

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            ((globalfood)this.getApplication()).setfoodnames(Foodname);
            ((globalfood)this.getApplication()).setfoodnumses(Foodnum);
            ((globalfood)this.getApplication()).setfoodfeels(Foodfeel);
            ((globalfood)this.getApplication()).seteattimes(Eattime);
            ((globalfood)this.getApplication()).setlatitudes(latitude1);
            ((globalfood)this.getApplication()).setlongitudes(longitude1);
            ((globalfood)this.getApplication()).setarrs(byteArray);
            ((globalfood)this.getApplication()).viewaddnum();
            Intent intent = new Intent(getApplicationContext(), foodlist.class);

            startActivity(intent);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            drawable = new BitmapDrawable(imageBitmap);
            imageView.setImageBitmap(imageBitmap);
        }
    }

}
