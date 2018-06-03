package com.example.yiming.opencv_demo.intro1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.yiming.opencv_demo.R;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static com.example.yiming.opencv_demo.intro1.Const.*;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class BlurImageActivity extends AppCompatActivity {
    ImageView ivImage;
    ImageView ivImageProcessed;
    Mat src, src_gray;
    static int ACTION_MODE = 0;
    static int REQUEST_READ_EXTERNAL_STORAGE = 0;
    static boolean read_external_storage_granted = false;
    private final int SELECT_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivImage = findViewById(R.id.ivImage);
        ivImageProcessed = findViewById(R.id.ivImageProcessed);
        Intent intent = getIntent();
        if (intent.hasExtra("ACTION_MODE")) {
            ACTION_MODE = intent.getIntExtra("ACTION_MODE", 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("permission", "request READ_EXTERNAL_STORAGE");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            Log.i("permission", "READ_EXTERNAL_STORAGE already granted");
            read_external_storage_granted = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.imagetransform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_load_image && read_external_storage_granted) {
            Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            startActivityForResult(photoPicker, SELECT_PHOTO);
            return true;
        } else if (!read_external_storage_granted) {
            Log.i("permission", "READ_EXTERNAL_STORAGE denied");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECT_PHOTO == resultCode) {
            final Uri imageUri = data.getData();
            try {
                final InputStream inputStream=getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage= BitmapFactory.decodeStream(inputStream);
                src=new Mat(selectedImage.getHeight(),selectedImage.getWidth(), CvType.CV_8UC4);
                Utils.bitmapToMat(selectedImage,src);
                src_gray=new Mat(selectedImage.getHeight(),selectedImage.getWidth(),CvType.CV_8UC1);
                switch (ACTION_MODE){
                    case GAUSSIAN_BLUR:
                        Imgproc.GaussianBlur(src,src,new Size(9,9),0);
                        break;
                }
                Bitmap processedImage=Bitmap.createBitmap(src.cols(),src.rows(),Bitmap.Config.ARGB_8888);
                Log.i("imageType", CvType.typeToString(src.type()) + "");
                Utils.matToBitmap(src, processedImage);
                ivImage.setImageBitmap(selectedImage);
                ivImageProcessed.setImageBitmap(processedImage);
                Log.i("process", "process done");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("permission", "READ_EXTERNAL_STORAGE granted");
                read_external_storage_granted = true;
            } else {
                Log.e("permission", "READ_EXTERNAL_STORAGE refused");
                read_external_storage_granted = false;
            }
        }
    }
}

