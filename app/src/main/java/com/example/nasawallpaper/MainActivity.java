package com.example.nasawallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "NasaAPI";
    public static final String API_KEY = "C8X3Ne6xlKtjlfcPkk6q3YJvmYtE6lY8NIq4aAhF";
    public static ImageView imageLoaded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, WallPaperService.class);
        startService(intent);

        imageLoaded = findViewById(R.id.loadedImage);

    }


    protected Bitmap getBitmap() {
        Bitmap bitmap;
        try {
//            URL url = new URL(strings[0]);

            //
            String key="AIzaSyDdBPCVzYyCmtFtZSSihqOSUsPZglM5x3E";

            URL url = new URL(
                    "https://www.googleapis.com/customsearch/v1?key="+
                            key+"&cx=42a504d9a5afa4755&q="+
                            "car"+"&alt=json"+"&searchType=image"
            );

            URL url2 = new URL(
                    "https://api.nasa.gov/planetary/apod?api_key="+"C8X3Ne6xlKtjlfcPkk6q3YJvmYtE6lY8NIq4aAhF"
            );

            Log.i(TAG, "URL1 - " + url);
            Log.i(TAG, "URL12 - " + url2);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();

            Log.i(TAG, "CONNECTION - " + conn.toString());
            Log.i(TAG, "CONNECTION2 - " + conn2.toString());

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            Log.i(TAG, "URL-31 -- " + conn.getInputStream());

            conn2.setRequestMethod("GET");
            conn2.setRequestProperty("Accept", "application/json");
            Log.i(TAG, "URL-32 -- " + conn2.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(
                    (conn2.getInputStream())));

            Log.i(TAG, "BR - " + br);
            Log.i(TAG, "BR2 - " + br2);

            String output;
            while ((output = br2.readLine()) != null) {
                Log.i(TAG, "IMAGE21 - " + output);     //Will print the google search links

                if(output.contains("url")) {
                    Log.i(TAG, "FOUND");

                    String link=output.substring(output.indexOf("url\":\"")+("url\":\"").length(), output.indexOf("\",\"media_type\""));

                    Log.i(TAG, "IMAGE222 - " + link);

                    URL urlNASA = new URL(link);

                    try {
                        HttpURLConnection connection = (HttpURLConnection) urlNASA.openConnection();
                        connection.connect();

                        InputStream inputStream = connection.getInputStream();

                        Bitmap bitmapNASA = BitmapFactory.decodeStream(inputStream);

                        Log.i(TAG, "Bitmap NASA - " + bitmapNASA.toString());

                        return bitmapNASA;


                    } catch (Exception e) {
                        continue;
                    }

                }
            }
            conn2.disconnect();
            //
            //            while ((output = br.readLine()) != null) {
            //
            //                if(output.contains("\"link\": \"")){
            //                    String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
            //                    Log.i(TAG, "IMAGE - " + link);     //Will print the google search links
            //
            //                    url = new URL(link);
            //
            //                    try {
            //                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //                        connection.connect();
            //
            //                        InputStream inputStream = connection.getInputStream();
            //
            //                        return BitmapFactory.decodeStream(inputStream);
            //
            //                    } catch (Exception e) {
            //                        continue;
            //                    }
            //                }
            //            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
