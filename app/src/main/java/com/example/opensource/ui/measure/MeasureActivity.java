package com.example.opensource.ui.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.R;

import java.net.HttpURLConnection;


public class MeasureActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

        TextView resText = (TextView)findViewById(R.id.restext);
        Button reqButton = (Button)findViewById(R.id.reqbutton);

        reqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"이것은 Toast 메시지입니다.", Toast.LENGTH_SHORT).show();;

                HttpURLConnection conn = null;
            }
        });


    }
}