package com.example.yiming.opencv_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yiming.opencv_demo.intro1.ChooseBlurType;

public class MainActivity extends AppCompatActivity {
    ListView listVIew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("opencv_java3");
        listVIew = findViewById(R.id.listVIew);
        String[] s = new String[]{"Blur Image"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, s);
        listVIew.setAdapter(arrayAdapter);
        listVIew.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(MainActivity.this, ChooseBlurType.class);
                    break;
                default:
                    intent = new Intent(MainActivity.this, ChooseBlurType.class);
            }
            startActivity(intent);
        });

    }
}

//所谓滑窗就是通过滑动图片，使得图像块一次移动一个像素（从左到右，从上往下）。
//OpenCV 里提供了六种算法：TM_SQDIFF（平方差匹配法）、TM_SQDIFF_NORMED（归一化平方差匹配法）
// 、TM_CCORR（相关匹配法）、TM_CCORR_NORMED（归一化相关匹配法）
// 、TM_CCOEFF（相关系数匹配法）、TM_CCOEFF_NORMED（归一化相关系数匹配法）。
