package com.example.opensource.ui.measure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MeasureActivity extends AppCompatActivity {


    final static int TAKE_PICTURE = 1;
    private static final String TAG = "measure";
    private MeasurePicture measurePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);


        Button reqButton = (Button) findViewById(R.id.reqbutton);
    }

    static final int PERMISSIONS_REQUEST_CODE = 1000;
    String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private boolean hasPermissions(String[] permissions) {
        int result;

        //스트링 배열에 있는 퍼미션들의 허가 상태 여부 확인
        for (String perms : permissions) {

            result = ContextCompat.checkSelfPermission(this, perms);

            if (result == PackageManager.PERMISSION_DENIED) {
                //허가 안된 퍼미션 발견
                return false;
            }
        }

        //모든 퍼미션이 허가되었음
        return true;
    }

    public void btnClickTaking(View view) {

        if (view.getId() == R.id.take_piture) {
//            Intent intent = new Intent(getApplicationContext(), CameraTakingActivity.class);
//            startActivity(intent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                //퍼미션 상태 확인
                if (!hasPermissions(PERMISSIONS)) {
                    //퍼미션 허가 안되어있다면 사용자에게 요청
                    requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TAKE_PICTURE);
                }
            }
            // captureCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && data.hasExtra("data")) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {

                Log.d(TAG, bitmap.getClass().getName());
                ImageView imageFrame = (ImageView) findViewById(R.id.imageView);
                imageFrame.setImageBitmap(bitmap);
                Intent nextIntent = new Intent(getApplicationContext(), MeasurePicture.class);
                nextIntent.putExtra("image", bitmap);
                nextIntent.putExtra("base64text", BitmapToString(bitmap));
                startActivity(nextIntent);
            } else {
                finish();
            }
        }
    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
}

