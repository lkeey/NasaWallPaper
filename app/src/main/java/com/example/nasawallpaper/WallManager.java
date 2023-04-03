package com.example.nasawallpaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WallManager extends AsyncTask<Object, Void, Bitmap> {

    private static final String TAG = "WallManager";

    @Override
    protected Bitmap doInBackground(Object... objects) {
        Bitmap bitmap;
        try {
//            URL url = new URL(strings[0]);

            //
            String key="AIzaSyDdBPCVzYyCmtFtZSSihqOSUsPZglM5x3E";

            URL url = new URL(
                    "https://api.nasa.gov/planetary/apod?api_key="+"C8X3Ne6xlKtjlfcPkk6q3YJvmYtE6lY8NIq4aAhF"
            );


            Log.i(TAG, "URL1 - " + url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.i(TAG, "CONNECTION - " + conn.toString());

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            Log.i(TAG, "URL-2 -- " + conn.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            Log.i(TAG, "BR - " + br);

            String output;
            while ((output = br.readLine()) != null) {
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
