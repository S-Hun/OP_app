package com.example.opensource.ui.measure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.opensource.MainActivity;
import com.example.opensource.R;
import com.example.opensource.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MeasureResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_result);

        Intent intent = getIntent();

        String res = intent.getExtras().getString("res");
//        Log.d("res",res);

        JSONObject res_json = null;
        Bitmap result_image = null;

        try {
            res_json = new JSONObject(res);
            String temp = (String) res_json.get("result");
            String[] temparr = temp.split("'");
            result_image = (Bitmap) StringToBitmap(temparr[1]);

            // 이미지 파일로 저장하는 부분


            // 내부저장소 경로 받아오기
            File storage = getFilesDir();
            // 생성할 파일이름 받아오기
            String filename = (String) res_json.get("image_name")+".jpg";
            // 빈 파일 생성
            File tempFile = new File(storage, filename);
            try {

                FileOutputStream os = new FileOutputStream(tempFile);
                // compress 메소드로 스트림에 비트맵 저장
                result_image.compress(Bitmap.CompressFormat.JPEG, 100, os);
                // 스트림 종료
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

//



        } catch (JSONException e) {
            e.printStackTrace();
        }

        ImageView resultImageView = (ImageView)findViewById(R.id.imageView3);
        resultImageView.setImageBitmap(result_image);

//        resultImageView.setImageBitmap(StringToBitmap());


    }
    public void goHomeActivity(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
    public void goTakePicture(View view) {

        Intent intent = new Intent(this, MeasureActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
