package com.course.project;

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

import java.io.ByteArrayOutputStream;

import kotlin.text.UStringsKt;

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

        Button foodlist_btn = findViewById(R.id.foodlist_btn);
        foodlist_btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), foodlist.class);
            startActivity(intent);
        });
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
        foodinfo_btn.setOnClickListener(this::onClick);

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

    private void onClick(View view) {
        String Foodname = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String Foodnum = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String Foodfeel = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String Eattime = ((EditText) findViewById(R.id.editText4)).getText().toString();
        Double latitude1 = latitude; // 위도
        Double longitude1 = longitude; // 경도
        Intent intent = new Intent(MainActivity.this, foodlist.class);
        intent.putExtra("위도1", latitude1);
        intent.putExtra("경도1", longitude1);
        intent.putExtra("음식이름", Foodname);
        intent.putExtra("음식개수", Foodnum);
        intent.putExtra("음식평", Foodfeel);
        intent.putExtra("먹었었던 시간", Eattime);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra("음식사진",byteArray);

        startActivity(intent);

    }
}