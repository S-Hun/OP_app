package com.example.opensource.ui.measure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MeasurePicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_picture);

        final Intent intent = getIntent();
        Bitmap image = (Bitmap) intent.getParcelableExtra("image");

        ImageView imageFrame = (ImageView) findViewById(R.id.reqImage);
        TextView textview = (TextView) findViewById(R.id.base64text);
        imageFrame.setImageBitmap(image);
        textview.setText(intent.getExtras().getString("base64text"));

        final TextView resText = (TextView) findViewById(R.id.restext);
        Button reqButton = (Button) findViewById(R.id.reqbutton);

        reqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json_data = new JSONObject();

                HttpUtil transfer = new HttpUtil();

                try {
                    json_data.put("b64_image", intent.getExtras().getString("base64text"));

                    String json_string = json_data.toString();

                    transfer.execute(json_string);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    class HttpUtil extends AsyncTask<String, Void, Void> {
        String res = "";

        // 요청 전에 하는 작업
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        public Void doInBackground(String... params) {
            try {
                String url = "http://210.124.143.3:8000/";
                URL obj = new URL(url);

                HttpURLConnection conn = (HttpURLConnection) ((new URL(params[0]).openConnection()));

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true); // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
                conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                //Write

                OutputStream os = conn.getOutputStream();

                os.write(params[1].getBytes("UTF-8"));

                os.flush();

                os.close();


                //Read

                BufferedReader br = null;

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                } else {

                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));

                }


                String line;
                StringBuffer response = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                br.close();
                this.res = response.toString();
            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;

        }

        // 응답을 받고 난후의 작업
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
            TextView resText = (TextView) findViewById(R.id.restext);
            resText.setText(res);
        }
    }

}
