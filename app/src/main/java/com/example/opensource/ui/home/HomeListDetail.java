package com.example.opensource.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.opensource.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeListDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_detail);

        Intent intent = getIntent();
        String filename = intent.getExtras().getString("filename");
        Log.d("filename", filename);
        File storage = getFilesDir();

        try {
            File f=new File(storage, filename);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView image = (ImageView)findViewById(R.id.imageView4);
            image.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
