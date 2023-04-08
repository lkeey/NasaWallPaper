package com.example.nasawallpaper;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SiteDataApi extends AsyncTask<Object, Void, Void> {

    private static final String TAG = "Tele Site";
    private static final String API_KEY = "very_secret_key_666";

    @Override
    protected Void doInBackground(Object... obj) {

        try {

            // instead of "Data_Amount" you can use any else database
            URL url = new URL(
                    "https://l14key.pythonanywhere.com/get/Data_Amount/" + API_KEY
            );

            Log.i(TAG, "URL - " + url);

            // connect to site
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.i(TAG, "CONNECTION2 - " + conn.toString());

            conn.setRequestMethod("GET");
            Log.i(TAG, "GET ANSWER");

            // we will get json response
            conn.setRequestProperty("Accept", "application/json");
            Log.i(TAG, "URL-3 -- " + conn.getInputStream());

            // into string
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            Log.i(TAG, "BR2 - " + br);

            String output;

            // read all lines
            while ((output = br.readLine()) != null) {
                Log.i(TAG, "DATA-TELEBOT - " + output);
            }

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
