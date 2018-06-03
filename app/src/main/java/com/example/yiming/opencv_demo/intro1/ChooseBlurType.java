package com.example.yiming.opencv_demo.intro1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yiming.opencv_demo.MainActivity;
import com.example.yiming.opencv_demo.R;

import static com.example.yiming.opencv_demo.intro1.Const.*;


public class ChooseBlurType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transform);
        Button bMean, bGaussian, bMedian, bSharpen, bDilate, bErode, bThreshold, bAdaptiveThreshold;
        bMean = findViewById(R.id.bMean);
        bMedian = findViewById(R.id.bMedian);
        bGaussian = findViewById(R.id.bGaussian);
        bSharpen = findViewById(R.id.bSharpen);
        bDilate = findViewById(R.id.bDilate);
        bErode = findViewById(R.id.bErode);
        bThreshold = findViewById(R.id.bThreshold);
        bAdaptiveThreshold = findViewById(R.id.bAdaptiveThreshold);

        bMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", MEAN_BLUR);
                startActivity(i);
            }
        });

        bMedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", MEDIAN_BLUR);
                startActivity(i);
            }
        });

        bGaussian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", GAUSSIAN_BLUR);
                startActivity(i);
            }
        });

        bSharpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", SHARPEN);
                startActivity(i);
            }
        });

        bDilate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", DILATE);
                startActivity(i);
            }
        });

        bErode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", ERODE);
                startActivity(i);
            }
        });

        bThreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BlurImageActivity.class);
                i.putExtra("ACTION_MODE", THRESHOLD);
                startActivity(i);
            }
        });

        bAdaptiveThreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("ACTION_MODE", ADAPTIVE_THRESHOLD);
                startActivity(i);
            }
        });
    }
}
