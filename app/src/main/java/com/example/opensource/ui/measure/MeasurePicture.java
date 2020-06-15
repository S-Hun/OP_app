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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MeasurePicture extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_picture);


        ImageView imageFrame = (ImageView) findViewById(R.id.reqImage);
        TextView textview = (TextView) findViewById(R.id.base64text);

        final Intent intent = getIntent();
        Bitmap image = (Bitmap) intent.getParcelableExtra("image");

        imageFrame.setImageBitmap(image);
        textview.setText(intent.getExtras().getString("base64text"));

        Button reqButton = (Button) findViewById(R.id.reqbutton);

        reqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JSONObject json_data = new JSONObject();
//
//                HttpUtil transfer = new HttpUtil();
//
//                try {
//                    json_data.put("b64_image", intent.getExtras().getString("base64text"));
//
//                    String json_string = json_data.toString();
//
//                    transfer.execute(json_string);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {
                    JSONObject json_data = new JSONObject();
                    json_data.put("b64_image", intent.getExtras().getString("base64text"));

                    String json_string = json_data.toString();


                    new HttpUtil().execute(json_string);

                } catch (Exception e) {
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
                String url = "http://ebb3bc6d4a60.ngrok.io/";
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type","application/json");


                StringBuffer buffer = new StringBuffer();
                buffer.append(params[0]);

                OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                PrintWriter writer = new PrintWriter(outStream);
                writer.write(buffer.toString());
                writer.flush();

//                Toast.makeText(getApplicationContext(), "연결중", Toast.LENGTH_SHORT).show();

                int resCode = conn.getResponseCode();

                InputStreamReader is = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(is);
                String line;
                StringBuffer response = new StringBuffer();
                while((line = br.readLine()) != null) {
                    response.append(line);
                    response.append("");
                }
                br.close();

                res = response.toString();

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
            TextView textview = (TextView)findViewById(R.id.base64text);
            textview.setText(res);
        }
    }

}
